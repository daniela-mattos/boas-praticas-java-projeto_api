package br.com.alura.adopet.api.dto;

public record AtualizaTutorDto(Long idTutor,
                               String nome,
                               String telefone,
                               String email) {
}
