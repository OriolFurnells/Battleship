package salvo.battleship.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private ScoreRepository scoreRepo;

    @RequestMapping("/games")
    public List<Map<String, Object>> getAll() {
        return gameRepo.findAll()
                .stream()
                .map(game -> makeGameDTO(game))
                .collect(Collectors.toList());
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerRepo.findOne(gamePlayerId);

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

    private Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> MapaDto = new LinkedHashMap<String, Object>();
        MapaDto.put("gamePlayerId", gamePlayer.getId());
        MapaDto.put("players", gamePlayer.getPlayer() != null ? makePlayerDTO(gamePlayer.getPlayer()) : null);
        return MapaDto;
    }

    private Map<String, Object> makePlayerDTO(Player player) {
        Map<String, Object> PlayerDto = new LinkedHashMap<String, Object>();
        PlayerDto.put("playerId", player.getId());
        PlayerDto.put("playerEmail", player.getUserName());
        PlayerDto.put("playerScore", player.getScores());
        return PlayerDto;
    }

    private Map<String, Object> makeScoresDto(Player player) {
        double totalPoints = player.getScores()
                .stream()
                .mapToDouble(score -> score.getPlayerScore())
                .sum();
        double totalWins= player.getScores()
                .stream()
                .filter(score -> score.getPlayerScore() == 1)
                .count();
        double totalTies= player.getScores()
                .stream()
                .filter(score -> score.getPlayerScore() == 0.5)
                .count();
        double totalLosers= player.getScores()
                .stream()
                .filter(score -> score.getPlayerScore() == 0)
                .count();

        Map<String, Object> tableLeader = new LinkedHashMap<String, Object>();
        tableLeader.put("playerEmail", player.getUserName());
        tableLeader.put("totalPoints", totalPoints);
        tableLeader.put("totalWins",totalWins);
        tableLeader.put("totalTies",totalTies);
        tableLeader.put("totalLosers",totalLosers);
        return tableLeader;
    }

    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> gameDto = new LinkedHashMap<String, Object>();
        gameDto.put("gameId", game.getId());
        gameDto.put("gameCreated", game.getDateInitial());
        gameDto.put("gamePlayers", game.getGamePlayers().stream().map(gamePlayer -> makeGamePlayerDTO(gamePlayer))
                .collect(Collectors.toList()));
        gameDto.put("gameScores", game.getScores());
        return gameDto;
    }

    private List<Map<String, Object>> makeShipsDTO(Set<Ship> ships) {
        return ships.stream().map(ship -> {
            Map<String, Object> shipsDto= new LinkedHashMap<String, Object>();
            shipsDto.put("shipType", ship.getType());
            shipsDto.put("shipCell_Locations", ship.getCellLocations());
            return shipsDto;
        }).collect(Collectors.toList());
        }

    private List<Map<String, Object>> makeSalvoDTO(Set<Salvo> salvos) {
        return salvos.stream().map(salvo -> {
            Map<String, Object> salvoDto= new LinkedHashMap<String, Object>();
            salvoDto.put("turn", salvo.getTurn());
            salvoDto.put("salvo_Locations", salvo.getSalvoLocations());
            return salvoDto;
        }).collect(Collectors.toList());
    }

    private Map<String, Object> makeGamePlayerWithVersusDTO(GamePlayer gamePlayer) {
        Map<String, Object> MapaDto = new LinkedHashMap<String, Object>();
        MapaDto.put("gamePlayerId", gamePlayer.getId());
        MapaDto.put("players", makePlayerDTO(gamePlayer.getPlayer()));
        MapaDto.put("ships", makeShipsDTO(gamePlayer.getShips()));
        MapaDto.put("salvoes", makeSalvoDTO(gamePlayer.getSalvos()));
        return MapaDto;
    }

    private Map<String, Object> makeGameCompleteDTO(GamePlayer gamePlayer) {
        Map<String, Object> gameWithAll = new LinkedHashMap<String, Object>();
        gameWithAll.put("gameId", gamePlayer.getGame().getId());
        gameWithAll.put("gameCreated", gamePlayer.getGame().getDateInitial());
        gameWithAll.put("gamePlayers", gamePlayer.getGame().getGamePlayers().stream().map(gamePlayerMap -> makeGamePlayerWithVersusDTO(gamePlayerMap)).collect(Collectors.toList()));
        return gameWithAll;
    }
}



