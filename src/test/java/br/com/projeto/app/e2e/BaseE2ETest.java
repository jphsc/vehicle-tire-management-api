package br.com.projeto.app.e2e;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class BaseE2ETest {
	
	@Autowired
    protected MockMvc mockMvc;

    @Autowired
    private Environment environment;
    
    @Autowired
    protected TestRestTemplate restTemplate;

    @BeforeEach
    void validarProfile() {
        String profile = String.join(",", environment.getActiveProfiles());

        if (!profile.contains("test")) {
            throw new IllegalStateException("Testes E2E só podem rodar no profile TEST");
        }
    }
}
