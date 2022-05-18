package br.com.evoluitecnologia.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.evoluitecnologia.core.entities.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long>{}