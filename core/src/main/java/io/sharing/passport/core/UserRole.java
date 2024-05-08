package io.sharing.passport.core;

/**
 * 권한
 */
public enum UserRole {
    GUEST("게스트"),
    HOST("호스트")
    ;

    private final String description;

    UserRole (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
