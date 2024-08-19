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
import flavia.dev.delivery_restaurantes.request.AddCarrinhoItemRequest;
import flavia.dev.delivery_restaurantes.service.CarrinhoSerive;
import flavia.dev.delivery_restaurantes.service.UserService;




@Service
public class CarrinhoServiceImplementation implements CarrinhoSerive {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CarrinhoItemRepository carrinhoItemRepository;
	@Autowired
	private ComidaRepository comidaRepository;

	@Override
	public CarrinhoItem addItemToCarrinho(AddCarrinhoItemRequest req, String jwt) throws UserException, ComidaException, CarrinhoException, CarrinhoItemException {

		User user = userService.findUserPerfilByJwt(jwt);
		
		Optional<Comida> menuItem=comidaRepository.findById(req.getMenuItemId());
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
		newCarrinhoItem.setValorTotal(req.getQuantidade()*menuItem.get().getValor());
		
		CarrinhoItem savedItem=carrinhoItemRepository.save(newCarrinhoItem);
		carrinho.getItems().add(savedItem);
		carrinhoRepository.save(carrinho);
		
		return savedItem;

	}

	@Override
	public CarrinhoItem updateCarrinhoItemQuantidade(Long carrinhoItemId,int quantidade) throws CarrinhoItemException {
		Optional<CarrinhoItem> carrinhoItem= carrinhoItemRepository.findById(carrinhoItemId);
		if(carrinhoItem.isEmpty()) {
			throw new CarrinhoItemException("cart item not exist with id "+ carrinhoItemId);
		}
		carrinhoItem.get().setQuantidade(quantidade);
		carrinhoItem.get().setValorTotal((carrinhoItem.get().getComida().getValor()*quantidade));
		return carrinhoItemRepository.save(carrinhoItem.get());
	}

	@Override
	public Carrinho removeItemFromCarrinho(Long carrinhoItemId, String jwt) throws UserException, 
	CarrinhoException, CarrinhoItemException {

		User user = userService.findUserPerfilByJwt(jwt);

		Carrinho carrinho = findCarrinhoByUserId(user.getId());
		
		Optional<CarrinhoItem> carrinhoItem= carrinhoItemRepository.findById(carrinhoItemId);
		
		if(carrinhoItem.isEmpty()) {
			throw new CarrinhoItemException("cart item not exist with id "+carrinhoItemId);
		}

		carrinho.getItems().remove(carrinhoItem.get());
		return carrinhoRepository.save(carrinho);
	}

	@Override
	public Long calcularTotalCarrinho(Carrinho carrinho) throws UserException {

		Long total = 0L;
		for (CarrinhoItem carrinhoItem : carrinho.getItems()) {
			total += carrinhoItem.getComida().getValor() * carrinhoItem.getQuantidade();
		}
		return total;
	}

	@Override
	public Carrinho findCarrinhoById(Long id) throws CarrinhoException {
		Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
		if(carrinho.isPresent()) {
			return carrinho.get();
		}
		throw new CarrinhoException("Cart not found with the id "+id);
	}

	@Override
	public Carrinho findCarrinhoByUserId(Long userId) throws CarrinhoException, UserException {
	
		Optional<Carrinho> opt=carrinhoRepository.findByCliente_Id(userId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CarrinhoException("cart not found");
		
	}

	@Override
	public Carrinho limparCarrinho(Long userId) throws CarrinhoException, UserException {
		Carrinho carrinho=findCarrinhoByUserId(userId);
		
		carrinho.getItems().clear();
		return carrinhoRepository.save(carrinho);
	}

	

}
