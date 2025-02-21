package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizaTutorDto;
import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoDadosTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private List<ValidacaoDadosTutor> validacoes;

    public void cadastrarTutor(CadastrarTutorDto dto) {

        //validacão
        validacoes.forEach(v -> v.validarDados(dto));
        repository.save(new Tutor(dto.nome(), dto.telefone(), dto.email()));
    }

    public void atualizarTutor(AtualizaTutorDto dto) {
        Tutor tutor = repository.getReferenceById(dto.idTutor());
        tutor.atualizarDados(dto);
    }
}
