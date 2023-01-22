package br.com.fiap.fmba.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.fmba.controller.AbstractController;
import br.com.fiap.fmba.controller.payload.ordemservico.OrdemServicoRequest;
import br.com.fiap.fmba.resources.exception.AutenticatorException;
import br.com.fiap.fmba.resources.exception.BusinessException;
import br.com.fiap.fmba.resources.exception.WebServiceException;
import br.com.fiap.fmba.service.OrdemServicoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/ordem_servico")
public class OrdemServicoController extends AbstractController {

	@Autowired
	private OrdemServicoService service = null;
	
	@ApiOperation(value = "Envia Ordem de Servico para processamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ordem de Servico enviada com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<?> send(@RequestBody OrdemServicoRequest ordemServico) throws WebServiceException, BusinessException, AutenticatorException {		
		this.service.send(ordemServico);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
