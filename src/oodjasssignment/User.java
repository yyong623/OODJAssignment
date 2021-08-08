package oodjasssignment;

public class User {
   
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
    
    public void searchProduct(){
        //Code
    }
    
    public void viewOrder(){
        //Code
    }
    
    public void deleteOrder(){
        //Code
    }
    
    public void editProfile(){
        //Code
    }
}
