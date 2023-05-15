/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ufersa.verygoodprice.very_good_price.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo Roberto
 */
public class ItemVenda {
        private Connection con;
      
        public ItemVenda() {
        this.con = new ConnectionFactory().getConnection();
    }
      
        
      public void cadastraItem(ItemVenda obj){
        
           try {

            String sql = "insert into tb_itensvendas (venda_id, produto_id,qtd,subtotal) values (?,?,?,?)";
          
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());;
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            stmt.execute();
            stmt.close();

           

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }
 
        
    }  
          public List<ItemVenda> listaItensPorVenda(int venda_id) {
        
        List<ItemVenda> lista = new ArrayList<>();
//        select p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i
//inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id =10; 
        try {
            String query = "select p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i "
                                 + " inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id = ? ";
       
            PreparedStatement ps = con.prepareStatement(query);         
            ps.setInt(1, venda_id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ItemVenda item = new ItemVenda();
                produtos prod = new produtos();
                
                 prod.setName(rs.getString("p.name"));
                 item.setQtd(rs.getInt("i.qtd"));
                 prod.setPrice(rs.getDouble("p.price"));
                 item.setSubtotal(rs.getDouble("i.subtotal"));
                
                 item.setProduto(prod);         
              
                
                lista.add(item);
            }
            return lista;
            
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

    public Vendas getVenda() {
        return venda;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    public produtos getProduto() {
        return produto;
    }

    public void setProduto(produtos produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    private int id;
    private Vendas venda;
    private produtos produto;
    private int qtd;
    private double subtotal;
}
