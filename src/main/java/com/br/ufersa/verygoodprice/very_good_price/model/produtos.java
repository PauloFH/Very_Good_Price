/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ufersa.verygoodprice.very_good_price.model;

import java.awt.Component;
import java.awt.HeadlessException;
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
public class produtos {
    private Connection con;
    private int id;
    private String name;
    private double price;
    private int qtd_estoque;

    public produtos(){
     
        this.con = new ConnectionFactory().getConnection();
    
    }
    
    public void cadastrar(produtos obj){
        try {
             String sql = "insert into tb_produtos (name,price,qtd_estoque) values (?,?,?)";
             PreparedStatement st = con.prepareStatement(sql);
             st.setString(1, obj.getName());
             st.setDouble(2, obj.getPrice());
             st.setInt(3, obj.getQtd_estoque());
             st.execute();
             st.close();
             JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
        } catch (HeadlessException | SQLException e) {
             JOptionPane.showMessageDialog(null, "Seguinte erro ao cadastrar produto: "+e);
        }
       
    }
    public void alterar(produtos obj){
        try {
            String sql = "update tb_produtos set name=?,price=?,qtd_estoque=? where id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, obj.getName());
            st.setDouble(2, obj.getPrice());
            st.setInt(3, obj.getQtd_estoque());
            st.setInt(4, obj.getId());
            st.execute();
            st.close();
            JOptionPane.showMessageDialog(null, "Produto Alterardo com Sucesso!");

        } catch (HeadlessException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro : " + e);
        }
    }
    public void excluir(produtos obj){
        try {
            String sql = "delete from tb_produtos where id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, obj.getId());
            st.execute();
            st.close();
            JOptionPane.showMessageDialog(null, "Produto excluido com Sucesso!");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro : " + e);
        }
    }
    public List<produtos> listarProdutos(){
    try{
        List<produtos> lista = new ArrayList<>();
        String sql = "select p.id, p.name,p.price,p.qtd_estoque from tb_produtos as p ";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            produtos obj = new produtos();
            obj.setId(rs.getInt("p.id"));
            obj.setName(rs.getString("p.name"));
            obj.setPrice(rs.getDouble("p.price"));
            obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
             lista.add(obj);
        }
            return lista;
    }catch(SQLException E){
         JOptionPane.showMessageDialog(null, "Erro :" + E);
         return null;   
    }
    }
    public List<produtos> listarProdutosPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<produtos> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select p.id, p.name, p.price, p.qtd_estoque from tb_produtos as p "
                    + " where p.name like ?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nome);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                produtos obj = new produtos();
                obj.setId(rs.getInt("p.id"));
                obj.setName(rs.getString("p.name"));
                obj.setPrice(rs.getDouble("p.price"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
        
    }
    
    public produtos consultaPorNome(String nome){
        try {
            String sql = "select p.id, p.name, p.price, p.qtd_estoque from tb_produtos as p "
                    +"where p.name =  ? ";
            
             PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();
            produtos obj = new produtos();
            if(rs.next()){
                obj.setId(rs.getInt("p.id"));
                obj.setName(rs.getString("p.name"));
                obj.setPrice(rs.getDouble("p.price"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
            }
            return obj;
        } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }
    
     public produtos buscaPorCodigo(int id) {
        try {
            //1 passo - criar o sql , organizar e executar.

            String sql = "select * from tb_produtos where id =  ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            produtos obj = new produtos();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setPrice(rs.getDouble("price"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));

            }

            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return null;
        }
    }
         public void baixaEstoque(int id, int qtd_nova) {
        try {

            String sql = "update tb_produtos  set qtd_estoque= ?  where id=?";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();

           // JOptionPane.showMessageDialog(null, "Produto Alterardo com Sucesso!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }
     
         public void adicionarEstoque(int id, int qtd_nova) {
        try {

            String sql = "update tb_produtos  set qtd_estoque= ?  where id=?";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();

           // JOptionPane.showMessageDialog(null, "Produto Alterardo com Sucesso!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }  
          public int retornaEstoqueAtual(int id) {
        try {
            int qtd_estoque = 0;

            String sql = "SELECT qtd_estoque from tb_produtos where id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {           
                qtd_estoque = (rs.getInt("qtd_estoque"));    
            }

            return qtd_estoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }
    public static void LimpaTela(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            }
        }
    }
}
