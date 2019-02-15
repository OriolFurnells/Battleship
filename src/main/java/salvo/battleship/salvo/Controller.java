package salvo.battleship.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

public class Controller {

    @Autowired
    private PlayerRepository playerRepository;
}
