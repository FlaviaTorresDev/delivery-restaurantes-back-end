package flavia.dev.delivery_restaurantes.service;

import java.util.List;

import flavia.dev.delivery_restaurantes.model.Notificacao;
import flavia.dev.delivery_restaurantes.model.Pedido;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.model.User;



public interface NotificacaoService {
	
	public Notificacao enviarPedidoStatusNotificacao(Pedido pedido);
	public void enviarRestaurantNotificacao(Restaurant restaurant, String messagem);
	public void enviarPromocaoNotificacao(User user, String messagem);
	
	public List<Notificacao> findUsersNotification(Long userId);

}

