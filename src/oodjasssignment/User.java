package oodjasssignment;

//Import Exceptation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class User {
   
    Scanner scan = new Scanner(System.in);
    
    //Data Type
    static String name,email, phoneNum, mailingAdd;
    public static String username;
    protected String password;
    
    //Method
    
    //Setter and Getter Username & Password
    public static void setUsername(String username) {
        User.username = username;
    }
    
    public static String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
        
    //Setter and Getter Variable

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMailingAdd() {
        return mailingAdd;
    }

    public void setMailingAdd(String mailingAdd) {
        this.mailingAdd = mailingAdd;
    }
        
      
    //Search method
    public void searchProduct(JTable table, String valueToSearch){  
                       
        try{
            
            BufferedReader br = new BufferedReader(new FileReader("Product.txt"));
            
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            String line;
            int count = 0;
            while((line = br.readLine()) != null){
                
                if(line.contains(valueToSearch)){
                    String[] row = line.split("/");
                    model.addRow(row);
                    count++;
                }
            }
            if(count == 0){
               JOptionPane.showMessageDialog(null, "No Item Found !"); 
            }
       
        }catch(IOException e){
            System.out.println("File Not Found");
        }               
    }                

    public void viewOrder(JTable table){
        
        //Open File       
        
        int count = 0;
        try(BufferedReader bfr = new BufferedReader(new FileReader("Order.txt"))){
            
            
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            //Get line from txt file
            Object[] tableLine = bfr.lines().toArray();
                                    
            for(int i = 0 ; i < tableLine.length; i++){
                String[] line = tableLine[i].toString().split("/");
                model.addRow(line); 
                count++;
            }
            bfr.close();
        }catch (IOException e){
            System.out.println(e);
        }
        
        if(count == 0){
            JOptionPane.showMessageDialog(null, "No Order Found ! \n Please Go Add one.");
        }
    }
    
    public void deleteOrder(JTable orderListTable){
        DefaultTableModel model = (DefaultTableModel)orderListTable.getModel();
                
        //Get selected row index
        int rowSelected = orderListTable.getSelectedRow();
        //Check if a row is selected
        if(rowSelected == -1){ //return -1 if no row is selected
            
            JOptionPane.showMessageDialog(null, "Please Select an Item to delete");
        }else{
        
            model.removeRow(orderListTable.getSelectedRow());
            
            JOptionPane.showMessageDialog(null, "Item deleted");
        
            String tempFile = "tempOder.txt";
            File oldFile = new File ("Order.txt");
            File newFile = new File (tempFile);

            //Read text file to delete record as well

            try{
                FileWriter fw = new FileWriter(tempFile,true);

                BufferedWriter bw = new BufferedWriter(fw);

                for(int i = 0 ; i < orderListTable.getRowCount(); i ++){
                    for(int j = 0 ; j < orderListTable.getColumnCount(); j ++){ 

                            bw.write(orderListTable.getValueAt(i, j)+ "/");
                    }
                    bw.newLine();
                }
                bw.flush();
                bw.close();
                fw.close();
                oldFile.delete();
                File replace = new File("Order.txt");
                newFile.renameTo(replace);
                
            }catch(IOException e ){
                System.out.println(e);
            }
        }
}
    
//    //View Profile
//    public void viewProfile(JTextField textFd1, JTextField textFd2,JTextField textFd3,JTextField textFd4){
//        
//        String name, phoneNum, email, mailingAdd;
//        
//        try{
//            Scanner scan = new Scanner(new File("Customer.txt"));
//            
//            while(scan.hasNext()){
//                name = scan.next();
//                phoneNum = scan.next();
//                email = scan.next();
//                mailingAdd = scan.next();
//            }
//            
//                        
//            textFd1.setText(name);
//            textFd2.setText(phoneNum);
//            textFd3.setText(email);
//            textFd4.setText(mailingAdd);
//            
//        }catch(FileNotFoundException e){
//            System.out.println(e);
//        }
//    }
    
    public void editProfile(String OldText,String newText){
        File f1 = new File("Search.txt");
        
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
            String newWord = oldWord.replaceAll(OldText,newText );
            
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
