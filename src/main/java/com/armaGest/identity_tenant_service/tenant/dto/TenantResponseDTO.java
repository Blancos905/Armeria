package com.armaGest.identity_tenant_service.tenant.dto;

import java.time.LocalDateTime;

public class TenantResponseDTO {

    private Long id;
    private String name;
    private String companyIdentifier;
    private String status;
    private boolean deleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCompanyIdentifier() { return companyIdentifier; }
    public String getStatus() { return status; }
    public boolean isDeleted() { return deleted; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCompanyIdentifier(String companyIdentifier) { this.companyIdentifier = companyIdentifier; }
    public void setStatus(String status) { this.status = status; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
