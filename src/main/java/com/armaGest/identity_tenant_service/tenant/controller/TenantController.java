package com.armaGest.identity_tenant_service.tenant.controller;

import com.armaGest.identity_tenant_service.tenant.dto.CreateTenantRequest;
import com.armaGest.identity_tenant_service.tenant.dto.TenantResponseDTO;
import com.armaGest.identity_tenant_service.tenant.dto.UpdateTenantRequest;
import com.armaGest.identity_tenant_service.tenant.entity.Tenant;
import com.armaGest.identity_tenant_service.tenant.mapper.TenantMapper;
import com.armaGest.identity_tenant_service.tenant.service.TenantService;
import com.armaGest.identity_tenant_service.tenant.service.TenantValidationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;
    private final TenantValidationService validationService;

    public TenantController(TenantService tenantService, TenantValidationService validationService) {
        this.tenantService = tenantService;
        this.validationService = validationService;
    }

    @PostMapping
    public ResponseEntity<TenantResponseDTO> create(@Valid @RequestBody CreateTenantRequest request) {

        validationService.validateUnique(request);

        Tenant tenant = TenantMapper.fromCreateRequest(request);
        Tenant saved = tenantService.createTenant(tenant);

        return ResponseEntity
                .created(URI.create("/api/tenants/" + saved.getId()))
                .body(TenantMapper.toResponseDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody UpdateTenantRequest request) {

        Tenant tenant = validationService.validateExists(id);
        TenantMapper.applyUpdate(tenant, request);

        Tenant updated = tenantService.updateTenant(tenant);
        return ResponseEntity.ok(TenantMapper.toResponseDTO(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponseDTO> get(@PathVariable Long id) {
        Tenant tenant = validationService.validateExists(id);
        return ResponseEntity.ok(TenantMapper.toResponseDTO(tenant));
    }

    @GetMapping
    public ResponseEntity<Page<TenantResponseDTO>> getAll(Pageable pageable) {

        Page<Tenant> page = tenantService.findAll(pageable);
        Page<TenantResponseDTO> mappedPage = page.map(TenantMapper::toResponseDTO);

        return ResponseEntity.ok(mappedPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        validationService.validateExists(id);
        tenantService.softDeleteTenant(id);
        return ResponseEntity.noContent().build();
    }
}
