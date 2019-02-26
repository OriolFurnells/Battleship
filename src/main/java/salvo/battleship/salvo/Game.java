package salvo.battleship.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    //1 id y 1 fecha y hora de inicio no modificable
    private long id;
    private String dateInitial;


    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();


    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }

    public Game() {
    }

    public Game(long id) {
        this.id = id;
    }

    public String getDateInitial() {
        Date dateNoFormat = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat changeFormat = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto
        String dateInitial = changeFormat.format(dateNoFormat);

        return dateInitial;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public void setDateInitial(Date objDate) {
        this.dateInitial = dateInitial;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }
/*
    public String dateStartGame() {
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat changeFormat = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto
        String dateInitial = changeFormat.format(dateNoFormat);
        return dateInitial;
    }
*/




}
