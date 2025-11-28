package com.armaGest.identity_tenant_service.user.repository;

import com.armaGest.identity_tenant_service.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByKeycloakId(String keycloakId);
    List<User> findByTenantId(Long tenantId);
}
