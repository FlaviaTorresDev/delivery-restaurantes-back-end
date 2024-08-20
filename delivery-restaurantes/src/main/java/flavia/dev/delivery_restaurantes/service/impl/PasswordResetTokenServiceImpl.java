package flavia.dev.delivery_restaurantes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flavia.dev.delivery_restaurantes.model.PasswordResetToken;
import flavia.dev.delivery_restaurantes.repository.PasswordResetTokenRepository;
import flavia.dev.delivery_restaurantes.service.PasswordResetTokenService;


@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public PasswordResetToken findByToken(String token) {
		PasswordResetToken passwordResetToken =passwordResetTokenRepository.findByToken(token);
		return passwordResetToken;
	}

	@Override
	public void delete(PasswordResetToken resetToken) {
		passwordResetTokenRepository.delete(resetToken);
		
	}

}