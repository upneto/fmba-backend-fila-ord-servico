package br.com.fiap.fmba.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.fmba.controller.api.OrdemServicoController;
import br.com.fiap.fmba.controller.payload.ordemservico.OrdemServicoRequest;
import br.com.fiap.fmba.controller.payload.ordemservico.OrdemServicoResponse;
import br.com.fiap.fmba.resources.exception.AutenticatorException;
import br.com.fiap.fmba.resources.exception.BusinessException;
import br.com.fiap.fmba.resources.exception.WebServiceException;
import br.com.fiap.fmba.service.OrdemServicoService;

@RunWith(SpringRunner.class)
public class OrdemServicoControllerTest {
	
	static final List<OrdemServicoResponse> LIST_PAYLOAD = new ArrayList<>();
	static {
		LIST_PAYLOAD.add(OrdemServicoResponse.builder()				
				.codigo(1L)
		 		.dataInicio((new SimpleDateFormat()).format(new Date()))
		 		.dataFinal((new SimpleDateFormat()).format(new Date()))
		 		.nomeCliente("Mock Cliente")
		 		.veiculo("Mock Veiculo")
		 		.placa("XPT1234")
				.build());
	}
	
	static final OrdemServicoRequest REQUEST = OrdemServicoRequest.builder()
			.id(1L)
			.cliente(new BigInteger("1"))
			.dataCriacao(new Date())
			.dataFinal(new Date())
			.status(1)				
			.build();
	
	@TestConfiguration
    static class OrdemServicoControllerTestConfiguration {        
		
		@Bean
        public OrdemServicoController ordemServicoController() {
            return new OrdemServicoController();
        }
    }
	
	@Autowired
	private OrdemServicoController controller;
	
	@MockBean
	private OrdemServicoService service;

	@Before
	public void setUp() throws Exception {
		Mockito.doNothing().when(service).send(Mockito.any(OrdemServicoRequest.class));
	}

	@Test
	public void testInsert() throws WebServiceException, BusinessException, AutenticatorException {				
		ResponseEntity<?> result = this.controller.send(REQUEST);
		Mockito.verify(service, Mockito.times(1)).send(REQUEST);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
}
