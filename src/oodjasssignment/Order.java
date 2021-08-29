package oodjasssignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
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
    boolean identifier = false;
    
    

    //Method
    public void addOrder(JTable shoppingTable, String id, String name, String type, String price, String quantity, JLabel quantityPre) {

        DefaultTableModel model = (DefaultTableModel) shoppingTable.getModel();

        Object[] columns = {"Product ID", "Product Name", "Type", "Price", "Quntity", "Total"};

        model.setColumnIdentifiers(columns);
        shoppingTable.setModel(model);

        try {
            Object[] row = new Object[6];

            //Get Text to row
            row[0] = id;
            row[1] = name;
            row[2] = type;
            row[3] = price;
            row[4] = quantity;

            double prices = Double.parseDouble(price);
            int unit;
            unit = Integer.parseInt(quantity);
            //Get the value of the exist quantity
            int qua = Integer.parseInt(quantityPre.getText());

            //Check if the input quantity is sufficient                
            if (unit <= 0) {
                JOptionPane.showMessageDialog(null, "Please Enter at least one amount !");

            } else if (qua >= unit) {

                //Write to txt file (Order that has been selected)
                //Get all the row to append into text file for recording
                String totalAmount = String.valueOf(unit * prices);
                row[5] = totalAmount;

                model.addRow(row);

                //Set the quantityPre to minus the enter unit
                int newQua = qua - unit;

                quantityPre.setText(String.valueOf(newQua));    //Assign new Quantity

                //Update on txt file
                update(String.valueOf(qua), String.valueOf(newQua));

                FileWriter fw = new FileWriter("Order.txt", true);
                //Write to txt file (Order that has been selected)
                fw.write(row[0] + "/" + row[1] + "/" + row[2] + "/" + row[3] + "/" + row[4] + "/" + row[5] + "\n");

            } else {
                JOptionPane.showMessageDialog(null, "Not enough Sufficient !");
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void readFile(String file) {
        Scanner scanRead;
        ArrayList<String> list = new ArrayList<String>();

        try {
            scanRead = new Scanner(new File(file));

            while (scanRead.hasNext()) {
                list.add(scanRead.next());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        HashSet<String> orderItemCheck = new HashSet<String>(list);
        
        
    }

    
    
    
    private static void delimitedData(String str) {
        String strs;

        String[] data = str.split("/");
    }

    public void check(String file, String id) {

        try (Scanner scanFile = new Scanner(new FileReader(file))) {
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                String[] words = line.split("/");

                if (id.equals(words[0])) {
                    JOptionPane.showMessageDialog(null, "You have entered this product");
                    identifier = true;
                }
            }
            scanFile.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

    }

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
