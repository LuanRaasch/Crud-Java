package br.com.agenda.aplicacao;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Rodinei rodinha");
        contato.setIdade(33);
        contato.setDataCadastro(new Date());

        //contatoDAO.save(contato);

        // Atualizar contato
        Contato contato1 = new Contato();
        contato1.setNome("Luan Raasch ");
        contato1.setIdade(23);
        contato1.setDataCadastro(new Date());
        contato1.setId(1);//é o numero que está no banco de dados

        //contatoDAO.update(contato1);

        //Deletar contato pelo id
        //contatoDAO.deleteById(6);

        //Visualização dos registros do banco de dados TODOS

        for ( Contato c : contatoDAO.getContatos()) {
            System.out.println("Contato: " + c.getNome());

        }
    }
}
