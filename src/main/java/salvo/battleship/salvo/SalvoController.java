package salvo.battleship.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class SalvoController {


    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerRepo.findOne(gamePlayerId);
          return makeGamePlayerShipsDTO(gamePlayer);
    }


    @Autowired
    private GameRepository gameRepor;

    @RequestMapping("/games")
    public List<Map<String, Object>> getAll() {

        return gameRepor.findAll()
                .stream()
                .map(game -> makeGameDTO(game))
                .collect(Collectors.toList());
    }

    private Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> MapaDto = new LinkedHashMap<String, Object>();
        MapaDto.put("GamePlayerId", gamePlayer.getId());
        MapaDto.put("Players", makePlayerDTO(gamePlayer.getPlayer()));
        return MapaDto;
    }

    private Map<String, Object> makePlayerDTO(Player player) {
        Map<String, Object> PlayerDto = new LinkedHashMap<String, Object>();
        PlayerDto.put("PlayerId", player.getId());
        PlayerDto.put("PlayerEmail", player.getUserName());
        return PlayerDto;
    }

    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> gameDto = new LinkedHashMap<String, Object>();
        gameDto.put("GameId", game.getId());
        gameDto.put("GameCreated", game.getDateInitial());
        gameDto.put("GamePlayers", game.getGamePlayers().stream().map(gamePlayer -> makeGamePlayerDTO(gamePlayer)).collect(Collectors.toList()));
        return gameDto;
    }

    private Map<String, Object> makeGamePlayerShipsDTO(GamePlayer gamePlayer) {
        Map<String, Object> gameWithShip = new LinkedHashMap<String, Object>();
        gameWithShip.put("GameId", gamePlayer.getGame().getId());
        gameWithShip.put("GameCreated", gamePlayer.getGame().getDateInitial());
        gameWithShip.put("gamePlayers", gamePlayer.getGame().getGamePlayers().stream().map(gamePlayerMap -> makeGamePlayerDTO(gamePlayerMap)).collect(Collectors.toList()));
        gameWithShip.put("Ships", makeShipsDTO(gamePlayer.getShips()));
        return gameWithShip;
    }

        private List<Map<String, Object>> makeShipsDTO(Set<Ship> ships) {
        return ships.stream().map(ship -> {
            Map<String, Object> shipsDto= new LinkedHashMap<String, Object>();
            shipsDto.put("ShipType", ship.getType());
            shipsDto.put("ShipCell_Locations", ship.getCellLocations());
            return shipsDto;
        }).collect(Collectors.toList());
        }




}



