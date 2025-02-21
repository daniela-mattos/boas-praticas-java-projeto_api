package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;

public record PetDto(Long id,
                     TipoPet tipo,
                     String nome,
                     String raca,
                     Integer idade) {

    public PetDto(Pet pet) {
        this.id = pet.getId();
        this.tipo = pet.getTipo();
        this.nome = pet.getNome();
        this.raca = pet.getRaca();
        this.idade = pet.getIdade();
    }
}
