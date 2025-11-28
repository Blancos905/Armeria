package com.armaGest.identity_tenant_service.tenant.mapper;

import com.armaGest.identity_tenant_service.tenant.dto.CreateTenantRequest;
import com.armaGest.identity_tenant_service.tenant.dto.TenantResponseDTO;
import com.armaGest.identity_tenant_service.tenant.dto.UpdateTenantRequest;
import com.armaGest.identity_tenant_service.tenant.entity.Tenant;
import com.armaGest.identity_tenant_service.tenant.entity.TenantStatus;

import java.time.LocalDateTime;

public class TenantMapper {

    public static TenantResponseDTO toResponseDTO(Tenant tenant) {
        TenantResponseDTO dto = new TenantResponseDTO();
        dto.setId(tenant.getId());
        dto.setName(tenant.getName());
        dto.setCompanyIdentifier(tenant.getCompanyIdentifier());
        dto.setStatus(tenant.getStatus().name());
        dto.setDeleted(tenant.isDeleted());
        dto.setCreatedAt(tenant.getCreatedAt());
        dto.setUpdatedAt(tenant.getUpdatedAt());
        return dto;
    }

    public static Tenant fromCreateRequest(CreateTenantRequest request) {
        Tenant tenant = new Tenant();
        tenant.setName(request.getName());
        tenant.setCompanyIdentifier(request.getCompanyIdentifier());
        tenant.setStatus(TenantStatus.ACTIVE);
        tenant.setCreatedAt(LocalDateTime.now());
        tenant.setDeleted(false);
        return tenant;
    }

    public static void applyUpdate(Tenant tenant, UpdateTenantRequest request) {

        if (request.getName() != null)
            tenant.setName(request.getName());

        if (request.getCompanyIdentifier() != null)
            tenant.setCompanyIdentifier(request.getCompanyIdentifier());

        if (request.getStatus() != null)
            tenant.setStatus(request.getStatus());

        tenant.setUpdatedAt(LocalDateTime.now());
    }
}
