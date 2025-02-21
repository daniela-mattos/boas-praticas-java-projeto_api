package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoEmailAbrigo implements ValidacaoDadosAbrigo {

    @Autowired
    private AbrigoRepository repository;

    @Override
    public void validar(CadastrarAbrigoDto dto) {
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if(emailJaCadastrado){
            throw new ValidacaoException("Email já cadastrado.");
        }
    }
}
