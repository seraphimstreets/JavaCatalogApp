import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.lang.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType; 

class addBooksScene extends Scene{

    private TextField bookIDField;
    private TextField titleField;
    private TextField authorField;
    private TextField publisherField;
    private TextField priceField;
    private MySQLManager manager;
    private Stage primaryStage;
    private Scene prevScene, showScene;
    private Alert a = new Alert(AlertType.NONE); 

    showBookScene scene2;

    public addBooksScene(Parent root, double width, double height){
        
        super(root,width,height);
       // vbox = new VBox();
        
     }
    public void createUI(Stage stage, Scene prevScene, Scene showScene, MySQLManager manager){

        this.primaryStage = stage;
        this.primaryStage.setScene(this);
        this.prevScene = prevScene;
        this.showScene = showScene;
        this.manager = manager;
        Scene here = this;

        TextField tf1, tf2, tf3, tf4, tf5;
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Label bookIDL = new Label("Enter book ID:");
        grid.add(bookIDL,0,1);

        tf1 = new TextField();
        grid.add(tf1,1,1);


        Label titleL = new Label("Enter title:");
        grid.add(titleL,0,2);

        tf2 = new TextField();
        grid.add(tf2,1,2);

        Label authorL = new Label("Enter author name:");
        grid.add(authorL,0,3);

        tf3 = new TextField();
        grid.add(tf3,1,3);

        Label publisherL = new Label("Enter publisher:");
        grid.add(publisherL,0,4);

        tf4 = new TextField();
        grid.add(tf4,1,4);

        Label priceL = new Label("Enter price:");
        grid.add(priceL,0,5);

        tf5 = new TextField();
        grid.add(tf5,1,5);

        Button addBtn = new Button("Add book");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(addBtn);
        grid.add(addBtn,1,7);

        Button viewBtn = new Button("View booklist");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(viewBtn);
        grid.add(viewBtn,0,7);

        viewBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                
            scene2 = new showBookScene(new Group(),600,500);
            scene2.createUI(primaryStage,prevScene,here,manager);
            
            
                
            }

    });

        addBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                if(!manager.addBooks(tf2.getText(),tf3.getText(),tf4.getText(),Double.valueOf(tf5.getText()))){
                    a.setAlertType(AlertType.INFORMATION);
                    a.setTitle("Update confirmation");
                    a.setHeaderText("Update not successful");
                    a.setContentText("That Book ID is already in use");
                    a.show();
                }else{
                    tf1.setText("");
                    tf2.setText("");
                    tf3.setText("");
                    tf4.setText("");
                    tf5.setText("");
                    a.setAlertType(AlertType.INFORMATION);
                    a.setTitle("Update confirmation");
                    a.setHeaderText("Update successful");
                    a.setContentText("The book has been added.");
                    a.show();
                }
            }

    });

    ((Group) this.getRoot()).getChildren().addAll(grid);

    }
}