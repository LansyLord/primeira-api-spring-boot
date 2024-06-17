package br.com.spring_api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;


import br.com.spring_api.modelo.Mensagem;
import br.com.spring_api.modelo.Pessoa;
import br.com.spring_api.repositorio.Repositorio;

@Service
public class Servico {
    
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;
    
    public ResponseEntity<?> cadastrar(Pessoa obj){
        if(obj.getNome().equals("")){
            mensagem.setMensagem("Insira um nome válido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        
        if(obj.getIdade() <= 0){
            mensagem.setMensagem("Insira uma idade maior que zero");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
    }

    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> selecionarPeloCodigo(int codigo){
        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Não existe nenhum usuário de código "+ codigo);
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
    }

    public ResponseEntity<?> editar(Pessoa obj){
        if(acao.countByCodigo(obj.getCodigo()) == 0){
            mensagem.setMensagem("Não existe nenhum usuário de código "+ obj.getCodigo());
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        if(obj.getNome().equals("")){
            mensagem.setMensagem("Insira um nome válido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        
        if(obj.getIdade() <= 0){
            mensagem.setMensagem("Insira uma idade maior que zero");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
    }

    public ResponseEntity<?> remover(int codigo){
        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Não existe nenhum usuário de código "+ codigo);
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        Pessoa obj = acao.findByCodigo(codigo);
        acao.delete(obj);

        mensagem.setMensagem("Usuário excluido com sucesso");
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
}
