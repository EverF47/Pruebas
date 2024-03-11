package com.PTecnic1.P1.repository;

import com.PTecnic1.P1.entity.ClientEty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* @author everf*/

@Repository

public interface ClientRep extends JpaRepository<ClientEty, Long>{
}