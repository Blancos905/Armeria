package com.armaGest.identity_tenant_service.tenant.service;

import com.armaGest.identity_tenant_service.tenant.dto.CreateTenantRequest;
import com.armaGest.identity_tenant_service.tenant.entity.Tenant;
import com.armaGest.identity_tenant_service.tenant.entity.TenantStatus;
import com.armaGest.identity_tenant_service.tenant.exception.TenantNotFoundException;
import com.armaGest.identity_tenant_service.tenant.exception.TenantInactiveException;
import com.armaGest.identity_tenant_service.tenant.repository.TenantRepository;

import org.springframework.stereotype.Service;

@Service
public class TenantValidationService {

    private final TenantRepository tenantRepository;

    public TenantValidationService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public Tenant validateExists(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("Tenant non trovato con id: " + id));
    }

    public void validateIsActive(Tenant tenant) {
        if (tenant.getStatus() != TenantStatus.ACTIVE) {
            throw new TenantInactiveException("Tenant inattivo: " + tenant.getId());
        }
    }

    public void validateUnique(CreateTenantRequest request) {

        tenantRepository.findByName(request.getName()).ifPresent(t -> {
            throw new IllegalArgumentException("Il nome del tenant è già in uso");
        });

        tenantRepository.findByCompanyIdentifier(request.getCompanyIdentifier()).ifPresent(t -> {
            throw new IllegalArgumentException("Il Company Identifier è già in uso");
        });
    }
}
