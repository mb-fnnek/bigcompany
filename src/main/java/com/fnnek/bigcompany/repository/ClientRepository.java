package com.fnnek.bigcompany.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fnnek.bigcompany.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
