package salvo.battleship.salvo;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;
    private String password;


    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set <GamePlayer> gamePlayers = new HashSet<>();


    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    private Set<Score> scores= new HashSet<>();


    public Player() {

    }

    public Player(String username, String password) {
        this.userName= username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public void addGamePlayer (GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

    public void addScore (Score score){
        score.setPlayer(this);
        scores.add(score);
    }


    public Score getScore (Game game){
            //cogemos los scores y los transformamos en --
        return scores.stream()
                //buscamos la coincidencia comparando con el game pasamos por parametro (entre parentesis)
                .filter(score -> score.getGame().equals(game))
                //filter y map siempre devuelven lista
                //devuelve un optional que nos permite extraer
                .findFirst()
                //si no, devuelve null
               .orElse(null);
    }

}

