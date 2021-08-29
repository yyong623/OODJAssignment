/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodjasssignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
/**
 *
 * @author ASUS
 */
public class Admin extends User {
    public int AdminId;
    public static boolean repeatIdentifier;

    public void setAdminId(int AdminId) {
        this.AdminId = AdminId;
    }  
    
    
    public static void viewTable(JTable ViewProdTable, String txtFileName){
    try (BufferedReader bfr = new BufferedReader(new FileReader(txtFileName))){
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
    
    public void addProd(String ProdId, String ProdName,String FragileCheck, String ProdPrice, String ProdQuan){
        repeatChecker("Product.txt", name);
        if (repeatIdentifier = false){
            try {
               FileWriter fw = new FileWriter("Product.txt",true);
              fw.write( ProdId +"/"+  ProdName +"/"+FragileCheck +"/"+ ProdPrice +"/"+  ProdQuan +"\n");
               fw.close();
           } catch (IOException ex) {
              Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
        
        }
    
    }
    
    public void repeatChecker(String FileName, String comparedUserName){
        Scanner FileReader = new Scanner(FileName);
        repeatIdentifier = false;   
        while (FileReader.hasNextLine())
        {
            String s = FileReader.nextLine();
            String[] sArray = s.split("/");
            
            if (comparedUserName.equals(sArray[0]))
            {
                JOptionPane.showMessageDialog(null,
                        "repeated", "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                repeatIdentifier = true;
                FileReader.close();
                
            }
            else
            {
                FileReader.close();
            }
        }

    }
        
    public void addCus(String name,String email,String phoneNum,String mailingAdd,String password){
        repeatChecker("Customer.txt", name);
        if (repeatIdentifier = false){
        try {
            FileWriter fw = new FileWriter("Customer.txt",true);
            fw.write( name +"/"+ password  +"/"+ email +"/"+  mailingAdd +"/"+ phoneNum +"\n");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
        
        }
    }
}
