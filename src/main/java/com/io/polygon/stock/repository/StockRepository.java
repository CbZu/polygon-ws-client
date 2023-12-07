package com.io.polygon.stock.repository;

import com.io.polygon.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
