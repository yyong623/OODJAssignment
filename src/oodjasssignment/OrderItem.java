package oodjasssignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderItem extends Order{
    
    //Data Tpye
    public String productId, productName,type;
    public int orderItemQuantity;
    public double price;
    
    //Method  
    
    public void calculateFinalTotal(JLabel totalOutput, String name){
        double total = 0.0;
        //Get total price that stores in order list file
        Scanner scan;
        
        try{            
            scan = new Scanner(new File ("OrderList.txt"));
            
            while(scan.hasNextLine()){
                String lines = scan.nextLine();
                String[] price = lines.split("/");
                
                if(name.equals(price[1])){
                    double amount = Double.parseDouble(price[7]);
                    total = total + amount;
                }
            }
            totalOutput.setText(String.valueOf(String.format("%.2f", total)));
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    //Might not use (dlt later)
//    public void removeItems(String filePath, JTable table){
//        
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        table.setModel(model);
//        
//        int i = table.getSelectedRow();
//        
//        String tempFile = "temp.txt";
//        File oldFile = new File(filePath);
//        File newFile = new File(tempFile);
//        
//        String orderId = "";
//        String cusName = "";
//        String productId = "";
//        String productName = "";
//        String type = "";
//        String price = "";
//        String quantity = "";
//        String total = "";
//        if(i == 1){
//            model.removeRow(table.getSelectedRow());
//            try{
//                FileWriter fw = new FileWriter(tempFile,true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                PrintWriter pw = new PrintWriter(bw);
//
//                Scanner scan = new Scanner(new File(filePath));
//                scan.useDelimiter("[/\n}");
//
////                while(scan.hasNextLine()){
////                    String line = scan.nextLine();
////                    String[] array = line.split("/");
////
////                    if(!array[0].equals(i)){
////                        pw.println(orderId + "/" + cusName + "/" + productId + "/" + productName + "/" + type + "/" + price + "/" + quantity + "/" + total + "\n" );
////
////                    }
////
////                }
//                int flag = 1;
//                while(scan.hasNext()){
//                    orderId = scan.next();
//                    cusName = scan.next();
//                    productId = scan.next();
//                    productName = scan.next();
//                    type = scan.next();
//                    price = scan.next();
//                    quantity = scan.next();
//                    total = scan.next();
//                    
//                    if(flag == table.getSelectedRow()){                    
//                        pw.println(orderId + "/" + cusName + "/" + productId + "/" + productName + "/" + type + "/" + price + "/" + quantity + "/" + total + "\n" );
//                        JOptionPane.showMessageDialog(null, "Deleted");
//                    }else{
//                        JOptionPane.showMessageDialog(null, "Please Selected a row");
//                    }
//
//                }
//                scan.close();
//                pw.flush();
//                pw.close();
//                oldFile.delete();
//                File replace = new File(filePath);
//                newFile.renameTo(replace);           
//
//            }catch(IOException e){
//                System.out.println(e);
//            }
//        }else{
//            JOptionPane.showMessageDialog(null, "Please Set Item to delete");
//        }
//    }
    
}
