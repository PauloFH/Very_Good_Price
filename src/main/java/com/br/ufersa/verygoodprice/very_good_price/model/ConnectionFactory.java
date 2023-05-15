/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ufersa.verygoodprice.very_good_price.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Paulo Roberto
 */
public class ConnectionFactory {
    
     public synchronized Connection getConnection() {
         try {
             return DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com/sql10618260","sql10618260","5vufPHwr5d");
             
         } catch (Exception e) {
              throw new RuntimeException(e);
         }
     
     }
         
    
    
}
