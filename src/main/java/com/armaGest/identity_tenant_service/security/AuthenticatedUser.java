package com.armaGest.identity_tenant_service.security;

public class AuthenticatedUser {
    private final String username;
    private final Long tenantId;

    public AuthenticatedUser(String username, Long tenantId) {
        this.username = username;
        this.tenantId = tenantId;
    }

    public String getUsername() { return username; }
    public Long getTenantId() { return tenantId; }
}
