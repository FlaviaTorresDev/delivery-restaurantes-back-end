package flavia.dev.delivery_restaurantes.service.impl;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import flavia.dev.delivery_restaurantes.model.PagamentoResponse;
import flavia.dev.delivery_restaurantes.model.Pedido;
import flavia.dev.delivery_restaurantes.service.PagamentoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PagamentoServiceImpl implements PagamentoService{
	
	
	@Value("${stripe.api.key}")
	 private String stripeSecretKey;

	@Override
	public PagamentoResponse generatePagamentoLink(Pedido pedido) throws StripeException {

	  Stripe.apiKey = stripeSecretKey;

	        SessionCreateParams params = SessionCreateParams.builder()
	                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
	                .setMode(SessionCreateParams.Mode.PAYMENT)
	                .setSuccessUrl("https://zosh-food.vercel.app/payment/success/"+pedido.getId())
	                .setCancelUrl("https://zosh-food.vercel.app/cancel")
	                .addLineItem(SessionCreateParams.LineItem.builder()
	                        .setQuantity(1L)
	                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
	                                .setCurrency("usd")
	                                .setUnitAmount((long) pedido.getMontanteTotal()*100) // Specify the order amount in cents
	                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
	                                        .setName("pizza burger")
	                                        .build())
	                                .build())
	                        .build())
	                .build();
	        
	        Session session = Session.create(params);
	        
	        System.out.println("session _____ " + session);
	        
	        PagamentoResponse res = new PagamentoResponse();
	        res.setPagamento_url(session.getUrl());
	        
	        return res;
	    
	}

}
