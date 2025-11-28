package com.armaGest.identity_tenant_service.user.mapper;

import com.armaGest.identity_tenant_service.user.dto.CreateUserRequest;
import com.armaGest.identity_tenant_service.user.dto.UpdateUserRequest;
import com.armaGest.identity_tenant_service.user.dto.UserResponseDTO;
import com.armaGest.identity_tenant_service.user.entity.Role;
import com.armaGest.identity_tenant_service.user.entity.User;
import com.armaGest.identity_tenant_service.user.entity.UserStatus;

import java.time.LocalDateTime;
import java.util.Set;

public class UserMapper {

    public static UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setKeycloakId(user.getKeycloakId());
        dto.setTenantId(user.getTenantId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setRoles(user.getRoles());
        dto.setStatus(user.getStatus().name());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    public static User fromCreateRequest(CreateUserRequest request) {
        if (request == null) return null;
        User user = new User();
        user.setKeycloakId(request.getKeycloakId());
        user.setTenantId(request.getTenantId());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setRoles(request.getRoles() != null ? request.getRoles() : Set.of(Role.OPERATORE));
        user.setStatus(UserStatus.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public static void updateUserFromRequest(User user, UpdateUserRequest request) {
        if (user == null || request == null) return;
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getRoles() != null) user.setRoles(request.getRoles());
        if (request.getStatus() != null) {
            try {
                user.setStatus(UserStatus.valueOf(request.getStatus()));
            } catch (IllegalArgumentException e) {
                // Mantieni status precedente se invalido
            }
        }
        user.setUpdatedAt(LocalDateTime.now());
    }
}
