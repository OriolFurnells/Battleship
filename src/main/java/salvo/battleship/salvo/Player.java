package salvo.battleship.salvo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;


    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set <GamePlayer> gamePlayers = new HashSet<>();


    public void addGamePlayer (GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

//    public void addPet(Pet pet) {
//        pet.setOwner(this);
//        pets.add(pet);
//    }

    public Player() {

    }

    public Player(String username) {
        this.userName= username;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return id + " " +userName;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
}

