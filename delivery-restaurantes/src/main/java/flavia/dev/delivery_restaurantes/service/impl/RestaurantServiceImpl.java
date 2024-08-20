package flavia.dev.delivery_restaurantes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.delivery_restaurantes.dto.RestaurantDto;
import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.Endereco;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.model.User;
import flavia.dev.delivery_restaurantes.repository.RestaurantRepository;
import flavia.dev.delivery_restaurantes.repository.UserRepository;
import flavia.dev.delivery_restaurantes.repository.enderecoRepository;
import flavia.dev.delivery_restaurantes.request.CriarRestaurantRequest;
import flavia.dev.delivery_restaurantes.service.RestaurantService;
import flavia.dev.delivery_restaurantes.service.UserService;


@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private enderecoRepository enderecoRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public Restaurant createRestaurant(CriarRestaurantRequest req,User user) {
		Endereco endereco=new Endereco();
		endereco.setCidade(req.getEndereco().getCidade());
		endereco.setPais(req.getEndereco().getPais());
		endereco.setNumero(req.getEndereco().getNumero());
		endereco.setCep(req.getEndereco().getCep());
		endereco.setEstado(req.getEndereco().getEstado());
		endereco.setRua(req.getEndereco().getRua());
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		
		Restaurant restaurant = new Restaurant();
		
		restaurant.setEndereco(enderecoSalvo);
		restaurant.setInformacaoContato(req.getInformacaoContato());
		restaurant.setCozinhaTipo(req.getTipoCozinha());
		restaurant.setDescricao(req.getDescricao());
		restaurant.setImagens(req.getImagens());
		restaurant.setNome(req.getNome());
		restaurant.setHorarioAbertura(req.getHorarioAbertura());
		restaurant.setDataRegistro(req.getDataRegistro());
		restaurant.setProprietário(user);
		Restaurant savedRestaurant = restaurantRepository.save(restaurant);

		return savedRestaurant;
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CriarRestaurantRequest updatedReq)
			throws RestaurantException {
		Restaurant restaurant = findRestaurantById(restaurantId);
		if (restaurant.getCozinhaTipo() != null) {
			restaurant.setCozinhaTipo(updatedReq.getTipoCozinha());
		}
		if (restaurant.getDescricao() != null) {
			restaurant.setDescricao(updatedReq.getDescricao());
		}
		return restaurantRepository.save(restaurant);
	}
	
	@Override
	public Restaurant findRestaurantById(Long restaurantId) throws RestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		if (restaurant.isPresent()) {
			return restaurant.get();
		} else {
			throw new RestaurantException("Restaurant with id " + restaurantId + "not found");
		}
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws RestaurantException {
		Restaurant restaurant = findRestaurantById(restaurantId);
		if (restaurant != null) {
			restaurantRepository.delete(restaurant);
			return;
		}
		throw new RestaurantException("Restaurant with id " + restaurantId + " Not found");

	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		return restaurantRepository.findAll();
	}


	@Override
	public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException {
		Restaurant restaurants=restaurantRepository.listarByIdProprietario(userId);
		return restaurants;
	}



	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		return restaurantRepository.listar(keyword);
	}

	@Override
	public RestaurantDto addToFavorites(Long restaurantId,User user) throws RestaurantException {
		Restaurant restaurant=findRestaurantById(restaurantId);
		
		RestaurantDto dto=new RestaurantDto();
		dto.setTitulo(restaurant.getNome());
		dto.setImages(restaurant.getImagens());
		dto.setId(restaurant.getId());
		dto.setDescricao(restaurant.getDescricao());

		boolean isFavorited = false;
		List<RestaurantDto> favorites = user.getFavorites();
		for (RestaurantDto favorite : favorites) {
			if (favorite.getId().equals(restaurantId)) {
				isFavorited = true;
				break;
			}
		}

		if (isFavorited) {
			favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		} else {
			favorites.add(dto);
		}
		
		User updatedUser = userRepository.save(user);
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws RestaurantException {
		Restaurant restaurant=findRestaurantById(id);
		restaurant.setAberto(!restaurant.isAberto());
		return restaurantRepository.save(restaurant);
	}

}
