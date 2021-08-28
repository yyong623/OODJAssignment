package oodjasssignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Order{
    
    //Data Type
    public String orderId, orderCustomerId, orderDate;
    public int orderNumber = 0;
    public float tax, totalPrice;
    
    
    
    //Method
    public void addOrder(JTable shoppingTable, JLabel id, JLabel name, JLabel type, JLabel price, JTextField quantity, JLabel quantityPre){
                                
        DefaultTableModel model = (DefaultTableModel)shoppingTable.getModel();
                
        Object[] columns = {"Product ID","Product Name","Type","Price","Quntity","Total"};
        
        model.setColumnIdentifiers(columns);
        shoppingTable.setModel(model);
        
                
        try{            
            Object [] row = new Object[6];

            //Get Text to row
            row[0] = id.getText();
            row[1] = name.getText();
            row[2] = type.getText();
            row[3] = price.getText();
            row[4] = quantity.getText();            

            double prices = Double.parseDouble(price.getText());
            int unit = Integer.parseInt(quantity.getText());
            //Get the value of the exist quantity
            int qua = Integer.parseInt(quantityPre.getText());
                        
//            HashSet <String> hs = new HashSet <String>();               
//            Scanner scanFile = new Scanner(new FileReader("Order.txt"));
            
//            while(scanFile.hasNext()){
//                
//            }
                
//            boolean i = scanFile.hasNext();
                
//            if(i == hs.contains(row[1])){
//                JOptionPane.showMessageDialog(null, "NONONO");
//            }else{
                //Check if the input quantity is sufficient
            if(qua > unit && unit > 0){  

                String totalAmount = String.valueOf(unit * prices);
                row[5] = totalAmount;

                model.addRow(row);

                //Get all the row to append into text file for recording            
                FileWriter fw = new FileWriter("Order.txt",true);
                //Write to txt file (Order that has been selected)
                fw.write( row[0] +"/"+  row[1] +"/"+ row[2] +"/"+  row[3] +"/"+  row[4] + row[5] +"\n");
                fw.close(); 

                //Set the quantityPre to minus the enter unit
                int newQua = qua - unit;

                quantityPre.setText(String.valueOf(newQua));    //Assign new Quantity

                //Update on txt file
                update(String.valueOf(qua),String.valueOf(newQua));

            }else{
                JOptionPane.showMessageDialog(null, "Not enough Sufficient !");
            }
            
            
                    
            //}
        }catch(IOException e){
            System.out.println(e);
        }        
    }
    
//    public void calculateTotalPayment(JTable table, JTextField text){
//        double total = 0;
//        
//        //Get row count
//        for (int i = 0; i < table.getRowCount(); i ++){
//            double amount = Double.parseDouble(table.getValueAt(i, 4).toString());
//            total += amount;
//        }        
//        text.setText(String.valueOf(total));
//    }  
    
//    public void deleteOrder(int orderNumber){
//        if(orderNumber != 0){
//            orderNumber -= 1;
//        }else{
//            //Remove the order from the storage 
//        }        
//    }
//    
//    public float calculateTax (float tax){
//        return tax = (float) (totalPrice * 0.6);
//    } 

    //Generate Order ID
    public void randomId(JTextField textField){
        Random random = new Random();
        int n = random.nextInt(1000) + 1;
        String value = String.valueOf(n);  
        textField.setText(value);
    }   
    
    public void update(String OldText,String newText){
        File f1 = new File("Product.txt");
        
        //Give oldText a null string to keep word
        String oldWord = "";
        
        BufferedReader bfr = null;
        
        FileWriter fw = null;
        
        try{
            bfr = new BufferedReader(new FileReader(f1));            
            //Read all line in the file
            String line = bfr.readLine();
            
            //If name is updated
            while(line != null){
                oldWord = oldWord + line + System.lineSeparator();
                
                line = bfr.readLine();
            }
            
            //Replace oldText into newText
            String newWord = oldWord.replaceAll(OldText,newText);
            
            //Rewrite the text file with new data
            fw = new FileWriter(f1);
            
            fw.write(newWord);
            
        }catch (IOException e){
            System.out.println(e);
        }finally{
            //Close all the reader and writer
            try {
                bfr.close();
                fw.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}
