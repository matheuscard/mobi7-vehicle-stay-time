package com.mob7.vehiclestaytime.infrastructure.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "carro")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarEntity {
    @Id
    private Long id;
    @Column(name = "placa")
    private String plate;
    @Column(name = "velocidade")
    private Integer velocity;
    @Column(name = "ignicao")
    private boolean ignition;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "carro_id", referencedColumnName = "id")
    @JsonBackReference
    private List<PositionEntity> positions;
}
