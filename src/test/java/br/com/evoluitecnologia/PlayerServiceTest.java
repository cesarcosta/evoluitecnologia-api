package br.com.evoluitecnologia;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.evoluitecnologia.core.entities.Player;
import br.com.evoluitecnologia.core.repositories.PlayerRepository;
import br.com.evoluitecnologia.core.services.PlayerService;

@SpringBootTest
public class PlayerServiceTest {

  PlayerService playerService;

  @Mock
  private PlayerRepository playerRepository;

  @BeforeEach
  public void setUp() throws Exception {
    playerService = new PlayerService(playerRepository);
  }

  @Test
  public void savePlayerTest() {
    try {
      Player player1 = new Player("Player 1", 5, 5);
      Player player = playerService.save(player1);
      assertEquals(player, player1);
      Mockito.verify(playerRepository, Mockito.times(1)).save(player1);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getAllPlayersTest() {
    List<Player> list = new ArrayList<Player>();

    Player player1 = new Player("Player 1", 5, 5);
    Player player2 = new Player("Player 2", 10, 13);
    Player player3 = new Player("Player 3", 4, 6);

    list.add(player1);
    list.add(player2);
    list.add(player3);

    Mockito.when(playerService.findAll()).thenReturn(list);

    List<Player> listPlayers = playerService.findAll();

    assertEquals(3, listPlayers.size());
    Mockito.verify(playerRepository, Mockito.times(1)).findAll();
  }

  @Test
  public void getPlayerByIdTest() {
    try {
      Player player1 = new Player("Player 1", 5, 5);

      Player playerCreated = playerService.save(player1);

      Optional<Player> playerResult = playerService.getById(playerCreated.getId());

      Mockito.when(playerService.getById(playerCreated.getId())).thenReturn(playerResult);

      assertEquals(player1, playerCreated);
      Mockito.verify(playerRepository, Mockito.times(1)).findById(playerCreated.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void deletePlayerByIdTest() {
    try {
      Player player1 = new Player("Player 1", 5, 5);

      Player playerCreated = playerService.save(player1);

      Optional<Player> playerResult = playerService.getById(playerCreated.getId());

      Mockito.when(playerService.getById(playerCreated.getId())).thenReturn(playerResult);

      assertEquals(player1, playerCreated);

      List<Player> players = playerService.findAll();

      assertEquals(0, players.size());

      Mockito.verify(playerRepository, Mockito.times(1)).deleteById(playerCreated.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
