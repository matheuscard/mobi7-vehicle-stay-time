package com.mob7.vehiclestaytime.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "posicao")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PositionEntity {
    @Id
    private Long id;
    @Column(name = "placa")
    private String plate;
    @Column(name = "data")
    private LocalDateTime data;
    @Column(name = "velocidade")
    private Integer velocity;
    private Double latitude;
    private Double longitude;
    @Column(name = "ignicao")
    private boolean ignition;

}
