package flavia.dev.delivery_restaurantes.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.delivery_restaurantes.model.PedidoItem;
import flavia.dev.delivery_restaurantes.repository.PedidoItemRepository;
import flavia.dev.delivery_restaurantes.service.PedidoItemService;

;
@Service
public class PedidoItemServiceImpl implements PedidoItemService {
	@Autowired
	 private PedidoItemRepository pedidoItemRepository;

	    @Override
	    public PedidoItem createPedidoIem(PedidoItem pedidoItem) {
	    	
	    	PedidoItem newPedidoItem=new PedidoItem();
//	    	newOrderItem.setMenuItem(orderItem.getMenuItem());
//	    	newOrderItem.setOrder(orderItem.getOrder());
	    	newPedidoItem.setQuantidade(pedidoItem.getQuantidade());
	        return pedidoItemRepository.save(newPedidoItem);
	    }
}