package salvo.battleship.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SalvoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository ) {
        return (args) -> {
            //created players
            Player player1 = new Player("j.bauer@ctu.gov", "24");
            Player player2 = new Player("c.obrian@ctu.gov", "42");
            Player player3 = new Player("kim_bauer@gmail.com","kb");
            Player player4 = new Player("t.almeida@ctu.gov","mole");

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
            GamePlayer gamePlayer3 = new GamePlayer();
            GamePlayer gamePlayer4 = new GamePlayer();
            GamePlayer gamePlayer5 = new GamePlayer();
            GamePlayer gamePlayer6 = new GamePlayer();
            GamePlayer gamePlayer7 = new GamePlayer();
            GamePlayer gamePlayer8 = new GamePlayer();
            GamePlayer gamePlayer9 = new GamePlayer();
            GamePlayer gamePlayer10 = new GamePlayer();
            GamePlayer gamePlayer11 = new GamePlayer();
            GamePlayer gamePlayer13 = new GamePlayer();
            GamePlayer gamePlayer15 = new GamePlayer();
            GamePlayer gamePlayer16 = new GamePlayer();

            //Created Lists of LocationsShips
            List<String> locationOne = Arrays.asList("B2", "C2", "D2", "E2","F2");
            List<String> locationTwo = Arrays.asList("H2", "H3", "H4");
            List<String> locationThree = Arrays.asList("E1", "F1", "G1");
            List<String> locationFour = Arrays.asList("B5", "C5", "D5","E5");
            List<String> locationFive= Arrays.asList("B4", "B5");
            //created ships with locations
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

            //Created Lists of Salvo
            List<String> salvo01 = Arrays.asList("B5", "C5", "F12");
            List<String> salvo02 = Arrays.asList("B4", "B5", "B6");
            List<String> salvo03 = Arrays.asList("F2", "D5");
            List<String> salvo04 = Arrays.asList("E1", "H3", "A2");
            List<String> salvo05 = Arrays.asList("A2", "A4", "G6");
            List<String> salvo06 = Arrays.asList("B5", "D5", "C7");
            List<String> salvo07 = Arrays.asList("A3", "H6");
            List<String> salvo08 = Arrays.asList("C5", "C6");
            List<String> salvo09 = Arrays.asList("G6", "H6", "A4");
            List<String> salvo10 = Arrays.asList("H1", "H2", "H3");
            List<String> salvo11 = Arrays.asList("A2", "A3", "D8");
            List<String> salvo12 = Arrays.asList("E1", "F2","G3");

            //created salvos with locations
            Salvo sv1= new Salvo (1, salvo01);
            Salvo sv2= new Salvo (1, salvo02);
            Salvo sv3= new Salvo (2, salvo03);
            Salvo sv4= new Salvo (2, salvo04);
            Salvo sv5= new Salvo (1, salvo05);
            Salvo sv6= new Salvo (1, salvo06);
            Salvo sv7= new Salvo (2, salvo07);
            Salvo sv8= new Salvo (2, salvo08);
            Salvo sv9= new Salvo (1, salvo09);
            Salvo sv10= new Salvo(1, salvo10);
            Salvo sv11= new Salvo(2, salvo11);
            Salvo sv12= new Salvo(2, salvo12);

            //created scores (win 1.00 - tie 0.5 - lose 0.00
            Score score1 = new Score (1.00);
            Score score2 = new Score (0.00);

            Score score3 = new Score (0.50);
            Score score4 = new Score (0.50 );

            Score score5 = new Score (1.00);
            Score score6 = new Score (0.00);

            Score score7 = new Score (1.00);
            Score score8 = new Score (0.00);

            //add players- games- ships - salvoes to everyOne

            game1.addGamePlayer(gamePlayer1);
            game1.addGamePlayer(gamePlayer2);
            player1.addGamePlayer(gamePlayer1);
            player2.addGamePlayer(gamePlayer2);
            gamePlayer1.addShip(Submarine);
            gamePlayer1.addShip(Destroyer);
            gamePlayer1.addShip(PatrolBoat);
            gamePlayer1.addSalvo(sv1);
            gamePlayer1.addSalvo(sv3);
            gamePlayer2.addShip(Destroyer2);
            gamePlayer2.addShip(PatrolBoat2);
            gamePlayer2.addSalvo(sv2);
            gamePlayer2.addSalvo(sv4);
            player1.addScore(score1);
            player2.addScore(score2);
            game1.addScore(score1);
            game1.addScore(score2);
            //
            game2.addGamePlayer(gamePlayer3);
            game2.addGamePlayer(gamePlayer4);
            player1.addGamePlayer(gamePlayer3);
            player2.addGamePlayer(gamePlayer4);
            gamePlayer3.addShip(Submarine2);
            gamePlayer3.addShip(Carrier);
            gamePlayer3.addSalvo(sv5);
            gamePlayer3.addSalvo(sv7);
            gamePlayer4.addShip(Carrier2);
            gamePlayer4.addShip(Battleship2);
            gamePlayer4.addSalvo(sv6);
            gamePlayer4.addSalvo(sv8);
            player2.addScore(score4);
            player1.addScore(score3);
            game2.addScore(score3);
            game2.addScore(score4);
            //
            game3.addGamePlayer(gamePlayer5);
            game3.addGamePlayer(gamePlayer6);
            player2.addGamePlayer(gamePlayer5);
            player4.addGamePlayer(gamePlayer6);
            //            gamePlayer5.addShip(Destroyer);
            //            gamePlayer5.addShip(PatrolBoat);
            //            gamePlayer6.addShip(Submarine);
            //            gamePlayer6.addShip(PatrolBoat);
            player2.addScore(score5);
            player1.addScore(score6);
            game3.addScore(score5);
            game3.addScore(score6);
            //
            game4.addGamePlayer(gamePlayer7);
            game4.addGamePlayer(gamePlayer8);
            player2.addGamePlayer(gamePlayer7);
            player1.addGamePlayer(gamePlayer8);
            //            gamePlayer7.addShip(Destroyer);
            //            gamePlayer7.addShip(PatrolBoat);
            //            gamePlayer8.addShip(Submarine);
            //            gamePlayer8.addShip(PatrolBoat);
            player2.addScore(score7);
            player1.addScore(score8);
            game4.addScore(score7);
            game4.addScore(score8);
            //
            game5.addGamePlayer(gamePlayer9);
            game5.addGamePlayer(gamePlayer10);
            player4.addGamePlayer(gamePlayer9);
            player1.addGamePlayer(gamePlayer10);
            //            gamePlayer9.addShip(Destroyer);
            //            gamePlayer9.addShip(PatrolBoat);
            //            gamePlayer10.addShip(Submarine);
            //            gamePlayer10.addShip(PatrolBoat);
            //
//            game6.addGamePlayer(gamePlayer11);
            //            gamePlayer11.addShip(Destroyer);
            //            gamePlayer11.addShip(PatrolBoat);
            //
            game7.addGamePlayer(gamePlayer13);
            player4.addGamePlayer(gamePlayer13);
            //
            game8.addGamePlayer(gamePlayer15);
            game8.addGamePlayer(gamePlayer16);
            player3.addGamePlayer(gamePlayer15);
            player4.addGamePlayer(gamePlayer16);
            //            gamePlayer15.addShip(Destroyer);
            //            gamePlayer15.addShip(PatrolBoat);
            //            gamePlayer16.addShip(Submarine);
            //            gamePlayer16.addShip(PatrolBoat);
            //

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
            // gameRepository.save(game6);
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


            //save salvos
            salvoRepository.save(sv1);
            salvoRepository.save(sv2);
            salvoRepository.save(sv3);
            salvoRepository.save(sv4);
            salvoRepository.save(sv5);
//            salvoRepository.save(sv6);
            salvoRepository.save(sv7);
            salvoRepository.save(sv8);

            //save scores
            scoreRepository.save(score1);
            scoreRepository.save(score2);
            scoreRepository.save(score3);
            scoreRepository.save(score4);
            scoreRepository.save(score5);
            scoreRepository.save(score6);
            scoreRepository.save(score7);
            scoreRepository.save(score8);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    }

    //valido usuario
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private PlayerRepository playerRepo;

        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userName-> {
                Player player = playerRepo.findByUserName(userName);
                if (player != null) {
                    return User
                            .withDefaultPasswordEncoder()
                            .username(player.getUserName())
                            .password(player.getPassword())
                            .roles("USER")
                            .build();
                } else {
                    throw new UsernameNotFoundException("Unknown user: " + userName);
                }
            });
        }
}


//defino donde puede entrar segun el rol
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/web/games.html",
                        "/web/scritps/games.js",
                        "/web/styles/ship.css",
                        "/api/leaderBoard",
                        "/api/games",
                        "/favicon.ico",
                        "/api/players"
                ).permitAll()
                .anyRequest().hasAnyAuthority("USER");

        http.formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .loginPage("/api/login")
                .permitAll();

        http.logout().logoutUrl("/api/logout")
                .permitAll();

        // turn off checking for CSRF tokens
        http.csrf().disable();

        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}