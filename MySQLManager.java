import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class MySQLManager {
    ArrayList<Book> booklist = new ArrayList<Book>();
    ArrayList<PasswordSet> passwordlist = new ArrayList<PasswordSet>();
    PasswordSet ps1;


    // https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html

    private Connection conn = null;

    public boolean connectToDatabase() {
        try {

           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","python");

            System.out.println("Connection to booksdb established");

            return true;

        } catch (SQLException ex) {
    // handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
    return false;
        }
    }//end connectToDatabase


    private Connection conn2 = null;

    public boolean connectToDatabase2() {
        try {
            conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/passworddb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","python");

            System.out.println("Connection to passworddb established");

            return true;

        } catch (SQLException ex) {
    // handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
    return false;
        }
    }

    public ArrayList<Book> getBooks(){

        
        System.out.println("Printing book list records");
        try {
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","python");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM booklist";
            ResultSet rs = stmt.executeQuery(query);
          
            // Now do something with the ResultSet ....
            while (rs.next()) {
                int bookID = rs.getInt("BookID");
                String title = rs.getString("title");
                String authorName = rs.getString("authorName");
                String publisher = rs.getString("publisher");
                double price = rs.getDouble("price");
                System.out.println(bookID + "\t" + title +
                                   "\t" + authorName + "\t" + publisher +
                                   "\t" + price);

                booklist.add(new Book(bookID, title, authorName, publisher, price));
            }
            //conn.close();
            return booklist;
        }// end try
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    } //end method

    public boolean addBooks(String title,String authorName,String publisher,double price){
        try{
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","python");
            PreparedStatement pst = conn.prepareStatement("INSERT INTO booklist (title, authorName, publisher, price) VALUES (?,?,?,?)");
            //pst.setInt(1,bookID);
            pst.setString(1,title);
            pst.setString(2,authorName);
            pst.setString(3,publisher);
            pst.setDouble(4,price);

            int count = pst.executeUpdate();
            System.out.println(count + "records inserted");
            //conn.close();
            //st.executeUpdate(+"("+bookID+","+title+","+authorName+","+publisher+","+price+")");

            return true;
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }

    public PasswordSet getPassword(String uname){
        try {
            //conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/passworddb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","python");
            Statement stmt = conn2.createStatement();
            String query = "SELECT * FROM passworddb.passwordlist WHERE Username = '" + uname + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
          
            // Now do something with the ResultSet ....
           while(rs.next()){
                int passwordID = rs.getInt("PasswordID");
                String password = rs.getString("Password");
                String username = rs.getString("Username");
                System.out.println(username);
                ps1 = new PasswordSet(passwordID, password, username);
           }
           // conn2.close();
         return ps1;
        }// end try
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
            
        }//end catch

    }//end method

    /*

    public int databaseSize(){
        
        Statement stmt = conn2.createStatement();
        int dbsize = stmt.executeUpdate("SELECT * FROM passworddb.passwordlist");

        return dbsize;
        
    }

    */
    public boolean addUser(String password, String username){
        try{
           // conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/passworddb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","python");
            Statement stmt = conn2.createStatement();
            ResultSet rscount = stmt.executeQuery("SELECT COUNT(*) FROM passworddb.passwordlist;");
            int passwordID = 0;
            while(rscount.next()){
                passwordID = rscount.getInt(1);
            }
            passwordID += 1;
            PreparedStatement pst = conn2.prepareStatement("INSERT INTO passwordlist VALUES (?,?,?)");
            pst.setInt(1,passwordID);
            pst.setString(2,password);
            pst.setString(3,username);
           

            int count = pst.executeUpdate();
            System.out.println(count + "records inserted");
           // conn2.close();
            //st.executeUpdate(+"("+bookID+","+title+","+authorName+","+publisher+","+price+")");
            return true;

        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }


    
}//end class