package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void save(Contato contato) {

        String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySql();

            //Criamos uma PreparedStatement, para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //Adicionar os valores esperados pela query
            pstm.setString(1,contato.getNome());
            pstm.setInt(2,contato.getIdade());
            pstm.setDate(3,new Date(contato.getDataCadastro().getTime()));

            //Executar a query
            pstm.execute();

            System.out.println("Contato salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Fechar conexões
            try {
                if(pstm !=null) {
                    pstm.close();
                }

                if(conn !=null){
                    conn.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? " +
                "WHERE id= ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar conexão com o banco
            conn = ConnectionFactory.createConnectionToMySql();

            //Criar a classe para executar a query
            pstm =(PreparedStatement) conn.prepareStatement(sql);

            //Adicionar os valores para atualizar
            pstm.setString(1,contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //Qual o id do resgistro que deseja atualizar?
            pstm.setInt(4, contato.getId());

            //Executar query

            pstm.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteById (int id) {
        String sql = "DELETE FROM contatos WHERE id = ? ";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstm !=null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Contato> getContatos() {
        String sql = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar dados do banco
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Contato contato = new Contato();

                //Recuperar id
                contato.setId(rset.getInt("id"));
                //Recuperar nome
                contato.setNome(rset.getString("nome"));
                //Recuperar idade
                contato.setIdade(rset.getInt("idade"));
                //Recuperar data de cadastro
                contato.setDataCadastro(rset.getDate("dataCadastro"));

                contatos.add(contato);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contatos;
    }
}
