package oodjasssignment;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OrderItem extends Order{
    
    //Data Tpye
    public int orderId;
    public int orderItemQuantity;
    
    //Method - choose how many quantity 
    public void ItemOrderQuantity ( int orderItemQuantity ){
        DefaultTableModel model = new DefaultTableModel();
        
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
    
    public void calculateTotalPayment(JTable table, JTextField textField){
        double total = 0.0;
        //Get row count
        for (int i = 0; i < table.getRowCount(); i ++){
            double amount = Double.parseDouble((table.getValueAt(i, 5).toString()));
            total += amount;
        }
        
        textField.setText(String.valueOf(total));
    }
}
