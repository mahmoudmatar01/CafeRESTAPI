package com.fiorecafe.fiore.fiore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@OpenAPIDefinition(servers =
        {
                @Server(
                        url = "http://localhost:5920",
                        description = "Matar Local Project for Historian API"
                )
        }
)
@CrossOrigin(
        origins = "*",
        maxAge = 3600,
        allowedHeaders = "*")
public class FioreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FioreApplication.class, args);
    }

}
