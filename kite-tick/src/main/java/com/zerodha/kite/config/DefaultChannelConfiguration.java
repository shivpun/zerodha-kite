package com.zerodha.kite.config;

import static com.zerodha.kite.contant.KiteConstant.MODE_FULL;
import static com.zerodha.kite.contant.KiteDateFormat.DATE_MM_FORMAT;
import static com.zerodha.kite.contant.KiteDateFormat.DATE_TIMESTAMP_FORMAT;
import static com.zerodha.kite.contant.KiteTickQuery.INSERT_KITE_DEPTH;
import static com.zerodha.kite.contant.KiteTickQuery.INSERT_KITE_TICK;
import static com.zerodha.kite.contant.KiteTickQuery.SELECT_TOKEN_FROM_INSTRUMENT;
import static com.zerodha.kite.contant.KiteTickQuery.SEQ_KITE_DEPTH;
import static com.zerodha.kite.contant.KiteTickQuery.SEQ_KITE_TICK;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerodha.kite.contant.KiteHeader;
import com.zerodha.kite.domain.Depth;
import com.zerodha.kite.domain.KiteMessage;
import com.zerodha.kite.domain.KiteUser;
import com.zerodha.kite.domain.Tick;
import com.zerodha.kite.util.DateUtil;
import com.zerodha.kite.util.KiteTickUtil;

@Configuration
public class DefaultChannelConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultChannelConfiguration.class);

	@Value(value = "${kite.location.json}")
	private String resource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Bean(name = "websocket.tick")
	public MessageChannel websocketTicksChannel() {
		return new DirectChannel();
	}

	@Bean(name = "websocket.tick.jdbc")
	public MessageChannel jdbcInsertTicksChannel() {
		return new DirectChannel();
	}

	@Bean(name = "chart")
	public MessageChannel chartChannel() {
		return new DirectChannel();
	}

	@Bean(name = "kiteUser")
	public KiteUser kiteUser(@Value(value = "${kite.websocket.userId}") String userId,
			@Value(value = "${kite.websocket.publicToken}") String publicToken) {
		KiteUser kiteUser = new KiteUser();
		kiteUser.setPublicToken(publicToken);
		kiteUser.setUserId(userId);
		return kiteUser;
	}

	@Bean(name = "kiteMessage")
	public KiteMessage kiteMessage(KiteUser kiteUser, @Value(value = "${kite.websocket.cookie}") String cookie) {
		List<Long> tokens = jdbcTemplate.queryForList(SELECT_TOKEN_FROM_INSTRUMENT, Long.class);
		KiteMessage kiteMessage = new KiteMessage();
		kiteMessage.setKiteUser(kiteUser);
		kiteMessage.addMessage(KiteTickUtil.subscribe(tokens));
		kiteMessage.addMessage(KiteTickUtil.mode(tokens, MODE_FULL));
		kiteMessage.addHeader("Cookie", cookie);
		KiteHeader.defaultWebSocket(kiteMessage);
		return kiteMessage;
	}

	@ServiceActivator(inputChannel = "websocket.tick.jdbc", outputChannel = "jsonTicksChannel")
	public Message<List<Tick>> kiteTickJdbc(Message<List<Tick>> tickMessages) {
		List<Tick> ticks = tickMessages.getPayload();
		for (Tick tick : ticks) {
			BigDecimal tickId = fetchTickSequence(SEQ_KITE_TICK);
			insertToTickDB(tick, tickId);
			insertToDepth(tick.getMarketDepth(), tickId);
		}
		return tickMessages;
	}

	@ServiceActivator(inputChannel = "jsonTicksChannel")
	public void kiteTickToJson(Message<List<Tick>> tickMessages) throws JsonGenerationException, JsonMappingException, IOException {
		String dirUrl = resource + File.separator + DateUtil.getDate(new Date(), DATE_MM_FORMAT);
		LOGGER.info(String.format("[%s] Tick is been written in json file at [%s]", "kiteTickToJson", dirUrl));
		File dataDir = new File(dirUrl);
		if (!dataDir.exists()) {
			dataDir.mkdir();
		}
		File file = new File(dirUrl + File.separator + DateUtil.getDate(new Date(), DATE_TIMESTAMP_FORMAT) + ".json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(file, tickMessages.getPayload());
	}

	public BigDecimal fetchTickSequence(String sequenceQuery) {
		return jdbcTemplate.queryForObject(sequenceQuery, BigDecimal.class);
	}

	public void insertToDepth(Map<String, List<Depth>> depths, BigDecimal tickId) {
		for (Map.Entry<String, List<Depth>> depth : depths.entrySet()) {
			for (Depth d : depth.getValue()) {
				final BigDecimal depthId = fetchTickSequence(SEQ_KITE_DEPTH);
				jdbcTemplate.update(INSERT_KITE_DEPTH, new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setBigDecimal(1, depthId);
						ps.setBigDecimal(2, tickId);
						ps.setInt(3, d.getQuantity());
						ps.setDouble(4, d.getPrice());
						ps.setInt(5, d.getOrders());
						ps.setString(6, depth.getKey());
					}

				});
			}
		}
	}

	public void insertToTickDB(Tick tick, final BigDecimal tickId) {
		jdbcTemplate.update(INSERT_KITE_TICK, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setBigDecimal(1, tickId);
				ps.setString(2, tick.getMode());
				ps.setBoolean(3, tick.isTradable());
				ps.setDouble(4, tick.getInstrumentToken());
				ps.setDouble(5, tick.getLastTradedPrice());
				ps.setDouble(6, tick.getHighPrice());
				ps.setDouble(7, tick.getLowPrice());
				ps.setDouble(8, tick.getOpenPrice());
				ps.setDouble(9, tick.getClosePrice());
				ps.setDouble(10, tick.getChange());
				ps.setDouble(11, tick.getLastTradedQuantity());
				ps.setDouble(12, tick.getAverageTradePrice());
				ps.setDouble(13, tick.getVolumeTradedToday());
				ps.setDouble(14, tick.getTotalBuyQuantity());
				ps.setDouble(15, tick.getTotalSellQuantity());
				ps.setDouble(16, tick.getOi());
				ps.setDouble(17, tick.getOpenInterestDayHigh());
				ps.setDouble(18, tick.getOpenInterestDayLow());
				ps.setTimestamp(19, DateUtil.getTimeStamp(tick.getTickTimestamp()));
				ps.setTimestamp(20, DateUtil.getTimeStamp(tick.getLastTradedTime()));
			}
		});
	}
}
