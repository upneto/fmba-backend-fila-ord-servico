package br.com.fiap.fmba.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.fmba.controller.payload.ordemservico.OrdemServicoRequest;
import br.com.fiap.fmba.resources.exception.BusinessException;
import br.com.fiap.fmba.resources.exception.WebServiceException;

@Service
public class OrdemServicoService extends AbstractService {

	@Autowired 
	private RabbitTemplate amqpTemplate = null;
    	
	/**
	 * Insert
	 * @param ordemServico
	 * @throws WebServiceException
	 * @throws BusinessException
	 */
	public void send(OrdemServicoRequest ordemServico) {
		LOGGER.info("Enviando MQ: " + ordemServico.toString());
        this.amqpTemplate.convertAndSend(ordemServico.toString());
        LOGGER.info("Enviado com sucesso!");
	}
}
