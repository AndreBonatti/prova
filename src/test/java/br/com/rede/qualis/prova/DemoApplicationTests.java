package br.com.rede.qualis.prova;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.rede.qualis.prova.repository.ProcedimentoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class DemoApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

	final String BASE_PATH = "http://localhost:8089/autorizador";

	 @Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ProcedimentoRepository procedimentoRep;

	@Test
	@Order(1)
	void createProcedimento_200() throws Exception {
		String path = BASE_PATH + "/cadastro/procedimento/%s/idade/%s/sexo/%s/autoriza/%s";
		path = String.format(path, 123456, 10, 'M', true);

		log.info(path);

		mockMvc.perform(post(path).content("").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	@Order(2)
	void createProcedimento_erroParamentro_400() throws Exception {

		String path = BASE_PATH + "/cadastro/procedimento/%s/idade/%s/sexo/%s/autoriza/%s";
		path = String.format(path, "555A", 10, 'M', true);

		log.info(path);

		mockMvc.perform(post(path).content("").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Order(3)
	void consultaAutorizacao_aceita_200() throws Exception {

		String path = BASE_PATH + "/procedimento/%s/idade/%s/sexo/%s";
		path = String.format(path, 123456, 10, 'M');

		log.info(path);

		mockMvc.perform(get(path).content("").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data", is("true")));


	}
	
	@Test
	@Order(4)
	void consultaAutorizacao_negada_200() throws Exception {
		
		String path = BASE_PATH + "/procedimento/%s/idade/%s/sexo/%s";
		path = String.format(path, 123456, 20, 'M');
		
		log.info(path);
		
		mockMvc.perform(get(path).content("").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("$.data", is("false")));		
		
	}
	
	@Test
	@Order(5)
	void consultaAutorizacao_errParametro_400() throws Exception {
		
		String path = BASE_PATH + "/procedimento/%s/idade/%s/sexo/%s";
		path = String.format(path, 123456, 20, "MA");
		
		log.info(path);
		
		mockMvc.perform(get(path).content("").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());		
		
	}

}
