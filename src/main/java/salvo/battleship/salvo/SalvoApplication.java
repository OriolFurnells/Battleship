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
	public CommandLineRunner initData(PlayerRepository repository) {
		return (args) -> {
			// save a couple of players
			repository.save(new Player("oriol.furnells@gmail.com"));
			repository.save(new Player("rodrigo@brasil.br"));
			repository.save(new Player("nagash@armenia.arm"));
			repository.save(new Player("yeray@tenerife.es"));
			repository.save(new Player("pancho@guatemala.gt"));

					};
	}

}