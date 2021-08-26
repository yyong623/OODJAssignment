package oodjasssignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Order{
    
    //Data Type
    public String orderId, orderCustomerId, orderDate;
    public int orderNumber = 0;
    public float tax, totalPrice;
    
    
    //Method
    public void addOrder(JTable searchViewProduct, JTable shoppingTable){
                                
        DefaultTableModel model = (DefaultTableModel)searchViewProduct.getModel();
        
        int[] rowSelected = searchViewProduct.getSelectedRows();
        
        Object[] row = new Object[3];
        
        //Add to shopping cart                       
        DefaultTableModel model1 = (DefaultTableModel) shoppingTable.getModel();
        try{
            //Random random = new Random();
            for(int i = 0; i < rowSelected.length; i++){

                    //To create random 
                    //int randomNum = random.nextInt(100) + 1;

                    //Show Product Id 
                    row[0] = model.getValueAt(rowSelected[i],0);
                    
                    //Show Product Name
                    row[1] = model.getValueAt(rowSelected[i], 1);
                    
                    //Show Price
                    row[2] = model.getValueAt(rowSelected[i], 2);

                    //Show Quantity
                    row[3] = model.getValueAt(rowSelected[i], 3);

                    model1.addRow(row);
                }

            //Get all the row to append into text file for recording
            
            FileWriter fw = new FileWriter("Order.txt",true);
            
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
    public void randomId(){
        Random random = new Random();
        int n = random.nextInt(1000);
        String value = String.valueOf(n);  
    }
    
}
