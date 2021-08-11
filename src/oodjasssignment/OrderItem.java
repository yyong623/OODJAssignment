package oodjasssignment;

public class OrderItem extends Order{
    
    //Data Tpye
    public int orderItemNumber;
    
    //Method
    public void addItemOrder ( int orderItemNumber ){
        if(orderItemNumber >= 0){
            orderItemNumber += 1;
        }
    }
    
    public void deleteItemOrder(int orderItemNumber){
        if(orderItemNumber != 0){
            orderItemNumber -= 1;
        }else{
           //Remove the item from the order list
        }
    }
}
