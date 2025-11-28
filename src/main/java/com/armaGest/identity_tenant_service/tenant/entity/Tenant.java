package com.armaGest.identity_tenant_service.tenant.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "company_identifier", nullable = false, unique = true, length = 50)
    private String companyIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TenantStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean deleted = false;

    public Tenant() {}

    // GETTER & SETTER
    // --------------------------------------------------

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCompanyIdentifier() { return companyIdentifier; }
    public TenantStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public boolean isDeleted() { return deleted; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCompanyIdentifier(String companyIdentifier) { this.companyIdentifier = companyIdentifier; }
    public void setStatus(TenantStatus status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
