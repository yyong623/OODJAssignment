  package oodjasssignment;

public class OrderItem extends Order{
    
    //Data Tpye
    public int orderId;
    public int orderItemQuantity;
    
    //Method - choose how many quantity 
    public void addItemOrder ( int orderItemQuantity ){
        if(orderItemQuantity >= 0){
            orderItemQuantity += 1;
        }
    }
    
    public void deleteItemOrder(int orderItemQuantity){
        if(orderItemQuantity != 0){
            orderItemQuantity -= 1;
        }else{
           //Remove the item from the order list
        }
    }
    
    public void calculateTotalPayment(){
        super.totalPrice = (super.orderNumber * super.tax * orderItemQuantity);
    }
}
