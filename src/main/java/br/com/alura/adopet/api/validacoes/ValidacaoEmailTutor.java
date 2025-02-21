package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoEmailTutor implements ValidacaoDadosTutor {

    @Autowired
    private TutorRepository repository;

    @Override
    public void validarDados(CadastrarTutorDto dto) {
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if(emailJaCadastrado){
            throw new ValidacaoException("Email já cadastrado para outro Tutor.");
        }
    }
}
