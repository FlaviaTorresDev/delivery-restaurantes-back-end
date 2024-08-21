package flavia.dev.delivery_restaurantes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import flavia.dev.delivery_restaurantes.exception.PedidoException;
import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.Pedido;
import flavia.dev.delivery_restaurantes.service.PedidoService;
import flavia.dev.delivery_restaurantes.service.UserService;


@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private UserService userService;
	
	
    @DeleteMapping("/Pedido/{pedidoId}")
    public ResponseEntity<String> deletePedido(@PathVariable Long pedidoId) throws PedidoException{
    	if(pedidoId!=null) {
    		pedidoService.cancelarPedido(pedidoId);
    	return ResponseEntity.ok("Order deleted with id)"+pedidoId);
    }else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST) ;
    }
    
    
    @GetMapping("/pedido/restaurant/{restaurantId}")
    public ResponseEntity<List<Pedido>> getAllRestaurantPedidos(
    		@PathVariable Long restaurantId,
    		@RequestParam(required = false) String pedido_status) throws PedidoException, RestaurantException{
    	
    		List<Pedido> pedidos = pedidoService.getPedidosOfRestaurant(restaurantId, pedido_status);
    				
    		
//    		System.out.println("ORDER STATUS----- "+orderStatus);
    		return ResponseEntity.ok(pedidos);
    		
    	
    	
    }
    
    @PutMapping("/pedidos/{pedidoId}/{pedidoStatus}")
    public ResponseEntity<Pedido> updateOrders(@PathVariable Long pedidoId,@PathVariable String pedidoStatus) throws PedidoException, RestaurantException{
    	
    	Pedido pedidos = pedidoService.updatePedido(pedidoId, pedidoStatus);
    		return ResponseEntity.ok(pedidos);
    		
    }

}
