package com.armaGest.identity_tenant_service.user.controller;

import com.armaGest.identity_tenant_service.security.AuthenticatedUser;
import com.armaGest.identity_tenant_service.user.dto.CreateUserRequest;
import com.armaGest.identity_tenant_service.user.dto.UpdateUserRequest;
import com.armaGest.identity_tenant_service.user.dto.UserResponseDTO;
import com.armaGest.identity_tenant_service.user.entity.User;
import com.armaGest.identity_tenant_service.user.mapper.UserMapper;
import com.armaGest.identity_tenant_service.user.service.UserService;
import com.armaGest.identity_tenant_service.user.service.UserValidationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserValidationService validationService;

    public UserController(UserService userService, UserValidationService validationService) {
        this.userService = userService;
        this.validationService = validationService;
    }

    // ------------------- CREATE -------------------
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequest request,
                                                      Authentication authentication) {
        AuthenticatedUser authUser = (AuthenticatedUser) authentication.getDetails();
        checkTenantAccess(authUser, request.getTenantId());

        validationService.validateUsernameUnique(request.getUsername());
        validationService.validateKeycloakIdUnique(request.getKeycloakId());

        User user = UserMapper.fromCreateRequest(request);
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(UserMapper.toUserResponseDTO(savedUser));
    }

    // ------------------- GET BY ID -------------------
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id,
                                                   Authentication authentication) {
        User user = validationService.validateUserExists(id);
        AuthenticatedUser authUser = (AuthenticatedUser) authentication.getDetails();
        checkTenantAccess(authUser, user.getTenantId());

        return ResponseEntity.ok(UserMapper.toUserResponseDTO(user));
    }

    // ------------------- GET BY TENANT -------------------
    @GetMapping("/tenant/{tenantId}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN_AZIENDA')")
    public ResponseEntity<List<UserResponseDTO>> getUsersByTenant(@PathVariable Long tenantId,
                                                                  Authentication authentication) {
        AuthenticatedUser authUser = (AuthenticatedUser) authentication.getDetails();
        checkTenantAccess(authUser, tenantId);

        List<User> users = userService.getUsersByTenant(tenantId);
        List<UserResponseDTO> dtos = users.stream().map(UserMapper::toUserResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // ------------------- UPDATE -------------------
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                      @RequestBody UpdateUserRequest request,
                                                      Authentication authentication) {
        User user = validationService.validateUserExists(id);
        AuthenticatedUser authUser = (AuthenticatedUser) authentication.getDetails();
        checkTenantAccess(authUser, user.getTenantId());

        UserMapper.updateUserFromRequest(user, request);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(UserMapper.toUserResponseDTO(updatedUser));
    }

    // ------------------- DELETE -------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id,
                                           Authentication authentication) {
        User user = validationService.validateUserExists(id);
        AuthenticatedUser authUser = (AuthenticatedUser) authentication.getDetails();
        checkTenantAccess(authUser, user.getTenantId());

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------- HELPERS -------------------
    private void checkTenantAccess(AuthenticatedUser authUser, Long resourceTenantId) {
        // SUPER_ADMIN può accedere a tutto
        if (authUser.getTenantId() == null || authUser.getTenantId().equals(resourceTenantId)) return;

        // Se il tenant non coincide -> accesso negato
        throw new org.springframework.security.access.AccessDeniedException(
                "Access denied for tenant " + resourceTenantId
        );
    }
}
