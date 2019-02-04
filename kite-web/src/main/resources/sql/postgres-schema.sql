DROP TABLE KITE_TICK;

CREATE TABLE KITE_TICK (
TICK_ID NUMERIC NOT NULL PRIMARY KEY,
mode varchar(15),
tradable boolean NOT NULL,
instrumentToken NUMERIC,
lastTradedPrice NUMERIC,
highPrice NUMERIC,
lowPrice NUMERIC,
openPrice NUMERIC,
closePrice NUMERIC,
change NUMERIC,
lastTradeQuantity NUMERIC,
averageTradePrice NUMERIC,
volumeTradedToday NUMERIC,
totalBuyQuantity NUMERIC,
totalSellQuantity NUMERIC,
oi NUMERIC,
openInterestDayHigh NUMERIC,
openInterestDayLow NUMERIC,
tickTimestamp TIMESTAMP,
lastTradedTime TIMESTAMP,
CREATED_TIME TIMESTAMP
);

CREATE TABLE OHLC (
ohcl_id NUMERIC,
token_Id NUMERIC,
open NUMERIC,
high NUMERIC,
close NUMERIC,
low NUMERIC,
UW NUMERIC,
LW NUMERIC,
body NUMERIC,
profit NUMERIC,
timeframe NUMERIC,
chartTime varchar(5000),
time TIMESTAMP,
candleType VARCHAR(150),
pattern VARCHAR(50000)
);

CREATE TABLE KITE_INSTRUMENT_TOKEN (
TOKEN_ID NUMERIC  NOT NULL PRIMARY KEY
)

INSERT INTO KITE_TICK_0 SELECT * FROM KITE_TICK;

insert into kite_tick_0(tick_id, tradable) values (1,'true')

CREATE OR REPLACE FUNCTION trigger_kite_tick() RETURNS TRIGGER AS $kite_tick_audit$
    BEGIN
       IF (TG_OP = 'INSERT') THEN
            INSERT INTO %s  SELECT * FROM %s k WHERE k.tick_id=NEW.tick_id;
            RETURN NEW;
        END IF;
        RETURN NULL; -- result is ignored since this is an AFTER trigger
    END;
$kite_tick_audit$ LANGUAGE plpgsql;

CREATE TRIGGER kite_tick_audit
AFTER INSERT OR UPDATE OR DELETE ON kite_tick_0
    FOR EACH ROW EXECUTE PROCEDURE trigger_kite_tick();

SELECT EXISTS 
(
	SELECT 1
	FROM information_schema.tables 
	WHERE table_schema = 'public'
	AND table_name = ?
);

CREATE TABLE KITE_DEPTH (
DEPTH_ID NUMERIC NOT NULL PRIMARY KEY,
TICK_ID NUMERIC,
quantity NUMERIC,
price NUMERIC,
orders NUMERIC,
type varchar(15),
CREATED_TIME TIMESTAMP
);



CREATE SEQUENCE KITE_TICK_SEQ START 1;
CREATE SEQUENCE KITE_DEPTH_SEQ START 1;
CREATE SEQUENCE KITE_OHLC_SEQ START 1;
CREATE SEQUENCE KITE_ALGO_SEQ START 1;

INSERT INTO OHCL (
ohcl_id ,
token_Id,
open ,
high ,
close,
low ,
UW ,
LW ,
body ,
profit,
timeframe ,
time ,
candleType
) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)
