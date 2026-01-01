package br.com.ghx.farmacontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FarmacontrolApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(FarmacontrolApplication.class, args);
        var env = context.getEnvironment();

        System.out.println("SISTEMA PRONTO!");
        System.out.println("USER: " + env.getProperty("POSTGRES_USER"));

        // TESTE DE SANIDADE DO FLYWAY
        try {
            org.flywaydb.core.Flyway flyway = context.getBean(org.flywaydb.core.Flyway.class);
            System.out.println("FLYWAY ENCONTRADO! Migrações pendentes: " + flyway.info().pending().length);
        } catch (Exception e) {
            System.err.println("ERRO: O Flyway não foi iniciado pelo Spring!");
        }
    }
}
