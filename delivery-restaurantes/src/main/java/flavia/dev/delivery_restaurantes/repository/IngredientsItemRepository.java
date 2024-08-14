package flavia.dev.delivery_restaurantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import flavia.dev.delivery_restaurantes.model.IngredientsItem;

public interface IngredientsItemRepository extends JpaRepository<IngredientsItem, Long> {

	
	List<IngredientsItem> findByRestaurantId(Long id);
	@Query("SELECT e FROM IngredientsItem e "
			+ "WHERE e.restaurant.id = :restaurantId "
			+ "AND lower(e.nome) = lower(:nome)"
			+ "AND e.category.nome = :categoryName")
	
	public IngredientsItem findByRestaurantIdAndNameIngoreCase(
			@Param("restaurantId") Long restaurantId, 
			@Param("name") String name,
			@Param("categoryName") String categoryName);
}
