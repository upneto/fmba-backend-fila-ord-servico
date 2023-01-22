package br.com.fiap.fmba;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

public abstract class AbstractTest<T> {

	@TestConfiguration
    static class ClassTestingTestConfiguration {        
		
		@SuppressWarnings("unchecked")
		@Bean
        public <T> T classTesting() {
			try {
				return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
        }
    }
	
	@Autowired
	protected T classTesting;
}
