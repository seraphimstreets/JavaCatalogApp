import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.Parent;
import java.util.ArrayList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Window;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import java.lang.Integer;
import java.lang.Double;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

class showBookScene extends Scene {
   
    private Stage primaryStage;
    private TableView<Book> table;
    private MySQLManager mysqlManager;
    private Parent root;
    private VBox vbox;
    private Scene prevScene;
    private Scene addScene;
    private Scene here = this;
    private ObservableList<Book> obooklist;
    private FilteredList<Book> f1Book;
    private HBox hbox;
    

    addBooksScene scene3;
    //((Group) scene2.getRoot()).getChildren().addAll(vbox);
    
    public showBookScene(Parent root, double width, double height){
        
       super(root,width,height);
       vbox = new VBox(15.0);
       vbox.setFillWidth(true);
       vbox.prefWidthProperty().bind(this.widthProperty());
       vbox.prefHeightProperty().bind(this.heightProperty());
       
    }
    

    public void createUI(Stage stage, Scene prevScene, Scene addScene, MySQLManager manager){
        
        this.primaryStage = stage;
        this.mysqlManager = manager;

        this.primaryStage.setScene(this);
        this.prevScene=prevScene;

        
        
        table = new TableView<Book>();

        
        table.setEditable(true);

      
    
        TableColumn<Book,Integer> bookIDColumn = new TableColumn<Book,Integer>("Book ID");
        bookIDColumn.setCellValueFactory(new PropertyValueFactory("bookID"));
        TableColumn<Book,String> titleColumn = new TableColumn<Book,String>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("title"));
        TableColumn authorNameColumn = new TableColumn("Author");
        authorNameColumn.setCellValueFactory(new PropertyValueFactory("authorName"));
        TableColumn publisherColumn= new TableColumn("Publisher");
        publisherColumn.setCellValueFactory(new PropertyValueFactory("publisher"));
        TableColumn priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));

        
        

        table.getColumns().setAll(bookIDColumn,titleColumn,authorNameColumn,publisherColumn,priceColumn);
        //table.getItems().clear();
        

        
        ((Group) this.getRoot()).getChildren().addAll(vbox);
        
        updateBookList();

        ChoiceBox<String> choicebox = new ChoiceBox();
        choicebox.getItems().addAll("Book ID","Title","Author","Publisher","Price");
        choicebox.setValue("Book ID");

        TextField textField = new TextField();
        textField.setPromptText("Search here!");
        textField.setOnKeyReleased(keyEvent ->
        {
            switch(choicebox.getValue())
            {
                
                case "Book ID":
                    f1Book.setPredicate(p -> Integer.toString(p.getBookID()).contains(textField.getText()));
                    break;
                    
                
                case "Title":
                    f1Book.setPredicate(p -> p.getTitle().toLowerCase().contains(textField.getText().toLowerCase().trim()));
                    break;
                case "Author":
                    f1Book.setPredicate(p -> p.getAuthorName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
                    break;
                case "Publisher":
                    f1Book.setPredicate(p -> p.getPublisher().toLowerCase().contains(textField.getText().toLowerCase().trim()));
                    break;
                
                case "Price":
                    f1Book.setPredicate(p -> Double.toString(p.getPrice()).contains(textField.getText()));
                    break;
                    
            }
        }
        );
        
        choicebox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {//reset table and textfield when new choice is selected
            if (newVal != null)
            {
                textField.setText("");
                f1Book.setPredicate(null);//This is same as saying f1Book.setPredicate(p->true);
            }
        });
        
       

        Button logoutbtn = new Button("Back");
        Button addbtn = new Button("Add books");
        hbox = new HBox(10.0, choicebox, textField, logoutbtn, addbtn);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(table,hbox);

        logoutbtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                
                primaryStage.setScene(prevScene);
                
            }

        });

        
        addbtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                
                scene3 = new addBooksScene(new Group(),800,500);
                scene3.createUI(primaryStage,prevScene,here,manager);
                
                
            }

        });


    }

    public void updateBookList(){
       
        ArrayList<Book> booklist = mysqlManager.getBooks();
        obooklist = FXCollections.observableArrayList(booklist);
        booklist.clear();
        f1Book = new FilteredList(obooklist, p -> true);
        table.setItems(f1Book);
       // table.setItems(obooklist);
        
        
    }

        /*ArrayList<Book> booklist = manager.getBooks();
        ObservableList<Book> obooklist = FXCollections.observableArrayList(booklist);
       
    
        Label label = new Label("Booklist");
        label.setFont(new Font("Arial",20));
        
        table.setEditable(true);
    
        TableColumn bookIDColumn = new TableColumn("Book ID");
        TableColumn titleColumn = new TableColumn("Title");
        TableColumn authorNameColumn = new TableColumn("Author");
        TableColumn publisherColumn= new TableColumn("Publisher");
        TableColumn priceColumn = new TableColumn("Price");
    
        table.getColumns().addAll(bookIDColumn,titleColumn,authorNameColumn,publisherColumn,priceColumn);
        */
        /*
        for(Book i : booklist) {
            int bookID = i.bookID;
            String title = i.title;
            String authorName = i.authorName;
            String publisher = i.publisher;
            double price =  i.price;
        
            booklist.add(new Book(bookID, title, authorName, publisher, price));
        }
        */
       /* final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,0,0,10));
        vbox.getChildren().addAll(label,table);*/

} //end class