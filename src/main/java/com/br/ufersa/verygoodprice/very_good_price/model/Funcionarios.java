/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ufersa.verygoodprice.very_good_price.model;

import com.br.ufersa.verygoodprice.very_good_price.view.FrmLogin;
import com.br.ufersa.verygoodprice.very_good_price.view.Frmmenu;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Paulo Roberto
 */
public class Funcionarios {

    public static Funcionarios getLogado() {
        return logado;
    }

    public static void setLogado(Funcionarios logado) {
        Funcionarios.logado = logado;
    }
    private static Funcionarios logado;
    private int id;
     private String nome;
    private String login;
    private String senha;
    private int cargo;
    private Connection con;
    
    public Funcionarios(){
     
        this.con = new ConnectionFactory().getConnection();
    
    }
    public void cadastrarFuncionarios(Funcionarios obj) {
        
        try {

            //1 passo  - criar o comando sql
            String sql = "insert into tb_funcionarios (nome,login,senha,cargo) "
                    + " values (?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getLogin());
            stmt.setString(3, obj.getSenha());
            stmt.setInt(4, obj.getCargo());
                        //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    public void alterarFuncionario(Funcionarios obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "update tb_funcionarios  set  nome=?,login=?, senha=?, cargo=? where id =?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getLogin());

            stmt.setString(3, obj.getSenha());
            stmt.setInt(4, obj.getCargo());
            stmt.setInt(5, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }
    public void excluirFuncionario(Funcionarios obj) {
        try {


            String sql = "delete from tb_funcionarios  where id = ?";


            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }
    
    public List<Funcionarios> listarFuncionarios() {
        try {

            //1 passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setLogin(rs.getString("login"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getInt("cargo"));
   
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
    
     public Funcionarios consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios  where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setLogin(rs.getString("login"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getInt("cargo"));
                
            }

            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
            return null;
        }
    }
     
      public List<Funcionarios> listarFuncionariosPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setLogin(rs.getString("login"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getInt("cargo"));
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }

       public void efetuaLogin(String login, String senha ) {
        try {

            //1 passo - SQL
            String sql = "select * from tb_funcionarios where login = ? and senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //Usuario logou
                Funcionarios obj = new Funcionarios();
                  obj.setId(rs.getInt("id"));
                  setLogado(obj);
                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    Frmmenu tela = new Frmmenu();
                    tela.usuariologado = rs.getString("nome");
                    tela.setVisible(true);

            } else {
                //Dados incorretos
                JOptionPane.showMessageDialog(null, "Dados incorretos!");
                new FrmLogin().setVisible(true);

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro : " + erro);
        }

    }
       public static void LimpaTela(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            }
        }
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
 
}
