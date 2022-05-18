package br.com.evoluitecnologia.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.evoluitecnologia.core.entities.Player;
import br.com.evoluitecnologia.core.services.PlayerService;

@RestController
@CrossOrigin(origins = "*")
public class PlayerController {
  
  @Autowired
	private PlayerService playerService;

  @GetMapping("/players")
  public ResponseEntity<List<Player>> list() {
    try {
      List<Player> players = playerService.findAll();
      return new ResponseEntity<>(players, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/players")
  public ResponseEntity<Player> save(@RequestBody Player player) {
		try {
			Player playerCreated = playerService.save(player);
			return new ResponseEntity<>(playerCreated, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
  }

  @GetMapping("/players/{id}")
	public ResponseEntity<Player> getById(@PathVariable("id") long id) {
		try {
			Optional<Player> player = playerService.getById(id);
			if (player.isPresent()) {
				return new ResponseEntity<>(player.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

  @PutMapping("/players/{id}")
	public ResponseEntity<Player> update(@PathVariable("id") long id, @RequestBody Player player) {
		try {
			Optional<Player> playerDb = playerService.getById(id);
			if (playerDb.isPresent()) {
				Player playerUpdated = playerDb.get();
				playerUpdated.setName(player.getName());
				playerUpdated.setMatch(player.getMatch());
				playerUpdated.setVictory(player.getVictory());
				return new ResponseEntity<>(playerService.save(playerUpdated), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

  @DeleteMapping("/players/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			playerService.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}