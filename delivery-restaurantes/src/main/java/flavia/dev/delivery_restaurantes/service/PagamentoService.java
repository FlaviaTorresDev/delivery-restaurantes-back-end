package flavia.dev.delivery_restaurantes.service;

import com.stripe.exception.StripeException;

import flavia.dev.delivery_restaurantes.model.PagamentoResponse;
import flavia.dev.delivery_restaurantes.model.Pedido;


public interface PagamentoService {
	
	public PagamentoResponse generatePagamentoLink(Pedido pedido) throws StripeException;

}