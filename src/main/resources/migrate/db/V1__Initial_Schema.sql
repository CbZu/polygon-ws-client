CREATE TABLE `stock` (
  `id` bigint NOT NULL,
  `datetime` varchar(255) DEFAULT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  `open` decimal(38,2) DEFAULT NULL,
  `close` decimal(38,2) DEFAULT NULL,
  `high` decimal(38,2) DEFAULT NULL,
  `low` decimal(38,2) DEFAULT NULL,
  `volumeweightedaverageprice` decimal(38,2) DEFAULT NULL,
  `todayopen` decimal(38,2) DEFAULT NULL,
  `todayvolumeweightedaverageprice` decimal(38,2) DEFAULT NULL,
  `volume` decimal(38,2) DEFAULT NULL,
  `averagetradesize` decimal(38,2) DEFAULT NULL,
  `aggregate_volume` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table stock_seq (next_val bigint) engine=InnoDB

insert into stock_seq values ( 1 )