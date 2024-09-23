package com.mob7.vehiclestaytime.infrastructure.persistence;

import com.mob7.vehiclestaytime.infrastructure.persistence.entities.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    @Query("SELECT p FROM PositionEntity p " +
            "WHERE  p.plate LIKE %:plate% AND p.date BETWEEN :startDate AND :endDate")
    List<PositionEntity> findFilteredPositions(@Param("plate") String plate,
                                               @Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);

}
