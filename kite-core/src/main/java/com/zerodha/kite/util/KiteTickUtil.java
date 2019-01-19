package com.zerodha.kite.util;

import static com.zerodha.kite.contant.KiteConstant.MODE_FULL;
import static com.zerodha.kite.contant.KiteConstant.MODE_LTP;
import static com.zerodha.kite.contant.KiteConstant.MODE_QUOTE;
import static com.zerodha.kite.contant.KiteConstant.MODE;
import static com.zerodha.kite.contant.KiteConstant.SUBSCRIBE;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerodha.kite.domain.Depth;
import com.zerodha.kite.domain.Tick;

public class KiteTickUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(KiteTickUtil.class);
	
	public static String subscribe(List<Long> tokens) {
		Map<String, Object> subscribe = new HashMap<String, Object>();
		subscribe.put("a", SUBSCRIBE);
		subscribe.put("v", tokens);

		ObjectMapper mapper = new ObjectMapper();
		String subscribeValue = null;
		try {
			subscribeValue = mapper.writeValueAsString(subscribe);
		} catch (JsonProcessingException e) {
			LOGGER.error("Unable to parse object to json for subscribe");
		}
		return subscribeValue;
	}
	
	public static String mode(List<Long> tokens, String mode) {
		Map<String, Object> modeData = new HashMap<String, Object>();
		List<Object> tokenData = new ArrayList<Object>();
		tokenData.add(mode);
		tokenData.add(tokens);
		modeData.put("a", MODE);
		modeData.put("v", tokenData);

		ObjectMapper mapper = new ObjectMapper();
		String modeValue = null;
		try {
			modeValue = mapper.writeValueAsString(modeData);
		} catch (JsonProcessingException e) {
			LOGGER.error("Unable to parse object to json for subscribe");
		}
		return modeValue;
	}

	public static List<Tick> parseBinary(byte[] binaryPackets) {
		List<Tick> ticks = new ArrayList<Tick>();
		List<byte[]> packets = splitPackets(binaryPackets);
		for (int i = 0; i < packets.size(); i++) {
			byte[] bin = (byte[]) packets.get(i);
			byte[] t = Arrays.copyOfRange(bin, 0, 4);
			int x = ByteBuffer.wrap(t).getInt();

			int segment = x & 0xFF;

			int dec1 = segment == 3 ? 10000000 : 100;
			if (bin.length == 8) {
				Tick tick = getLtpQuote(bin, x, dec1);
				ticks.add(tick);
			} else if ((bin.length == 28) || (bin.length == 32)) {
				Tick tick = getIndeciesData(bin, x);
				ticks.add(tick);
			} else if (bin.length == 44) {
				Tick tick = getQuoteData(bin, x, dec1);
				ticks.add(tick);
			} else if (bin.length == 184) {
				Tick tick = getQuoteData(bin, x, dec1);
				tick.setMode(MODE_FULL);
				ticks.add(getFullData(bin, dec1, tick));
			}
		}
		return ticks;
	}

	public static Tick getFullData(byte[] bin, int dec, Tick tick) {
		long lastTradedtime = convertToLong(getBytes(bin, 44, 48)) * 1000L;
		if (isValidDate(lastTradedtime)) {
			tick.setLastTradedTime(new Date(lastTradedtime));
		} else {
			tick.setLastTradedTime(null);
		}
		tick.setOi(convertToDouble(getBytes(bin, 48, 52)));
		tick.setOpenInterestDayHigh(convertToDouble(getBytes(bin, 52, 56)));
		tick.setOpenInterestDayLow(convertToDouble(getBytes(bin, 56, 60)));
		long tickTimeStamp = convertToLong(getBytes(bin, 60, 64)) * 1000L;
		if (isValidDate(tickTimeStamp)) {
			tick.setTickTimestamp(new Date(tickTimeStamp));
		} else {
			tick.setTickTimestamp(null);
		}
		tick.setMarketDepth(getDepthData(bin, dec, 64, 184));
		return tick;
	}

	public static Map<String, List<Depth>> getDepthData(byte[] bin, int dec, int start, int end) {
		byte[] depthBytes = getBytes(bin, start, end);
		int s = 0;
		List<Depth> buy = new ArrayList<Depth>();
		List<Depth> sell = new ArrayList<Depth>();
		for (int k = 0; k < 10; k++) {
			s = k * 12;
			Depth depth = new Depth();
			depth.setQuantity((int) convertToDouble(getBytes(depthBytes, s, s + 4)));
			depth.setPrice(convertToDouble(getBytes(depthBytes, s + 4, s + 8)) / dec);
			depth.setOrders((int) convertToDouble(getBytes(depthBytes, s + 8, s + 10)));
			if (k < 5) {
				buy.add(depth);
			} else {
				sell.add(depth);
			}
		}
		Map<String, List<Depth>> depthMap = new HashMap<String, List<Depth>>();
		depthMap.put("buy", buy);
		depthMap.put("sell", sell);
		return depthMap;
	}

	public static Tick getQuoteData(byte[] bin, int x, int dec1) {
		Tick tick2 = new Tick();
		tick2.setMode(MODE_QUOTE);
		tick2.setInstrumentToken(x);
		double lastTradedPrice = convertToDouble(getBytes(bin, 4, 8)) / dec1;
		tick2.setLastTradedPrice(lastTradedPrice);
		tick2.setLastTradedQuantity(convertToDouble(getBytes(bin, 8, 12)));
		tick2.setAverageTradePrice(convertToDouble(getBytes(bin, 12, 16)) / dec1);
		tick2.setVolumeTradedToday(convertToDouble(getBytes(bin, 16, 20)));
		tick2.setTotalBuyQuantity(convertToDouble(getBytes(bin, 20, 24)));
		tick2.setTotalSellQuantity(convertToDouble(getBytes(bin, 24, 28)));
		tick2.setOpenPrice(convertToDouble(getBytes(bin, 28, 32)) / dec1);
		tick2.setHighPrice(convertToDouble(getBytes(bin, 32, 36)) / dec1);
		tick2.setLowPrice(convertToDouble(getBytes(bin, 36, 40)) / dec1);
		double closePrice = convertToDouble(getBytes(bin, 40, 44)) / dec1;
		tick2.setClosePrice(closePrice);
		setChangeForTick(tick2, lastTradedPrice, closePrice);
		return tick2;
	}

	public static void setChangeForTick(Tick tick, double lastTradedPrice, double closePrice) {
		if (closePrice != 0.0D) {
			tick.setNetPriceChangeFromClosingPrice((lastTradedPrice - closePrice) * 100.0D / closePrice);
		} else {
			tick.setNetPriceChangeFromClosingPrice(0.0D);
		}
	}

	public static Tick getIndeciesData(byte[] bin, int x) {
		int dec = 100;
		Tick tick = new Tick();
		tick.setMode(MODE_FULL);
		tick.setTradable(false);
		tick.setInstrumentToken(x);
		tick.setLastTradedPrice(convertToDouble(getBytes(bin, 4, 8)) / dec);
		tick.setHighPrice(convertToDouble(getBytes(bin, 8, 12)) / dec);
		tick.setLowPrice(convertToDouble(getBytes(bin, 12, 16)) / dec);
		tick.setOpenPrice(convertToDouble(getBytes(bin, 16, 20)) / dec);
		tick.setClosePrice(convertToDouble(getBytes(bin, 20, 24)) / dec);
		tick.setNetPriceChangeFromClosingPrice(convertToDouble(getBytes(bin, 24, 28)) / dec);
		if (bin.length > 28) {
			long tickTimeStamp = convertToLong(getBytes(bin, 28, 32)) * 1000L;
			if (isValidDate(tickTimeStamp)) {
				tick.setTickTimestamp(new Date(tickTimeStamp));
			} else {
				tick.setTickTimestamp(null);
			}
		}
		return tick;
	}

	public static boolean isValidDate(long date) {
		if (date <= 0L) {
			return false;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);
		calendar.setTimeInMillis(date);
		try {
			calendar.getTime();
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static Tick getLtpQuote(byte[] bin, int x, int dec1) {
		Tick tick1 = new Tick();
		tick1.setMode(MODE_LTP);
		tick1.setTradable(true);
		tick1.setInstrumentToken(x);
		tick1.setLastTradedPrice(convertToDouble(getBytes(bin, 4, 8)) / dec1);
		return tick1;
	}

	public static List<byte[]> splitPackets(byte[] bin) {
		List<byte[]> packets = new ArrayList<byte[]>();
		int noOfPackets = getLengthFromByteArray(getBytes(bin, 0, 2));
		int j = 2;
		for (int i = 0; i < noOfPackets; i++) {
			int sizeOfPacket = getLengthFromByteArray(getBytes(bin, j, j + 2));
			byte[] packet = Arrays.copyOfRange(bin, j + 2, j + 2 + sizeOfPacket);
			packets.add(packet);
			j = j + 2 + sizeOfPacket;
		}
		return packets;
	}

	public static byte[] getBytes(byte[] bin, int start, int end) {
		return Arrays.copyOfRange(bin, start, end);
	}

	public static double convertToDouble(byte[] bin) {
		ByteBuffer bb = ByteBuffer.wrap(bin);
		bb.order(ByteOrder.BIG_ENDIAN);
		if (bin.length < 4) {
			return bb.getShort();
		}
		if (bin.length < 8) {
			return bb.getInt();
		}
		return bb.getDouble();
	}

	public static long convertToLong(byte[] bin) {
		ByteBuffer bb = ByteBuffer.wrap(bin);
		bb.order(ByteOrder.BIG_ENDIAN);
		return bb.getInt();
	}

	public static int getLengthFromByteArray(byte[] bin) {
		ByteBuffer bb = ByteBuffer.wrap(bin);
		bb.order(ByteOrder.BIG_ENDIAN);
		return bb.getShort();
	}
}
