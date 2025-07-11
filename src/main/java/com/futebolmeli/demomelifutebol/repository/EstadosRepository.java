package com.futebolmeli.demomelifutebol.repository;

import com.futebolmeli.demomelifutebol.entity.Estados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosRepository extends JpaRepository<Estados, Long> {
}
