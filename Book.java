//import javafx.beans.property.StringProperty;
//import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.*;

public class Book{
    private IntegerProperty bookID;
     //String title;
     private StringProperty title;
     private StringProperty authorName;
     private StringProperty publisher;
     private DoubleProperty price;

    public Book(int a, String b, String c,String d,double e){
        this.setBookID(a);
        this.setTitle(b);
        this.setAuthorName(c);
        this.setPublisher(d);
        this.setPrice(e);
    }
    

    public void setBookID(int value) { BookIDProperty().set(value); }
     public int getBookID() { return BookIDProperty().get(); }
     public IntegerProperty BookIDProperty() { 
         if (bookID == null) bookID = new SimpleIntegerProperty(this, "bookID");
         return bookID; 
     }

     public void setTitle(String value) { TitleProperty().set(value); }
     public String getTitle() { return TitleProperty().get(); }
     public StringProperty TitleProperty() { 
         if (title == null) title = new SimpleStringProperty(this, "title");
         return title; 
     }

     public void setAuthorName(String value) { AuthorNameProperty().set(value); }
     public String getAuthorName() { return AuthorNameProperty().get(); }
     public StringProperty AuthorNameProperty() { 
         if (authorName == null) authorName = new SimpleStringProperty(this, "authorName");
         return authorName; 
     }
     
     public void setPublisher(String value) { PublisherProperty().set(value); }
     public String getPublisher() { return PublisherProperty().get(); }
     public StringProperty PublisherProperty() { 
         if (publisher == null) publisher = new SimpleStringProperty(this, "publisher");
         return publisher; 
     }

     public void setPrice(double value) { PriceProperty().set(value); }
     public double getPrice() { return PriceProperty().get(); }
     public DoubleProperty PriceProperty() { 
         if (price == null) price = new SimpleDoubleProperty(this, "price");
         return price; 
     }
     

    //getter & setter for all
}