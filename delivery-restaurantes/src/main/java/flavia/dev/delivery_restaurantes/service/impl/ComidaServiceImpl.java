package flavia.dev.delivery_restaurantes.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.delivery_restaurantes.exception.ComidaException;
import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.Categoria;
import flavia.dev.delivery_restaurantes.model.Comida;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.repository.ComidaRepository;
import flavia.dev.delivery_restaurantes.repository.IngredientsCategoriaRepository;
import flavia.dev.delivery_restaurantes.request.CriarComidaRequest;
import flavia.dev.delivery_restaurantes.service.ComidaService;
import flavia.dev.delivery_restaurantes.service.IngredientsService;




@Service
public class ComidaServiceImpl implements ComidaService {
	@Autowired
	private ComidaRepository comidaRepository;
	

	
//	@Autowired
//	private RestaurantRepository restaurantRepository;


	
	@Autowired
	private IngredientsService ingredientService;
	
	@Autowired
	private IngredientsCategoriaRepository ingredientCategoroaRepo;

	@Override
	public Comida criarComida(CriarComidaRequest  req,
						   Categoria categoria,
						   Restaurant restaurant)
			throws ComidaException,
	RestaurantException {

			Comida comida=new Comida();
			comida.setComidaCategoria(categoria);
			comida.setCreationDate(new Date());
			comida.setDescricao(req.getDescricao());
			comida.setImagens(req.getImagens());
			comida.setNome(req.getNome());
			comida.setValor((long) req.getValor());
			comida.setSazonal(req.isSazonal());		
			comida.setVegetariano(req.isVegetariano());
			comida.setIngredients(req.getIngredients());
			comida.setRestaurant(restaurant);
			comida = comidaRepository.save(comida);

			restaurant.getComidas().add(comida);
			return comida;
		
	}

	@Override
	public void apagarComida(Long comidaId) throws ComidaException {
		Comida comida=findComidaById(comidaId);
		comida.setRestaurant(null);;
		comidaRepository.delete(comida);

	}


	@Override
	public List<Comida> getRestaurantsComida(
			Long restaurantId, 
			boolean isVegetariano, 
			boolean isNonveg,
			boolean isSazonal,
			String comidaCategoria) throws ComidaException {
		List<Comida> comidas = comidaRepository.findByRestaurantId(restaurantId);
		
		
		
	    if (isVegetariano) {
	        comidas = filterByVegetariano(comidas, isVegetariano);
	    }
	    if (isNonveg) {
	        comidas = filterByNonveg(comidas, isNonveg);
	    }

	    if (isSazonal) {
	        comidas = filterBySazonal(comidas, isSazonal);
	    }
	    if(comidaCategoria!=null && !comidaCategoria.equals("")) {
	    	comidas = filterByComidaCategoria(comidas, comidaCategoria);
	    }
		
		return comidas;
		
	}
	
	private List<Comida> filterByVegetariano(List<Comida> comidas, boolean isVegetarian) {
	    return comidas.stream()
	            .filter(comida -> comida.isVegetariano() == isVegetarian)
	            .collect(Collectors.toList());
	}
	private List<Comida> filterByNonveg(List<Comida> comidas, boolean isNonveg) {
	    return comidas.stream()
	            .filter(comida -> comida.isVegetariano() == false)
	            .collect(Collectors.toList());
	}
	private List<Comida> filterBySazonal(List<Comida> comidas, boolean isSeasonal) {
	    return comidas.stream()
	            .filter(comida -> comida.isSazonal() == isSeasonal)
	            .collect(Collectors.toList());
	}
	private List<Comida> filterByComidaCategoria(List<Comida> comidas, String comidaCategoria) {
	    
		return comidas.stream()
			    .filter(comida -> {
			        if (comida.getComidaCategoria() != null) {
			            return comida.getComidaCategoria().getNome().equals(comidaCategoria);
			        }
			        return false; // Return true if food category is null
			    })
			    .collect(Collectors.toList());
	}

	@Override
	public List<Comida> encontrarComida(String keyword) {
		List<Comida> items=new ArrayList<>();
		
		if(keyword!="") {
			System.out.println("keyword -- "+keyword);
			items=comidaRepository.pesquisarByNomeCategoria(keyword);
		}
		
		return items;
	}

	@Override
	public Comida updateAvailibilityStatus(Long id) throws ComidaException {
		Comida comida = findComidaById(id);
		
		comida.setAvaliacoes(!comida.isAvaliacoes());
		comidaRepository.save(comida);
		return comida;
	}

	@Override
	public Comida findComidaById(Long comidaId) throws ComidaException {
		Optional<Comida> comida = comidaRepository.findById(comidaId);
		if (comida.isPresent()) {
			return comida.get();
		}
		throw new ComidaException("food with id" + comidaId + "not found");
	}

}
