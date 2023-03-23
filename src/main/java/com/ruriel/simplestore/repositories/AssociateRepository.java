package com.ruriel.simplestore.repositories;

import com.ruriel.simplestore.entities.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

}
