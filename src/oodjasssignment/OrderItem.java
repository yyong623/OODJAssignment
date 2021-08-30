package oodjasssignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OrderItem extends Order{
    
    //Data Tpye
    public String productId, productName,type;
    public int orderItemQuantity;
    public double price;
    
    //Method
    //Get Final Total Price
//    public void calculateTotalPayment(JTable table, JTextField textField){
//        double total = 0.0;
//        //Get row count
//        for (int i = 0; i < table.getRowCount(); i ++){
//            double amount = Double.parseDouble((table.getValueAt(i, 6).toString()));
//            total += amount;
//        }
//        
//        textField.setText(String.valueOf(total));
//    }  
    
    public void calculateFinalTotal(JTextField textField){
        double total = 0.0;
        //Get total price that stores in order list file
        Scanner scan;
        
        try{
            scan = new Scanner(new File ("OrderList.txt"));
            
            while(scan.hasNextLine()){
                String lines = scan.nextLine();
                String[] price = lines.split("/");
                double amount = Double.parseDouble(price[6]);
                total = total + amount;
                if (price[3].equals("Fragile")){
                    total = total + 5.00;
                }else if (price[3].equals("NonFragile")){
                    total = total;
                }
                
            }
            textField.setText(String.valueOf(String.format("%.2f", total)));
            
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        
    }

}
