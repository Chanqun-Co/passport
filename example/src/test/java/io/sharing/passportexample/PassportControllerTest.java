package io.sharing.passportexample;

import io.sharing.passport.core.PassportDetails;
import io.sharing.passport.core.PassportTokenProvider;
import io.sharing.passport.core.configuration.PassportProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PassportControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PassportTokenProvider tokenProvider;

    @DisplayName("헤더에 passport 토큰을 실어서 보내면 PassportResolver가 passport 정보를 파싱하여 응답한다.")
    @Test
    public void passportTest() throws Exception {
        PassportDetails passportDetails = PassportDetails.builder()
                .userUuid("85b73dbd-44da-4d22-8e62-518f67faac38")
                .email("kimzerovirus@email.com")
                .lastName("kim")
                .firstName("zerovirus")
                .build();

        String passportToken = tokenProvider.generate(passportDetails);

        mockMvc.perform(get("/passport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(PassportProperties.HEADER_NAME, passportToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("userUuid").value(passportDetails.getUserUuid()))
                .andExpect(jsonPath("email").value(passportDetails.getEmail()))
                .andExpect(jsonPath("lastName").value(passportDetails.getLastName()))
                .andExpect(jsonPath("firstName").value(passportDetails.getFirstName()));
    }
}