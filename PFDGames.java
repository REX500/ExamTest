package GUILayer;

import AppLayer.Costumer;
import AppLayer.Employee;
import AppLayer.Game;
import dbAcces.dataBase;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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
    int checkLogIn = 0;
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

        button1 = new Button("Log In");

        VBox box = new VBox();
        box.setSpacing(10);
        box.setPadding(new Insets(15,0,0,70));
        box.getChildren().addAll(label1, text1, text2, button1);

        if(checkLogIn>0){
            borderPane.setTop(null);
            borderPane.setBottom(null);
            window.setWidth(270);
            window.setHeight(200);
            window.setTitle("Log in");
        }

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
                    window.setTitle(realName+" is using the system");
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

        button1 = new Button("Info");



        button1.setOnAction(e->{
            Employee emp = table.getSelectionModel().getSelectedItem();
            String fname, lname, mail, city, address,zip, phone, bank, cpr;
            int id;
            fname = emp.getFirstName();
            lname = emp.getLastName();
            mail = emp.getMail();
            city = emp.getCity();
            address = emp.getAddress();
            zip = emp.getZip();
            phone = emp.getPhoneNum();
            bank = emp.getBankAcc();
            cpr = emp.getCPR();
            id = emp.getId();
            employeeInfo(id, fname, lname, mail, city, address, zip, phone, bank, cpr);
        });

        VBox box = new VBox(6);

        box.getChildren().addAll(table, button1);
        borderPane.setCenter(box);
    }
    // method that display an employee with his name and offers the user to fire him and so on...

    private void employeeInfo(int id, String fname, String lname, String mail, String city, String add, String zip, String phone, String bank, String cpr){
        VBox mainVBox = new VBox(10);
        HBox mainHBox = new HBox(6);
        VBox infoVBox = new VBox(4);
        VBox btnVBox = new VBox(4);

        label1 = new Label("ID: "+id);
        label2 = new Label("First Name: "+fname);
        label3 = new Label("Last Name: "+lname);
        label4 = new Label("Mail Address: "+mail);
        label5 = new Label("City: "+city);
        Label label6 = new Label("Address: "+add);
        Label label7 = new Label("Zip: "+zip);
        Label label8 = new Label("Phone Number: "+phone);
        Label label9 = new Label("Bank Account Number: "+bank);
        Label label10 = new Label("CPR Number: "+cpr);

        button1 = new Button("Fire Employee");
        button2 = new Button("Back to Employee's");
        button3 = new Button("Back to Main Menu");

        infoVBox.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10);
        btnVBox.getChildren().addAll(button1, button2, button3);

        String nameExt = fname+".jpg";
        Image image = new Image(nameExt);

        // resizes the image to have width of 100 while preserving the ratio and using
        // higher quality filtering method; this ImageView is also cached to
        // improve performance
        ImageView iv2 = new ImageView();
        iv2.setImage(image);
        iv2.setFitWidth(150);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);

        //adding action listeners to the buttons
        button1.setOnAction(e-> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Are you sure");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to fire this employee?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                dataBase db = new dataBase();
                try {
                    db.deleteEmployee(fname);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        });
        button2.setOnAction(e -> {
            try {
                employeeMethod();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        button3.setOnAction(e-> logInScreen());

        mainHBox.getChildren().addAll(iv2, infoVBox, btnVBox);

        borderPane.setCenter(mainHBox);
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

        //making a search bar bellow
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search games...");
        searchBar.setMinWidth(400);
        Button searchButton = new Button("Search");
        searchButton.setVisible(true);

        //adding these two to the horizontal box
        HBox searchBox = new HBox(8);
        searchBox.getChildren().addAll(searchBar, searchButton);

        HBox hBox1 = new HBox(8);
        hBox1.getChildren().addAll(button1, button2,button3);

        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        Menu view = new Menu("View");

        MenuItem fileGames = new MenuItem("Games");
        MenuItem fileCustomers = new MenuItem("Customers");
        MenuItem fileEmployee = new MenuItem("Employee");
        MenuItem fileLogOut = new MenuItem("Log out");
        MenuItem fileExit = new MenuItem("Exit");
        MenuItem viewFull = new MenuItem("Full Screen");
        MenuItem viewExitFull = new MenuItem("Normal View");

        Menu editColour = new Menu("Change Theme");
        MenuItem darkTheme = new MenuItem("Dark Theme");
        MenuItem lightTheme = new MenuItem("Light Theme");
        editColour.getItems().addAll(darkTheme,lightTheme);

        MenuItem helpHelp = new MenuItem("Help");

        //adding actions to the menu items
        fileExit.setOnAction(e-> System.exit(0));

        //adding key accelerators to the menu items
        //fileOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        fileExit.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        //fileSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        //fileSaveAs.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        //viewFull.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
        //viewExitFull.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

        // adding menu items to the menus

        file.getItems().addAll(fileGames, fileCustomers, fileEmployee,fileLogOut,fileExit);
        edit.getItems().addAll(editColour);
        help.getItems().addAll(helpHelp);
        view.getItems().addAll(viewFull, viewExitFull);

        fileGames.setOnAction(e -> {
            try {
                gameMethod();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        fileEmployee.setOnAction(e -> {
            try {
                employeeMethod();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        fileCustomers.setOnAction(e -> {
            try {
                costumerTable();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        fileLogOut.setOnAction(e->{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Are you sure?");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to log out?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                checkLogIn++;
                // ... user chose OK
                logIn();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        });

        menu = new MenuBar();
        menu.getMenus().addAll(file,edit,view,help);

        VBox topBox = new VBox(3);
        topBox.getChildren().addAll(menu, searchBox);

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
        button2.setOnAction(e -> {
            try {
                costumerTable();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        searchButton.setOnAction(e->{
            String searchResult = searchBar.getText();
            dataBase db = new dataBase();
            ArrayList<Game> gameArray = null;
            try {
                gameArray = db.getGames();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            for(int i = 0 ; i < gameArray.size(); i++){
                if(searchResult.equals(gameArray.get(i).getName())){
                    String name = gameArray.get(i).getName();
                    String genre = gameArray.get(i).getGenre();
                    String pegi = gameArray.get(i).getPEGI();
                    String plat = gameArray.get(i).getPlatform();
                    String price = gameArray.get(i).getPrice();
                    int quan = gameArray.get(i).getQuantity();
                    gameInfoMethod(name, genre, pegi, quan, plat, price);
                    break;
                }
                // if search result didnt find any match we display the message
                int size = gameArray.size();
                if(size - i ==1){
                    Alert a = new Alert(AlertType.WARNING);
                    a.setTitle("No Results");
                    a.setContentText("We didnt find any matching games");
                    a.setHeaderText(null);
                    a.showAndWait();
                }
            }

        });

        VBox box = new VBox(20);
        box.getChildren().addAll(hBox1);
        box.setPadding(new Insets(20,0,0,0));

        borderPane.setCenter(box);
        borderPane.setTop(topBox);
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
        //handling the rent button aka button2
        button2.setOnAction(e-> {
            Game g = tableGame.getSelectionModel().getSelectedItem();
            String nemeGame = g.getName();
            String nameGenre = g.getGenre();
            String pegi = g.getPEGI();
            String price = g.getPrice();
            String plat = g.getPlatform();
            int copies = g.getQuantity();
            sellMethod(nemeGame, copies, nameGenre, pegi, price, plat);
        });

        borderPane.setCenter(vbox);

    }

    private void sellMethod(String name, int copies, String genre, String pegi, String price, String plat){
        label1 = new Label("Costumer Name: ");
        label2 = new Label("CPR: ");
        text1 = new TextField();
        text2 = new TextField();
        button1 = new Button("Rent");
        button2 = new Button("Cancel");

        grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(10);
        grid.setConstraints(label1, 0,0);
        grid.setConstraints(text1, 1,0);
        grid.setConstraints(label2, 0,1);
        grid.setConstraints(text2, 1,1);
        grid.setConstraints(button2, 0,2);
        grid.setConstraints(button1, 1,2);

        grid.getChildren().addAll(label1,label2, button2, button1, text1, text2);

        button2.setOnAction(e -> {
            try {
                gameMethod();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        button1.setOnAction(e-> {
            dataBase db = new dataBase();
            ArrayList<Costumer> costArray = null;
            try {
                costArray = db.getCostumers();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            String customerName = text1.getText();
            String customerCpr = text2.getText();
            for(int i = 0; i < costArray.size() ; i++){
                String realName = costArray.get(i).getFirstName();
                String realCPR = costArray.get(i).getCpr();
                if(customerName.equals(realName) && customerCpr.equals(realCPR)){
                    // if the costumer exists we will sell the game to him
                    sellGameMethod(name, genre, plat,customerName, customerCpr);
                }
            }
        });

        borderPane.setCenter(grid);
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

        button1.setOnAction(e-> sellMethod(gameName, gameQuan, gameGenre, gamePegi,gamePrice, gamePlat));
        button2.setOnAction(e->{
            TextInputDialog dialog = new TextInputDialog("walter");
            dialog.setTitle("Add copies");
            dialog.setHeaderText(null);
            dialog.setContentText("How much copies would you like to add?");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                String copy = result.get();
                int realCopy = Integer.parseInt(copy);
                dataBase db = new dataBase();
                try {
                    db.addCopies(gameName,realCopy);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    TableView<Costumer> costTable;
    private void costumerTable() throws SQLException {
        //making a table bellow
        TableColumn<Costumer, String> costFname = new TableColumn<>("First Name");
        costFname.setMinWidth(100);
        costFname.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Costumer, String> costLname = new TableColumn<>("Last Name");
        costLname.setMinWidth(100);
        costLname.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Costumer, String> costMail = new TableColumn<>("Mail");
        costMail.setMinWidth(100);
        costMail.setCellValueFactory(new PropertyValueFactory<>("mail"));

        TableColumn<Costumer, String> costCity = new TableColumn<>("City");
        costCity.setMinWidth(100);
        costCity.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Costumer, Integer> costAdd = new TableColumn<>("Address");
        costAdd.setMinWidth(100);
        costAdd.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Costumer, Integer> costZip = new TableColumn<>("Zip");
        costZip.setMinWidth(100);
        costZip.setCellValueFactory(new PropertyValueFactory<>("zip"));

        TableColumn<Costumer, Integer> costPhone = new TableColumn<>("Phone Number");
        costPhone.setMinWidth(100);
        costPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

        TableColumn<Costumer, Integer> costBank = new TableColumn<>("Bank Account Number");
        costBank.setMinWidth(100);
        costBank.setCellValueFactory(new PropertyValueFactory<>("bankAcc"));

        TableColumn<Costumer, String> costCpr = new TableColumn<>("CPR");
        costCpr.setMinWidth(100);
        costCpr.setCellValueFactory(new PropertyValueFactory<>("cpr"));


        costTable = new TableView<>();
        costTable.setItems(getCostumers());
        costTable.getColumns().addAll(costFname, costLname, costMail, costCity, costAdd, costZip, costPhone, costBank, costCpr);
        costTable.setEditable(true);

        button1 = new Button("Info");
        button2 = new Button("Register a customer");
        button3 = new Button("Back");

        //adding action listeners to the buttons

        button1.setOnAction(e->{
            Costumer emp = costTable.getSelectionModel().getSelectedItem();
            String fname, lname, mail, city, address,zip, phone, bank, cpr;
            int id;
            fname = emp.getFirstName();
            lname = emp.getLastName();
            mail = emp.getMail();
            city = emp.getCity();
            address = emp.getAddress();
            zip = emp.getZip();
            phone = emp.getPhoneNum();
            bank = emp.getBankAcc();
            cpr = emp.getCpr();
            customerInfo(fname, lname, mail, city, address, zip, phone, bank, cpr);
        });
        button3.setOnAction(e-> logInScreen());
        button2.setOnAction(e->{
            addCustomer();
        });

        VBox box = new VBox(8);
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(button1, button2, button3);
        box.getChildren().addAll(costTable, hBox);

        borderPane.setCenter(box);
    }

    // method that adds a new customer to the database examTest/costumer

    private void addCustomer(){

        label1 = new Label("First Name: ");
        label2 = new Label("Last Name: ");
        label3 = new Label("Mail: ");
        label4 = new Label("City: ");
        label5 = new Label("Address: ");
        Label label6 = new Label("Zip: ");
        Label label7 = new Label("Phone Number: ");
        Label label8 = new Label("Bank acc: ");
        Label label9 = new Label("Cpr: ");

        text1 = new TextField();
        text2 = new TextField();
        text3 = new TextField();
        text4 = new TextField();
        text5 = new TextField();
        TextField text6 = new TextField();
        TextField text7 = new TextField();
        TextField text8 = new TextField();
        TextField text9 = new TextField();

        button1 = new Button("Save");
        button2 = new Button("Cancel");

        HBox hBox = new HBox(10);
        // positioning the buttons bellow the last text field
        hBox.setPadding(new Insets(0,0,0,120));
        hBox.getChildren().addAll(button2, button1);

        grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(10);

        grid.setConstraints(label1, 0,0);
        grid.setConstraints(text2, 0,1);
        grid.setConstraints(label2, 0,1);
        grid.setConstraints(text2,1,1);
        grid.setConstraints(label3, 0,2);
        grid.setConstraints(text3, 1,2);
        grid.setConstraints(label4, 0,3);
        grid.setConstraints(text4, 1,3);
        grid.setConstraints(label5, 0,4);
        grid.setConstraints(text5, 1,4);
        grid.setConstraints(label6, 0,5);
        grid.setConstraints(text6, 1,5);
        grid.setConstraints(label7, 0,6);
        grid.setConstraints(text7, 1,6);
        grid.setConstraints(label8, 0,7);
        grid.setConstraints(text8, 1,7);
        grid.setConstraints(label9, 0,8);
        grid.setConstraints(text9,1,8);

        grid.getChildren().addAll(label1,label2,label3,label4,label5,label6,label7,label8,label9,text1,text2,text3,text4,text5,text6,text7,text8,text9);

        //adding action listener to buttons bellow
        // button 2 is the easier one so he will be first
        button2.setOnAction(e->{
            try {
                costumerTable();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        button1.setOnAction(e->{
            // now we have to call the method from dataBase class and parse the parameters
            dataBase db = new dataBase();
            //taking the parameters from text fields
            String fname = text1.getText();
            String lname = text2.getText();
            String mail = text3.getText();
            String city = text4.getText();
            String add = text5.getText();
            String test = text6.getText();
            int zip = Integer.parseInt(test);
            String num = text7.getText();
            String bank = text8.getText();
            String cpr = text9.getText();

            // putting them into the addCustomer method

            try {
                db.addCustomer(fname, lname, mail, city, add, zip, num, bank, cpr);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Operation Successful!");
            alert.setHeaderText(null);
            alert.setContentText("Customer added to the database!");
            alert.showAndWait();
            try {
                costumerTable();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(grid, hBox);

        borderPane.setCenter(vBox);
        window.setHeight(520);
    }

    // method that displays the customer info is bellow

    private void customerInfo(String fname, String lname, String mail, String city, String add, String zip, String phone, String bank, String cpr){

        HBox mainHBox = new HBox(6);
        VBox infoVBox = new VBox(4);
        VBox btnVBox = new VBox(4);

        label2 = new Label("First Name: "+fname);
        label3 = new Label("Last Name: "+lname);
        label4 = new Label("Mail Address: "+mail);
        label5 = new Label("City: "+city);
        Label label6 = new Label("Address: "+add);
        Label label7 = new Label("Zip: "+zip);
        Label label8 = new Label("Phone Number: "+phone);
        Label label9 = new Label("Bank Account Number: "+bank);
        Label label10 = new Label("CPR Number: "+cpr);

        button1 = new Button("Delete customer");
        button2 = new Button("Back to Customer's");
        button3 = new Button("Back to Main Menu");

        infoVBox.getChildren().addAll(label2, label3, label4, label5, label6, label7, label8, label9, label10);
        btnVBox.getChildren().addAll(button1, button2, button3);

        //adding action listeners to the buttons
        button1.setOnAction(e-> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Are you sure");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this customer?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                dataBase db = new dataBase();
                try {
                    db.deleteCustomer(cpr);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        });
        button2.setOnAction(e -> {
            try {
                costumerTable();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        button3.setOnAction(e-> logInScreen());

        mainHBox.getChildren().addAll(infoVBox, btnVBox);

        borderPane.setCenter(mainHBox);




    }

    private ObservableList<Costumer> getCostumers() throws SQLException {
        ObservableList<Costumer> list = FXCollections.observableArrayList();
        dataBase db = new dataBase();
        ArrayList<Costumer> costumerArray = db.getCostumers();
        for(int i = 0; i < costumerArray.size(); i++){
            list.add(costumerArray.get(i));
        }
        return list;
    }

    private void sellGameMethod(String name, String genre, String plat, String customerName, String customerCpr){
        dataBase db = new dataBase();
        try {
            db.sellGame(name);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        //here we have to put the game into the table to be connected with a customer
        // take the gameID, game genre, pegi rating and so on along with a user id or a cpr
        // and put it into a rented game table
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            db.sellFillTable(name, genre,dateFormat.format(date), plat, customerCpr );
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        // when we execute that we get a message
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setTitle("You have bought a game!");
        a.setContentText("Game purchase complete!");
        a.setContentText(null);
        a.showAndWait();
        logInScreen();
    }
}
