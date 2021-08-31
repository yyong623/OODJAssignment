package oodjasssignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Order {

    //Data Type
    public String productName;
    public int orderId,productId;
    public int orderNumber;
    public double totalPrice;
    boolean identifier;

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", productId=" + productId + ", productName=" + productName + ", orderNumber=" + orderNumber + ", totalPrice=" + totalPrice + '}';
    }
    
    Object[] row = new Object[7];
    
    Admin ad = new Admin();
    User user = new User();
    
    boolean areEqual = true;

    //Method
    public void addOrder(JTable shoppingTable, String orderId,String id, String name, String type, String price, String quantity, JLabel quantityPre) {

        DefaultTableModel model = (DefaultTableModel) shoppingTable.getModel();

        Object[] columns = {"Order ID","Product ID", "Product Name", "Type", "Price", "Quntity", "Total"};

        model.setColumnIdentifiers(columns);
        shoppingTable.setModel(model);

        try {
            check("OrderList.txt", id);
            
            //Get Text to row
            row[6] = orderId; 
            row[0] = id;
            row[1] = name;
            row[2] = type;
            row[3] = price;
            row[4] = quantity;
                        
            double prices = Double.parseDouble(price);
            int unit = Integer.parseInt(quantity);
            //Get the value of the exist quantity
            int qua = Integer.parseInt(quantityPre.getText());

            if (unit <= 0) {
                JOptionPane.showMessageDialog(null, "Please Enter at least one amount !");

            } else if (qua >= unit) {

                if(identifier == false){    //This is where no duplication occur

                    
                    if(row[2].equals("Fragile")){
                        //Write to txt file (Order that has been selected)
                        //Get all the row to append into text file for recording 
                        String totalAmount = String.valueOf((unit * prices) + 5.0);
                        row[5] = totalAmount;
                    }else if(row[2].equals("NonFragile")){
                        String totalAmount = String.valueOf((unit * prices));
                        row[5] = totalAmount;
                    }
                    //Set the quantityPre to minus the enter unit
                    int newQua = qua - unit;

                    quantityPre.setText(String.valueOf(newQua));    //Assign new Quantity

                    //Update on txt file
                    update(String.valueOf(qua), String.valueOf(newQua));

                    model.addRow(row); 

                    FileWriter fw = new FileWriter("OrderList.txt", true);
                    //Write to txt file (Order that has been selected)
                    fw.write(row[6] + "/" + row[0] + "/" + row[1] + "/" + row[2] + "/" + row[3] + "/" + row[4] + "/" + row[5] + "/" + "\n");
                    //fw.write(Arrays.toString(row));
                    fw.close();
                }  
            }else {
                JOptionPane.showMessageDialog(null, "Not enough Sufficient !");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    
    }

    public boolean check(String file, String id) {
        
        //flag = 0;
        try (Scanner scanFile = new Scanner(new FileReader(file))) {
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                String[] words = line.split("/");

                if (id.equals(words[1])) {
                    JOptionPane.showMessageDialog(null, "You have entered this product");
                    identifier = true;                   
                    break;
                }else{
                    identifier = false;
                }
                
            }
            scanFile.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return identifier;
    }
    
    //Get Customer Id and compare to txt file
    public void compareCusId( String cusName, JTable tableBill){
        Scanner sc;          
        try {
            DefaultTableModel model = (DefaultTableModel) tableBill.getModel();
//            
            Object[] list = new Object[3];
//            Object[] columns = {"Order ID","Total"};
//            model.setColumnIdentifiers(list);
            tableBill.setModel(model);
            
            sc = new Scanner(new FileReader("Order.txt"));
            int flag = 0;
            while(sc.hasNextLine()){
                String line1 = sc.nextLine();
                String[] Array = line1.split("/");
              
                //Compare
                if(Array[0].contains(cusName)){   
                    flag = 1; 
                    //Print the orderId and total
                    InterfaceShoppingCart shoppingCart = new InterfaceShoppingCart();
                    shoppingCart.setVisible(true); 
                    shoppingCart.pack();
                    shoppingCart.setLocationRelativeTo(null);
                    
                    System.out.println("HI");
                    
                    ViewOrder("Order.txt",tableBill,cusName);
                  
                    
//                }else{
//                    JOptionPane.showMessageDialog(null, "You have no order");
//                    flag = 0;
//                }
                    break;
                }
            }
            
//            if(flag == 1){                       
//                ViewOrder("Order.txt", tableBill,cusName);
//            }
        }catch(IOException e){
            System.out.println(e);
        }               
    }


    public void ViewOrder(String fileName, JTable tableBill, String cusId){
        //get all the customer id (compare) and print customer's order Id (All Order)
        
        try(BufferedReader bfr = new BufferedReader(new FileReader(fileName))){            
            
            DefaultTableModel model = (DefaultTableModel)tableBill.getModel();
            
            Object[] tableLine = bfr.lines().toArray();
            
//            for(int k = 0 ; k < tableLine.length; k++){
//                String [] dataRow = tableLine[k].toString().split("/");
//                model.addRow(dataRow);
//                System.out.println("hi");
//                if(cusId.equals(model.getValueAt(k, 0))){  
//                    System.out.println("bye");
//                    model.addRow(dataRow);
//                }
//            }
            
            
            
            
            //Get line from txt file
//            Object[] tableLine = bfr.lines().toArray();
                                    
                for(int i = 0 ; i < tableLine.length; i++){                
                    String[] dataRow = tableLine[i].toString().split("/");
    //                if(cusId.contains(dataRow[0])){
                        model.addRow(dataRow);    
    //                }
                }
            bfr.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
    
//    //Compare two file 
//    public void FindOrderId(String olFile, String oFile, JTable table){
//        Scanner sc1;  
//                
//        try {
//            sc1 = new Scanner(new FileReader(olFile));
//        
//            Scanner sc2 = new Scanner(new FileReader(oFile));
//
//            while(sc1.hasNextLine() && sc2.hasNextLine()){
//                String line1 = sc1.nextLine();
//                String line2 = sc2.nextLine();
//
//                String[] olArray = line1.split("/");
//                String[] oArray = line2.split("/");
//                
//                //Compare
//                if(oArray[1].equals(olArray[0])){
//                    //if equal print that line 
//                    areEqual = true;
//                }else{
//                    areEqual = false;
//                }
//                sc1.nextLine();
//                sc2.nextLine();
//            }
//            
//            //if the order Id in both order and orderlist is same get all the line
//            //Set the lines into table of bill
//            if(areEqual == true){
//                ViewOrderCart(olFile, table);
//            }else{
//                JOptionPane.showMessageDialog(null, "You have no order");
//            }
//            
//            sc1.close();
//            sc2.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
            
//    public void ViewOrderCart (String fileName , JTable table){
//
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//        int rowCount = model.getRowCount();
//
//        Object[][] list = new Object[rowCount][];
//
//        model.setColumnIdentifiers(list);
//        table.setModel(model);
//
//        try{
//            Scanner sRead = new Scanner (new File (fileName));
//
//            while(sRead.hasNextLine()){
//                String line = sRead.nextLine();
//                String[] array = line.split("/");
//
//                for( int i = 0 ; i < rowCount ; i++){
//                    list[i][0] = array[1];
//                    list[i][1] = array[2];
//                    list[i][2] = array[3];
//                    list[i][3] = array[4];
//                    list[i][4] = array[5];
//                    list[i][5] = array[6];
//
//                    model.addRow(list);
//                }
//            }
//        }catch(FileNotFoundException e){
//            System.out.println(e);
//        }
//    }

    //Generate Order ID
    public void randomId(JTextField textField) {
        Random random = new Random();
        int n = random.nextInt(1000) + 1;
        String value = String.valueOf(n);
        textField.setText(value);
        
    }

    public void update(String OldText, String newText) {
        File f1 = new File("Product.txt");

        //Give oldText a null string to keep word
        String oldWord = "";

        BufferedReader bfr = null;

        FileWriter fw = null;

        try {
            bfr = new BufferedReader(new FileReader(f1));
            //Read all line in the file
            String line = bfr.readLine();

            //If name is updated
            while (line != null) {
                oldWord = oldWord + line + System.lineSeparator();

                line = bfr.readLine();
            }

            //Replace oldText into newText
            String newWord = oldWord.replaceAll(OldText, newText);

            //Rewrite the text file with new data
            fw = new FileWriter(f1);

            fw.write(newWord);

        } catch (IOException e) {
            System.out.println(e);
        } finally {
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
