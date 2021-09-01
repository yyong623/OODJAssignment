package oodjasssignment;

//Import Exceptation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class User {
   
    Scanner scan = new Scanner(System.in);
    
    //Data Type
    static String name,email, phoneNum, mailingAdd;
    public static String username;
    protected String password;
    static boolean loginIdentifier;
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
    public void search(JTable table, String valueToSearch, String FileName){  
                       
        try{
            
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            
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

    public void viewOrder(JTable table, String fileName){
        
        //Open File
        int count = 0;
        try(BufferedReader bfr = new BufferedReader(new FileReader(fileName))){
            
            
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
    
    public void deleteOrder(JTable orderListTable, String FileName, String orderId, String cusId){
        
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
                
        //Get selected row index
        int rowSelected = orderListTable.getSelectedRow();
        //Check if a row is selected
        if(rowSelected == -1){ //return -1 if no row is selected
            
            JOptionPane.showMessageDialog(null, "Please Select an Item to delete");
        }else{        
                        
            JOptionPane.showMessageDialog(null, "Item deleted");

            model.removeRow(orderListTable.getSelectedRow());
            
            //Read text file to delete record as well
            String tempFile = "tempOrder.txt";
            File oldFile = new File (FileName);
            File newFile = new File (tempFile);
            
            try{
                FileWriter fw = new FileWriter(tempFile,true);

                BufferedWriter bw = new BufferedWriter(fw);
                
                for(int i = 0 ; i < orderListTable.getRowCount(); i ++){
                    bw.write(orderId +"/"+ cusId +"/");
                    for(int j = 0 ; j < orderListTable.getColumnCount(); j ++){                         
                        bw.write(orderListTable.getValueAt(i, j).toString()+ "/");
                        
                    }
                    bw.newLine();
                }
                bw.flush();
                bw.close();
                fw.flush();
                fw.close();
                
                oldFile.delete();
                File replace = new File(FileName);
                newFile.renameTo(replace);
                
            }catch(IOException e ){
                System.out.println(e);
            }
        }
    }
    
    public void edit(String fileName, String OldText,String newText,JTextField text){
        
        File f1 = new File(fileName);
        
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
            
            //Set Text HERE ! --> updated info
            text.setText("null");
            text.setText(newText);
            
            //Rewrite the text file with new data
            fw = new FileWriter(f1);
            
            fw.write(newWord);
            JOptionPane.showMessageDialog(null, "Successfully Updated");
        }catch (IOException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Update Failed");
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
    
//View Profile
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
        }catch(FileNotFoundException e){
                    JOptionPane.showMessageDialog(null,
                        "File Not Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        
        }


}
    

