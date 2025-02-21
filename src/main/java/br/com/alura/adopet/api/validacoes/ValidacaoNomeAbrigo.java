package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoNomeAbrigo implements ValidacaoDadosAbrigo {

    @Autowired
    private AbrigoRepository repository;

    @Override
    public void validar(CadastrarAbrigoDto dto) {
        boolean nomeJaCadastrado = repository.existsByNome(dto.nome());

        if(nomeJaCadastrado){
            throw new ValidacaoException("Nome já cadastrado.");
        }
    }
}
