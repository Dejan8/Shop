/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.CustomerDao;
import model.Person;

/**
 *
 * @author D
 */
public class FXMLDocumentController implements Initializable {

    Person person;

    CustomerDao dao;

    @FXML
    private TextField id;

    @FXML
    private ComboBox flowRate;

    @FXML
    private ComboBox<String> rate;

    @FXML
    private ComboBox<String> duration;

    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TableView<Person> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        person = new Person();
        try {
            tableView.getItems().setAll(parseUserList());

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<String> sizeList = FXCollections.<String>observableArrayList("2", "5", "10", "20", "50", "100");
        flowRate.setItems(sizeList);
        flowRate.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            flowRate.valueProperty().bindBidirectional(person.flowRateProperty());
        });

        ObservableList<String> rList = FXCollections.<String>observableArrayList("1", "5", "10", "100", "Flat");
        rate.setItems(rList);
        rate.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue1) -> {
            rate.valueProperty().bindBidirectional(person.rateProperty());
        });

        ObservableList<String> dList = FXCollections.<String>observableArrayList("1", "2");
        duration.setItems(dList);
        duration.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue1) -> {
            duration.valueProperty().bindBidirectional(person.durationProperty());
        });

        name.textProperty().bindBidirectional(person.nameProperty());
        address.textProperty().bindBidirectional(person.addressProperty());

    }

    @FXML
    private void savePerson() throws SQLException, ClassNotFoundException {
        if (person.isValid()) {
            person.save();
            dao.addCustomer(person);

            clearPerson();
            tableView.getItems().setAll(parseUserList());
        } else {

            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = person.errorsProperty().get();

            errList.forEach((errList1) -> {
                errMsg.append(errList1);
            });
            System.out.println(errMsg);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Person can be saved!");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();

        }
    }

    @FXML
    private void deletePerson() throws SQLException {
        
        try {
            
            int cid = tableView.getSelectionModel().getSelectedItem().getId();
         
            dao.deleteCustomer(cid);
            tableView.getItems().setAll(parseUserList());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An error has occurred !");
            alert.setHeaderText("Select the person you want to delete");

            alert.showAndWait();
        }
    }

    @FXML
    private void closeForm() {
        Platform.exit();

    }

    @FXML
    private void clearPerson() throws SQLException {

        person.flowRateProperty().set("Flow rate");
        person.rateProperty().set("Rate");
        person.durationProperty().set("Contract duration");
        person.nameProperty().set("");
        person.addressProperty().set("");

    }

    private List<Person> parseUserList() throws SQLException {
        dao = new CustomerDao();
        List<Person> List = dao.getAllCustomers();
        return List;
    }

}
