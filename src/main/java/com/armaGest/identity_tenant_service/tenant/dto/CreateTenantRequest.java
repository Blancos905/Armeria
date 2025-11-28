package com.armaGest.identity_tenant_service.tenant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTenantRequest {

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(max = 100, message = "Il nome non può superare 100 caratteri")
    private String name;

    @NotBlank(message = "Company Identifier è obbligatorio")
    @Size(max = 50, message = "Company Identifier non può superare 50 caratteri")
    private String companyIdentifier;

    public String getName() { return name; }
    public String getCompanyIdentifier() { return companyIdentifier; }

    public void setName(String name) { this.name = name; }
    public void setCompanyIdentifier(String companyIdentifier) { this.companyIdentifier = companyIdentifier; }
}
