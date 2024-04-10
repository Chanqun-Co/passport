package io.sharing.passport.core;

import java.io.Serializable;

public class PassportDetails implements Serializable {
    private String userUuid;
    private String email;
    private String firstName;
    private String lastName;

    public PassportDetails() {
    }

    public PassportDetails(String userUuid, String email, String firstName, String lastName) {
        this.userUuid = userUuid;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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
        sb.append("LastName=").append(this.lastName);
        sb.append("]");
        return sb.toString();
    }

    public static class PassportBuilder {
        private String userUuid;
        private String email;
        private String firstName;
        private String lastName;

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

        public PassportDetails build() {
            return new PassportDetails(this.userUuid, this.email, this.firstName, this.lastName);
        }
    }
}
