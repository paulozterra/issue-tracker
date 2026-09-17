package com.paulozterra.issuetracker.controller;

import com.paulozterra.issuetracker.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @WebMvcTest carga solo la capa web (no el contexto completo), pero si
 * spring-boot-starter-security esta en el classpath tambien activa los
 * filtros de seguridad de forma automatica con una configuracion por
 * defecto (que exige autenticacion). Para reflejar el comportamiento real
 * de la app se importa la SecurityConfig real del proyecto (permitAll),
 * en vez de deshabilitar seguridad en el test.
 */
@WebMvcTest(HealthController.class)
@Import(SecurityConfig.class)
class HealthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void deberiaDevolverStatusUpConHttp200() throws Exception {
		mockMvc.perform(get("/health"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(content().json("{\"status\":\"UP\"}"));
	}
}
