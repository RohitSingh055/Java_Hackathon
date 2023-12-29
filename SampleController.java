package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gluonhq.charm.glisten.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SampleController<customer_name, item_name, price, qty> {

    @FXML
    private AnchorPane Main_form;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private Hyperlink admin_hyperlink;

    @FXML
    private Button admin_loginBtn;

    @FXML
    private PasswordField admin_password;

    @FXML
    private TextField admin_username;

    @FXML
    private AnchorPane employee_form;
    
    @FXML
    private Button add_btn;


    @FXML
    private AnchorPane bill_main;

    @FXML
    private TextField customer_name;

    @FXML
    private TextField item_name;

    @FXML
    private TextField price;

    @FXML
    private Button print_btn;

    @FXML
    private TextField qty;
    
    @FXML
    private TextField employee_id;

    @FXML
    private Button employee_loginBtn;

    @FXML
    private PasswordField employee_password;
    
    @FXML
    private Button billBtn;
    
    @FXML
    private Hyperlink employee_hyperlink;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    public void employeeLogin()
    {
	String employeeData = "SELECT * FROM employee WHERE employee_id=? and password=?";
        connect = database.connectDb();

	try{
	
	    Alert alert;
   	    prepare = connect.prepareStatement(employeeData);
	    if(employee_id.getText().isEmpty() || employee_password.getText().isEmpty())
	    {
	      	alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all blank fields");
			alert.showAndWait();
	     }
	    else
	    {
		prepare.setString(1,employee_id.getText());
		prepare.setString(2,employee_password.getText());
		result = prepare.executeQuery();

		if(result.next())
		{
		  //Lets create dashboard form for employee
		  
		  // Hide login form
		
		  alert = new Alert(AlertType.INFORMATION);
	  	  alert.setTitle("Information Message");
		  alert.setHeaderText(null);
		  alert.setContentText("Successfully Login!");
		  alert.showAndWait();

	
		  employee_loginBtn.getScene().getWindow().hide();

		  // Now lets create for employee form
		  Parent root = FXMLLoader.load(getClass().getResource("employeeDashboard.fxml"));

		  Stage stage = new Stage();
		  Scene scene = new Scene(root);
		
		  stage.setScene(scene);
		  stage.show();
		}
		else
		{		   
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Wrong EmployeeID/Password");
			alert.showAndWait();
		}
		
	
	}
}
	catch(Exception e){e.printStackTrace();}}
    
    public void adminLogin(){
    	
    	String adminData = "Select * From admin WHERE username=? and password=?";
    	connect = database.connectDb();

    	try{
    	
    	Alert alert;
    	// Check if the textfields are empty or not
    	
    	if(admin_username.getText().isEmpty() || admin_password.getText().isEmpty())
    	{
    		alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Message");
    		alert.setHeaderText(null);
    		alert.setContentText("Please fill all blank fields");
    		alert.showAndWait();
    		
    	}
    	else
    	{
    		prepare = connect.prepareStatement(adminData);
    		prepare.setString(1,admin_username.getText());
    		prepare.setString(2,admin_password.getText());
    		result = prepare.executeQuery();

    		if(result.next())
    		{
    		  // Then lets proceed to dashboard form .Lets create dashboard form for admin
    		  
    		  // Hide login form
    		
    		  alert = new Alert(AlertType.INFORMATION);
    	  	  alert.setTitle("Information Message");
    		  alert.setHeaderText(null);
    		  alert.setContentText("Successfully Login!");
    		  alert.showAndWait();

    		  // Lets try now but first we need to insert the data first
    		  admin_loginBtn.getScene().getWindow().hide();

    		  // Now lets create for employee form
    		  Parent root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));
    		  
    		  Stage stage = new Stage();
    		  Scene scene = new Scene(root);
    		
    		  stage.setScene(scene);
    		  stage.show();
    		}
    		else
    		{

    			// If wrong data you gave then error message		   
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Wrong Username/Password");
    			alert.showAndWait();
    		}
    		
    	}
    	}catch(Exception e){e.printStackTrace();}}
    
    public void switchForm(ActionEvent event)
    {
    	if(event.getSource()==admin_hyperlink){
    		admin_form.setVisible(false);
    		employee_form.setVisible(true);
    	}else if(event.getSource() == employee_hyperlink){
    		admin_form.setVisible(true);
    		employee_form.setVisible(false);
    	}
    }
    
    public void bill(ActionEvent event)
    {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("billing.fxml"));
			Stage stage = new Stage();
  		  Scene scene = new Scene(root);
  		
  		  stage.setScene(scene);
  		  stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void addProduct()
    {
    	String addProd = "insert into product(cust_name,item_name,price,qty) values(?,?,?,?)";
        connect = database.connectDb();

        try{
	
	    Alert alert;
	   
   	    prepare = connect.prepareStatement(addProd);
	  
		prepare.setString(1,customer_name.getText());
		prepare.setString(2,item_name.getText());
		prepare.setString(3,price.getText());
		prepare.setString(4,qty.getText());
		result = prepare.executeQuery();

		if(result.next())
		{
		  
		  alert = new Alert(AlertType.INFORMATION);
	  	  alert.setTitle("Information Message");
		  alert.setHeaderText(null);
		  alert.setContentText("Successfully Added!");
		  alert.showAndWait();

	
		  add_btn.getScene().getWindow().hide();

		  // Now lets create for employee form
		  Parent root = FXMLLoader.load(getClass().getResource("ProductDashboard.fxml"));

		  Stage stage = new Stage();
		  Scene scene = new Scene(root);
		
		  stage.setScene(scene);
		  stage.show();
		}
		else
		{		   
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Wrong Entry");
			alert.showAndWait();
		}
		
	
	}catch(Exception e){e.printStackTrace();}}
    
    public void close()
    {
    	System.exit(0);
    }
    
    

}
