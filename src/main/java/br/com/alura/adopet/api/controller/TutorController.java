package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizaTutorDto;
import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(CadastrarTutorDto dto) {
        try{
            tutorService.cadastrarTutor(dto);
            return ResponseEntity.ok("Tutor cadastrado com sucesso!");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Isso não está de fato atualizando tutor
    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(AtualizaTutorDto dto) {
        try {
            tutorService.atualizarTutor(dto);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
