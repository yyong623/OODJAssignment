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
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class User {
   
    Scanner scan = new Scanner(System.in);
    
    //Data Type
    public String name,email, phoneNum, mailingAdd;
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
            Scanner scan = new Scanner(new File("Product.txt"));

            //Read line by line using Scanner
            while(scan.hasNextLine()){
                valueToSearch = scan.nextLine();
                if(valueToSearch.contains(scan.nextLine())){
                    String result = valueToSearch;
                                        
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
                table.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter(valueToSearch.trim()));
            }
            scan.close();
        }catch(FileNotFoundException e){
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
            System.out.println("No Order Found !");
        }
    }
    
    public void deleteOrder(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
                
        //Get selected row index
        int rowSelected = table.getSelectedRow();
        //Check if a row is selected
        if(rowSelected >= 0){
            model.removeRow(rowSelected);
            JOptionPane.showMessageDialog(null, "Item deleted");
            
            //Read text file to delete record as well
            try{
                FileWriter fw = new FileWriter("Order.txt",true);
                
                BufferedWriter bfr = new BufferedWriter(fw);
                            
                //PrintWriter pw = new PrintWriter(bfr);
                
                //Get all the row and col from the jTable
                for (int i = 0 ; i < table.getRowCount() ; i++){
                    for(int j = 0 ; j < table.getRowCount(); j++){
                        bfr.write(table.getValueAt(i, j).toString()+ " / ");
                    }
                    bfr.newLine();
                }
                bfr.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(null,e);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Please Select an Item to delete");
        }
        
    }
    
    public void editProfile(String name, String phoneNum, String email, String mailingAdd){
//        try{
//            FileReader fr = new FileReader("database.txt");
//
//            BufferedReader bfr = new BufferedReader(new FileReader(f1));
//
//            FileWriter fw = null;
//        
//            
//            
//            
//            //Read all line in the file
//            String line = bfr.readLine();
//            
//            //If name is updated
//            while(line != null){
//                oldWord = oldWord + line + System.lineSeparator();
//                
//                line = bfr.readLine();
//            }
//            
//            //Replace oldText into newText
//            String newWord = oldWord.replaceAll(oldText, newText);
//            
//            //Rewrite the text file with new data
//            fw = new FileWriter(f1);
//            
//            fw.write(newWord);
//            
//        }catch (IOException e){
//            System.out.println(e);
//        }finally{
//            //Close all the reader and writer
//            try {
//                bfr.close();
//                fw.close();
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//        }
    }
}
