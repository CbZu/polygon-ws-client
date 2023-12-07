package com.io.polygon.stock.mapper;

import com.io.polygon.stock.dto.PolygonStockWebsocketAggregatesDto;
import com.io.polygon.stock.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    List<StockEntity> polygonStockWebsocketDtosToStockEntities(List<PolygonStockWebsocketAggregatesDto> polygonStockWebsocketAggregatesDto);

    @Mappings({
            @Mapping(target = "datetime", source = "s", qualifiedByName = "convertDateTime"),
            @Mapping(target = "symbol", source = "sym"),
            @Mapping(target = "open", source = "o"),
            @Mapping(target = "close", source = "c"),
            @Mapping(target = "high", source = "h"),
            @Mapping(target = "low", source = "l"),
            @Mapping(target = "volumeweightedaverageprice", source = "vw"),
            @Mapping(target = "todayopen", source = "op"),
            @Mapping(target = "todayvolumeweightedaverageprice", source = "a"),
            @Mapping(target = "volume", source = "av"),
            @Mapping(target = "averagetradesize", source = "z"),
            @Mapping(target = "aggregateVolume", source = "v"),
    })
    StockEntity polygonStockWebsocketDtoToStockEntity(PolygonStockWebsocketAggregatesDto polygonStockWebsocketAggregatesDto);

    @Named("convertDateTime")
    public static String convertDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
