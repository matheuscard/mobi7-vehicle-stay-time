package com.mob7.vehiclestaytime.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "ponto_interesse")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PointInterestEntity {
    @Id
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "raio")
    private Integer radius;
    private Double latitude;
    private Double longitude;
}
