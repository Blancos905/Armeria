package com.armaGest.identity_tenant_service.tenant.service;

import com.armaGest.identity_tenant_service.tenant.entity.Tenant;
import com.armaGest.identity_tenant_service.tenant.repository.TenantRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TenantService {

    private static final Logger logger = LoggerFactory.getLogger(TenantService.class);

    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public Tenant createTenant(Tenant tenant) {
        logger.info("Creazione tenant {}", tenant.getName());
        tenant.setCreatedAt(LocalDateTime.now());
        tenant.setStatus(tenant.getStatus());
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Tenant tenant) {
        logger.info("Aggiornamento tenant {}", tenant.getId());
        tenant.setUpdatedAt(LocalDateTime.now());
        return tenantRepository.save(tenant);
    }

    public void softDeleteTenant(Long id) {
        logger.info("Soft delete tenant {}", id);
        Tenant tenant = tenantRepository.findById(id).orElseThrow();
        tenant.setDeleted(true);
        tenantRepository.save(tenant);
    }

    public Optional<Tenant> findById(Long id) {
        return tenantRepository.findById(id);
    }

    public Page<Tenant> findAll(Pageable pageable) {
        return tenantRepository.findAll(pageable);
    }
}
