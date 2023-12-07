package com.io.polygon.stock.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Stock")
@Getter
@Setter
public class StockEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String datetime;

    @Column
    private String symbol;

    @Column
    private BigDecimal open;

    @Column
    private BigDecimal close;

    @Column
    private BigDecimal high;

    @Column
    private BigDecimal low;

    @Column
    private BigDecimal volumeweightedaverageprice;

    @Column
    private BigDecimal todayopen;

    @Column
    private BigDecimal todayvolumeweightedaverageprice;

    @Column
    private BigDecimal volume;

    @Column
    private BigDecimal averagetradesize;

    @Column
    private BigDecimal aggregateVolume;

}
