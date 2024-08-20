package flavia.dev.delivery_restaurantes.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;

import flavia.dev.delivery_restaurantes.exception.CarrinhoException;
import flavia.dev.delivery_restaurantes.exception.PedidoException;
import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.exception.UserException;
import flavia.dev.delivery_restaurantes.model.Carrinho;
import flavia.dev.delivery_restaurantes.model.CarrinhoItem;
import flavia.dev.delivery_restaurantes.model.Endereco;
import flavia.dev.delivery_restaurantes.model.Notificacao;
import flavia.dev.delivery_restaurantes.model.PagamentoResponse;
import flavia.dev.delivery_restaurantes.model.Pedido;
import flavia.dev.delivery_restaurantes.model.PedidoItem;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.model.User;
import flavia.dev.delivery_restaurantes.repository.PedidoItemRepository;
import flavia.dev.delivery_restaurantes.repository.PedidoRepository;
import flavia.dev.delivery_restaurantes.repository.RestaurantRepository;
import flavia.dev.delivery_restaurantes.repository.UserRepository;
import flavia.dev.delivery_restaurantes.repository.enderecoRepository;
import flavia.dev.delivery_restaurantes.request.CriarPedidoRequest;
import flavia.dev.delivery_restaurantes.service.CarrinhoSerive;
import flavia.dev.delivery_restaurantes.service.NotificacaoService;
import flavia.dev.delivery_restaurantes.service.PagamentoService;
import flavia.dev.delivery_restaurantes.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	private enderecoRepository enderecoRepository;
	@Autowired
	private CarrinhoSerive carrinhoService;
	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@Autowired
	private NotificacaoService notificacaoService;
	

	

	@Override
	public PagamentoResponse createPedido(CriarPedidoRequest pedido,User user) throws UserException, RestaurantException, CarrinhoException, StripeException {
		
	    Endereco envioEndereco = pedido.getDeliveryEndereco();

	    
	    Endereco enderecoSalvo = enderecoRepository.save(envioEndereco);
	    
	    if(!user.getEnderecos().contains(enderecoSalvo)) {
	    	user.getEnderecos().add(enderecoSalvo);
	    }
	    
		
		System.out.println("user addresses --------------  "+user.getEnderecos());
		   
		 userRepository.save(user);
	    
	    Optional<Restaurant> restaurant = restaurantRepository.findById(pedido.getRestaurantId());
	    if(restaurant.isEmpty()) {
	    	throw new RestaurantException("Restaurant not found with id "+pedido.getRestaurantId());
	    }
	    
	    Pedido createdPedido = new Pedido();
	    
	    createdPedido.setCliente(user);
	    createdPedido.setDeliveryEndereco(enderecoSalvo);
	    createdPedido.setCriadoEm(new Date());
	    createdPedido.setStatusPedido("PENDING");
	    createdPedido.setRestaurant(restaurant.get());

        Carrinho carrinho = carrinhoService.findCarrinhoByUserId(user.getId());
        
	    List<PedidoItem> pedidoItems = new ArrayList<>();
	    
	    for (CarrinhoItem carrinhoItem : carrinho.getItems()) {
	        PedidoItem pedidoItem = new PedidoItem();
	        pedidoItem.setComida(carrinhoItem.getComida());
	        pedidoItem.setIngredientes(carrinhoItem.getIngredients());
	        pedidoItem.setQuantidade(carrinhoItem.getQuantidade());
	        pedidoItem.setTotalValor(carrinhoItem.getComida().getValor()* carrinhoItem.getQuantidade());

	        PedidoItem savedPedidoItem = pedidoItemRepository.save(pedidoItem);
	        pedidoItems.add(savedPedidoItem);
	    }
   
	     Long totalvalor = carrinhoService.calcularTotalCarrinho(carrinho);

	     createdPedido.setMontanteTotal(totalvalor);
	     createdPedido.setRestaurant(restaurant.get());
  
	     createdPedido.setItems(pedidoItems);
	    Pedido savedPedido = pedidoRepository.save(createdPedido);

	   restaurant.get().getOrders().add(savedPedido);
	   
	   restaurantRepository.save(restaurant.get());
	   

	   
	   PagamentoResponse res=pagamentoService.generatePagamentoLink(savedPedido);
	   return res;

	}

	@Override
	public void cancelarPedido(Long pedidoId) throws PedidoException {
           Pedido pedido = findPedidoById(pedidoId);
           if(pedido==null) {
        	   throw new PedidoException("Order not found with the id "+pedidoId);
           }
		
		    pedidoRepository.deleteById(pedidoId);
		
	}
	
	

	public Pedido findPedidoById(Long pedidoId) throws PedidoException {
		Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
		if(pedido.isPresent()) return pedido.get();
		
		throw new PedidoException("Order not found with the id "+pedidoId);
	}

	@Override
	public List<Pedido> getUserPedidos(Long userId) throws PedidoException {
		List<Pedido> pedido=pedidoRepository.listarAllPedidosUser(userId);
		return pedido;
	} 

	@Override
	public List<Pedido> getPedidosOfRestaurant(Long restaurantId,String pedidoStatus) throws PedidoException, RestaurantException {
		
			List<Pedido> pedidos = pedidoRepository.listarPedidosRestaurant(restaurantId);
			
			if(pedidoStatus!=null) {
				pedidos = pedidos.stream()
						.filter(pedido->pedido.getStatusPedido().equals(pedidoStatus))
						.collect(Collectors.toList());
			}
			
			return pedidos;
	}
//    private List<MenuItem> filterByVegetarian(List<MenuItem> menuItems, boolean isVegetarian) {
//    return menuItems.stream()
//            .filter(menuItem -> menuItem.isVegetarian() == isVegetarian)
//            .collect(Collectors.toList());
//}
	
	

	@Override
	public Pedido updatePedido(Long pedidoId, String statusPedido) throws PedidoException {
		Pedido pedido=findPedidoById(pedidoId);
		
		System.out.println("--------- "+statusPedido);
		
		if(statusPedido.equals("OUT_FOR_DELIVERY") || statusPedido.equals("DELIVERED") 
				|| statusPedido.equals("COMPLETED") || statusPedido.equals("PENDING")) {
			pedido.setStatusPedido(statusPedido);
			Notificacao notificacao=notificacaoService.enviarPedidoStatusNotificacao(pedido);
			return pedidoRepository.save(pedido);
		}
		else throw new PedidoException("Please Select A Valid Order Status");
		
		
	}
	
	

}
