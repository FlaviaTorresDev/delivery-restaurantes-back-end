package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import flavia.dev.delivery_restaurantes.model.Eventos;
import flavia.dev.delivery_restaurantes.service.exception.RestaurantException;



public interface EventosService {
	
	public Eventos createEventos(Eventos evento,Long restaurantId) throws RestaurantException;
	
	public List<Eventos> findAllEvento();
	
	public List<Eventos> findRestaurantsEvento(Long id);
	
	public void deleteEvento(Long id) throws Exception;
	
	public Eventos findById(Long id) throws Exception;

}
