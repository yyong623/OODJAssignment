package oodjasssignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Order{
    
    //Data Type
    public String orderId, orderCustomerId, orderDate;
    public int orderNumber = 0;
    public float tax, totalPrice;
    
    
    
    //Method
    public void addOrder(JTable shoppingTable){
                                
        DefaultTableModel model = (DefaultTableModel)shoppingTable.getModel();
        
        int[] rowSelected = shoppingTable.getSelectedRows();
        
        Object[] row = new Object[5];
        
        try{
            //Random random = new Random();
            for(int i = 0; i < rowSelected.length; i++){

                    //To create random 
                    
                    //Show Product Id 
                    row[0] = model.getValueAt(rowSelected[i],0).toString();
                    
                    //Show Product Name
                    row[1] = model.getValueAt(rowSelected[i], 1).toString();
                    
                    //Show Price
                    row[2] = model.getValueAt(rowSelected[i], 2).toString();

                    //Show Quantity
                    row[3] = model.getValueAt(rowSelected[i], 3).toString();
                    
                    //Show Total of the quantity inserted
                    row[4] = model.getValueAt(rowSelected[i], 4).toString();

                    model.addRow(row);
                }

            //Get all the row to append into text file for recording
            
            FileWriter fw = new FileWriter("Order.txt",true);
            //Write to txt file
            fw.write( row[0] +"/"+  row[1] +"/"+ row[2] +"/"+  row[3] +"\n");
            
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

    //Generate Order ID
    public void randomId(JTextField textField){
        Random random = new Random();
        int n = random.nextInt(1000) + 1;
        String value = String.valueOf(n);  
        textField.setText(value);
    }
    
}
