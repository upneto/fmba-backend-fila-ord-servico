package br.com.fiap.fmba.controller.payload.ordemservico;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoRequest {

	private long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dataCriacao;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dataFinal;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dataInicio;
	
	private int status;
	
	private BigInteger veiculo;
	
	private BigInteger cliente;
	
	@Override
	public String toString() {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			return ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "Não foi posível converter objeto para JSON!!!";
		}
	}
}
