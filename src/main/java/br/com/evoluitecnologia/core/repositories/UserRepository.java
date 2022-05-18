package br.com.evoluitecnologia.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.evoluitecnologia.core.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{}