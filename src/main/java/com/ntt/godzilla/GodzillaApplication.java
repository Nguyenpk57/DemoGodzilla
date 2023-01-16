package com.ntt.godzilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EntityScan({"com.ntt.godzilla.entity"})
@SpringBootApplication(scanBasePackages = "com.ntt.godzilla",exclude = HibernateJpaAutoConfiguration.class)
@EnableCaching(proxyTargetClass = true)
public class GodzillaApplication {
	public static void main(String[] args) {
		SpringApplication.run(GodzillaApplication.class, args);
	}

}
