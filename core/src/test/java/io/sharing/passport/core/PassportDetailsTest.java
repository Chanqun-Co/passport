package io.sharing.passport.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PassportDetailsTest {

    @DisplayName("UserRole을 추가한다.")
    @Test
    public void addRoleTest() {
        PassportDetails details = PassportDetails.builder()
                .userUuid(UUID.randomUUID().toString())
                .email("kimzerovirus@email.com")
                .lastName("kim")
                .firstName("zerovirus")
                .roles(new ArrayList<>(List.of(UserRole.GUEST)))
                .build();

        UserRole addedRole = UserRole.HOST;
        details.addRole(addedRole);

        assertThat(details.getRoles().size()).isEqualTo(2);
        assertThat(details.getRoles()).contains(addedRole);
    }

    @DisplayName("UserRole을 제거한다.")
    @Test
    public void removeRoleTest() {
        PassportDetails details = PassportDetails.builder()
                .userUuid(UUID.randomUUID().toString())
                .email("kimzerovirus@email.com")
                .lastName("kim")
                .firstName("zerovirus")
                .roles(new ArrayList<>(List.of(UserRole.GUEST, UserRole.HOST)))
                .build();

        UserRole removedRole = UserRole.HOST;
        details.removeRole(removedRole);

        assertThat(details.getRoles().size()).isEqualTo(1);
        assertThat(details.getRoles()).doesNotContain(removedRole);
    }
}