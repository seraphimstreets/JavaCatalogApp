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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.HPos;
import javafx.geometry.VPos;



public class createUserScene extends Scene{

    private MySQLManager manager;
    private Scene loginScene;
    private TextField userTextField;
    private PasswordField pwBox;
    private PasswordField pwBox2;
    private Stage primaryStage;
    private GridPane grid;
    private Button adduserbtn;
    private Button backbtn;
    private Alert a = new Alert(AlertType.NONE); 

    public createUserScene(GridPane root, double width, double height){
        
        super(root,width,height);
        grid = root;
        
     }


    public void createUI(Stage stage, Scene prevScene, MySQLManager manager){

        this.primaryStage = stage;
        this.primaryStage.setScene(this);
        this.manager = manager;

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Create New User");
        scenetitle.setId("welcome-text");
        //scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2,1);

        Label userName = new Label("Enter username:");
        grid.add(userName,0,1);

        userTextField = new TextField();
        grid.add(userTextField,1,1);

        Label pw = new Label("Enter your password:");
        grid.add(pw,0,2);

        pwBox = new PasswordField();
        grid.add(pwBox,1,2);

        Label pw2 = new Label("Confirm your password:");
        grid.add(pw2,0,3);

        pwBox2 = new PasswordField();
        grid.add(pwBox2,1,3);

        adduserbtn = new Button("Add user");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(adduserbtn);
        grid.add(hbBtn,1,4);

        backbtn = new Button("Back to login");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn2.getChildren().add(backbtn);
        grid.add(hbBtn2,0,4);

        backbtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                
                primaryStage.setScene(prevScene);
                
            }

        });

        adduserbtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                if(pwBox.getText().equals(pwBox2.getText())){
                    if(!manager.addUser(pwBox.getText(),userTextField.getText())){
                        a.setAlertType(AlertType.INFORMATION);
                        a.setTitle("User update attempt");
                        a.setHeaderText("Username already in use");
                        a.setContentText("Please choose a unique username.");
                        a.show();
                    }
                    else{
                        String uname = userTextField.getText();
                        pwBox.setText("");
                        pwBox2.setText("");
                        userTextField.setText("");
                        a.setAlertType(AlertType.INFORMATION);
                        a.setTitle("User update confirmation");
                        a.setHeaderText("User created successfully");
                        a.setContentText("User " + uname + " has been added.");
                        a.show();
                    }
                }else{
                    a.setAlertType(AlertType.INFORMATION);
                    a.setTitle("User update confirmation");
                    a.setHeaderText("User creation unsucessful");
                    a.setContentText("Please enter matching passwords.");
                    a.show();
                }
                
            }

        });




    }
}