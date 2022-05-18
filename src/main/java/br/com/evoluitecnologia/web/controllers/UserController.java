package br.com.evoluitecnologia.web.controllers;

import java.util.List;
import java.util.Optional;

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

import br.com.evoluitecnologia.core.entities.User;
import br.com.evoluitecnologia.core.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
  
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> list() {
    try {
      List<User> users = (List<User>) userRepository.findAll();
      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/users")
  public ResponseEntity<User> save(@RequestBody User user) {
    User userCreated = userRepository.save(user);
    return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
  }

  @GetMapping("/users/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

  @PutMapping("/users/{id}")
	public ResponseEntity<User> updateTutorial(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> tutorialData = userRepository.findById(id);
		if (tutorialData.isPresent()) {
			User userUpdated = tutorialData.get();
			userUpdated.setName(user.getName());
			userUpdated.setEmail(user.getEmail());
			return new ResponseEntity<>(userRepository.save(userUpdated), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

  @DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}