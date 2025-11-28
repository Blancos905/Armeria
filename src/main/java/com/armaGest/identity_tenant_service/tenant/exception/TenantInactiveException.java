package com.armaGest.identity_tenant_service.tenant.exception;

public class TenantInactiveException extends RuntimeException {
    public TenantInactiveException(String msg) {
        super(msg);
    }
}
