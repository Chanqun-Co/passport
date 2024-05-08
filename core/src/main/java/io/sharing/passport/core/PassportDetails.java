package io.sharing.passport.core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PassportDetails implements Serializable {
    private String userUuid;
    private String email;
    private String firstName;
    private String lastName;
    private final List<UserRole> roles = new LinkedList<>();

    public PassportDetails() {
    }

    public String getUserUuid() {
        return userUuid;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void addRole(UserRole role){
        this.roles.add(role);
    }

    public void removeRole(UserRole role) {
        this.roles.remove(role);
    }

    public static PassportBuilder builder() {
        return new PassportBuilder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" [");
        sb.append("User UUID=").append(this.userUuid).append(", ");
        sb.append("Email=").append(this.email).append(", ");
        sb.append("FirstName=").append(this.firstName).append(", ");
        sb.append("LastName=").append(this.lastName).append(", ");
        sb.append("roles=").append(this.roles);
        sb.append("]");
        return sb.toString();
    }

    public static class PassportBuilder {
        private final PassportDetails data = new PassportDetails();

        public PassportBuilder() {
        }

        public PassportBuilder email(String email) {
            this.data.email = email;
            return this;
        }

        public PassportBuilder lastName(String lastName) {
            this.data.lastName = lastName;
            return this;
        }

        public PassportBuilder firstName(String firstName) {
            this.data.firstName = firstName;
            return this;
        }

        public PassportBuilder userUuid(String userUuid) {
            this.data.userUuid = userUuid;
            return this;
        }

        public PassportBuilder roles(List<UserRole> roles) {
            this.data.roles.addAll(roles);
            return this;
        }

        public PassportDetails build() {
            return this.data;
        }
    }
}
