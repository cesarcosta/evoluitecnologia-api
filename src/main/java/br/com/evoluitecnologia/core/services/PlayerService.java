package br.com.evoluitecnologia.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evoluitecnologia.core.entities.Player;
import br.com.evoluitecnologia.core.repositories.PlayerRepository;
import static br.com.evoluitecnologia.core.util.IsNullUtil.isNullOrEmpty;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
  
  private PlayerRepository repository;
  
  @Autowired
  public PlayerService(PlayerRepository playerRepository) {
    this.repository = playerRepository;
  }

  public Player save(Player player) throws Exception {
    if (isNullOrEmpty(player.getName()) || isNullOrEmpty(player.getMatch()) || isNullOrEmpty(player.getVictory())) {
      throw new Exception("Name, Matches and Victorys are required!");
    }
    repository.save(player);
    return player;
  }

  public List<Player> findAll() {
    List<Player> players = (List<Player>) repository.findAll();
    return players;
  } 

  public Optional<Player> getById(Long id) throws Exception {
    if (isNullOrEmpty(id)) {
      throw new Exception("Player ID is required!");
    }
    Optional<Player> player = repository.findById(id);
    return player;
  }

  public void remove(Long id) throws Exception {
    if (isNullOrEmpty(id)) {
      throw new Exception("Player ID is required!");
    }
    repository.deleteById(id);
  }
}
