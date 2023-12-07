package com.io.polygon.stock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum MarketDataChannels {

    AGGREGATES_PER_MINUTE("AM"),
    AGGREGATES_PER_SECOND("A"),
    TRADES("T"),
    QUOTES("Q"),
    FAIR_MARKET_VALUE("FMV"),
    OTHER("OTHER");

    private String value;

    public static MarketDataChannels getByValue(String value) {
        for (MarketDataChannels channel : values()) {
            if (channel.getValue().equalsIgnoreCase(value)) {
                return channel;
            }
        }
        return OTHER;
    }

}
