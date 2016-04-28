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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private void employeeMethod() throws SQLException {
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

        borderPane.setCenter(table);
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

        MenuItem fileNew = new MenuItem("Games");
        MenuItem fileSaveAs = new MenuItem("Customers");
        MenuItem fileSave = new MenuItem("Employee");
        MenuItem fileClose = new MenuItem("Close");
        MenuItem viewFull = new MenuItem("Full Screen");
        MenuItem viewExitFull = new MenuItem("Normal View");

        Menu editColour = new Menu("Change Theme");
        MenuItem darkTheme = new MenuItem("Dark Theme");
        MenuItem lightTheme = new MenuItem("Light Theme");
        editColour.getItems().addAll(darkTheme,lightTheme);

        MenuItem helpHelp = new MenuItem("Help");

        //adding actions to the menu items
        fileClose.setOnAction(e-> System.exit(0));

        //adding key accelerators to the menu items
        //fileOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        fileClose.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        //fileSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        //fileSaveAs.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        //viewFull.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
        //viewExitFull.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

        // adding menu items to the menus

        file.getItems().addAll(fileNew, fileSave, fileSaveAs,fileClose);
        edit.getItems().addAll(editColour);
        help.getItems().addAll(helpHelp);
        view.getItems().addAll(viewFull, viewExitFull);

        fileNew.setOnAction(e -> {
            try {
                gameMethod();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        fileSave.setOnAction(e -> {
            try {
                employeeMethod();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        menu = new MenuBar();
        menu.getMenus().addAll(file,edit,view,help);

        //adding actions to buttons

        button3.setOnAction(e -> {
            try {
                employeeMethod();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        button1.setOnAction(e -> {
            try {
                gameMethod();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        borderPane.setCenter(grid);
        borderPane.setTop(menu);
        window.setHeight(400);
        window.setWidth(500);
    }

    TableView<Game> tableGame;
    private void gameMethod() throws SQLException {
        button1 = new Button("Info");
        button2 = new Button("Rent");
        button3 = new Button("Delete");

        //making a table bellow
        TableColumn<Game, String> gameName = new TableColumn<>("Game");
        gameName.setMinWidth(100);
        gameName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Game, String> gameGenre = new TableColumn<>("Genre");
        gameGenre.setMinWidth(100);
        gameGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Game, String> gamePegi = new TableColumn<>("PEGI");
        gamePegi.setMinWidth(100);
        gamePegi.setCellValueFactory(new PropertyValueFactory<>("PEGI"));

        TableColumn<Game, String> gamePlatform = new TableColumn<>("Platform");
        gamePlatform.setMinWidth(100);
        gamePlatform.setCellValueFactory(new PropertyValueFactory<>("platform"));

        TableColumn<Game, Integer> gamePrice = new TableColumn<>("Price");
        gamePrice.setMinWidth(100);
        gamePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Game, Integer> gameQuantity = new TableColumn<>("Quantity");
        gameQuantity.setMinWidth(100);
        gameQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableGame = new TableView<>();
        tableGame.setItems(getGames());
        tableGame.getColumns().addAll(gameName, gameGenre, gamePegi, gamePlatform, gamePrice, gameQuantity);
        tableGame.setEditable(true);

        VBox vbox = new VBox(5);
        HBox hbox = new HBox(5);

        hbox.getChildren().addAll(button1, button2, button3);
        vbox.getChildren().addAll(tableGame, hbox);

        //adding action listener to the buttons, so we can have some actual functionality
        button1.setOnAction(e-> {
            Game game = tableGame.getSelectionModel().getSelectedItem();
            String gameNameMethod = game.getName();
            String gameGenreMetdo = game.getGenre();
            String gamePegiMethod = game.getPEGI();
            int gameQuanMethod = game.getQuantity();
            String gamePlatMethod = game.getPlatform();
            String gamePriceMethod = game.getPrice();

            gameInfoMethod(gameNameMethod, gameGenreMetdo, gamePegiMethod, gameQuanMethod,gamePlatMethod, gamePriceMethod);
        });

        borderPane.setCenter(vbox);

    }

    private ObservableList<Game> getGames() throws SQLException {
        ObservableList<Game> list = FXCollections.observableArrayList();
        // we need to use the array list with employee objects to fill up the list
        // after that we will return this list to be used by a table to fill up itself
        dataBase db = new dataBase();
        ArrayList<Game> gameArray = db.getGames();
        for(int i = 0; i< gameArray.size(); i++){
            list.add(gameArray.get(i));
        }
        return list;
    }

    private void gameInfoMethod(String gameName, String gameGenre, String gamePegi, int gameQuan, String gamePlat, String gamePrice){
        VBox vBox = new VBox(10);
        HBox hBox = new HBox();

        VBox boxWithLabels = new VBox(3);
        label1 = new Label(gameName);
        label2 = new Label(gameGenre);
        label3 = new Label(gamePegi);
        label4 = new Label(gamePlat);
        label5 = new Label(gamePrice);
        Label label6 = new Label(String.valueOf(gameQuan));

        button1 = new Button("Rent");
        button2 = new Button("Add new copies");

        // load the image
        // connecting the actual game with a .jpg extension
        String nameExt = gameName+".jpg";
        Image image = new Image(nameExt);

        // resizes the image to have width of 100 while preserving the ratio and using
        // higher quality filtering method; this ImageView is also cached to
        // improve performance
        ImageView iv2 = new ImageView();
        iv2.setImage(image);
        iv2.setFitWidth(100);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);

        VBox vBox1 = new VBox(2);
        vBox1.getChildren().addAll(label1, label2, label3, label4, label5, label6);

        VBox vBox2 = new VBox(3);
        vBox2.getChildren().addAll(button1, button2);

        hBox.getChildren().addAll(iv2, vBox1, vBox2);

        borderPane.setCenter(hBox);
    }
}
