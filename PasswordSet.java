public class PasswordSet{
    private int passwordID;
    private String password;
    private String username;

    public PasswordSet(int passwordID, String password, String username){
        this.passwordID = passwordID;
        this.password = password;
        this.username = username;
    }

    public int getPasswordID(){
        return passwordID;
    }

    

    public String getPassword(){
        return password;

    }

    public void setPassword(String npassword){
        this.password = npassword;
    }

    public String getUsername(){
        return username;

    }


}