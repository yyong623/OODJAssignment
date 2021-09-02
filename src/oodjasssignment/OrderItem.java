package oodjasssignment;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JLabel;

public class OrderItem extends Order{
    
    //Data Tpye
    public String productName, type;            //Product Name, Product Type (Fragile/NonFragile)    
    public int orderItemQuantity, productId;    //QUantity choosen, Product Id
    public double price;                        //Total price of choosen by the quantity insert
    
    //Method

    /**
     * @param totalOutput
     * @param name
     */
    @Override
    public void calculateFinalTotal(JLabel totalOutput, String name){
        double total = 0.0;
        //Get total price that stores in order list file
        Scanner scan;
        
        try{            
            scan = new Scanner(new File ("OrderList.txt"));
            
            while(scan.hasNextLine()){
                String lines = scan.nextLine();
                String[] price = lines.split("/");
                
                if(name.equals(price[1])){
                    double amount = Double.parseDouble(price[7]);
                    total = total + amount;
                }
            }
            totalOutput.setText(String.valueOf(String.format("%.2f", total)));
            scan.close();
        }catch(IOException e){
            System.out.println(e);
        }        
    } 
}
