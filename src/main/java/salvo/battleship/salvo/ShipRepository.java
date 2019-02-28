package salvo.battleship.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface ShipRepository  extends JpaRepository<Ship, Long> {

    List<GamePlayer> findById(long id);

}