package oodjasssignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Order {

    //Variable
   
    public String orderId;         //Order Id
    public int orderNumber;     //Order Quantity Number
    public double totalPrice;   //Total Price Ordered
    boolean identifier;         //Boolean

    public int getOrderNumber() {
        return orderNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    //Object 
    Object[] row = new Object[8];
    
    Admin ad = new Admin();
    User user = new User();
    
    boolean areEqual = false;

    //Method
    public void addOrder(JTable shoppingTable, String orderId,String cusId,String id, String name, String type, String price, String quantity, JLabel quantityPre) {

        DefaultTableModel model = (DefaultTableModel) shoppingTable.getModel();

        Object[] columns = {"Product ID", "Product Name", "Type", "Price", "Quantity", "Total"};

        model.setColumnIdentifiers(columns);
        shoppingTable.setModel(model);

        try {
            check("OrderList.txt", id, cusId, orderId);  

            //Get Text to row
            row[6] = orderId;
            row[7] = cusId;
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

                    //Update on txt file (Update Method)
                    update(String.valueOf(qua), String.valueOf(newQua));

                    model.addRow(row); 

                    FileWriter fw = new FileWriter("OrderList.txt", true);
                    //Write to txt file (Order that has been selected)
                    fw.write(row[6] + "/" + row[7] + "/" + row[0] + "/" + row[1] + "/" + row[2] + "/" + row[3] + "/" + row[4] + "/" + row[5] + "/" + "\n");
                    //fw.write(Arrays.toString(row));
                    fw.flush();
                    fw.close();
                }  
            }else {
                JOptionPane.showMessageDialog(null, "Not enough Sufficient !");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    
    }

    public boolean check(String file, String id, String name, String orderId) {
        
        try (Scanner scanFile = new Scanner(new FileReader(file))) {
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                String[] words = line.split("/");
                
                if(name.equals(words[1]) && orderId.equals(words[0])){
                    if (id.equals(words[2])) {      //txt file index is [2]
                        JOptionPane.showMessageDialog(null, "You have entered this product");
                        identifier = true;                   
                        break;
                    } 
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
    

    //Generate Order ID
    public void randomId(JTextField textField) {
        Random random = new Random();
        int n = random.nextInt(1000) + 1;
        String value = String.valueOf(n);
        textField.setText(value);
        
    }
    
    //update the present amount
    public static void update(String OldText, String newText) {
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
    
    public void calculateFinalTotal(JLabel totalOutput, String name){
        double total = 0.0;
        //Get total price that stores in order list file
        Scanner scan;
        
        try{            
            scan = new Scanner(new File ("Order.txt"));
            
            while(scan.hasNextLine()){
                String lines = scan.nextLine();
                String[] price = lines.split("/");
                
                if(name.equals(price[1])){
                    double amount = Double.parseDouble(price[2]);
                    total = total + amount;
                }
            }
            totalOutput.setText(String.valueOf(String.format("%.2f", total)));
            scan.close();
        }catch(IOException e){
            System.out.println(e);
        }        
    }
}
