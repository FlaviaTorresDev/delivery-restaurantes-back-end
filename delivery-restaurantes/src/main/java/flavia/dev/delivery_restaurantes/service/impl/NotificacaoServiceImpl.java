package flavia.dev.delivery_restaurantes.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.delivery_restaurantes.model.Notificacao;
import flavia.dev.delivery_restaurantes.model.Pedido;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.model.User;
import flavia.dev.delivery_restaurantes.repository.NotificacaoRepository;
import flavia.dev.delivery_restaurantes.service.NotificacaoService;


@Service
public class NotificacaoServiceImpl implements NotificacaoService {

	@Autowired
	private NotificacaoRepository notificationRepository;
	
	@Override
	public Notificacao enviarPedidoStatusNotificacao(Pedido pedido) {
		Notificacao notificacao = new Notificacao();
		notificacao.setMensagem("your order is "+pedido.getStatusPedido() + " order id is - "+pedido.getId());
		notificacao.setCliente(pedido.getCliente());
		notificacao.setEnvioEm(new Date());
		
		return notificationRepository.save(notificacao);
	}

	@Override
	public void enviarRestaurantNotificacao(Restaurant restaurant, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enviarPromocaoNotificacao(User user, String mensagem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Notificacao> findUsersNotification(Long userId) {
		// TODO Auto-generated method stub
		return notificationRepository.findByClienteId(userId);
	}

}
