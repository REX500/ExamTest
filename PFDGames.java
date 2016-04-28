package GUILayer;

import AppLayer.Employee;
import AppLayer.Game;
import dbAcces.dataBase;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Filip on 25-04-2016.
 */
public class PFDGames extends Application{
    public static void main(String[] args){
        launch(args);
    }

    Stage window;
    BorderPane borderPane;
    Button button1,button2, button3, button4, button5;
    Label label1, label2, label3, label4, label5;

    @Override
    public void start(Stage s){
        window = s;
        window.setTitle("This is the app");
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(0,4,4,4));
        // first we call the log-in method
        logIn();


        Scene scene = new Scene(borderPane, 500,400);
        window.setScene(scene);
        window.show();
    }

    TextField text1, text2, text3, text4, text5;
    private void logIn(){
        text1 = new TextField();
        text2 = new TextField();
        label1 = new Label();

        text1.setPromptText("Username");
        text2.setPromptText("CPR");

        text1.setMaxWidth(100);
        text2.setMaxWidth(100);

        label1.textProperty().bind(text1.textProperty());

        button1 = new Button("Log In");

        VBox box = new VBox();
        box.setSpacing(10);
        box.setPadding(new Insets(15,0,0,70));
        box.getChildren().addAll(label1, text1, text2, button1);

        //testing if the input is the same as the employee's data

        button1.setOnAction(e->{
            dataBase db = new dataBase();
            ArrayList<Employee> empArray = null;
            try {
                empArray = db.getEmployees();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            String name = text1.getText();
            String pass = text2.getText();
            for(int i = 0; i < empArray.size() ; i++){
                String realName = empArray.get(i).getFirstName();
                String realCPR = empArray.get(i).getCPR();
                if(name.equals(realName) && pass.equals(realCPR)){
                    // if the credentials are correct we switch the screen into the table method that contains all
                    // the employee's that we currently have
                    System.out.println("Picka");
                    logInScreen();
                    break;
                }
            }
        });

        window.setHeight(200);
        window.setWidth(270);
        borderPane.setCenter(box);
    }
    TableView<Employee> table;
    private void tableMethod() throws SQLException {
        TableColumn<Employee, Integer> empId = new TableColumn<>("Employee id");
        empId.setMinWidth(50);
        empId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Employee, String> empFName= new TableColumn<>("First Name");
        empFName.setMinWidth(100);
        empFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Employee, String> empLName = new TableColumn<>("Last Name");
        empLName.setMinWidth(100);
        empLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Employee, String> empMail = new TableColumn<>("Mail");
        empMail.setMinWidth(100);
        empMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        TableColumn<Employee, String> empCity = new TableColumn<>("City");
        empCity.setMinWidth(100);
        empCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        TableColumn<Employee, String> empAddress = new TableColumn<>("Address");
        empAddress.setMinWidth(100);
        empAddress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        TableColumn<Employee, String> empZip = new TableColumn<>("Zip Code");
        empZip.setMinWidth(100);
        empZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        TableColumn<Employee, String> empPhone = new TableColumn<>("Phone Number");
        empPhone.setMinWidth(100);
        empPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        TableColumn<Employee, String> empBankAcc = new TableColumn<>("Bank Account Number");
        empBankAcc.setMinWidth(100);
        empBankAcc.setCellValueFactory(new PropertyValueFactory<>("bankAcc"));
        TableColumn<Employee, String> empCpr = new TableColumn<>("CPR Number");
        empCpr.setMinWidth(100);
        empCpr.setCellValueFactory(new PropertyValueFactory<>("CPR"));

        table = new TableView<>();
        table.setItems(getEmployees());
        table.getColumns().addAll(empId,empFName, empLName, empMail, empCity, empAddress, empZip, empPhone, empBankAcc, empCpr);
        table.setEditable(true);
    }

    private ObservableList<Employee> getEmployees() throws SQLException {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        // we need to use the array list with employee objects to fill up the list
        // after that we will return this list to be used by a table to fill up itself
        dataBase db = new dataBase();
        ArrayList<Employee> empArray = db.getEmployees();
        for(int i = 0; i< empArray.size(); i++){
            list.add(empArray.get(i));
        }
        return list;
    }
    MenuBar menu;
    GridPane grid;
    //login screen bellow
    private void logInScreen(){
        button1 = new Button("Games");
        button2 = new Button("Customers");
        button3 = new Button("Employee's");

        grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(10);
        grid.setPadding(new Insets(4,4,4,4));
        grid.setConstraints(button1, 0,0);
        grid.setConstraints(button2, 1,0);
        grid.setConstraints(button3, 0,1);
        grid.getChildren().addAll(button1, button2, button3);

        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        Menu view = new Menu("View");

        MenuItem fileNew = new MenuItem("New");
        MenuItem fileSaveAs = new MenuItem("Save As");
        MenuItem fileSave = new MenuItem("Save");
        MenuItem fileOpen = new MenuItem("Open");
        MenuItem fileOpenFile = new MenuItem("Open File...");
        MenuItem fileClose = new MenuItem("Close");
        MenuItem viewFull = new MenuItem("Full Screen");
        MenuItem viewExitFull = new MenuItem("Normal View");

        Menu editColour = new Menu("Change Theme");
        MenuItem darkTheme = new MenuItem("Dark Theme");
        MenuItem lightTheme = new MenuItem("Light Theme");
        editColour.getItems().addAll(darkTheme,lightTheme);

        MenuItem helpHelp = new MenuItem("Help");

        // adding menu items to the menus

        file.getItems().addAll(fileNew, fileSave, fileSaveAs,fileOpen, fileOpenFile,fileClose);
        edit.getItems().addAll(editColour);
        help.getItems().addAll(helpHelp);
        view.getItems().addAll(viewFull, viewExitFull);

        menu = new MenuBar();
        menu.getMenus().addAll(file,edit,view,help);

        borderPane.setCenter(grid);
        borderPane.setTop(menu);
        window.setHeight(500);
        window.setWidth(400);
    }
}
