package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.Eventos;



public interface EventosService {
	
	public Eventos criarEventos(Eventos evento,Long restaurantId) throws RestaurantException;
	
	public List<Eventos> findAllEvento();
	
	public List<Eventos> findRestaurantsEvento(Long id);
	
	public void deleteEvento(Long id) throws Exception;
	
	public Eventos findById(Long id) throws Exception;

}
