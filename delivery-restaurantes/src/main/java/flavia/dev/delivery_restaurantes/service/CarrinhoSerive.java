package flavia.dev.delivery_restaurantes.service;

import flavia.dev.delivery_restaurantes.exception.CarrinhoException;
import flavia.dev.delivery_restaurantes.exception.CarrinhoItemException;
import flavia.dev.delivery_restaurantes.exception.ComidaException;
import flavia.dev.delivery_restaurantes.exception.UserException;
import flavia.dev.delivery_restaurantes.model.Carrinho;
import flavia.dev.delivery_restaurantes.model.CarrinhoItem;
import flavia.dev.delivery_restaurantes.request.AddCarrinhoItemRequest;


public interface CarrinhoSerive {

	public CarrinhoItem addItemToCarrinho(AddCarrinhoItemRequest req, String jwt) throws UserException, ComidaException, CarrinhoException, CarrinhoItemException;

	public CarrinhoItem updateCarrinhoItemQuantidade(Long carrinhoItemId, int quantidade) throws CarrinhoItemException;

	public Carrinho removeItemFromCarrinho(Long CarrinhoItemId, String jwt) throws UserException, CarrinhoException, CarrinhoItemException;

	public Long calcularTotalCarrinho(Carrinho carrinho) throws UserException;
	
	public Carrinho findCarrinhoById(Long id) throws CarrinhoException;
	
	public Carrinho findCarrinhoByUserId(Long userId) throws CarrinhoException, UserException;
	
	public Carrinho limparCarrinho(Long userId) throws CarrinhoException, UserException;
	

	

}
