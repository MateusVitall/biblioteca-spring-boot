package com.example.biblioteca.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.biblioteca.entidades.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  List<Usuario> findByNome(String nome);
  
}