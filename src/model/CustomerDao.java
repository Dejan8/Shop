package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class CustomerDao {

    private Connection conn = null;

    

    public void addCustomer(Person personBean) throws ClassNotFoundException, SQLException {
        conn = ConnDb.Connection.getConnection();
        String flowRate2 = personBean.getFlowRate();
        String rate2 = personBean.getRate();
        String duration2 = personBean.getDuration();
        String name2 = personBean.getName();
        String address2 = personBean.getAddress();

        Statement st = conn.createStatement();
        st.execute("insert into person(FlowRate,Rate,Duration,PersonName,Address) values('" + flowRate2 + "','" + rate2 + "','" + duration2 + "','" + name2 + "','" + address2 + "')");
        System.out.println("Proslo addCustomer");
    }

    public List getAllCustomers() throws SQLException {
        conn = ConnDb.Connection.getConnection();
        List customers = new ArrayList();
        try {

            String sql = "SELECT * FROM person";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person personBean = new Person();
                personBean.setId(rs.getInt("PersonID"));
                personBean.setFlowRate(rs.getString("FlowRate"));
                personBean.setRate(rs.getString("Rate"));
                personBean.setDuration(rs.getString("Duration"));
                personBean.setName(rs.getString("PersonName"));
                personBean.setAddress(rs.getString("Address"));
                customers.add(personBean);

            }
        } catch (SQLException e) {
        }

        return customers;
    }

    public void deleteCustomer(int PersonID) throws SQLException {
        conn = ConnDb.Connection.getConnection();
        try {
            String sql = "DELETE FROM person WHERE PersonID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, PersonID);
            ps.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
      public void updateCustomer(Person personBean) throws SQLException {
        conn = ConnDb.Connection.getConnection();
        
        int id = personBean.getId();
        String flowRate2 = personBean.getFlowRate();
        String rate2 = personBean.getRate();
        String Duration = personBean.getDuration();
        String name2 = personBean.getName();
        String address2 = personBean.getAddress();
        
        
        Statement st = conn.createStatement();
        st.execute("UPDATE person SET FlowRate='" + flowRate2 + "' , Rate='" + rate2 + "' , Duration='" + Duration + "' , PersonName='" + name2 + "' , Address='" + address2 + "'   where PersonID ='" + id + "'");
       
        JOptionPane.showMessageDialog(null, "Article Updated");
       
          
    }
       public void buyArticle(Person personBean) throws SQLException {
        conn = ConnDb.Connection.getConnection();
        
        int id = personBean.getId();
        String flowRate2 = personBean.getFlowRate();
        String rate2 = personBean.getRate();
        String Duration = personBean.getDuration();
        String name2 = personBean.getName();
        String address2 = personBean.getAddress();
        
        
        Statement st = conn.createStatement();
        st.execute("UPDATE person SET  Duration=Duration -'" + Duration  +  "'   where PersonID ='" + id + "'");
       
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation complete!");
            alert.setHeaderText(null);
            alert.setContentText("Article "+name2+" has been purchased");
            alert.showAndWait();
            
       
          
    }
}
