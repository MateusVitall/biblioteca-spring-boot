package com.example.biblioteca.controller;

import com.example.biblioteca.entidades.Livro;
import com.example.biblioteca.repositorios.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController{

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        return ResponseEntity.ok(livroRepository.save(livro));
    }

    @GetMapping
    public List<Livro> listarLivros(@RequestParam(required = false) Boolean emprestado) {
        if (emprestado != null) {
            return livroRepository.findByEmprestado(emprestado);
        }
        return livroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivro(@PathVariable UUID id) {
        Optional<Livro> livro = livroRepository.findById(id);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}