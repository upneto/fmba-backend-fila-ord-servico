package br.com.fiap.fmba.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.fiap.fmba.service.payload.OrdemServicoMessagePayload;

@Service
public class FMBAMailSender extends AbstractService {
	
	@Value("${spring.mail.username}")
	private String masterMail = null;
	
	@Value("${spring.mail.password}")
	private String masterMailPass = null;
	
	private static final SimpleDateFormat _SDF = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
    private JavaMailSender mailSender = null;
	
	public void sendEmail(OrdemServicoMessagePayload ordemServico) {
        
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(new String[] { ordemServico.getEmailDestinatario(), this.masterMail });
        message.setSubject("FMBA - Ordem de Servico: " + ordemServico.getCodigoServico());
        message.setText(this.getBody(ordemServico));

        this.mailSender.send(message);
    }

	/**
	 * 
	 * @param ordemServico
	 * @return
	 */
	private String getBody(OrdemServicoMessagePayload ordemServico) {
		StringBuilder bodyMessage = new StringBuilder();
		bodyMessage.append("Presado(a) Sr(a) ").append(ordemServico.getNomeDestinatario()).append("\n\n");
		bodyMessage.append("O seu serviço número ").append(ordemServico.getCodigoServico()).append(" foi cadastrado em nosso sistema.\n");
		bodyMessage.append("Abaixo estão alguns dados sobre o serviço:\n\n");
		bodyMessage.append("Veiculo: ").append(ordemServico.getMarcaVeiculo()).append(" ").append(ordemServico.getModeloVeiculo()).append("\n");
		bodyMessage.append("Placa: ").append(ordemServico.getPlacaVeiculo()).append("\n");
		bodyMessage.append("Data Início para o Serviço: ").append(_SDF.format(ordemServico.getDataInicioServico())).append("\n");
		bodyMessage.append("Prazo Final para conclusão do serviço: ").append(_SDF.format(ordemServico.getDataFinalServico())).append("\n");
		bodyMessage.append("Descrição: ").append(ordemServico.getDescricaoServico()).append("\n\n");
		bodyMessage.append("Obrigado.");
		
		return bodyMessage.toString();
	}
}
