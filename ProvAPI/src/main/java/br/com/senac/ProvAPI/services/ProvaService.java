package br.com.senac.ProvAPI.services;

import br.com.senac.ProvAPI.dtos.ProvaFiltroDto;
import br.com.senac.ProvAPI.dtos.ProvaRequestDto;
import br.com.senac.ProvAPI.entidades.Prova;
import br.com.senac.ProvAPI.repositorios.ProvaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvaService {
    private ProvaRepositorio provaRepositorio;

    public ProvaService(ProvaRepositorio provaRepositorio) {
        this.provaRepositorio = provaRepositorio;
    }

    public List<Prova> listar(ProvaFiltroDto filtro) {
        if(filtro.getTitulo() != null) {
            return provaRepositorio.findByTitulo(filtro.getTitulo());
        }
        return provaRepositorio.findAll();
    }

    public Prova criar(ProvaRequestDto prova) {
        Prova provaPersist = this.provaRequestDtoParaProva(prova);
        return provaRepositorio.save(provaPersist);
    }

    public Prova atualizar(Long id, ProvaRequestDto prova) {
        if(provaRepositorio.existsById(id)) {
            Prova provaPersist = this.provaRequestDtoParaProva(prova);
            provaPersist.setId(id);
            return provaRepositorio.save(provaPersist);
        }
        throw new RuntimeException("Prova não encontrada");
    }

    public void deletar(Long id) {
        if(provaRepositorio.existsById(id)) {
            provaRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Prova não encontrada");
        }
    }

    private Prova provaRequestDtoParaProva(ProvaRequestDto entrada) {
        Prova saida = new Prova();
        saida.setTitulo(entrada.getTitulo());
        saida.setMateria(entrada.getMateria());
        saida.setData(entrada.getData());
        return saida;
    }
}