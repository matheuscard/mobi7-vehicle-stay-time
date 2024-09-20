package com.mob7.vehiclestaytime.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ponto_interesse_id", referencedColumnName = "id")
    @JsonBackReference
    private List<PositionEntity> positions;
}
