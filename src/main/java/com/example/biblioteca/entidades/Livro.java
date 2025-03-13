package com.example.biblioteca.entidades;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String titulo;
    private String autor;
    private String genero;
    private String editora;
    private int anoPublicacao;
    private String isbn;
    private boolean emprestado;
    
    public Livro() {
    }

    public Livro(String titulo, String autor, String genero, String editora, int anoPublicacao, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
        this.emprestado = false;
    }

    public UUID getId() {
        return id;
    }
}
