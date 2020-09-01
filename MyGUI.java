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
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;

public class MyGUI extends Application {

    private TextField userTextField;
    private PasswordField pwBox;
    private Button loginbtn;
    private Button newuserbtn; 

    showBookScene scene2;
    addBooksScene scene3;
    MySQLManager manager;
    Scene loginScene;
    menuScene scene4;
    createUserScene scene5;
    private GridPane menuGrid = new GridPane();
    private GridPane cUserGrid = new GridPane();
    private Alert a;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Forms");

        manager = new MySQLManager();
        manager.connectToDatabase2();


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));


        Text scenetitle = new Text("Welcome");
        scenetitle.setId("welcome-text");
        //scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2,1);

        Label userName = new Label("User Name:");
        grid.add(userName,0,1);

        userTextField = new TextField();
        grid.add(userTextField,1,1);

        Label pw = new Label("Password");
        grid.add(pw,0,2);

        pwBox = new PasswordField();
        grid.add(pwBox,1,2);

        loginbtn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginbtn);
        grid.add(hbBtn,1,4);

        newuserbtn = new Button("Create account");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn2.getChildren().add(newuserbtn);
        grid.add(hbBtn2,0,4);
        scene4 = new menuScene(menuGrid,420,250);
        scene5 = new createUserScene(cUserGrid,420,250);

        loginbtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                
                    PasswordSet ps1 = manager.getPassword(userTextField.getText());
                    
                    if(ps1.getPassword().equals(pwBox.getText())){
                        
                        userTextField.setText("");
                        pwBox.setText("");
                        scene4.createUI(primaryStage,loginScene,manager);
                    
                    }else{
                        a = new Alert(AlertType.INFORMATION);
                        a.setTitle("Password confirmation");
                        a.setHeaderText("Login unsuccessful");
                        a.setContentText("The username or password was incorrect");
                        a.show();
                    }
                    /*
                }catch{
                    a = new Alert(AlertType.INFORMATION);
                    a.setTitle("Password confirmation");
                    a.setHeaderText("Login unsuccessful");
                    a.setContentText("The username or password was incorrect");
                    a.show();
                    */
                
                
            }

        });

        newuserbtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                    
                    scene5.createUI(primaryStage,loginScene,manager);
                }

        });
/*
        Button addBtn = new Button("Add");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(addBtn);
        grid.add(hbBtn1,1,4);

        Button viewBtn = new Button("View");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(viewBtn);
        grid.add(hbBtn2,0,4);

     

       

        
        

        addBtn.setOnAction(new EventHandler<ActionEvent>(){
            

            @Override
            public void handle(ActionEvent e){
                
              

                scene3 = new addBooksScene(new Group(),800,500);
                scene3.createUI(primaryStage,loginScene,scene2,manager);
                
            }
        });   

        viewBtn.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent e){
                    
                  
    
                    scene2 = new showBookScene(new Group(),600,500);
                    scene2.createUI(primaryStage,loginScene,scene3,manager);
                    
                }

        });

        */

        loginScene = new Scene(grid, 300, 275);

        primaryStage.setScene(loginScene);
        loginScene.getStylesheets().add(getClass().getResource("forms.css").toExternalForm());

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
