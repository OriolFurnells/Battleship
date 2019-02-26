package salvo.battleship.salvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository) {
        return (args) -> {
            //created players
            Player player1 = new Player("j.bauer@ctu.gov");
            Player player2 = new Player("c.obrian@ctu.gov");
            Player player3 = new Player("kim_bauer@gmail.com");
            Player player4 = new Player("t.almeida@ctu.gov");

            //created games
            Game game1 = new Game();
            Game game2 = new Game();
            Game game3 = new Game();
            Game game4 = new Game();
            Game game5 = new Game();
            Game game6 = new Game();
            Game game7 = new Game();
            Game game8 = new Game();

           //created gamePlayers
            GamePlayer gamePlayer1 = new GamePlayer();
            GamePlayer gamePlayer2 = new GamePlayer();
            player1.addGamePlayer(gamePlayer1);
            game1.addGamePlayer(gamePlayer1);
            player2.addGamePlayer(gamePlayer2);
            game1.addGamePlayer(gamePlayer2);

            GamePlayer gamePlayer3 = new GamePlayer();
            GamePlayer gamePlayer4 = new GamePlayer();
            player1.addGamePlayer(gamePlayer3);
            game2.addGamePlayer(gamePlayer3);
            player2.addGamePlayer(gamePlayer4);
            game2.addGamePlayer(gamePlayer4);

            GamePlayer gamePlayer5 = new GamePlayer();
            GamePlayer gamePlayer6 = new GamePlayer();
            player2.addGamePlayer(gamePlayer5);
            game3.addGamePlayer(gamePlayer5);
            player4.addGamePlayer(gamePlayer6);
            game3.addGamePlayer(gamePlayer6);

            GamePlayer gamePlayer7 = new GamePlayer();
            GamePlayer gamePlayer8 = new GamePlayer();
            player2.addGamePlayer(gamePlayer7);
            game4.addGamePlayer(gamePlayer7);
            player1.addGamePlayer(gamePlayer8);
            game4.addGamePlayer(gamePlayer8);

            GamePlayer gamePlayer9 = new GamePlayer();
            GamePlayer gamePlayer10 = new GamePlayer();
            player4.addGamePlayer(gamePlayer9);
            game5.addGamePlayer(gamePlayer9);
            player1.addGamePlayer(gamePlayer10);
            game5.addGamePlayer(gamePlayer10);

            GamePlayer gamePlayer11 = new GamePlayer();
            player3.addGamePlayer(gamePlayer11);
            game6.addGamePlayer(gamePlayer11);

            GamePlayer gamePlayer13 = new GamePlayer();
            player4.addGamePlayer(gamePlayer13);
            game7.addGamePlayer(gamePlayer13);

            GamePlayer gamePlayer15 = new GamePlayer();
            GamePlayer gamePlayer16 = new GamePlayer();
            player3.addGamePlayer(gamePlayer15);
            game8.addGamePlayer(gamePlayer15);
            player4.addGamePlayer(gamePlayer16);
            game8.addGamePlayer(gamePlayer16);

            //Created Lists
            List<String> locationOne = Arrays.asList("B2", "C2", "D2", "E2","F2");
            List<String> locationTwo = Arrays.asList("H2", "H3", "H4");
            List<String> locationThree = Arrays.asList("E1", "F1", "G1");
            List<String> locationFour = Arrays.asList("B5", "C5", "D5","E5");
            List<String> locationFive= Arrays.asList("B4", "B5");
            //created ships
            Ship Carrier= new Ship("Carrier", locationOne);
            Ship Battleship= new Ship("Battleship", locationFour);
            Ship Submarine= new Ship("Submarine", locationThree);
            Ship Destroyer= new Ship("Destroyer", locationTwo);
            Ship PatrolBoat= new Ship("PatrolBoat", locationFive);

            Ship Carrier2= new Ship("Carrier", locationOne);
            Ship Battleship2= new Ship("Battleship", locationFour);
            Ship Submarine2= new Ship("Submarine", locationThree);
            Ship Destroyer2= new Ship("Destroyer", locationTwo);
            Ship PatrolBoat2= new Ship("PatrolBoat", locationFive);

            //Asign ships and Locations to gamePlayer
            gamePlayer1.addShip(Submarine);
            gamePlayer1.addShip(Destroyer);
            gamePlayer1.addShip(PatrolBoat);

            gamePlayer2.addShip(Destroyer2);
            gamePlayer2.addShip(PatrolBoat2);

            gamePlayer3.addShip(Submarine2);
            gamePlayer3.addShip(Carrier);

            gamePlayer4.addShip(Carrier2);
            gamePlayer4.addShip(Battleship2);
//
//            gamePlayer5.addShip(Destroyer);
//            gamePlayer5.addShip(PatrolBoat);
//
//            gamePlayer6.addShip(Submarine);
//            gamePlayer6.addShip(PatrolBoat);
//
//            gamePlayer7.addShip(Destroyer);
//            gamePlayer7.addShip(PatrolBoat);
//
//            gamePlayer8.addShip(Submarine);
//            gamePlayer8.addShip(PatrolBoat);
//
//            gamePlayer9.addShip(Destroyer);
//            gamePlayer9.addShip(PatrolBoat);
//
//
//            gamePlayer10.addShip(Submarine);
//            gamePlayer10.addShip(PatrolBoat);
//
//            gamePlayer11.addShip(Destroyer);
//            gamePlayer11.addShip(PatrolBoat);
//
//            gamePlayer15.addShip(Destroyer);
//            gamePlayer15.addShip(PatrolBoat);
//
//            gamePlayer16.addShip(Submarine);
//            gamePlayer16.addShip(PatrolBoat);


            //save players
            playerRepository.save(player1);
            playerRepository.save(player2);
            playerRepository.save(player3);
            playerRepository.save(player4);
            //save games
            gameRepository.save(game1);
            gameRepository.save(game2);
            gameRepository.save(game3);
            gameRepository.save(game4);
            gameRepository.save(game5);
            gameRepository.save(game6);
            gameRepository.save(game7);
            gameRepository.save(game8);
            //save gamePlayers
            gamePlayerRepository.save(gamePlayer1);
            gamePlayerRepository.save(gamePlayer2);
            gamePlayerRepository.save(gamePlayer3);
            gamePlayerRepository.save(gamePlayer4);
            gamePlayerRepository.save(gamePlayer5);
            gamePlayerRepository.save(gamePlayer6);
            gamePlayerRepository.save(gamePlayer7);
            gamePlayerRepository.save(gamePlayer8);
            gamePlayerRepository.save(gamePlayer9);
            gamePlayerRepository.save(gamePlayer10);
            gamePlayerRepository.save(gamePlayer11);
            gamePlayerRepository.save(gamePlayer13);
            gamePlayerRepository.save(gamePlayer15);
            gamePlayerRepository.save(gamePlayer16);
            //save ships
            shipRepository.save(Carrier);
            shipRepository.save(Battleship);
            shipRepository.save(Submarine);
            shipRepository.save(Destroyer);
            shipRepository.save(PatrolBoat);
            shipRepository.save(Carrier2);
            shipRepository.save(Battleship2);
            shipRepository.save(Submarine2);
            shipRepository.save(Destroyer2);
            shipRepository.save(PatrolBoat2);










        };

    }


}