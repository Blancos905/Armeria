package com.armaGest.identity_tenant_service.user.service;

import com.armaGest.identity_tenant_service.user.entity.User;
import com.armaGest.identity_tenant_service.user.exception.UserAlreadyExistsException;
import com.armaGest.identity_tenant_service.user.exception.UserNotFoundException;
import com.armaGest.identity_tenant_service.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserValidationService {

    private final UserRepository userRepository;

    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User validateUserExists(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con id: " + userId));
    }

    public void validateUsernameUnique(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("Username già esistente: " + username);
        }
    }

    public void validateKeycloakIdUnique(String keycloakId) {
        if (userRepository.findByKeycloakId(keycloakId).isPresent()) {
            throw new UserAlreadyExistsException("KeycloakId già esistente: " + keycloakId);
        }
    }
}
