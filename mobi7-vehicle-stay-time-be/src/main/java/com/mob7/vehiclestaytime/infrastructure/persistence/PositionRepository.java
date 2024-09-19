package com.mob7.vehiclestaytime.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    @Query("SELECT p FROM PositionEntity p " +
            "WHERE (:plate IS NULL OR p.plate = :plate) AND " +
            "(:startDate IS NULL OR p.date >=:startDate) AND " +
            "(:endDate IS NULL OR p.date <=:endDate)")
    List<PositionEntity> findFilteredPositions(@Param("plate") String plate,
                                               @Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);
}
