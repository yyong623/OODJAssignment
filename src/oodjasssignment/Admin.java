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
        boolean repeatProd = repeatChecker("Product.txt", ProdId);
        if (repeatProd = false){
            try {
               FileWriter fw = new FileWriter("Product.txt",true);
              fw.write( ProdId +"/"+  ProdName +"/"+FragileCheck +"/"+ ProdPrice +"/"+  ProdQuan +"\n");
              JOptionPane.showMessageDialog(null,
                        "Record Added Successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
               fw.close();
           } catch (IOException ex) {
              Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
        
        }
    
    }
    
    public boolean repeatChecker(String FileName, String comparedUserName){
        
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
                repeatIdentifier = true;
                break;
            }
            else
            {
                repeatIdentifier = false;
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
        boolean repeatCus = repeatChecker("Customer.txt", name);
        if (repeatCus = false){
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
    
    public void editItems(String filePath, String OldEntry1, String OldEntry2,String OldEntry3, String OldEntry4, String OldEntry5,
            String newEntry1, String newEntry2,String newEntry3, String newEntry4, String newEntry5){
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
        if (ID.equals(OldEntry1)){
            pw.println( newEntry1 +"/"+  newEntry2 +"/"+newEntry3 +"/"+ newEntry4 +"/"+  newEntry5 );
        }
        else{
            pw.println(OldEntry1 +"/"+  OldEntry2 +"/"+OldEntry3 +"/"+ OldEntry4 +"/"+  OldEntry5 );
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
