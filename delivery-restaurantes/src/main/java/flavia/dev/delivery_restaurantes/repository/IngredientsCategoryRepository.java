package flavia.dev.delivery_restaurantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import flavia.dev.delivery_restaurantes.model.IngredientCategoria;




public interface IngredientsCategoryRepository extends JpaRepository<IngredientCategoria, Long>{
	
	
//	List<IngredientCategory> findByFoodId(Long menuItemId);
	List<IngredientCategoria> findByRestaurantId(Long id);

	@Query("SELECT e FROM IngredientCategory e "
			+ "WHERE e.restaurant.id = :restaurantId "
			+ "AND lower(e.name) = lower(:name)")
	IngredientCategoria findByRestaurantIdAndNameIgnoreCase(
			@Param("restaurantId") Long restaurantId, @Param("name") String name);
}
