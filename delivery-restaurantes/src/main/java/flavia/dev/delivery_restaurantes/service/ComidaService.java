package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import flavia.dev.delivery_restaurantes.model.Categoria;
import flavia.dev.delivery_restaurantes.model.Comida;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.service.exception.ComidaException;
import flavia.dev.delivery_restaurantes.service.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.service.request.CreateComidaRequest;

;

public interface ComidaService {

	public Comida createFood(CreateComidaRequest req,Categoria categoria,
						   Restaurant restaurant) throws ComidaException, RestaurantException;

	void deleteComida(Long ComidaId) throws ComidaException;
	
	public List<Comida> getRestaurantsComida(Long restaurantId,
			boolean isVegetarian, boolean isNonveg, boolean isSeasonal,String ComidaCategory) throws ComidaException;
	
	public List<Comida> encontrarComida(String keyword);
	
	public Comida findComidaById(Long comidaId) throws ComidaException;

	public Comida updateAvailibilityStatus(Long comidad) throws ComidaException;
}
