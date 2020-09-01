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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

public class menuScene extends Scene{
    showBookScene scene2;
    addBooksScene scene3;
    private MySQLManager manager;
    private Scene loginScene;
    private Stage primaryStage;
    private GridPane grid;
   

    public menuScene(GridPane root,double width,double height){
        super(root,width,height);
        grid = root;

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(25);
        grid.setPadding(new Insets(25,25,25,25));

        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints(125);
            grid.getColumnConstraints().add(column);
        }

    }

    public void createUI(Stage stage,Scene prevScene,MySQLManager manager){

        this.primaryStage = stage;
        this.primaryStage.setScene(this);
        this.manager = manager;
        Scene here = this;
        manager.connectToDatabase();

        //GridPane grid = new GridPane();
      
        

     

        

        Text scenetitle = new Text("Choose your action");
        scenetitle.setId("welcome-text");
        //scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 28));
        //grid.setConstraints(HPos.CENTER,VPos.CENTER);
        grid.add(scenetitle, 0, 0, 3,1);
        grid.setHalignment(scenetitle, HPos.CENTER);



    

        Button addBtn = new Button("Add");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.CENTER);
        hbBtn1.getChildren().add(addBtn);
        grid.add(hbBtn1,1,1);

        Button viewBtn = new Button("View");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.CENTER);
        hbBtn2.getChildren().add(viewBtn);
        grid.add(hbBtn2,0,1);

        Button logoutBtn = new Button("Logout");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.setAlignment(Pos.CENTER);
        hbBtn3.getChildren().add(logoutBtn);
        grid.add(hbBtn3,2,1);

        logoutBtn.setOnAction(new EventHandler<ActionEvent>(){
            

            @Override
            public void handle(ActionEvent e){
                
                
                primaryStage.setScene(prevScene);
                
            }
        });   


     

      // grid.setGridLinesVisible(true);

        
        

        addBtn.setOnAction(new EventHandler<ActionEvent>(){
            

            @Override
            public void handle(ActionEvent e){
                
            
                scene3 = new addBooksScene(new Group(),800,500);
                scene3.createUI(primaryStage,here,scene2,manager);
                
            }
        });   

        viewBtn.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent e){
                    
    
                    scene2 = new showBookScene(new Group(),600,500);
                    scene2.createUI(primaryStage,here,scene3,manager);
                    
                }

        });

        //((GridPane) this.getRoot()).getChildren().addAll(grid);
        this.getStylesheets().add(getClass().getResource("forms.css").toExternalForm());
    }

}