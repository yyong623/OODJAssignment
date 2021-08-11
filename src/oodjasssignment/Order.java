package oodjasssignment;

public class Order {
    
    //Data Type
    public String orderId, orderCustomerId, orderDate;
    public int orderNumber = 0;
    public float tax, totalPrice;
    
    //Method
    public void addOrder(int orderNumber){
        if(orderNumber >= 0){
            orderNumber += 1;
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
