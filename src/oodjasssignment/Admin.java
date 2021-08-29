/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodjasssignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
            }
            else
            {
                FileReader.close();
            }
        }FileReader.close();

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
    private static Scanner x;
    
    public void editProds(String filePath, String OldProdId, String OldProdName,String OldFragileCheck, String OldProdPrice, String OldProdQuan,
            String newProdId, String newProdName,String newFragileCheck, String newProdPrice, String newProdQuan){
    String TempFile = "temp.txt";
    File oldFile = new File(filePath);
    File newFile = new File(TempFile);
    String ID = ""; String Name = ""; String Fragile = ""; String Price = "";String ProdQuan = "";
    try{
        FileWriter fw = new FileWriter(TempFile,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        x = new Scanner(new File(filePath));
        x.useDelimiter("[/,\n]");
        while(x.hasNext()){
        ID =x.next();
        Name = x.next();
        Fragile = x.next();
        Price = x.next();
        ProdQuan = x.next();
        if (ID.equals(OldProdId)){
            pw.println( newProdId +"/"+  newProdName +"/"+newFragileCheck +"/"+ newProdPrice +"/"+  newProdQuan +"\n");
        }
        else{
            pw.println(OldProdId +"/"+  OldProdName +"/"+OldFragileCheck +"/"+ OldProdPrice +"/"+  OldProdQuan +"\n");
        }
        }
        x.close();
        pw.flush();
        pw.close();
        oldFile.delete();
        File dump = new File(filePath);
        newFile.renameTo(dump);
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,
                        "Error", "Error",
                        JOptionPane.INFORMATION_MESSAGE);
    }
    }
}
