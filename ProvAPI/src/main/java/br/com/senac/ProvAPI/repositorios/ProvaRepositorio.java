package br.com.senac.ProvAPI.repositorios;

import br.com.senac.ProvAPI.entidades.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaRepositorio extends JpaRepository<Prova, Long> {
    List<Prova> findByTitulo(String titulo);
}
