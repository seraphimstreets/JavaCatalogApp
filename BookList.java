import java.util.Scanner;
import java.util.ArrayList;

class BookList{
    //ArrayList<Object> BookInfo = new ArrayList<Object>();
    ArrayList<Book> booklist = new ArrayList<Book>();
    public void readList(){

        int bookID;
        String title;
        String authorName;
        String publisher;
        double price;
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Create another book? Enter 'Y' to proceed and 'N' to quit.");
            char confirmation = sc.nextLine().charAt(0);

            if(confirmation == 'N'){
                System.out.println("Exiting book creation...");
                break;

            }
            else if (confirmation == 'Y'){
                System.out.println("Enter book ID:"); 
                bookID = Integer.valueOf(sc.nextLine());
                System.out.println("Enter book title:");
                title = sc.nextLine();
                System.out.println("Enter author name:");
                authorName = sc.nextLine();
                System.out.println("Enter publisher:");
                publisher = sc.nextLine();
                System.out.println("Enter price:");
                price = Double.valueOf(sc.nextLine());
                System.out.println("Creating book...");
                booklist.add(new Book(bookID, title, authorName, publisher, price));
                System.out.println("Book created!");
            }
            else{
                System.out.println("Please enter a valid input.");
            }

        }

    }

    public void getList(){
        for(Book i : booklist){
            //System.out.print(i.bookID+", "+i.title+", "+i.authorName+", "+i.publisher+", "+i.price); 
            //System.out.println("");
        }
    }
}