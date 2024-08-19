package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import flavia.dev.delivery_restaurantes.dto.RestaurantDto;
import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.model.User;
import flavia.dev.delivery_restaurantes.request.CriarRestaurantRequest;




public interface RestaurantService {

	public Restaurant createRestaurant(CriarRestaurantRequest req,User user);

	public Restaurant updateRestaurant(Long restaurantId, CriarRestaurantRequest updatedRestaurant)
			throws RestaurantException;

	public void deleteRestaurant(Long restaurantId) throws RestaurantException;

	public List<Restaurant>getAllRestaurant();

	public List<Restaurant>searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws RestaurantException;

	public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException;
	
	public RestaurantDto addToFavorites(Long restaurantId,User user) throws RestaurantException;

	public Restaurant updateRestaurantStatus(Long id)throws RestaurantException;
}
