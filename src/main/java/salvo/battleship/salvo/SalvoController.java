package salvo.battleship.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class    SalvoController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private ScoreRepository scoreRepo;

    @RequestMapping("/games")
    public Map<String, Object> getAll(Authentication authentication) {
        List<Map<String,Object>> allGames = gameRepo.findAll()
                .stream()
                .map(game -> makeGameDTO(game))
                .collect(Collectors.toList());

        LinkedHashMap<String, Object> gamesDto = new LinkedHashMap<String, Object>();
        gamesDto.put("player", isGuest(authentication) ? null : playerLogDto(currentUser(authentication)));
        //error de mapeado
        gamesDto.put("games", allGames);

        return gamesDto;
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerId) {

        GamePlayer gamePlayer = gamePlayerRepo.getOne(gamePlayerId);

        return makeGameCompleteDTO(gamePlayer);
    }

    @RequestMapping("/leaderBoard")
    public List<Map<String, Object>> getTableRanked() {
        return playerRepo.findAll()
                .stream()
                .filter(player -> player.getScores().size() > 1)
                .map(player -> makeScoresDto(player))
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam String username, @RequestParam String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (playerRepo.findByUserName(username) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }
        playerRepo.save(new Player(username, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private LinkedHashMap<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        LinkedHashMap<String, Object> MapaDto = new LinkedHashMap<String, Object>();
        MapaDto.put("gamePlayerId", gamePlayer.getId());
        MapaDto.put("players", gamePlayer.getPlayer() != null ? makePlayerDTO(gamePlayer.getPlayer()) : null);
        return MapaDto;
    }

    private LinkedHashMap<String, Object> makePlayerDTO(Player player) {
        LinkedHashMap<String, Object> PlayerDto = new LinkedHashMap<String, Object>();
        PlayerDto.put("playerId", player.getId());
        PlayerDto.put("playerEmail", player.getUserName());
        PlayerDto.put("playerScore", player.getScores());

        return PlayerDto;
    }

    private LinkedHashMap<String, Object> playerLogDto(Player player) {
        LinkedHashMap<String, Object> PlayerLogDto = new LinkedHashMap<String, Object>();
        PlayerLogDto.put("playerId", player.getId());
        PlayerLogDto.put("playerEmail", player.getUserName());
        return PlayerLogDto;
    }

    private LinkedHashMap<String, Object> makeScoresDto(Player player) {
        double totalPoints = player.getScores()
                .stream()
                .mapToDouble(score -> score.getPlayerScore())
                .sum();
        double totalWins = player.getScores()
                .stream()
                .filter(score -> score.getPlayerScore() == 1)
                .count();
        double totalTies = player.getScores()
                .stream()
                .filter(score -> score.getPlayerScore() == 0.5)
                .count();
        double totalLosers = player.getScores()
                .stream()
                .filter(score -> score.getPlayerScore() == 0)
                .count();

        LinkedHashMap<String, Object> tableLeader = new LinkedHashMap<String, Object>();
        tableLeader.put("playerEmail", player.getUserName());
        tableLeader.put("totalPoints", totalPoints);
        tableLeader.put("totalWins", totalWins);
        tableLeader.put("totalTies", totalTies);
        tableLeader.put("totalLosers", totalLosers);
        return tableLeader;
    }

    private LinkedHashMap<String, Object> makeGameDTO(Game game) {
        LinkedHashMap<String, Object> gameDto = new LinkedHashMap<String, Object>();
        gameDto.put("gameId", game.getId());
        gameDto.put("gameCreated", game.getDateInitial());
        gameDto.put("gamePlayers", game.getGamePlayers().stream().map(gamePlayer -> makeGamePlayerDTO(gamePlayer))
                .collect(Collectors.toList()));
        gameDto.put("gameScores", game.getScores());

        return gameDto;
    }

    private Map<String, Object> makeGamesDto (Game game){
        LinkedHashMap<String, Object> gamesDto = new LinkedHashMap<String, Object>();
        gamesDto.put("player", null);
        gamesDto.put("games", makeGameDTO(game));

        return gamesDto;
    }

    private List<LinkedHashMap<String, Object>> makeShipsDTO(Set<Ship> ships) {
        return ships.stream().map(ship -> {
            LinkedHashMap<String, Object> shipsDto = new LinkedHashMap<String, Object>();
            shipsDto.put("shipType", ship.getType());
            shipsDto.put("shipCell_Locations", ship.getCellLocations());
            return shipsDto;
        }).collect(Collectors.toList());
    }

    private List<LinkedHashMap<String, Object>> makeSalvoDTO(Set<Salvo> salvos) {
        return salvos.stream().map(salvo -> {
            LinkedHashMap<String, Object> salvoDto = new LinkedHashMap<String, Object>();
            salvoDto.put("turn", salvo.getTurn());
            salvoDto.put("salvo_Locations", salvo.getSalvoLocations());
            return salvoDto;
        }).collect(Collectors.toList());
    }

    private LinkedHashMap<String, Object> makeGamePlayerWithVersusDTO(GamePlayer gamePlayer) {
        LinkedHashMap<String, Object> MapaDto = new LinkedHashMap<String, Object>();
        MapaDto.put("gamePlayerId", gamePlayer.getId());
        MapaDto.put("players", makePlayerDTO(gamePlayer.getPlayer()));
        MapaDto.put("ships", makeShipsDTO(gamePlayer.getShips()));
        MapaDto.put("salvoes", makeSalvoDTO(gamePlayer.getSalvos()));
        return MapaDto;
    }

    private LinkedHashMap<String, Object> makeGameCompleteDTO(GamePlayer gamePlayer) {
        LinkedHashMap<String, Object> gameWithAll = new LinkedHashMap<String, Object>();
        gameWithAll.put("gameId", gamePlayer.getGame().getId());
        gameWithAll.put("gameCreated", gamePlayer.getGame().getDateInitial());
        gameWithAll.put("gamePlayers", gamePlayer.getGame().getGamePlayers().stream().map(gamePlayerMap -> makeGamePlayerWithVersusDTO(gamePlayerMap)).collect(Collectors.toList()));
        return gameWithAll;
    }

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    public Player currentUser (Authentication authentication){
        return playerRepo.findByUserName(authentication.getName());
    }
}





