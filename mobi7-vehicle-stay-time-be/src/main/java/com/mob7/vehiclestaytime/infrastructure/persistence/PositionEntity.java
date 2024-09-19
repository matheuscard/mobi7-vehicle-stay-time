package com.mob7.vehiclestaytime.infrastructure.persistence;

import jakarta.persistence.*;
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
    private LocalDateTime date;
    @Column(name = "velocidade")
    private Integer velocity;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "ignicao")
    private boolean ignition;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ponto_interesse_id")
    private PointInterestEntity pointInterest;
}
