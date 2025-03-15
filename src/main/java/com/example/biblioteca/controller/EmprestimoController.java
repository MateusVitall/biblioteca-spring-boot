package com.example.biblioteca.controller;

import com.example.biblioteca.entidades.Emprestimo;
import com.example.biblioteca.entidades.Livro;
import com.example.biblioteca.entidades.Usuario;
import com.example.biblioteca.repositorios.EmprestimoRepository;
import com.example.biblioteca.repositorios.LivroRepository;
import com.example.biblioteca.repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping("/{livroId}/{usuarioId}")
    public ResponseEntity<?> pegarEmprestado(@PathVariable UUID livroId, @PathVariable Long usuarioId) {
        Optional<Livro> livroOpt = livroRepository.findById(livroId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (livroOpt.isEmpty() || usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Livro ou usuário não encontrado.");
        }

        Livro livro = livroOpt.get();
        if (livro.isEmprestado()) {
            return ResponseEntity.badRequest().body("Livro já está emprestado.");
        }

        // Atualiza o livro como emprestado
        livro.setEmprestado(true);
        livroRepository.save(livro);

        // Cria e salva o empréstimo
        Emprestimo emprestimo = new Emprestimo(livro, usuarioOpt.get(), LocalDate.now(), null);
        emprestimoRepository.save(emprestimo);

        return ResponseEntity.ok(emprestimo);
    }
}
