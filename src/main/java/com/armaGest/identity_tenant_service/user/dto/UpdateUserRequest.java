package com.armaGest.identity_tenant_service.user.dto;

import com.armaGest.identity_tenant_service.user.entity.Role;
import java.util.Set;

public class UpdateUserRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Set<Role> roles;
    private String status;

    public UpdateUserRequest() {}

    // Getter e Setter
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
