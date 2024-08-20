package flavia.dev.delivery_restaurantes.service;


import java.util.List;

import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.IngredientCategoria;
import flavia.dev.delivery_restaurantes.model.IngredientsItem;



public interface IngredientsService {
	
	public IngredientCategoria criarIngredientsCategoria(
			String nome,Long restaurantId) throws RestaurantException;

	public IngredientCategoria findIngredientsCategoriaById(Long id) throws Exception;

	public List<IngredientCategoria> findIngredientsCategoriaByRestaurantId(Long id) throws Exception;
	
	public List<IngredientsItem> findRestaurantsIngredients(
			Long restaurantId);

	
	public IngredientsItem criarIngredientsItem(Long restaurantId, 
			String ingredientName,Long ingredientCategoryId) throws Exception;

	public IngredientsItem updateStoke(Long id) throws Exception;
	
}