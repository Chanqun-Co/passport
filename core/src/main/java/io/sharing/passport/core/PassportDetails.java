package io.sharing.passport.core;

import java.io.Serializable;
import java.util.List;

public class PassportDetails implements Serializable {
    private String userUuid;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;

    public PassportDetails() {
    }

    public PassportDetails(String userUuid, String email, String firstName, String lastName, List<String> roles) {
        this.userUuid = userUuid;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
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

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String role){
        this.roles.add(role);
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
        private String userUuid;
        private String email;
        private String firstName;
        private String lastName;
        private List<String> roles;

        public PassportBuilder() {
        }

        public PassportBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PassportBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PassportBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PassportBuilder userUuid(String userUuid) {
            this.userUuid = userUuid;
            return this;
        }

        public PassportBuilder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public PassportDetails build() {
            return new PassportDetails(userUuid, email, firstName, lastName, roles);
        }
    }
}
