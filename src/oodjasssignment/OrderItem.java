package oodjasssignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.JTextField;

public class OrderItem extends Order{
    
    //Data Tpye
    public String productId, productName,type;
    public int orderItemQuantity;
    public double price;
    
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
    
    public void calculateFinalTotal(){
        double total = 0.0;
        //Get total price that stores in order list file
        Scanner scan;
        
        try{
            scan = new Scanner(new File ("OrderList.txt"));
            
            while(scan.hasNextLine()){
                String lines = scan.nextLine();
                String[] price = lines.split("/");
                
                total = total + Double.parseDouble(price[6]);
            }
            System.out.println("Total : " +total%.2f);
            
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        
    }
}
