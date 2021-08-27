/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodjasssignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
/**
 *
 * @author ASUS
 */
public class Admin extends User {
    public int AdminId;

    public void setAdminId(int AdminId) {
        this.AdminId = AdminId;
    }  
    
    public void viewCus(JTable ViewCusTable){
    try (BufferedReader bfr = new BufferedReader(new FileReader("Customer.txt"))){
                DefaultTableModel model = (DefaultTableModel)ViewCusTable.getModel();
            //Get line from txt file
            Object[] tableLine = bfr.lines().toArray();
                                    
            for(int i = 0 ; i < tableLine.length; i++){
                String[] line = tableLine[i].toString().split("/");
                model.addRow(line); 
            }
            bfr.close();
    }catch (IOException e){
            System.out.println(e);
        }
        
    }
    public void viewProd(JTable ViewProdTable){
    try (BufferedReader bfr = new BufferedReader(new FileReader("Product.txt"))){
                DefaultTableModel model = (DefaultTableModel)ViewProdTable.getModel();
            //Get line from txt file
            Object[] tableLine = bfr.lines().toArray();
                                    
            for(int i = 0 ; i < tableLine.length; i++){
                String[] line = tableLine[i].toString().split("/");
                model.addRow(line); 
            }
            bfr.close();
    }catch (IOException e){
            System.out.println(e);
        }
        
    }
    
    public void addProd(String ProdId, String ProdName, String ProdPrice, String ProdQuan){
        
        try {
            FileWriter fw = new FileWriter("Product.txt",true);
            fw.write( ProdId +"/"+  ProdName +"/"+ ProdPrice +"/"+  ProdQuan +"\n");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    public void addCus(String name,String email,String phoneNum,String mailingAdd,String password){
        try {
            FileWriter fw = new FileWriter("Customer.txt",true);
            fw.write( name +"/"+ password  +"/"+ email +"/"+  mailingAdd +"/"+ phoneNum +"\n");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
