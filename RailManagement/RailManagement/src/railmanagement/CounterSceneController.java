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
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private ComboBox<?> pGender;
    @FXML
    private Button addPassenger;
    @FXML
    private TextField pAge;
    @FXML
    private Button tUpdateBtn;
    @FXML
    private MenuItem tDeleteMenu;

    DisplayDatabase tTableData =new  DisplayDatabase();
      DisplayDatabase stTableData =new  DisplayDatabase();
 DisplayDatabase rTableData =new  DisplayDatabase();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
      
      stTableData.buildData(stTableView, "Select * from stationtable;");
       
        
        
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
            stationList.add(stCode);
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

    @FXML
    private void aPassenger(ActionEvent event) {
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
       DeleteDatabase.deleteRecord(id, "TrainTable");
       
       tTableData.buildData(tTableView, "Select * from traintable;");
       
    }

}
    

