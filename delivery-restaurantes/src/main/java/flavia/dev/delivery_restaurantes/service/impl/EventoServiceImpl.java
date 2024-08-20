package flavia.dev.delivery_restaurantes.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import flavia.dev.delivery_restaurantes.exception.RestaurantException;
import flavia.dev.delivery_restaurantes.model.Eventos;
import flavia.dev.delivery_restaurantes.model.Restaurant;
import flavia.dev.delivery_restaurantes.repository.EventoRepository;
import flavia.dev.delivery_restaurantes.service.EventosService;
import flavia.dev.delivery_restaurantes.service.RestaurantService;


@Service
public class EventoServiceImpl implements EventosService {
	
	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private RestaurantService restaurantService;
	
	@Override
	public Eventos criarEventos(Eventos evento,Long restaurantId) throws RestaurantException {
		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
		
		Eventos eventoCriado=new Eventos();
		eventoCriado.setRestaurant(restaurant);
		eventoCriado.setImagem(evento.getImagem());
		eventoCriado.setComecouEm(evento.getComecouEm());
		eventoCriado.setAcabouEm(evento.getAcabouEm());
		eventoCriado.setLocalizacao(evento.getLocalizacao());
		eventoCriado.setNome(evento.getNome());
		
		return eventoRepository.save(eventoCriado);
	}

	@Override
	public List<Eventos> findAllEvento() {
		return eventoRepository.findAll();
	}

	@Override
	public List<Eventos> findRestaurantsEvento(Long id) {
		// TODO Auto-generated method stub
		return eventoRepository.findEventsByRestaurantId(id);
	}

	@Override
	public void deleteEvento(Long id) throws Exception {
		Eventos evento=findById(id);
		eventoRepository.delete(evento);
		
	}

	@Override
	public Eventos findById(Long id) throws Exception {
		Optional<Eventos> opt=eventoRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("event not found withy id "+id);
		
	}

}
