package miracle;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Custo;
import model.CustoDao;
import model.CustomerDao;
import model.Person;

/**
 *
 * @author Dejan
 */
public class FXMLHomePageController implements Initializable {

    Person person;
    Custo custo;
    CustoDao cDao;
    CustomerDao dao;
    Connection conn;
    private ObservableList<String> masterData;
    String loginCustomerName = null;

    @FXML
    private Text listView;
    @FXML
    private Text addPerson;
    @FXML
    private TableView<Person> tView;
    @FXML
    private TableColumn<Person, Integer> id;
    @FXML
    private TableColumn<Person, String> flowRate;
    @FXML
    private TableColumn<Person, String> rate;
    @FXML
    private TableColumn<Person, String> duration;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> address;
    @FXML
    private Button addButton;
    @FXML
    private Text textFlowRate;
    @FXML
    private Text textRate;
    @FXML
    private Text textDuration;
    @FXML
    private Text textName;
    @FXML
    private Text textAddress;
    @FXML
    private TextField fieldFlowRate;
    @FXML
    private TextField fieldRate;
    @FXML
    private TextField fieldDuration;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldAddress;
    @FXML
    private Button deletePerson;
    @FXML
    private Button clearBtn;
    @FXML
    private Button updateButton;
    @FXML
    private Button updtArtcl;
    @FXML
    private TextField fieldId;
    @FXML
    private ImageView imgOnHomeScreen;
    @FXML
    private Text textUpdate;
    @FXML
    private Text textWelcome;
    @FXML
    private Text textEntry;
    @FXML
    private Text textArticlesList;
    @FXML
    private TableView<Custo> cView;
    @FXML
    private TableColumn<Custo, Integer> custo_id;
    @FXML
    private TableColumn<Custo, String> cName;
    @FXML
    private TableColumn<Custo, Integer> cPib;
    @FXML
    private TableColumn<Custo, String> cAddress;
    @FXML
    private Text textCustomerList;
    @FXML
    private Text textAddNewCustomer;
    @FXML
    private TextField customerNameField;
    @FXML
    private Text textCustomerName;
    @FXML
    private Text textCustomerVat;
    @FXML
    private Text textCustomerAddress;
    @FXML
    private TextField customerVatField;
    @FXML
    private TextField customerAddressField;
    @FXML
    private Text textCustomerGroup;
    @FXML
    private TextField customerGroupField;
    @FXML
    private Button clearCustomerButton;
    @FXML
    private Button addNewCustomerButton;
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button updateCustomerButton;
    @FXML
    private Button updateCusto;
    @FXML
    private Text textLginPage;

    @FXML
    private TextField passLogin;
    @FXML
    private ComboBox<String> comboBoxLogin;
    @FXML
    private Button loginButton;
    @FXML
    private Text selectCusto;
    @FXML
    private Text selectedCustoLabel;
    @FXML
    private Button chooseArticle;
    @FXML
    private Button buyButon;
    @FXML
    private Text textAccount;
    @FXML
    private Text textPassword;
    @FXML
    private Text textSelectInfo;
    @FXML
    private Text selectCusto1;
    @FXML
    private Text textUpdateCustomer;

    @Override

    public void initialize(URL url, ResourceBundle resources) {
        Thread t1 = new Thread(new MyRun());
        t1.start();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        flowRate.setCellValueFactory(new PropertyValueFactory<>("flowRate"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        custo_id.setCellValueFactory(new PropertyValueFactory<>("custoId"));
        cName.setCellValueFactory(new PropertyValueFactory<>("custoName"));
        cPib.setCellValueFactory(new PropertyValueFactory<>("custoPib"));
        cAddress.setCellValueFactory(new PropertyValueFactory<>("custoAddress"));
        //comboBoxLogin = new ComboBox<>();
        //comboBoxLogin.setItems(fillCombo());
        //comboBoxLogin.getSelectionModel().select(1);
        //masterData.add(custoBean.getCustoName());
        //cName.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), masterData));

        ObservableList<String> data = FXCollections.observableArrayList();

        try {
            conn = ConnDb.Connection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "SELECT * FROM custo";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Custo custoBean = new Custo();
                custoBean.setCustoName(rs.getString("custoName"));
                data.addAll(custoBean.getCustoName());

            }

        } catch (SQLException e) {
        }
        comboBoxLogin.getItems().setAll(data);
        comboBoxLogin.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        loginCustomerName = newValue;

                    }

                });

    }

    @FXML
    private void homeButton(MouseEvent event) {
        cView.setVisible(false);
        tView.setVisible(false);
        addPerson.setVisible(false);
        addButton.setVisible(false);
        textFlowRate.setVisible(false);
        textRate.setVisible(false);
        textDuration.setVisible(false);
        textName.setVisible(false);
        textAddress.setVisible(false);
        fieldFlowRate.setVisible(false);
        fieldRate.setVisible(false);
        fieldDuration.setVisible(false);
        fieldName.setVisible(false);
        fieldAddress.setVisible(false);
        deletePerson.setVisible(false);
        clearBtn.setVisible(false);
        addButton.setVisible(false);
        updateButton.setVisible(false);
        imgOnHomeScreen.setVisible(true);
        textWelcome.setVisible(true);
        textEntry.setVisible(true);
        textUpdate.setVisible(false);
        textArticlesList.setVisible(false);
        textCustomerList.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        textCustomerGroup.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(false);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(false);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        loginButton.setVisible(false);
        selectCusto.setVisible(false);
        selectedCustoLabel.setVisible(false);
        chooseArticle.setVisible(false);
        buyButon.setVisible(false);
        textPassword.setVisible(false);
        textAccount.setVisible(false);
        textSelectInfo.setVisible(false);
        textUpdateCustomer.setVisible(false);
    }

    @FXML
    private void listButton(MouseEvent event) {

        tView.setVisible(true);
        addPerson.setVisible(false);
        addButton.setVisible(false);
        textFlowRate.setVisible(false);
        textRate.setVisible(false);
        textDuration.setVisible(false);
        textName.setVisible(false);
        textAddress.setVisible(false);
        fieldFlowRate.setVisible(false);
        fieldRate.setVisible(false);
        fieldDuration.setVisible(false);
        fieldName.setVisible(false);
        fieldAddress.setVisible(false);
        deletePerson.setVisible(true);
        clearBtn.setVisible(false);
        updateButton.setVisible(true);
        imgOnHomeScreen.setVisible(false);
        textWelcome.setVisible(false);
        textEntry.setVisible(false);
        textUpdate.setVisible(false);
        textArticlesList.setVisible(true);
        cView.setVisible(false);
        textCustomerList.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        textCustomerGroup.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(false);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(false);
        updtArtcl.setVisible(false);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        loginButton.setVisible(false);
        selectCusto.setVisible(false);
        selectedCustoLabel.setVisible(false);
        chooseArticle.setVisible(false);
        buyButon.setVisible(false);
        textPassword.setVisible(false);
        textAccount.setVisible(false);
        textSelectInfo.setVisible(false);
        textUpdateCustomer.setVisible(false);

        person = new Person();
        try {
            tView.getItems().setAll(parseUserList());

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addPerson(MouseEvent event) {
        tView.setVisible(false);
        addPerson.setVisible(true);
        addButton.setVisible(true);
        textFlowRate.setVisible(true);
        textRate.setVisible(true);
        textDuration.setVisible(true);
        textName.setVisible(true);
        textAddress.setVisible(true);
        fieldFlowRate.setVisible(true);
        fieldRate.setVisible(true);
        fieldDuration.setVisible(true);
        fieldName.setVisible(true);
        fieldAddress.setVisible(true);
        deletePerson.setVisible(false);
        clearBtn.setVisible(true);
        updtArtcl.setVisible(false);
        updateButton.setVisible(false);
        imgOnHomeScreen.setVisible(false);
        textWelcome.setVisible(false);
        textEntry.setVisible(false);
        textUpdate.setVisible(false);
        textArticlesList.setVisible(false);
        cView.setVisible(false);
        textCustomerList.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        textCustomerGroup.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(false);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(false);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        loginButton.setVisible(false);
        selectCusto.setVisible(false);
        selectedCustoLabel.setVisible(false);
        chooseArticle.setVisible(false);
        buyButon.setVisible(false);
        textPassword.setVisible(false);
        textAccount.setVisible(false);
        textSelectInfo.setVisible(false);
        textUpdateCustomer.setVisible(false);
    }

    List<Person> parseUserList() throws SQLException {
        dao = new CustomerDao();
        List<Person> List = dao.getAllCustomers();
        return List;
    }

    List<Custo> parseCustoList() throws SQLException {
        cDao = new CustoDao();
        List<Custo> custoList = cDao.getAllCusto();
        return custoList;
    }

    @FXML
    private void addPersonButton(MouseEvent event) throws ClassNotFoundException, SQLException {

        person = new Person();
        parseUserList();

        String flRa = fieldFlowRate.getText();
        person.setFlowRate(flRa);
        String rt = fieldRate.getText();
        person.setRate(rt);
        String dur = fieldDuration.getText();
        person.setDuration(dur);
        String nam = fieldName.getText();
        person.setName(nam);
        String addr = fieldAddress.getText();
        person.setAddress(addr);

        if (person.isValid()) {
            person.save();
            dao.addCustomer(person);
            tView.getItems().setAll(parseUserList());
            listButton(event);
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
    private void deletePerson(MouseEvent event) {
        try {

            int cid = tView.getSelectionModel().getSelectedItem().getId();

            dao.deleteCustomer(cid);
            tView.getItems().setAll(parseUserList());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An error has occurred !");
            alert.setHeaderText("Select the person you want to delete!");

            alert.showAndWait();
        }
    }

    @FXML
    private void clearButtonAction(MouseEvent event) {
        fieldFlowRate.setText("");
        fieldRate.setText("");
        fieldDuration.setText("");
        fieldName.setText("");
        fieldAddress.setText("");
    }

    @FXML
    private void updateAtricleAction(MouseEvent event) throws SQLException {
        tView.setVisible(false);
        addPerson.setVisible(true);
        addButton.setVisible(false);
        textFlowRate.setVisible(true);
        textRate.setVisible(true);
        textDuration.setVisible(true);
        textName.setVisible(true);
        textAddress.setVisible(true);
        fieldFlowRate.setVisible(true);
        fieldRate.setVisible(true);
        fieldDuration.setVisible(true);
        fieldName.setVisible(true);
        fieldAddress.setVisible(true);
        deletePerson.setVisible(false);
        clearBtn.setVisible(true);
        updateButton.setVisible(false);
        updtArtcl.setVisible(true);
        addButton.setVisible(false);
        textUpdate.setVisible(true);
        addPerson.setVisible(false);
        cView.setVisible(false);
        textCustomerList.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        textCustomerGroup.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(false);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(false);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        loginButton.setVisible(false);
        selectCusto.setVisible(false);
        selectedCustoLabel.setVisible(false);
        chooseArticle.setVisible(false);
        buyButon.setVisible(false);
        textPassword.setVisible(false);
        textAccount.setVisible(false);
        textSelectInfo.setVisible(false);
        textArticlesList.setVisible(false);
        textUpdateCustomer.setVisible(false);
        
        try {

        int cid = tView.getSelectionModel().getSelectedItem().getId();
        String fRate = tView.getSelectionModel().getSelectedItem().getFlowRate();
        String rte = tView.getSelectionModel().getSelectedItem().getRate();
        String dur = tView.getSelectionModel().getSelectedItem().getDuration();
        String name = tView.getSelectionModel().getSelectedItem().getName();
        String addr = tView.getSelectionModel().getSelectedItem().getAddress();
        
        

        String In = Integer.toString(cid);
        fieldId.setText(In);
        fieldFlowRate.setText(fRate);
        fieldRate.setText(rte);
        fieldDuration.setText(dur);
        fieldName.setText(name);
        fieldAddress.setText(addr);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An error has occurred !");
            alert.setHeaderText("Select the article you want to update!");

            alert.showAndWait();
        }
        
        

       

    }

    @FXML
    private void updtArtcl(MouseEvent event) throws ClassNotFoundException, SQLException {

        updtArtcl.setVisible(false);
        String iN = fieldId.getText();
        int foo = Integer.parseInt(iN);
        person.setId(foo);
        String flRa = fieldFlowRate.getText();
        person.setFlowRate(flRa);
        String rt = fieldRate.getText();
        person.setRate(rt);
        String dur = fieldDuration.getText();
        person.setDuration(dur);
        String nam = fieldName.getText();
        person.setName(nam);
        String addr = fieldAddress.getText();
        person.setAddress(addr);
        
        System.out.println("Id articla je " + foo);

        if (person.isValid()) {
            person.save();
            dao.updateCustomer(person);
            tView.getItems().setAll(parseUserList());
            listButton(event);
        } else {
            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = person.errorsProperty().get();

            errList.forEach((errList1) -> {
                errMsg.append(errList1);
            });
            System.out.println(errMsg);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Person can't be saved!");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();

        }
    }

    @FXML
    private void listCusto(MouseEvent event) {
        tView.setVisible(false);
        addPerson.setVisible(false);
        addButton.setVisible(false);
        textFlowRate.setVisible(false);
        textRate.setVisible(false);
        textDuration.setVisible(false);
        textName.setVisible(false);
        textAddress.setVisible(false);
        fieldFlowRate.setVisible(false);
        fieldRate.setVisible(false);
        fieldDuration.setVisible(false);
        fieldName.setVisible(false);
        fieldAddress.setVisible(false);
        deletePerson.setVisible(false);
        clearBtn.setVisible(false);
        updateButton.setVisible(false);
        imgOnHomeScreen.setVisible(false);
        textWelcome.setVisible(false);
        textEntry.setVisible(false);
        textUpdate.setVisible(false);
        textArticlesList.setVisible(false);
        cView.setVisible(true);
        textCustomerList.setVisible(true);
        textAddNewCustomer.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        textCustomerGroup.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(false);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(true);
        updateCustomerButton.setVisible(true);
        updateCusto.setVisible(false);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        loginButton.setVisible(false);
        selectCusto.setVisible(false);
        selectedCustoLabel.setVisible(false);
        chooseArticle.setVisible(false);
        buyButon.setVisible(false);
        textPassword.setVisible(false);
        textAccount.setVisible(false);
        textSelectInfo.setVisible(false);
        textUpdateCustomer.setVisible(false);

        custo = new Custo();

        cDao = new CustoDao();
        try {
            cView.getItems().setAll(parseCustoList());

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addNewCustomer(MouseEvent event) {
        tView.setVisible(false);
        addPerson.setVisible(false);
        addButton.setVisible(false);
        textFlowRate.setVisible(false);
        textRate.setVisible(false);
        textDuration.setVisible(false);
        textName.setVisible(false);
        textAddress.setVisible(false);
        fieldFlowRate.setVisible(false);
        fieldRate.setVisible(false);
        fieldDuration.setVisible(false);
        fieldName.setVisible(false);
        fieldAddress.setVisible(false);
        deletePerson.setVisible(false);
        clearBtn.setVisible(false);
        updateButton.setVisible(false);
        imgOnHomeScreen.setVisible(false);
        textWelcome.setVisible(false);
        textEntry.setVisible(false);
        textUpdate.setVisible(false);
        textArticlesList.setVisible(false);
        cView.setVisible(false);
        textCustomerList.setVisible(false);
        textAddNewCustomer.setVisible(true);
        textCustomerName.setVisible(true);
        textCustomerVat.setVisible(true);
        textCustomerAddress.setVisible(true);
        customerNameField.setVisible(true);
        customerVatField.setVisible(true);
        customerAddressField.setVisible(true);
        textCustomerGroup.setVisible(true);
        customerGroupField.setVisible(true);
        clearCustomerButton.setVisible(false);
        clearCustomerButton.setVisible(true);
        addNewCustomerButton.setVisible(true);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(false);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        loginButton.setVisible(false);
        selectCusto.setVisible(false);
        selectedCustoLabel.setVisible(false);
        chooseArticle.setVisible(false);
        buyButon.setVisible(false);
        textPassword.setVisible(false);
        textAccount.setVisible(false);
        textSelectInfo.setVisible(false);
        textUpdateCustomer.setVisible(false);
    }

    @FXML
    private void clearCustomerButton(MouseEvent event) {

        customerNameField.setText("");
        customerVatField.setText("");
        customerAddressField.setText("");
        customerGroupField.setText("");
    }

    @FXML
    private void addNewCustomerButton(MouseEvent event) throws ClassNotFoundException, SQLException {

        custo = new Custo();
        parseCustoList();

        String cN = customerNameField.getText();
        custo.setCustoName(cN);
        String cPib = customerVatField.getText();

        custo.setCustoPib(cPib);
        String cAd = customerAddressField.getText();
        custo.setCustoAddress(cAd);

        if (custo.isValid()) {
            custo.save();
            cDao.addCusto(custo);
            cView.getItems().setAll(parseCustoList());
            listCusto(event);

        } else {
            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = custo.errorsProperty().get();

            errList.forEach((errList1) -> {
                errMsg.append(errList1);
            });
            System.out.println(errMsg);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer can't be saved!");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();

        }
    }

    @FXML
    private void deleteCustomer(MouseEvent event) {
        try {

            int cid = cView.getSelectionModel().getSelectedItem().getCustoId();

            cDao.deleteCusto(cid);
            cView.getItems().setAll(parseCustoList());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An error has occurred !");
            alert.setHeaderText("Select the customer you want to delete!");

            alert.showAndWait();
        }
    }

    @FXML
    private void updateCustomerAction(MouseEvent event) {
        cView.setVisible(false);
        addPerson.setVisible(false);
        textAddNewCustomer.setVisible(false);
        addButton.setVisible(false);
        textFlowRate.setVisible(false);
        textRate.setVisible(false);
        textDuration.setVisible(false);
        textName.setVisible(false);
        textAddress.setVisible(false);
        fieldFlowRate.setVisible(false);
        fieldRate.setVisible(false);
        fieldDuration.setVisible(false);
        fieldName.setVisible(false);
        fieldAddress.setVisible(false);
        deletePerson.setVisible(false);
        clearBtn.setVisible(false);
        updateButton.setVisible(false);
        updtArtcl.setVisible(false);
        addButton.setVisible(false);
        textUpdate.setVisible(false);
        addPerson.setVisible(false);
        cView.setVisible(false);
        textCustomerList.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(true);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        textCustomerName.setVisible(true);
        textCustomerVat.setVisible(true);
        textCustomerAddress.setVisible(true);
        customerNameField.setVisible(true);
        customerVatField.setVisible(true);
        customerAddressField.setVisible(true);
        textCustomerGroup.setVisible(true);
        customerGroupField.setVisible(true);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(true);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        loginButton.setVisible(false);
        selectCusto.setVisible(false);
        selectedCustoLabel.setVisible(false);
        chooseArticle.setVisible(false);
        buyButon.setVisible(false);
        textPassword.setVisible(false);
        textAccount.setVisible(false);
        textSelectInfo.setVisible(false);
        textUpdateCustomer.setVisible(true);
        
        
         try {

            int cid = cView.getSelectionModel().getSelectedItem().getCustoId();
        String cNam = cView.getSelectionModel().getSelectedItem().getCustoName();
        String cVa = cView.getSelectionModel().getSelectedItem().getCustoPib();
        String cAddr = cView.getSelectionModel().getSelectedItem().getCustoAddress();

        String cIn = Integer.toString(cid);
        System.out.println("Id Kupca" + cIn);
        customerNameField.setText(cNam);
        customerVatField.setText(cVa);
        customerAddressField.setText(cAddr);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An error has occurred !");
            alert.setHeaderText("Select the customer you want to update!");

            alert.showAndWait();
        }
        

        

    }

    @FXML
    private void updateCusto(MouseEvent event) throws SQLException, ClassNotFoundException {

        int cN = cView.getSelectionModel().getSelectedItem().getCustoId();
        custo.setId(cN);
        String cNam = customerNameField.getText();
        custo.setCustoName(cNam);
        String cVa = customerVatField.getText();
        custo.setCustoPib(cVa);
        String cAd = customerAddressField.getText();
        custo.setCustoAddress(cAd);

        if (custo.isValid()) {
            custo.save();
            cDao.updateCusto(custo);
            cView.getItems().setAll(parseCustoList());
            listCusto(event);
        } else {
            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = custo.errorsProperty().get();

            errList.forEach((errList1) -> {
                errMsg.append(errList1);
            });
            System.out.println(errMsg);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer can't be saved!");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();

        }
    }

    @FXML
    private void contactUs(MouseEvent event) throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.MAIL)) {
                URI mailto = new URI("mailto:dejan@example.com?subject=Hello%20There");
                desktop.mail(mailto);
            }
        }
    }

    @FXML
    private void loginPage(MouseEvent event) throws SQLException {
        tView.setVisible(false);
        addPerson.setVisible(false);
        addButton.setVisible(false);
        textFlowRate.setVisible(false);
        textRate.setVisible(false);
        textDuration.setVisible(false);
        textName.setVisible(false);
        textAddress.setVisible(false);
        fieldFlowRate.setVisible(false);
        fieldRate.setVisible(false);
        fieldDuration.setVisible(false);
        fieldName.setVisible(false);
        fieldAddress.setVisible(false);
        deletePerson.setVisible(false);
        clearBtn.setVisible(false);
        updateButton.setVisible(false);
        imgOnHomeScreen.setVisible(false);
        textWelcome.setVisible(false);
        textEntry.setVisible(false);
        textUpdate.setVisible(false);
        textArticlesList.setVisible(false);
        cView.setVisible(false);
        textCustomerList.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        textCustomerGroup.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(false);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(false);
        textLginPage.setVisible(true);
        loginButton.setVisible(true);
        selectCusto.setVisible(false);
        selectedCustoLabel.setVisible(false);
        chooseArticle.setVisible(false);
        buyButon.setVisible(false);
        textPassword.setVisible(true);
        textAccount.setVisible(true);
        textSelectInfo.setVisible(false);
        textUpdateCustomer.setVisible(false);
        custo = new Custo();

        cDao = new CustoDao();

        comboBoxLogin.setVisible(true);
        passLogin.setVisible(true);

    }

    @FXML
    private void loginAction(MouseEvent event) throws SQLException {
        if(loginCustomerName != null){
            
        tView.setVisible(false);
        addPerson.setVisible(false);
        addButton.setVisible(false);
        textFlowRate.setVisible(false);
        textRate.setVisible(false);
        textDuration.setVisible(false);
        textName.setVisible(false);
        textAddress.setVisible(false);
        fieldFlowRate.setVisible(false);
        fieldRate.setVisible(false);
        fieldDuration.setVisible(false);
        fieldName.setVisible(false);
        fieldAddress.setVisible(false);
        deletePerson.setVisible(false);
        clearBtn.setVisible(false);
        updateButton.setVisible(false);
        imgOnHomeScreen.setVisible(false);
        textWelcome.setVisible(false);
        textEntry.setVisible(false);
        textUpdate.setVisible(false);
        textArticlesList.setVisible(false);
        cView.setVisible(false);
        textCustomerList.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        textCustomerGroup.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(false);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(false);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        System.out.println("Zdravo " + loginCustomerName);
        loginButton.setVisible(false);
        selectCusto.setVisible(true);
        selectedCustoLabel.setVisible(true);
        selectCusto.setText(loginCustomerName);
        textPassword.setVisible(false);
        textAccount.setVisible(false);

        tView.setVisible(true);
        addPerson.setVisible(false);
        addButton.setVisible(false);
        textFlowRate.setVisible(false);
        textRate.setVisible(false);
        textDuration.setVisible(false);
        textName.setVisible(false);
        textAddress.setVisible(false);
        fieldFlowRate.setVisible(false);
        fieldRate.setVisible(false);
        fieldDuration.setVisible(false);
        fieldName.setVisible(false);
        fieldAddress.setVisible(false);
        deletePerson.setVisible(false);
        clearBtn.setVisible(false);
        updateButton.setVisible(false);
        imgOnHomeScreen.setVisible(false);
        textWelcome.setVisible(false);
        textEntry.setVisible(false);
        textUpdate.setVisible(false);
        textArticlesList.setVisible(false);
        cView.setVisible(false);
        textCustomerList.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        textCustomerGroup.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(false);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(false);
        updtArtcl.setVisible(false);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        loginButton.setVisible(false);
        chooseArticle.setVisible(true);
        buyButon.setVisible(false);
        textSelectInfo.setVisible(false);
        textUpdateCustomer.setVisible(false);

        person = new Person();
        try {
            tView.getItems().setAll(parseUserList());

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Choose Account");
            alert.showAndWait();
        }
        
      

    }

    @FXML
    private void chooseArticleAction(MouseEvent event) throws SQLException {

        tView.setVisible(false);
        addPerson.setVisible(true);
        addButton.setVisible(false);
        textFlowRate.setVisible(true);
        textRate.setVisible(true);
        textDuration.setVisible(true);
        textName.setVisible(true);
        textAddress.setVisible(true);
        fieldFlowRate.setVisible(true);
        fieldRate.setVisible(true);
        fieldDuration.setVisible(true);
        fieldName.setVisible(true);
        fieldAddress.setVisible(true);
        deletePerson.setVisible(false);
        clearBtn.setVisible(false);
        updateButton.setVisible(false);
        updtArtcl.setVisible(false);
        addButton.setVisible(false);
        textUpdate.setVisible(false);
        addPerson.setVisible(false);
        cView.setVisible(false);
        textCustomerList.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textAddNewCustomer.setVisible(false);
        textCustomerName.setVisible(false);
        textCustomerVat.setVisible(false);
        textCustomerAddress.setVisible(false);
        customerNameField.setVisible(false);
        customerVatField.setVisible(false);
        customerAddressField.setVisible(false);
        textCustomerGroup.setVisible(false);
        customerGroupField.setVisible(false);
        clearCustomerButton.setVisible(false);
        addNewCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        updateCustomerButton.setVisible(false);
        updateCusto.setVisible(false);
        textLginPage.setVisible(false);
        comboBoxLogin.setVisible(false);
        passLogin.setVisible(false);
        loginButton.setVisible(false);
        selectCusto.setVisible(false);
        selectedCustoLabel.setVisible(false);
        chooseArticle.setVisible(false);
        buyButon.setVisible(true);
        textPassword.setVisible(false);
        textAccount.setVisible(false);
        textSelectInfo.setVisible(true);
        textUpdateCustomer.setVisible(false);
        

        int cid = tView.getSelectionModel().getSelectedItem().getId();
        String fRate = tView.getSelectionModel().getSelectedItem().getFlowRate();
        String rte = tView.getSelectionModel().getSelectedItem().getRate();
        String dur = tView.getSelectionModel().getSelectedItem().getDuration();
        String name = tView.getSelectionModel().getSelectedItem().getName();
        String addr = tView.getSelectionModel().getSelectedItem().getAddress();

        String In = Integer.toString(cid);
        fieldId.setText(In);
        fieldFlowRate.setText(fRate);
        fieldRate.setText(rte);
        fieldDuration.setText("1");
        fieldName.setText(name);
        fieldAddress.setText(addr);

    }

    @FXML
    private void buyArticleAction(MouseEvent event) throws SQLException {
        updtArtcl.setVisible(false);
        String iN = fieldId.getText();
        int foo = Integer.parseInt(iN);
        person.setId(foo);
        String flRa = fieldFlowRate.getText();
        person.setFlowRate(flRa);
        String rt = fieldRate.getText();
        person.setRate(rt);
        String dur = fieldDuration.getText();
        person.setDuration(dur);
        String nam = fieldName.getText();
        person.setName(nam);
        String addr = fieldAddress.getText();
        person.setAddress(addr);

        if (person.isValid()) {
            person.save();
            dao.buyArticle(person);
            tView.getItems().setAll(parseUserList());
            loginAction(event);
        } else {
            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = person.errorsProperty().get();

            errList.forEach((errList1) -> {
                errMsg.append(errList1);
            });
            System.out.println(errMsg);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Article can't be purchased!");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();

        }
    }

}
