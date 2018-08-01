package model;


import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
 
public final class Person {
    
    ObservableList<Person> data;
    Person person;
    CustomerDao dao;
    
    
   private final SimpleIntegerProperty id = new SimpleIntegerProperty();
   private final SimpleStringProperty flowRate = new SimpleStringProperty(this, "flowRate", "");;
   private final SimpleStringProperty rate = new SimpleStringProperty(this, "rate", "");;
   private final SimpleStringProperty duration = new SimpleStringProperty(this, "duration", "");;
   private final SimpleStringProperty name = new SimpleStringProperty("");
   private final SimpleStringProperty address = new SimpleStringProperty("");
  

public Person() {
        this(0, "", "", "", "", "");
    }

 
    public Person(int id, String flowRate, String iRate, String cD, String name, String address) {
        setId(id);
        setFlowRate(flowRate);
        setRate(iRate);
        setDuration(cD);
        setName(name);
        setAddress(address);
    }

    public Person(int id, String name){
    }
    
    public Person(String flowRate, String iRate, String cD, String name, String address) {
     }

    Person(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id.get();
    }
 
    public void setId(int Id) {
        id.set(Id);
    }
     public IntegerProperty idProperty() {
        return id;
    }
    public String getFlowRate() {
        return flowRate.get();
    }
 
    public void setFlowRate(String fRate) {
        flowRate.set(fRate);
    }
     public StringProperty flowRateProperty() {
        return flowRate;
    }
        
    public String getRate() {
        return rate.get();
    }
    
    public void setRate(String iRate) {
        rate.set(iRate);
    }
     public StringProperty rateProperty() {
        return rate;
    }
    
    public String getDuration() {
        return duration.get();
    }
    
    public void setDuration(String cDur) {
        duration.set(cDur);
    }
     public StringProperty durationProperty() {
        return duration;
    }
     public String getName() {
        return name.get();
    }
    
    public void setName(String pName) {
        name.set(pName);
    }
     public StringProperty nameProperty() {
        return name;
    }
     public String getAddress() {
        return address.get();
    }
    
    public void setAddress(String pAddress) {
        address.set(pAddress);
    }
     public StringProperty addressProperty() {
        return address;
    }
      private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

    public ObjectProperty<ArrayList<String>> errorsProperty() {
        return errorList;
    }

    public boolean isValid() {

        boolean isValid = true;

        if (flowRate.get().equals("")) {
            errorList.getValue().add("Article Number can't be empty!  \n");
            isValid = false;
        }
        if (rate.get().equals("")) {
            errorList.getValue().add("Serial Number can't be empty!  \n");
            isValid = false;
        }
        if (duration.get().equals("")) {
            errorList.getValue().add("Quantity can't be empty! \n");
            isValid = false;
        }
        if (name.get() != null && name.get().equals("")) {
            errorList.getValue().add("Article Name can't be empty!  \n");
            isValid = false;
        }
        if (address.get().equals("")) {
            errorList.getValue().add("Manufacturer can't be empty!  \n");
            isValid = false;
        }

        return isValid;
    }

    public boolean save() {

        if (isValid()) {
            return true;
        }

            return false;

    }
}

