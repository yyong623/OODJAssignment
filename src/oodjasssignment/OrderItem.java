package oodjasssignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OrderItem extends Order{
    
    //Data Tpye
    public String productId, productName,type;
    public int orderItemQuantity;
    public double price;
    boolean identifier = false;
    
    //Method
    //Get Final Total Price
    public void calculateTotalPayment(JTable table, JTextField textField){
        double total = 0.0;
        //Get row count
        for (int i = 0; i < table.getRowCount(); i ++){
            double amount = Double.parseDouble((table.getValueAt(i, 5).toString()));
            total += amount;
        }
        
        textField.setText(String.valueOf(total));
    }
    
    
    //Get all data in txt file to array      
//    public static String[] readArray(String file){
//        
//        int totalChar = 0 ;
//        try{
//            Scanner s1 = new Scanner(new File(file));
//            while (s1.hasNextLine()){
//                totalChar = totalChar + 1;
//                s1.next();
//            }
//            
//            String[] word = new String [totalChar];
//            
//            Scanner s2 = new Scanner(new File(file));
//            
//            for ( int i = 0; i < totalChar ; i ++){
//                word[i] = s2.next();
//            }
//            return word;
//            
//        }catch(FileNotFoundException e){
//            System.out.println(e);
//        }
//        return null;
//    }
    
    
}
