package salvo.battleship.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
        private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    private Date dateNoFormat = new Date(); // Sistema actual La fecha y la hora se asignan a objDate

    public GamePlayer(Player p1, Game g1){}

    public GamePlayer(Player player, Game game, Date dateNoFormat) {
        this.player = player;
        this.game = game;
        this.dateNoFormat = dateNoFormat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Date getDateNoFormat() {
        return dateNoFormat;
    }

    public void setDateNoFormat(Date dateNoFormat) {
        this.dateNoFormat = dateNoFormat;
    }

    public String dateStartGame() {
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat changeFormat = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un argumento al objeto
        String dateStart = changeFormat.format(dateNoFormat);
        return dateStart;
    }

}
