package flavia.dev.delivery_restaurantes.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.delivery_restaurantes.exception.CarrinhoException;
import flavia.dev.delivery_restaurantes.exception.CarrinhoItemException;
import flavia.dev.delivery_restaurantes.exception.ComidaException;
import flavia.dev.delivery_restaurantes.exception.UserException;
import flavia.dev.delivery_restaurantes.model.Carrinho;
import flavia.dev.delivery_restaurantes.model.CarrinhoItem;
import flavia.dev.delivery_restaurantes.model.Comida;
import flavia.dev.delivery_restaurantes.model.User;
import flavia.dev.delivery_restaurantes.repository.CarrinhoItemRepository;
import flavia.dev.delivery_restaurantes.repository.CarrinhoRepository;
import flavia.dev.delivery_restaurantes.repository.ComidaRepository;
import flavia.dev.delivery_restaurantes.service.CarrinhoSerive;
import flavia.dev.delivery_restaurantes.service.UserService;
import flavia.dev.delivery_restaurantes.service.request.AddCarrinhoItemRequest;



@Service
public class CarrinhoServiceImplementation implements CarrinhoSerive {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CarrinhoItemRepository carrinhoItemRepository;
	@Autowired
	private ComidaRepository menuItemRepository;

	@Override
	public CarrinhoItem addItemToCarrinho(AddCarrinhoItemRequest req, String jwt) throws UserException, ComidaException, CarrinhoException, CarrinhoItemException {

		User user = userService.findUserProfileByJwt(jwt);
		
		Optional<Comida> menuItem=menuItemRepository.findById(req.getMenuItemId());
		if(menuItem.isEmpty()) {
			throw new ComidaException("Menu Item not exist with id "+req.getMenuItemId());
		}

		Carrinho carrinho = findCarrinhoByUserId(user.getId());

		for (CarrinhoItem carrinhoItem : carrinho.getItems()) {
			if (carrinhoItem.getComida().equals(menuItem.get())) {

				int newQuantity = carrinhoItem.getQuantidade() + req.getQuantidade();
				return updateCarrinhoItemQuantidade(carrinhoItem.getId(),newQuantity);
			}
		}

		CarrinhoItem newCarrinhoItem = new CarrinhoItem();
		newCarrinhoItem.setComida(menuItem.get());
		newCarrinhoItem.setQuantidade(req.getQuantidade());
		newCarrinhoItem.setCarrinho(carrinho);
		newCarrinhoItem.setIngredients(req.getIngredients());
		newCarrinhoItem.setTotalValor(req.getQuantidade()*menuItem.get().getValor());
		
		CarrinhoItem savedItem=carrinhoItemRepository.save(newCarrinhoItem);
		carrinho.getItems().add(savedItem);
		carrinhoRepository.save(carrinho);
		
		return savedItem;

	}

	@Override
	public CarrinhoItem updateCarrinhoItemQuantity(Long cartItemId,int quantity) throws CartItemException {
		Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
		if(cartItem.isEmpty()) {
			throw new CartItemException("cart item not exist with id "+cartItemId);
		}
		cartItem.get().setQuantity(quantity);
		cartItem.get().setTotalPrice((cartItem.get().getFood().getPrice()*quantity));
		return cartItemRepository.save(cartItem.get());
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, 
	CartException, CartItemException {

		User user = userService.findUserProfileByJwt(jwt);

		Cart cart = findCartByUserId(user.getId());
		
		Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
		
		if(cartItem.isEmpty()) {
			throw new CartItemException("cart item not exist with id "+cartItemId);
		}

		cart.getItems().remove(cartItem.get());
		return cartRepository.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws UserException {

		Long total = 0L;
		for (CartItem cartItem : cart.getItems()) {
			total += cartItem.getFood().getPrice() * cartItem.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws CartException {
		Optional<Cart> cart = cartRepository.findById(id);
		if(cart.isPresent()) {
			return cart.get();
		}
		throw new CartException("Cart not found with the id "+id);
	}

	@Override
	public Cart findCartByUserId(Long userId) throws CartException, UserException {
	
		Optional<Cart> opt=cartRepository.findByCustomer_Id(userId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartException("cart not found");
		
	}

	@Override
	public Cart clearCart(Long userId) throws CartException, UserException {
		Cart cart=findCartByUserId(userId);
		
		cart.getItems().clear();
		return cartRepository.save(cart);
	}

	

}
