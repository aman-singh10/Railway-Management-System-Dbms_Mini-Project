/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railmanagement;

import DB.DBConnection;
import DB.DeleteDatabase;
import DB.DisplayDatabase;
import DB.QueryDatabase;
import DB.UpdateRailDatabase;
import Model.Passenger;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 *
 * @author tanzeem
 */
public class CounterSceneController implements Initializable {

    @FXML
    private CheckBox mon;
    @FXML
    private CheckBox tue;
    @FXML
    private CheckBox wed;
    @FXML
    private CheckBox thu;
    @FXML
    private CheckBox fri;
    @FXML
    private CheckBox sat;
    @FXML
    private CheckBox sun;
    @FXML
    private Button addTrainBtn;
    @FXML
    private TextField f2a;
    @FXML
    private TextField f3a;
    @FXML
    private TextField fSl;
    @FXML
    private TextField tName;
    @FXML
    private TextField tNum;
    @FXML
    private TextField src;
    @FXML
    private TextField dest;
    @FXML
    private Button addRouteBtn;
    @FXML
    private TextField arT;
    @FXML
    private TextField drT;
    @FXML
    private ComboBox<String> cTrain;
    @FXML
    private ComboBox<String> cStation;
    @FXML
    private TextField sCode;
    @FXML
    private TextField sName;
    @FXML
    private Button aStation;
    @FXML
    private TableView<?> stTableView;
    @FXML
    private TableView<?> tTableView;
    @FXML
    private TableView<?> rTableView;
    
    
    ObservableList<String> trainList = FXCollections.observableArrayList();  
    ObservableList<String> stationList = FXCollections.observableArrayList();  
    @FXML
    private TextField pName;
    @FXML
    private ComboBox<String> pGender;
    @FXML
    private TextField pAge;
    @FXML
    private Button tUpdateBtn;
    @FXML
    private MenuItem tDeleteMenu;

    DisplayDatabase tTableData =new  DisplayDatabase();
    DisplayDatabase stTableData =new  DisplayDatabase();
    DisplayDatabase rTableData =new  DisplayDatabase();
   
    @FXML
    private Button addPassengerBtn;
    @FXML
    private ComboBox<String> cTNum;
    @FXML
    private ComboBox<String> cClass;
    @FXML
    private ComboBox<String> cSrc;
    @FXML
    private ComboBox<String> cDest;
    @FXML
    private Button cSubmitBtn;
    @FXML
    private TextField cPnr;
    @FXML
    private Button searchPntBtn;
    @FXML
    private TableView<Passenger> addPTableView;
    @FXML
    private Label cFare;
    @FXML
    private TableView<?> PnrTableView;
    @FXML
    private TextField sPnr;
    @FXML
    private Button searchPntBtn1;
    @FXML
    private TableView<?> passengerTableView;
    @FXML
    private AnchorPane animatePane;
    
    boolean animate=false;
    int x=10;     
    boolean addX=true;
    @FXML
    private MenuItem tDeleteMenu1;
    @FXML
    private TabPane mainPage;
    @FXML
    private GridPane loginPage;
    @FXML
    private Label loginMsg;
    @FXML
    private TextField uName;
    @FXML
    private PasswordField pass;
    @FXML
    private Button logOutBtn;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        animate=true;
//        startAnimateTrain();
        
       pnrData.buildData(PnrTableView, "Select * from ticketTable");
       createAddPassengerTable(addPTableView);
       
        cFare.setText("0.0");
        tTableData.buildData(tTableView, "Select * from traintable;");
      
      stTableData.buildData(stTableView, "Select * from stationtable;");
//       stTableData.buildData(tTableView, "");
        
        
        ResultSet rs = QueryDatabase.query("Select Number from traintable;");
        if(rs!=null){
            try {
                while(rs.next()){
                    trainList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(CounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        cTrain.setItems(trainList);
        cTNum.setItems(trainList);
        
         rs = QueryDatabase.query("Select STATION_NAME from stationtable;");
        if(rs!=null){
            try {
                while(rs.next()){
                    stationList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(CounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        cStation.setItems(stationList);
       
        // fill the class combobox in ticket counter.
        ObservableList<String> cls = FXCollections.observableArrayList();  
        cls.add("2A");
        cls.add("3A");
        cls.add("SL");
        cClass.setItems(cls);
        
        // fill the class combobox in ticket counter.
        ObservableList<String> gender = FXCollections.observableArrayList();  
        gender.add("M");
        gender.add("F");
        gender.add("O");
        pGender.setItems(gender);
        
        
        
    }    

String trainName = "";  
int trainNum = 0;
String tSrc="";	
String tDest ="";	
double tF2a = 0;	
double tF3a = 0;	
double tFSl = 0;
int tmon= 0;
int ttue= 0;
int twed= 0;
int tthu= 0;
int tfri= 0;
int tsat= 0;
int tsun = 0;
String stCode = "";
String stName = "";
    
    @FXML
    private void addTrain(ActionEvent event) {
        
        
        try {
            getTrainFields();
       if(uT){
            Connection c;
            c = DBConnection.connect();
            String query = "update rail.traintable set "
                    + "Name='"+trainName+"',\n" 
                    + "Src='"+tSrc+"',\n"
                    + "Dest='"+tDest+"',\n"
                    + "Fare2a='"+tF2a+"',\n"
                    + "Fare3a='"+tF3a+"',\n"
                    + "FareSL='"+tFSl+"',\n"
                    + "Mon='"+tmon+"',\n"
                    + "Tue='"+ttue+"',\n"
                    + "Wed='"+twed+"',\n"
                    + "Thu='"+tthu+"',\n"
                    + "Fri='"+tfri+"',\n"
                    + "Sat='"+tsat+"',\n"
                    + "Sun='"+tsun+"' \n"
                    +" where Number='"+trainNum+"';";                    
                   
            
            c.createStatement().executeUpdate(query);
            

            
            c.close();
           
            }else{
            Connection c;
            c = DBConnection.connect();
            String query = "INSERT INTO rail.traintable (Name,Number,Src,Dest,Fare2a,Fare3a,FareSL,Mon,Tue,Wed,Thu,Fri,Sat,Sun)VALUES("+
"'"+trainName+"',\n" +
"'"+trainNum+"',\n" +
"'"+tSrc+"',\n" +
"'"+tDest+"',\n" +
"'"+tF2a+"',\n" +
"'"+tF3a+"',\n" +
"'"+tFSl+"',\n" +
"'"+tmon+"',\n" +
"'"+ttue+"',\n" +
"'"+twed+"',\n" +
"'"+tthu+"',\n" +
"'"+tfri+"',\n" +
"'"+tsat+"',\n" +
"'"+tsun+"');";                    
                   
            
            c.createStatement().execute(query);
            

            
            c.close();
           
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         trainList.add(String.valueOf(trainNum));
          cTrain.setItems(trainList);
          cTNum.setItems(trainList);
       
         rTfields();
        
     
        tTableData.buildData(tTableView, "Select * from traintable;");
         
        
    }

    @FXML
    private void reqF3a(ActionEvent event) {
    }

    @FXML
    private void reqFSl(ActionEvent event) {
    }

    @FXML
    private void reqTNum(ActionEvent event) {
    }

    @FXML
    private void reqSrc(ActionEvent event) {
    }

    @FXML
    private void reqDest(ActionEvent event) {
    }

    @FXML
    private void reqF2a(ActionEvent event) {
    }

    @FXML
    private void addRoute(ActionEvent event) {
        getRouteFields();
        try {          
          
            Connection c;
            c = DBConnection.connect();
            String query = "INSERT INTO rail.ROUTETABLE (Train_Number ,STATION_NAME ,Arrival ,Departure )VALUES("+
                    "'"+rTnum+"'," +
                    "'"+rScode+"'," +
                    "'"+rArr+"'," +
                    "'"+rDst+"');";
            c.createStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(CounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
            rRfields();
           
            rTableData.buildData(rTableView, "Select * from routetable where Train_Number='"+rTnum+"';");
        
    }

    @FXML
    private void reqDrt(ActionEvent event) {
    }

    private void getTrainFields() {
        trainName = tName.getText();
        trainNum = Integer.parseInt(tNum.getText());
        tSrc = tNum.getText();
        tSrc = src.getText();
        tDest = dest.getText();
        
        tF2a = Double.parseDouble(f2a.getText());
        tF3a = Double.parseDouble(f3a.getText());
        tFSl = Double.parseDouble(fSl.getText());
        
        if(mon.isSelected())
            tmon = 1;
        if(tue.isSelected())
            ttue = 1;
        if(wed.isSelected())
            twed = 1;
        if(thu.isSelected())
            tthu = 1;
        if(fri.isSelected())
            tfri = 1;
        if(sat.isSelected())
            tsat = 1;
        if(sun.isSelected())
            tsun = 1;
        
        
        
        
    }

    private void rTfields() {
        tName.clear();
tNum.clear();    
src.clear();	 
dest.clear();	
f2a.clear();	
f3a.clear();	
fSl.clear();
mon.setSelected(false);
tue.setSelected(false);
wed.setSelected(false);
thu.setSelected(false);
fri.setSelected(false);
sat.setSelected(false);
sun.setSelected(false);
uT = false;
 tmon= 0;
 ttue= 0;
 twed= 0;
 tthu= 0;
 tfri= 0;
 tsat= 0;
 tsun = 0;

    }

    @FXML
    private void reqsName(ActionEvent event) {
    }

    @FXML
    private void aStation(ActionEvent event) {
        try {
            getSfields();
            Connection c;
            c = DBConnection.connect();
            String query = "INSERT INTO rail.stationtable (STATION_CODE,STATION_NAME)VALUES("+
                    "'"+stCode+"'," +
                    "'"+stName+"');";
            c.createStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(CounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
            stationList.add(stName);
            cStation.setItems(stationList);
        
            rSfields();
            stTableData.buildData(stTableView, "Select * from stationtable;");
    
    }

    private void getSfields() {
        stCode = sCode.getText();
        stName = sName.getText();
        
    }

    private void rSfields() {
       sName.clear();
sCode.clear(); 
    }
String rTnum = "";
String rScode = "";
String rArr = "";
String rDst = "";
    private void getRouteFields() {
       rTnum = cTrain.getValue();
       rScode = cStation.getValue();
       rArr = arT.getText();
       rDst = drT.getText();
       
    }

    private void rRfields() {
        
        
        cStation.setValue("");
        arT.clear();
        drT.clear();
        
        
    }

    String trainN="";
    @FXML
    private void rTrainFind(ActionEvent event) {
       trainN  = cTrain.getValue();
        if(trainN!=null && !trainN.isEmpty()){
        
        rTableData.buildData(rTableView, "Select * from routetable where Train_Number='"+trainN+"';");
        }
    }
boolean uT = false;
    @FXML
    private void updateT(ActionEvent event) {
        
       String trainNum = tNum.getText();
       if (trainNum!=null && !trainNum.isEmpty()){
           
           ResultSet rs = QueryDatabase.query("select * from traintable where number = '"+trainNum+"';");
           if (rs!=null){
               try {
                   while (rs.next()){
                       
                       uT=true;
                       tName.setText(rs.getString(2));
                       src.setText(rs.getString(4));
                       dest.setText(rs.getString(5));
                       f2a.setText(rs.getString(6));
                       f3a.setText(rs.getString(7));
                       fSl.setText(rs.getString(8));
                       if(rs.getString(9).equalsIgnoreCase("1"))
                                mon.setSelected(true);
                         if(rs.getString(10).equalsIgnoreCase("1"))
                                tue.setSelected(true);
                           if(rs.getString(11).equalsIgnoreCase("1"))
                                wed.setSelected(true);
                           if(rs.getString(12).equalsIgnoreCase("1"))
                                thu.setSelected(true);
                           if(rs.getString(13).equalsIgnoreCase("1"))
                                fri.setSelected(true);
                           if(rs.getString(14).equalsIgnoreCase("1"))
                                sat.setSelected(true);
                           if(rs.getString(15).equalsIgnoreCase("1"))
                                sun.setSelected(true);
                             
                           
                       
                       
                       
                   }  } catch (SQLException ex) {
                   Logger.getLogger(CounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
               }
           
           
           }
           }
        
        
        
    }

    @FXML
    private void tMenuDelete(ActionEvent event) {
        
       int index = tTableView.getSelectionModel().getFocusedIndex();
       ObservableList<ObservableList> data = tTableData.getData();
       ObservableList<String> itemData = data.get(index);
       
       int id = Integer.parseInt(itemData.get(0));
       String tNum = itemData.get(2);
       System.out.println(tNum);
       DeleteDatabase.deleteRecord(id, "TrainTable");
       
       trainList.remove(tNum);
       cTrain.setItems(trainList);
       cTNum.setItems(trainList);
       
       tTableData.buildData(tTableView, "Select * from traintable;");
       
    }

    ObservableList<Passenger> passengerList = FXCollections.observableArrayList();
   
   private void  deletePassenger(int index){
       
       
        passengerList.remove(index);
        addPTableView.refresh();
   
   }
   
   int passengerId = 0;
   double totalFare = 0;
   @FXML
    private void addPassenger(ActionEvent event) {
        
        String name = pName.getText();
        String age = pAge.getText();
        String gender = pGender.getValue();
        
        if(name!=null && !name.isEmpty()){
            if(age!=null && !age.isEmpty()){
                if(gender!=null && !gender.isEmpty()){
                        passengerId++;
                        passengerList.add(new Passenger(passengerId,name,age,gender));
                        totalFare+= ticketFare;
                         System.out.println(ticketFare);
                          System.out.println(totalFare);
                        cFare.setText(String.format("%.2f",totalFare));
                    }else{
                        pGender.requestFocus();
                    }
            }else{
                pAge.requestFocus();
            }
                
        }else{
            pName.requestFocus();
        }
         
       pName.clear();
       pAge.clear();
       
        
        addPTableView.setItems(passengerList);
        addPTableView.refresh();
        
    }

    @FXML
    private void cGetStations(ActionEvent event) {
        
        String trainNum = cTNum.getValue();
        
        if(trainNum!=null && !trainNum.isEmpty()){
            ObservableList<String> sList = FXCollections.observableArrayList();
            ResultSet rs = QueryDatabase.query("Select Station_Name from routeTable where Train_Number='"+trainNum+"';");
        if(rs!=null){
                try {
                    while(rs.next()){
                        
                        sList.add(rs.getString(1));
                        
                    }   } catch (SQLException ex) {
                    Logger.getLogger(CounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            cSrc.setItems(sList);
            cDest.setItems(sList);
            
        }
        }
        
    }
    double ticketFare = 0;
    String tCNum = "";
    String clss = "";
    String tCSrc = "";
    String tCDest = "";
     
    @FXML
    private void cGetFare(ActionEvent event) {
        
        tCNum = cTNum.getValue();
        if(tCNum==null || tCNum.isEmpty()){
        
            cTNum.requestFocus();
            return;
        }
        
         clss = cClass.getValue();
            if(clss!=null && !clss.isEmpty()){
                
                ResultSet rs= QueryDatabase.query("Select Fare2a,Fare3a,FareSL from traintable where Number='"+tCNum+"';");
                if(rs!=null){
                
                    try {
                        while(rs.next()){
                            if(clss.equalsIgnoreCase("2A")){
                                ticketFare = Double.parseDouble(rs.getString(1));
                            }
                             if(clss.equalsIgnoreCase("3A")){
                                ticketFare = Double.parseDouble(rs.getString(2));
                            }
                              if(clss.equalsIgnoreCase("SL")){
                                ticketFare = Double.parseDouble(rs.getString(3));
                            }
                            
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
                
             }
            
            System.out.println(ticketFare); 
       
    }

    @FXML
    private void createTicket(ActionEvent event) {
        
       boolean check =  getTPFields();
       if(check){
           if(!passengerList.isEmpty()){
               
             UpdateRailDatabase.UpdateTicketTable(tCNum,clss,tCSrc,tCDest,totalFare,passengerList);
                    
               
           }else{
             return;
           }
           
         resetCounterFields();  
          pnrData.buildData(PnrTableView, "Select * from ticketTable");
       }
        
    }
    
    DisplayDatabase pnrData = new DisplayDatabase();
    @FXML
    private void getPNR(ActionEvent event) {
        String query="";
        String pnr = cPnr.getText();
        if(pnr!=null && !pnr.isEmpty()){
        query = "Select * from ticketTable where pnr='"+pnr+"';";
        
        }else{
        query = "Select * from ticketTable;";
        
        }
        pnrData.buildData(PnrTableView, query);
        
    }
    
    
    
    
    public void createAddPassengerTable(TableView rTableView){
        
            TableColumn<Passenger, String> col_id = new TableColumn("No.");
                col_id.setPrefWidth(30);
                rTableView.getColumns().add(col_id);
                col_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, String>,
                        ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> t) {
                                // t.getValue() returns the Test instance for a particular TableView row
                                //    return t.getValue().testProperty();
                                // or
                                return new SimpleStringProperty(String.valueOf(t.getValue().getId()));
                            }
                        });

        
        
        
               TableColumn<Passenger, String>  col_name = new TableColumn("Name");
                //      col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, String>,
                        ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> t) {
                                // t.getValue() returns the Test instance for a particular TableView row
                                //    return t.getValue().testProperty();
                                // or
                                return new SimpleStringProperty(t.getValue().getName());
                            }
                        });
                col_name.setPrefWidth(100);
                rTableView.getColumns().add(col_name);
                col_name.setCellFactory(TextFieldTableCell.<Passenger>forTableColumn());



                TableColumn<Passenger, String>  col_value = new TableColumn("Age");
                col_value.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, String>,
                        ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> t) {
                                // t.getValue() returns the Test instance for a particular TableView row
                                //    return t.getValue().testProperty();
                                // or
                                return new SimpleStringProperty(t.getValue().getAge());
                            }
                        });

                col_value.setPrefWidth(60);
                rTableView.getColumns().add(col_value);
                col_value.setCellFactory(TextFieldTableCell.<Passenger>forTableColumn());


                TableColumn<Passenger, String>  col_gender = new TableColumn("Gender");
                col_gender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, String>,
                        ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> t) {
                                // t.getValue() returns the Test instance for a particular TableView row
                                //    return t.getValue().testProperty();
                                // or
                                return new SimpleStringProperty(t.getValue().getGender());
                            }
                        });

                col_gender.setPrefWidth(60);
                rTableView.getColumns().add(col_gender);
                col_value.setCellFactory(TextFieldTableCell.<Passenger>forTableColumn());


                //Insert Button
                TableColumn<Passenger, Boolean> col_action = new TableColumn<>("Delete");
                col_action.setSortable(false);

                col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, Boolean>,
                                ObservableValue<Boolean>>() {

                                    @Override
                                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Passenger, Boolean> p) {
                                        return new SimpleBooleanProperty(p.getValue() != null);
                                    }
                                });

                col_action.setCellFactory(new Callback<TableColumn<Passenger, Boolean>, TableCell<Passenger, Boolean>>() {

                            @Override
                            public TableCell<Passenger, Boolean> call(TableColumn<Passenger, Boolean> p) {
                                return new ButtonCell(rTableView);
                            }

                        });

                 col_action.setPrefWidth(30);

                rTableView.getColumns().add(col_action);
    
    
    }

     
    
    private boolean getTPFields() {
            tCNum= cTNum.getValue();
            clss = cClass.getValue();
            tCSrc = cSrc.getValue();
            tCDest = cDest.getValue();
            
            if(tCNum==null || tCNum.isEmpty()){
             cTNum.requestFocus();
              return false;
            
            }
            
             if(clss==null && clss.isEmpty()){
            
              cClass.requestFocus();
              return false;
            }
             
              if(tCSrc==null || tCSrc.isEmpty()){
                  cSrc.requestFocus();
                  return false;
            }
              
               if(tCDest==null || tCDest.isEmpty()){
                  cDest.requestFocus();
                  return false;
            }
            return true;
    }

    private void resetCounterFields() {
       cTNum.setValue("");
       cClass.setValue("");
       cSrc.setValue("");
       cDest.setValue("");
       
       pName.clear();
       pAge.clear();
       pGender.setValue("");
       ticketFare = 0;
       totalFare = 0;
       cFare.setText("0.0");
       
       passengerList.clear();
       addPTableView.refresh();
       
        
    }

    @FXML
    private void deleteTicket(ActionEvent event) {
        
          int index = PnrTableView.getSelectionModel().getSelectedIndex();
        ObservableList<ObservableList> data = pnrData.getData();
       ObservableList<String> itemData = data.get(index);
       
       int pnr = Integer.parseInt(itemData.get(0));
         Connection c ; 
           
           try{  
         c = DBConnection.connect(); 
        
         String query = "Delete from TicketTable where pnr='"+pnr+"';";
         c.createStatement().execute(query);
         c.close();
          pnrData.buildData(PnrTableView, "SElect * from ticketTable;");
           
       }catch(Exception e){  
        System.out.println("Error on Building Data");        
      }  
           
           
       
    }
    
    DisplayDatabase passengerData = new DisplayDatabase();
    @FXML
    private void getPassengers(ActionEvent event) {
       
        String pnr = sPnr.getText();
        if(pnr==null || pnr.isEmpty()){
            sPnr.requestFocus();
            return;
        }
        
        passengerData.buildData(passengerTableView, "Select * from passengerTable where PNR_NUM='"+pnr+"';");
        
        
    }

    @FXML
    private void animateTrain(MouseEvent event) {
        if(animate){
            animate=false;
        }else{
            animate=true;
            startAnimateTrain();
        }
    }

    private void startAnimateTrain() {
      Task task = new Task<Void>() {
        
        @Override public Void call() {
            try {
                

         
        while(animate){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if(addX){
                            if(x<=800){
                            animatePane.setTranslateX(x);
                            x+=3;
                            addX=true;
                            }else{
                            addX=false;

                            } 
                    }else{
                    
                            if(x>=3){
                            animatePane.setTranslateX(x);
                            x-=3;
                            addX=false;
                            }else{
                            addX=true;

                            } 
                    }
                }
            });
            Thread.sleep(100);

        }


        //   return null;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return null;
                }
            };
     Thread t = new  Thread(task);

    
       
       
        t.start();
    }

    @FXML
    private void stDelete(ActionEvent event) {
       
        
        int index = stTableView.getSelectionModel().getSelectedIndex();
        ObservableList<ObservableList> data = stTableData.getData();
       ObservableList<String> itemData = data.get(index);
       
       int id = Integer.parseInt(itemData.get(0));
         Connection c ; 
           
           try{  
         c = DBConnection.connect(); 
        
         String query = "Delete from stationTable where id='"+id+"';";
         c.createStatement().execute(query);
         c.close();
         stTableData.buildData(stTableView, "Select * from stationtable;");
           
       }catch(Exception e){  
        System.out.println("Error on Building Data");        
      }  
           
           
        
    }
    HashMap map = new HashMap();
    @FXML
    private void login(ActionEvent event) {
       map.clear();
        map.put("admin", "admin@123");
        map.put("naheda", "passwd");
//        map.put("admin", "admin@123");
        
        String name = uName.getText();
        String pwd = pass.getText();
        
        if(map.containsKey(name)){
            if(map.get(name).toString().equals(pwd)){
                    mainPage.setVisible(true);
                    loginPage.setVisible(false);
                    loginMsg.setText("");
                    logOutBtn.setVisible(true);
            }else{
                loginMsg.setText("Wrong credentials");
            }
        
        }else{
                loginMsg.setText("Wrong credentials");
            }
        
    }

    @FXML
    private void logOut(ActionEvent event) {
        
                     mainPage.setVisible(false);
                    loginPage.setVisible(true);
                    loginMsg.setText("Enter Credentials");
                    logOutBtn.setVisible(false);
                    uName.clear();
                    pass.clear();
    }
    
    
    
    
     //Define the button cell
    private class ButtonCell extends TableCell<Passenger, Boolean> {
        final Button cellButton = new Button("x");
         
        ButtonCell(final TableView tblView){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                    int selectdIndex = getTableRow().getIndex();
                      
                    deletePassenger(selectdIndex);
                       
                       
                    //delete the selected item in data
                    
                }
            });
        }
 
        
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                cellButton.setPrefSize(28, 20);
                setGraphic(cellButton);
                
            } else {
                 setGraphic(null);
                }
        }
    } 

}
    

