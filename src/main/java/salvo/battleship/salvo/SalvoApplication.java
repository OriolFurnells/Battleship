package salvo.battleship.salvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
        return (args) -> {
            // save a couple of players

            Player p1 = new Player("oriol.furnells@gmail.com");
            Player p2 = new Player("rodrigo@brasil.br");
            Player p3 = new Player("nagash@armenia.arm");
            Player p4 = new Player("yeray@tenerife.es");
            Player p5 = new Player("pancho@guatemala.gt");

            Game g1 = new Game();
            gameRepository.save(g1);

            //save a couple of games
            GamePlayer gp1 = new GamePlayer(p1, g1);
            gamePlayerRepository.save(gp1);

//            gamePlayerRepository.save(new GamePlayer());


        };

    }


}