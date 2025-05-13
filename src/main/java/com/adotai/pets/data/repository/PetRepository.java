package com.adotai.pets.data.repository;

import com.adotai.pets.data.entities.OrganizationEntity;
import com.adotai.pets.data.entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<PetEntity, String> {

    @Query("""
    SELECT p FROM PetEntity p
    WHERE (p.organization = :orgEntity)
      AND (:name IS NULL OR LOWER(p.name) LIKE :name%)
      AND (:age IS NULL OR CAST(p.age AS string) = :age)
      AND (:type IS NULL OR LOWER(p.type) LIKE :type%)
      AND (:breed IS NULL OR LOWER(p.breed) LIKE :breed%)
      AND (:status IS NULL OR LOWER(p.status) LIKE :status%)
    """)
    List<PetEntity> filterByValues(
            @Param("name") String name,
            @Param("age") String age,
            @Param("type") String type,
            @Param("breed") String breed,
            @Param("status") String status,
            @Param("orgEntity") OrganizationEntity orgEntity
    );
}
