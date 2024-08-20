package flavia.dev.delivery_restaurantes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.IngredientCategoria;
import flavia.dev.delivery_restaurantes.model.IngredientsItem;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.repository.IngredientsCategoriaRepository;
import flavia.dev.delivery_restaurantes.repository.IngredientsItemRepository;
import flavia.dev.delivery_restaurantes.service.IngredientsService;
import flavia.dev.delivery_restaurantes.service.RestaurantService;


@Service
public class IngredientsServiceImpl implements IngredientsService {

	@Autowired
	private IngredientsCategoriaRepository ingredientsCategoriaRepo;
	
	@Autowired
	private IngredientsItemRepository ingredientsItemRepository;
	
	
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Override
	public IngredientCategoria criarIngredientsCategoria(
			String nome,Long restaurantId) throws RestaurantException {
		
		IngredientCategoria isExist=ingredientsCategoriaRepo
				.findByRestaurantIdAndNameIgnoreCase(restaurantId,nome);
		
		if(isExist!=null) {
			return isExist;
		}

		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
		
		IngredientCategoria ingredientCategoria=new IngredientCategoria();
		ingredientCategoria.setRestaurant(restaurant);
		ingredientCategoria.setNome(nome);
		
		IngredientCategoria CategoriaCriada= ingredientsCategoriaRepo.save(ingredientCategoria);
		
		return CategoriaCriada;
	}

	@Override
	public IngredientCategoria findIngredientsCategoriaById(Long id) throws Exception {
		Optional<IngredientCategoria> opt=ingredientsCategoriaRepo.findById(id);
		if(opt.isEmpty()){
			throw new Exception("ingredient category not found");
		}
		return opt.get();
	}

	@Override
	public List<IngredientCategoria> findIngredientsCategoriaByRestaurantId(Long id) throws Exception {
		return ingredientsCategoriaRepo.findByRestaurantId(id);
	}

	@Override
	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {

		return ingredientsItemRepository.findByRestaurantId(restaurantId);
	}
	

	@Override
	public IngredientsItem criarIngredientsItem(Long restaurantId, 
			String ingredientNome, Long ingredientCategoriaId) throws Exception {
		
		IngredientCategoria categoria = findIngredientsCategoriaById(ingredientCategoriaId);
		
		IngredientsItem isExist = ingredientsItemRepository.
				findByRestaurantIdAndNameIngoreCase(restaurantId, ingredientNome,categoria.getNome());
		if(isExist!=null) {
			System.out.println("is exists-------- item");
			return isExist;
		}
		
		Restaurant restaurant=restaurantService.findRestaurantById(
				restaurantId);
		IngredientsItem item=new IngredientsItem();
		item.setNome(ingredientNome);
		item.setRestaurant(restaurant);
		item.setCategoria(categoria);
		
		IngredientsItem IngredientsSalvos = ingredientsItemRepository.save(item);
		categoria.getIngredients().add(IngredientsSalvos);

		return IngredientsSalvos;
	}


	@Override
	public IngredientsItem updateStoke(Long id) throws Exception {
		Optional<IngredientsItem> item=ingredientsItemRepository.findById(id);
		if(item.isEmpty()) {
			throw new Exception("ingredient not found with id "+item);
		}
		IngredientsItem ingredient=item.get();
		ingredient.setInStoke(!ingredient.isInStoke());
		return ingredientsItemRepository.save(ingredient);
	}

	

	

}
