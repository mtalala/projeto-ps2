package br.milliecarol.projeto;

import br.milliecarol.projeto.aplicacao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner {

	@Autowired
	ConsoleApp consoleApp;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Override
	public void run(String...Args) throws Exception {
		ConsoleApp.iniciar();
	}


}
