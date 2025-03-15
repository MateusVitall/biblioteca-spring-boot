package com.example.biblioteca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.entidades.Emprestimo;
import com.example.biblioteca.entidades.Livro;
import java.util.List;
import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID> {
    List<Livro> findByEmprestado(boolean emprestado);
}
