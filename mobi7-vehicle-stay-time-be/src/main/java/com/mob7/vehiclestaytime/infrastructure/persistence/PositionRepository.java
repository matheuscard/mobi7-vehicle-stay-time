package com.mob7.vehiclestaytime.infrastructure.persistence;

import com.mob7.vehiclestaytime.domain.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Long> {
}
