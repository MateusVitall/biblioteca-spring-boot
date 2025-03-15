package com.example.biblioteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblioteca.entidades.Livro;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    List<Livro> findByEmprestado(boolean emprestado);
}