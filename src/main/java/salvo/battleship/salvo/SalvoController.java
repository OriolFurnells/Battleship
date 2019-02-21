package salvo.battleship.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class SalvoController {




    @Autowired
    private GameRepository gameRepor;


    @RequestMapping("/games")
    public List<Map<String, Object>> getAll() {

        return gameRepor.findAll()
                .stream()
                .map(game -> makeGameDTO (game))
                .collect(Collectors.toList());
    }

    private Map<String, Object> makeGamePlayerDTO (GamePlayer gamePlayer){
        Map<String, Object> MapaDto = new LinkedHashMap<String, Object>();
//        MapaDto.put("game", makeGameDTO(gamePlayer.getGame()));
        MapaDto.put("id", gamePlayer.getId());
        MapaDto.put("player", makePlayerDTO(gamePlayer.getPlayer()));
        return MapaDto;
    }

    private Map<String, Object> makePlayerDTO(Player player) {
        Map<String, Object> PlayerDto = new LinkedHashMap<String, Object>();
        PlayerDto.put("id", player.getId());
        PlayerDto.put("email", player.getUserName());
        return PlayerDto;
    }

    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> gameDto = new LinkedHashMap<String, Object>();
        gameDto.put("GameId", game.getId());
        gameDto.put("GameCreated", game.getDateInitial());
        gameDto.put("gamePlayers", game.getGamePlayers().stream().map(gamePlayer -> makeGamePlayerDTO(gamePlayer)).collect(Collectors.toList()));
        return gameDto;
    }

}



