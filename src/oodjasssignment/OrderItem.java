package oodjasssignment;

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
}
