package flavia.dev.delivery_restaurantes.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import flavia.dev.delivery_restaurantes.model.Comida;


public interface ComidaRepository extends JpaRepository<Comida, Long> {

	
	List<Comida> findByRestaurantId(Long restaurantId);
	
	@Query("SELECT f FROM Comida f WHERE "
			+ "f.nome LIKE %:keyword% OR "
			+ "f.comidaCategoria.nome LIKE %:keyword% AND "
			+ "f.restaurant!=null"
	)
	List<Comida> pesquisarByNomeCategoria(@Param("keyword") String keyword);
	
}

