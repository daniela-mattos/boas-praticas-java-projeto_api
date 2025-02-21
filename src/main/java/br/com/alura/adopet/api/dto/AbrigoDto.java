package br.com.alura.adopet.api.dto;


import br.com.alura.adopet.api.model.Abrigo;

public record AbrigoDto(String nome,
                        String telefone,
                        String email) {

    public AbrigoDto(Abrigo abrigo) {
        this.nome = abrigo.getNome();
        this.telefone = abrigo.getTelefone();
        this.email = abrigo.getEmail();
    }
}
