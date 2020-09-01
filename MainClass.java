import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

class MainClass{
    public static void main(String[] args) throws Exception{
      
        
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
       

     
       
        /*
        Scanner sc = new Scanner(System.in);
        BookList booklist = new BookList();
        while(true){
            System.out.println("Enter 'C' to enter book creator and 'V' to view all books. Press 'E' to exit program.");
            char confirmation = sc.next().charAt(0);
            if(confirmation == 'C'){
                booklist.readList();
            }
            else if (confirmation == 'V'){
                booklist.getList();
            }
            else if (confirmation == 'E'){
                System.out.println("Exiting program...");
                break;
            }
        }
        */
        
    }
    
}