package br.com.evoluitecnologia.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "qt_victory")
    private Integer victory;

    @Column(name = "qt_match")
    private Integer match;
   
    public Player(String name, Integer victory, Integer match) {
      this.name = name;
      this.victory = victory;
      this.match = match;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getVictory() {
      return victory;
    }

    public void setVictory(Integer victory) {
      this.victory = victory;
    }

    public Integer getMatch() {
      return match;
    }

    public void setMatch(Integer match) {
      this.match = match;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Player other = (Player) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "Player [id=" + id + ", match=" + match + ", victory=" + victory + "]";
    }    
}