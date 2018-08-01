package model;


import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
 
public final class Custo {
    
    ObservableList<Custo> data;
    Custo custo;
    CustoDao cDao;
    
    
   private final SimpleIntegerProperty custoId = new SimpleIntegerProperty();
   private final SimpleStringProperty custoName = new SimpleStringProperty(this, "custoName", "");;
   private final SimpleStringProperty custoPib = new SimpleStringProperty(this,"custoPib", "");
   private final SimpleStringProperty custoAddress = new SimpleStringProperty(this, "custoAddress", "");;
  

public Custo() {
        this(0, "", "", "");
    }

 
    public Custo(int custoId, String custoName, String custoPib, String custoAddress) {
        setId(custoId);
        setCustoName(custoName);
        setCustoPib(custoPib);
        setCustoAddress(custoAddress);
        
    }

    public Custo(int custoId, String custoName){
        
    }
    
    public Custo(String custoName, String custoPib, String custoAddress) {
     }

    Custo(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCustoId() {
        return custoId.get();
    }
 
    public void setId(int cId) {
        custoId.set(cId);
    }
     public IntegerProperty idProperty() {
        return custoId;
    }
    public String getCustoName() {
        return custoName.get();
    }
 
    public void setCustoName(String cuName) {
        custoName.set(cuName);
    }
     public StringProperty custoNameProperty() {
        return custoName;
    }
    
    
    public String getCustoPib() {
        return custoPib.get();
    }
    
    public void setCustoPib(String cPib) {
        custoPib.set(cPib);
    }
     public StringProperty custoPibProperty() {
        return custoPib;
    }
    
     public String getCustoAddress() {
        return custoAddress.get();
    }
    
    public void setCustoAddress(String cAddress) {
        custoAddress.set(cAddress);
    }
     public StringProperty custoAddressProperty() {
        return custoAddress;
    }
      private final ObjectProperty<ArrayList<String>> errorList2 = new SimpleObjectProperty<>(this, "errorList2", new ArrayList<>());

    public ObjectProperty<ArrayList<String>> errorsProperty() {
        return errorList2;
    }

    public boolean isValid() {

        boolean isValid = true;

        if (custoName.get().equals("")) {
            errorList2.getValue().add("Name can't be empty!  \n");
            isValid = false;
        }
      
        if (custoPib.get().equals("")) {
            errorList2.getValue().add("Vat can't be empty!  \n");
            isValid = false;
        }
        //(text.matches("[0-9]+") && text.length() == 10)
        String pib = custoPib.get();
        System.out.println("PIB " + pib);
        
        if (!pib.matches ("[0-9]+")) {
            errorList2.getValue().add("Vat must contains only numbers!  \n");
            isValid = false;
        }
        if (pib.length() != 9) {
            errorList2.getValue().add("Vat must contains 9 numbers!  \n");
            isValid = false;
        }
        
        if (custoAddress.get().equals("")) {
            errorList2.getValue().add("Address can't be empty! \n");
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

