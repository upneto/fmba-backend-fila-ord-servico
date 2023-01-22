package br.com.fiap.fmba.resources.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
	
	@Value("${${env}.mq.QUEUE_NAME}")
	private final String QUEUE_NAME = null;
	
	@Value("${${env}.mq.URI}")
	private final String URI = null;
	
	@Value("${mq.auth.USER}")
	private final String MQ_USER = null; 
	
	@Value("${mq.auth.PASSWORD}")
	private final String MQ_PASSWORD = null;
	
	@Value("${mq.auth.HOST}")
	private final String MQ_HOST = null;
	
	@Value("${mq.auth.V_HOST}")
	private final String MQ_V_HOST = null;
	
	@Value("${mq.auth.PORT}")
	private final String MQ_PORT = null;
	

	@Bean
	public ConnectionFactory connectionFactory() {

		final CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setUsername(this.MQ_USER);
		factory.setPassword(this.MQ_PASSWORD);
		factory.setHost(this.MQ_HOST);
		factory.setPort(Integer.parseInt(this.MQ_PORT));
		factory.setVirtualHost(this.MQ_V_HOST);

		return factory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setRoutingKey(this.QUEUE_NAME);
		template.setDefaultReceiveQueue(this.QUEUE_NAME);
		return template;
	}

	@Bean
	public Queue queue() {
		return new Queue(this.QUEUE_NAME);
	}

}
