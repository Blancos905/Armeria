package com.armaGest.identity_tenant_service.tenant.exception;

public class TenantNotFoundException extends RuntimeException {
    public TenantNotFoundException(String msg) {
        super(msg);
    }
}
