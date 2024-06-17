package br.com.spring_api.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring_api.modelo.Cliente;
import br.com.spring_api.modelo.Pessoa;
import br.com.spring_api.repositorio.Repositorio;
import br.com.spring_api.servico.Servico;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
public class Controller {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);
    }

    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
       return servico.selecionar();
    }

    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj){
        return servico.editar(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo){
        return servico.remover(codigo);
    }

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo){
        return servico.selecionarPeloCodigo(codigo);
    }

    @GetMapping("/api/contador")
    public long contador(){
        return acao.count();
    }

    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem(){
       return acao.findByNomeContaining("ui");
    }

    @GetMapping("/api/ordenar/nome")
    public List<Pessoa> ordernarPorNome(){
        return acao.findAllByOrderByNome();
    }

    @GetMapping("/api/ordenar/idade")
    public List<Pessoa> ordenarPorIdade(){
        return acao.findByNomeOrderByIdade("Louise");
    }

    @GetMapping("/api/somaIdades")
    public int somaIdades(){
        return acao.somaIdades();
    }

    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual(){
        return acao.idadeMaiorIgual(25);
    }

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom(){
        return acao.findByNomeStartsWith("lo");
    }

    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom(){
        return acao.findByNomeEndsWith("o");
    }

    @GetMapping("")
    public String mensagem(){
        return "Hello World!";
    }

    @GetMapping("/boas-vindas/{nome}")
    public String sejaBemVindo(@PathVariable String nome) {
        return "Seja bem vindo(a) " + nome+ "!";
    }

    @GetMapping("/boas-vindas")
    public String sejaBemVindo() {
        return "Seja bem vindo(a)!";
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/api/cliente")
    public void cliente(@Valid @RequestBody Cliente obj){

    }
}
