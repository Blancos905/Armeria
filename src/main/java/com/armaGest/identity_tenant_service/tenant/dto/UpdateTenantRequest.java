package com.armaGest.identity_tenant_service.tenant.dto;

import com.armaGest.identity_tenant_service.tenant.entity.TenantStatus;
import jakarta.validation.constraints.Size;

public class UpdateTenantRequest {

    @Size(max = 100)
    private String name;

    @Size(max = 50)
    private String companyIdentifier;

    private TenantStatus status;

    public String getName() { return name; }
    public String getCompanyIdentifier() { return companyIdentifier; }
    public TenantStatus getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setCompanyIdentifier(String companyIdentifier) { this.companyIdentifier = companyIdentifier; }
    public void setStatus(TenantStatus status) { this.status = status; }
}
