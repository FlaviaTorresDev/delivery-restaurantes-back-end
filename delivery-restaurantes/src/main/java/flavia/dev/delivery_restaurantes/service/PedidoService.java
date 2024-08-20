package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import com.stripe.exception.StripeException;

import flavia.dev.delivery_restaurantes.exception.CarrinhoException;
import flavia.dev.delivery_restaurantes.exception.PedidoException;
import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.exception.UserException;
import flavia.dev.delivery_restaurantes.model.PagamentoResponse;
import flavia.dev.delivery_restaurantes.model.Pedido;
import flavia.dev.delivery_restaurantes.model.User;
import flavia.dev.delivery_restaurantes.request.CriarPedidoRequest;



public interface PedidoService {
	
	 public PagamentoResponse createPedido(CriarPedidoRequest order, User user) throws UserException, RestaurantException, CarrinhoException, StripeException;
	 
	 public Pedido updatePedido(Long pedidoId, String pedidoStatus) throws PedidoException;
	 
	 public void cancelarPedido(Long pedidoId) throws PedidoException;
	 
	 public List<Pedido> getUserPedidos(Long userId) throws PedidoException;
	 
	 public List<Pedido> getPedidosOfRestaurant(Long restaurantId,String orderStatus) throws PedidoException, RestaurantException;
	 

}