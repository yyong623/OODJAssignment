package oodjasssignment;

//Import File Buffered reader and writer
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;   //Import file not found exception
import java.io.IOException;

import java.io.FileReader;      //Import file writer and reader 
import java.io.FileWriter;

import java.util.Scanner;

import javax.swing.JOptionPane;     //JFrame import 
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel; //Table Model

public class User {
   
    Scanner scan = new Scanner(System.in);    
    
    //Data Type
    static String name,email, phoneNum, mailingAdd;     //Customer Name, Email, Phone Number, Mailing Address
    public static String username;                      //Customer UserName (to enter the system)
    private static String password;                          //Customer Passwords (strong and unique)
    static boolean loginIdentifier;
    
    //Setter and Getter Username & Password

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }       
        
    //Setter and Getter Variable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        User.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        User.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        User.phoneNum = phoneNum;
    }

    public String getMailingAdd() {
        return mailingAdd;
    }

    public void setMailingAdd(String mailingAdd) {
        User.mailingAdd = mailingAdd;
    }

    public User() {
    }
    
    
    //Method
    //Search method
    public void search(JTable table, String valueToSearch, String FileName){  
                       
        try{            
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            String line;
            int count = 0;
            while((line = br.readLine()) != null){  //Read line in the buffered reader
                
                if(line.contains(valueToSearch)){
                    String[] row = line.split("/");
                    model.addRow(row);
                    count++;
                }
            }
            if(count == 0){
               JOptionPane.showMessageDialog(null, "No Item Found !"); 
            }
            br.close();
        }catch(IOException e){
            System.out.println("File Not Found");
        }               
    }                

    public void viewOrder(JTable table, String fileName){
        
        //Open File
        int count = 0;
        try(BufferedReader bfr = new BufferedReader(new FileReader(fileName))){
            
            
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            //Get line from txt file
            Object[] tableLine = bfr.lines().toArray();
            
            //Loop Through the exists line
            for (Object tableLine1 : tableLine) {   //for(int i = 0 ; i < tableLine.length; i++){
                String[] line = tableLine1.toString().split("/");
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
    
    public void deleteOrder(JTable orderListTable, String FileName, String orderId, String cusId){
        
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
        
        //Get selected row index
        int rowSelected = orderListTable.getSelectedRow();
        //Check if a row is selected
        if(rowSelected == -1){      //Return -1 if no row is selected
            JOptionPane.showMessageDialog(null, "Please Select an Item to delete");
        }else{
            JOptionPane.showMessageDialog(null, "Item Deleted");
            
            model.removeRow(orderListTable.getSelectedRow());
            
            //Read text file to delete record 
            String tempFile = "tempOrder.txt";
            File oldFile = new File(FileName);
            File newFile = new File(tempFile);
            
            try{
                FileWriter fw1 = new FileWriter(tempFile,true);
                
                BufferedWriter bw1 = new BufferedWriter(fw1);
                
                for (int i = 0; i < orderListTable.getRowCount(); i++){
                    bw1.write(orderId +"/"+ cusId +"/");
                    for (int j = 0 ; j < orderListTable.getColumnCount(); j++){
                        bw1.write(orderListTable.getValueAt(i,j).toString()+"/");
                    }
                    bw1.newLine();
                }
                bw1.flush();                
                fw1.flush();
                bw1.close();
                fw1.close();
                
                oldFile.delete();
                File replace = new File(FileName);
                newFile.renameTo(replace);
                
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
    
    public void edit(String fileName, String OldText,String newText,JTextField text){
        
        File f1 = new File(fileName);
        
        //Give oldText a null string to keep word
        String oldWord = "";        
        BufferedReader bfr;        
        FileWriter fw;
        
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
            JOptionPane.showMessageDialog(null, "Successfully Updated");
            bfr.close();
            fw.close();
        }catch (IOException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Update Failed");
        }
    }
    
//View Profile

    /**
     *
     * @param fileName
     * @param name
     * @param phoneNum
     * @param email
     * @param mailingAdd
     */
    public void viewProfile(String fileName, JTextField name, JTextField phoneNum, JTextField email, JTextField mailingAdd){
        
        BufferedReader br ;        
        
        try{
            String lines;
            br = new BufferedReader(new FileReader(fileName));
            
            //int count = 0;
            while((lines = br.readLine()) != null){
                String[] array = lines.split("/"); 
                
                    name.setText(array[0]);
                    phoneNum.setText(array[4]);
                    email.setText(array[2]);
                    mailingAdd.setText(array[3]);                
            }
            br.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public static void loginFunc(String userFile,String Navigator,String username,String Password){
        CustomerInterface CustomerInterface = new CustomerInterface();
        AdminInterface AdminInterface = new AdminInterface();
        File loginScan = new File(userFile);
        try {
            Scanner read = new Scanner(loginScan);
            
            while (read.hasNextLine()){
                 
                String s = read.nextLine();  
                String[] sArray = s.split("/");
                 
                if (Password.equals(sArray[1]) && username.equals(sArray[0])){
                    JOptionPane.showMessageDialog(null,
                        "Login Successful", "Success",
                        JOptionPane.INFORMATION_MESSAGE); 
                    
                    if (Navigator == "Customer"){
                         

                        CustomerInterface.TextFieldProfileName.setText(sArray[0]);
                        CustomerInterface.TextFieldProfilePhoneNum.setText(sArray[4]);
                        CustomerInterface.TextFieldProfileEmail.setText(sArray[2]);
                        CustomerInterface.TextFieldProfileAddress.setText(sArray[3]); 
                        CustomerInterface.setVisible(true);
                        
                        AdminInterface.dispose();
                    }    
                    else if(Navigator == "Admin"){
                         

                        AdminInterface.AdminName.setText(sArray[0]);
                        AdminInterface.AdminPhone.setText(sArray[4]);
                        AdminInterface.AdminEmail.setText(sArray[2]);
                        AdminInterface.AdminAddress.setText(sArray[3]); 
                        AdminInterface.setVisible(true);
                        CustomerInterface.dispose();
                    }
                    break;
                }
                else if(!read.hasNextLine()){
                JOptionPane.showMessageDialog(null,
                        "UserName/Password Incorrect", "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
                else{}
            
            
            }
            read.close();
        }catch(FileNotFoundException e){
                    JOptionPane.showMessageDialog(null,
                        "File Not Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        
        }
    
        public String repeatChecker(String FileName, String comparedUserName){
        // the identifier that notifies the program if something is repeated
        String repeatIdentifier = "false";
        try (Scanner FileReader = new Scanner(new FileReader(FileName))){
            //scans the file 
        while (FileReader.hasNextLine())
        {
            String s = FileReader.nextLine();
            String[] sArray = s.split("/");
            // change the identifer to true if the compared item exist in the text file
            if (comparedUserName.equals(sArray[0]))
            {
                JOptionPane.showMessageDialog(null,
                        "repeated", "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                repeatIdentifier = "true";
                break;
            }
            else
            {}
        }
        FileReader.close();
        }
        catch (FileNotFoundException e) {System.out.println(e);}
        
        return repeatIdentifier;

    }
}
    

