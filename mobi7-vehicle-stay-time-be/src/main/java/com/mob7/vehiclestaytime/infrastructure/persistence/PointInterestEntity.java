package com.mob7.vehiclestaytime.infrastructure.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mob7.vehiclestaytime.domain.model.Position;
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pointInterest")
    @JsonBackReference
    private List<PositionEntity> positions;
}
