package flavia.dev.delivery_restaurantes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.Categoria;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.repository.CategoriaRepository;
import flavia.dev.delivery_restaurantes.service.CategoriaService;
import flavia.dev.delivery_restaurantes.service.RestaurantService;



@Service
public class CategoriaServiceImplementation implements CategoriaService {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Categoria criarCategoria(String nome,Long userId) throws RestaurantException {
		Restaurant restaurant=restaurantService.getRestaurantsByUserId(userId);
		Categoria criarCategoria=new Categoria();
		
		criarCategoria.setNome(nome);
		criarCategoria.setRestaurant(restaurant);
		return categoriaRepository.save(criarCategoria);
	}

	@Override
	public List<Categoria> findCategoriaByRestaurantId(Long id) throws RestaurantException {
		Restaurant restaurant  =restaurantService.findRestaurantById(id);
		return categoriaRepository.findByRestaurantId(id);
	}

	@Override
	public Categoria findCategoriaById(Long id) throws RestaurantException {
		Optional<Categoria> opt=categoriaRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new RestaurantException("category not exist with id "+id);
		}
		
		return opt.get();
	}

}
