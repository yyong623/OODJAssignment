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
                
                if (line.equals("")){
                continue;
                }else{model.addRow(line); }
            }
            bfr.close();
    }catch (IOException e){
            System.out.println(e);
        }
        
    }
    
    public void addProd(String ProdId, String ProdName,String FragileCheck, String ProdPrice, String ProdQuan){
        String repeatProd = repeatChecker("Product.txt", ProdId);
        if (repeatProd == "false"){
            try {
               BufferedWriter bw = new BufferedWriter(new FileWriter("Product.txt",true));
              bw.write(ProdId +"/"+ProdName +"/"+FragileCheck +"/"+ ProdPrice +"/"+ProdQuan +"\n");
              JOptionPane.showMessageDialog(null,
                        "Added Successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                bw.flush();
               bw.close();
           } catch (IOException ex) {
              Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
        JOptionPane.showMessageDialog(null,
                        "Record Exist", "Error",
                        JOptionPane.INFORMATION_MESSAGE);
        }
    
    }
    
    public String repeatChecker(String FileName, String comparedUserName){
        String repeatIdentifier = "false";
        try (Scanner FileReader = new Scanner(new FileReader(FileName))){
        while (FileReader.hasNextLine())
        {
            String s = FileReader.nextLine();
            String[] sArray = s.split("/");
            
            if (comparedUserName.equals(sArray[0]))
            {
                JOptionPane.showMessageDialog(null,
                        "repeated", "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                repeatIdentifier = "true";
                break;
            }
            else
            {
                
            }
        }
        FileReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        return repeatIdentifier;

    }
        
    public void addCus(String name,String email,String phoneNum,String mailingAdd,String password){
        String repeatCus = repeatChecker("Customer.txt", name);
        if (repeatCus == "false"){
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter("Customer.txt",true));
            fw.write( name +"/"+ password  +"/"+ email +"/"+  mailingAdd +"/"+ phoneNum +"\n");
            fw.close();
            JOptionPane.showMessageDialog(null,
                        "Added Successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
        
        }
    }

    private static Scanner x;
    
    public void editItems(String filePath, String OldEntry1, String OldEntry2,String OldEntry3, String OldEntry4, String OldEntry5,
            String newEntry1, String newEntry2,String newEntry3, String newEntry4, String newEntry5){
    File oldFile = new File(filePath);
    
    File newFile = new File("temp.txt");
    String Entry1 ="", Entry2 = "", Entry3 = "", Entry4="",Entry5="";
    
    try{
        FileWriter fw = new FileWriter(newFile, true);
        BufferedWriter bw = new BufferedWriter( fw );
        PrintWriter pw = new PrintWriter(bw);
        x = new Scanner(new File(filePath));
        x.useDelimiter("[/\n]");
        

        while(x.hasNext()){
           Entry1 = x.next();
           
           Entry2 = x.next();
           
           Entry3 = x.next();
           
           Entry4 = x.next();
           
           Entry5 = x.next();
           
           if (Entry1.equals(OldEntry1) || Entry2.equals(OldEntry2) ||Entry3.equals(OldEntry3) || Entry4.equals(OldEntry4) || Entry5.equals(OldEntry5)){
        pw.println(newEntry1 +"/"+  newEntry2 +"/"+newEntry3 +"/"+ newEntry4 +"/"+  newEntry5 );
        
        }
           else{
               pw.println(Entry1 +"/"+  Entry2 +"/"+Entry3 +"/"+ Entry4 +"/"+Entry5 );
               
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
    
    
public void exportChanges(JTable table, String filePath) throws IOException{
    
    File newFile = new File("temp.txt");
    File oldFile = new File(filePath);
    FileWriter fw = new FileWriter(newFile, true);
    BufferedWriter bw = new BufferedWriter(fw);
    for (int i = 0; i<table.getRowCount();i++){
        for (int j = 0;j<table.getColumnCount();j++){
            bw.write(table.getModel().getValueAt(i,j)+"/");
        }
    bw.write("\n");
    }
    oldFile.delete();
    File dump = new File(filePath);
    newFile.renameTo(dump);
    }
    

}
