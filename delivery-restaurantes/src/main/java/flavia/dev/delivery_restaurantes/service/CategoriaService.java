package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.Categoria;


public interface CategoriaService {
	
	public Categoria createCategoria (String nome,Long userId) throws RestaurantException;
	public List<Categoria> findCategoriaByRestaurantId(Long restaurantId) throws RestaurantException;
	public Categoria findCategoriaById(Long id) throws RestaurantException;

}