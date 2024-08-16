package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import flavia.dev.delivery_restaurantes.model.Categoria;
import flavia.dev.delivery_restaurantes.service.exception.RestaurantException;


public interface CategoriaService {
	
	public Categoria createCategoria (String nome,Long userId) throws RestaurantException;
	public List<Categoria> findCategoriaByRestaurantId(Long restaurantId) throws RestaurantException;
	public Categoria findCategoriaById(Long id) throws RestaurantException;

}