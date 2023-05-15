/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ufersa.verygoodprice.very_good_price.model;

import javax.swing.JOptionPane;

/**
 *
 * @author Paulo Roberto
 */
public class testConnection {
    
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null,"Connectado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Não foi connectado, erro: "+e);
        }
    }
}
