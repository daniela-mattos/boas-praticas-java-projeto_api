package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoDadosAbrigo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private List<ValidacaoDadosAbrigo> validacoes;

    public List<AbrigoDto> listarAbrigos() {
        return abrigoRepository
                .findAll()
                .stream()
                .map(AbrigoDto::new)
                .toList();
    }

    public void cadastrar(CadastrarAbrigoDto dto) {
        validacoes.forEach(v -> v.validar(dto));
        abrigoRepository.save(new Abrigo(dto.nome(), dto.telefone(), dto.email()));
    }

    public void cadastrarPet(CadastrarPetDto dto) {
        try {
            Long id = Long.parseLong(dto.idOuNome());
            Abrigo abrigo = abrigoRepository.getReferenceById(id);
            Pet pet = new Pet(dto.tipo(), dto.nome(), dto.raca(), dto.idade(), dto.cor(), dto.peso(),
                            abrigo);
            abrigo.getPets().add(pet);
            abrigoRepository.save(abrigo);
        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException("Abrigo não encontrado com a ID.");
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = abrigoRepository.findByNome(dto.idOuNome());
                Pet pet = new Pet(dto.tipo(), dto.nome(), dto.raca(), dto.idade(), dto.cor(), dto.peso(),
                        abrigo);
                abrigo.getPets().add(pet);
                abrigoRepository.save(abrigo);
            } catch (EntityNotFoundException enfe) {
                throw new ValidacaoException("Abrigo não encontrado com o nome.");
            }
        }
    }

    public List<PetDto> listarPetsDoAbrigo(String idOuNome) {
        Abrigo abrigo = carregarAbrigo(idOuNome);

        return petRepository
                .findByAbrigo(abrigo)
                .stream()
                .map(PetDto::new)
                .toList();
    }

    public Abrigo carregarAbrigo(String idOuNome) {
        Optional<Abrigo> optional;
        try {
            Long id = Long.parseLong(idOuNome);
            optional = abrigoRepository.findById(id);
        } catch (NumberFormatException exception) {
            optional = Optional.ofNullable(abrigoRepository.findByNome(idOuNome));
        }

        return optional.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));
    }


}
