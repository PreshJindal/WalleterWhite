package loginPage;
import dashboard.DashboardController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginPageController {
	
	Connection getCon;
	PreparedStatement pst;
	
	public LoginPageController()
	{
		getCon = getConnection();
	}
	//Connecting database with LoginPageController
	static Connection getConnection()
    {
    	Connection con = null;
    	try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/walleterwhite","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return con;
    }
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label detectTxt;

    @FXML
    private ImageView imgView;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField nameText;

    @FXML
    private PasswordField textPwd;


    String uname;
    @FXML
	    void enterApp(ActionEvent event) {
	    	int a=-1;
	    String name = nameText.getText();
    	System.out.println(textPwd.getText());
    	try
        {
            pst = getCon.prepareStatement("select * from customers where username = ?");
            pst.setString(1, nameText.getText());
            ResultSet records = pst.executeQuery();

            while(records.next())
            {
            	if(records.getString("password").equals(textPwd.getText()))
            	{
            		DashboardController dbc = new DashboardController();
            		dbc.setUserInformation(name, "20000");
            		try {
						Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Dashboard.fxml"));
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.setResizable(false);
						stage.show();
						
						Scene scene1 = (Scene)loginBtn.getScene();
						scene1.getWindow().hide();
						
						System.out.println("Opening WAIT!");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		a=0;
            	}
            }
            if(a!=0)
            {
            	System.out.println("wrong!");
        		Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("ERROR");
            	alert.setHeaderText("Wrong Information entered!");
            	alert.setContentText("Please ensure you have entered correct Username/Password !");
            	
            	alert.showAndWait();
    			System.out.println("Wrong!");
            }
        }
        catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    	
	    }

    @FXML
    void initialize() {
    	System.out.println(getCon);
    }

}
