package flavia.dev.delivery_restaurantes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import flavia.dev.delivery_restaurantes.exception.ComidaException;
import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.exception.UserException;
import flavia.dev.delivery_restaurantes.model.Comida;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.model.User;
import flavia.dev.delivery_restaurantes.request.CriarComidaRequest;
import flavia.dev.delivery_restaurantes.service.CategoriaService;
import flavia.dev.delivery_restaurantes.service.ComidaService;
import flavia.dev.delivery_restaurantes.service.RestaurantService;
import flavia.dev.delivery_restaurantes.service.UserService;



@RestController
@RequestMapping("/api/admin/food")
public class AdminMenuItemController {
	
	@Autowired
	private ComidaService comidaService;
	@Autowired
	private UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private CategoriaService categoriaService;

	@PostMapping()
	public ResponseEntity<Comida> createItem(
			@RequestBody CriarComidaRequest item, 
			@RequestHeader("Authorization") String jwt)
			throws ComidaException, UserException, RestaurantException {
		System.out.println("req-controller ----"+item);
		User user = userService.findUserPerfilByJwt(jwt);
//		Category category=categoryService.findCategoryById(item.getCategoryId());
		Restaurant restaurant=restaurantService.findRestaurantById(item.getRestaurantId());
			Comida comidaItem = comidaService.criarComida(item,item.getCategoria(),restaurant);
			return ResponseEntity.ok(comidaItem);

	}


	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws UserException, ComidaException {
		User user = userService.findUserPerfilByJwt(jwt);
		
			comidaService.apagarComida(id);
			return ResponseEntity.ok("Menu item deleted");
		
	
	}

	

	@GetMapping("/search")
	public ResponseEntity<List<Comida>> getMenuItemByName(@RequestParam String name)  {
		List<Comida> menuItem = comidaService.encontrarComida(name);
		return ResponseEntity.ok(menuItem);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Comida> updateAviliacaoStatus(
			@PathVariable Long id) throws ComidaException {
		Comida menuItems= comidaService.updateAvailibilityStatus(id);
		return ResponseEntity.ok(menuItems);
	}
	
	

}
