package dashboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DashboardController {
	
	Connection getCon;
	PreparedStatement pst;
	
	public DashboardController()
	{
		getCon = getConnection();
//		this.name = name;
//		this.balance = Integer.parseInt(balance);
	}
	static Connection getConnection()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/walleterwhite","root","");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	private static int balance=5000;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label BalanceTxt;

    @FXML
    private Button DepositBtn;

    @FXML
    private TextField amountTF;

    @FXML
    private Label nameTxt;

    @FXML
    private Button withdrawBtn;

    @FXML
    void doDeposit(ActionEvent event) {
    	BalanceTxt.setText(String.valueOf(balance+Integer.parseInt(amountTF.getText()))+"Rs.");
    	balance=balance+Integer.parseInt(amountTF.getText());
    }

    @FXML
    void doWithdraw(ActionEvent event) {
    	BalanceTxt.setText(String.valueOf(balance-Integer.parseInt(amountTF.getText())) + "Rs.");
    	balance=balance-Integer.parseInt(amountTF.getText());
    }
    
    public void printHello(String name)
    {
    	System.out.println("Hello"+name);
    }
    
    public void setUserInformation(String username,String balance)
    {
//  
//    	this.balance = Integer.parseInt(balance);
//    	System.out.println(name+" "+balance);
    }

    @FXML
    void initialize() {
    	BalanceTxt.setText(String.valueOf(balance));
    	nameTxt.setText("Rakesh");
    }

}
