package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTelefoneTutor implements ValidacaoDadosTutor {

    @Autowired
    private TutorRepository repository;

    @Override
    public void validarDados(CadastrarTutorDto dto) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());

        if(telefoneJaCadastrado){
            throw new ValidacaoException("Telefone já cadastrado para outro Tutor.");
        }
    }
}
