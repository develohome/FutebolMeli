package com.futebolmeli.demomelifutebol.repository;

import com.futebolmeli.demomelifutebol.entity.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {
    Boolean existsByClube(String clube);
    Boolean existsById(long id);

    //List<Clube> findByClubeAtivoTrue();
}
