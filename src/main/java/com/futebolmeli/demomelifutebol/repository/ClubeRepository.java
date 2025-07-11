package com.futebolmeli.demomelifutebol.repository;

import com.futebolmeli.demomelifutebol.entity.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {
}
