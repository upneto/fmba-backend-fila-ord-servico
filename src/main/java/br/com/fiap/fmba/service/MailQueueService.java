package br.com.fiap.fmba.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.fmba.service.payload.OrdemServicoMessagePayload;

@Component
public class MailQueueService extends AbstractService {
	
	@Autowired
	private FMBAMailSender fmbaMailSender = null;

	@RabbitListener(queues = { "${${env}.mq.QUEUE_NAME}" })
    public void receive(@Payload String message) {
		LOGGER.info(" => Obteve mensagem!!!");
        LOGGER.debug("Message " + message);
        
        try {
        	OrdemServicoMessagePayload ordemServico = new ObjectMapper().readValue(message, OrdemServicoMessagePayload.class);
        	this.fmbaMailSender.sendEmail(ordemServico);
        	LOGGER.info(" => Email enviado!!!");
		} catch (Exception e) {
			LOGGER.error("Não foi possivel enviar o email!", e);
		}  
    }
}
