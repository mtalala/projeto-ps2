package br.milliecarol.projeto.servico;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import br.milliecarol.projeto.*;
import br.milliecarol.projeto.repositorio.IniciativaRepository;
import br.milliecarol.projeto.repositorio.ObjetivoRepository;
import br.milliecarol.projeto.repositorio.ResultadoChaveRepository;


public class IniciativaServico {
    @Autowired
    private IniciativaRepository iniciativaRepository;
    @Autowired
    private ObjetivoRepository objetivoRepository;
    @Autowired
    private ResultadoChaveRepository resultadoChaveRepository;

    
}
