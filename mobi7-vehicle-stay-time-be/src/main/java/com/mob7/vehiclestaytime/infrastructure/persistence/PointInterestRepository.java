package com.mob7.vehiclestaytime.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface PointInterestRepository extends JpaRepository<PointInterestEntity,Long> {

    @Query(nativeQuery = true,
            value = "SELECT *,(6371000  * \n" +
                    "        acos(\n" +
                    "         cos(radians(:latitude)) * \n" +
                    "         cos(radians(latitude)) * \n" +
                    "         cos(radians(:longitude) - RADIANS(longitude)) + \n" +
                    "         sin(radians(:latitude)) * \n" +
                    "         sin(radians(latitude))\n" +
                    "      )) AS distance\n" +
                    "FROM ponto_interesse HAVING distance <= raio")
    Optional<List<PointInterestEntity>> findPointInterest(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude);
}
