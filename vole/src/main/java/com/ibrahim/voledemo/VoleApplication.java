package com.ibrahim.voledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@SpringBootApplication
@EnableElasticsearchRepositories
public class VoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoleApplication.class, args);
	}

}
