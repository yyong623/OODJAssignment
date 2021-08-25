package oodjasssignment;

import java.io.FileWriter;
import java.io.IOException;

public class Order{
    
    //Data Type
    public String orderId, orderCustomerId, orderDate;
    public int orderNumber = 0;
    public float tax, totalPrice;
    
    //Method
    public void addOrder(){
        try{
            FileWriter fw = new FileWriter("Order.txt",true);
            
            fw.write(orderId +"/"+ orderNumber +"/"+ orderDate +"/"+ totalPrice +"\n");
            
            fw.close();
        }catch(IOException e){
            System.out.println(e);
        }
        
        
    }
    
    public void deleteOrder(int orderNumber){
        if(orderNumber != 0){
            orderNumber -= 1;
        }else{
            //Remove the order from the storage 
        }        
    }
    
    public float calculateTax (float tax){
        return tax = (float) (totalPrice * 0.6);
    } 

}
