package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CustoDao {

    private Connection conn = null;

    public void addCusto(Custo custoBean) throws ClassNotFoundException, SQLException {

        conn = ConnDb.Connection.getConnection();
        String custoName = custoBean.getCustoName();
        String custoPin = custoBean.getCustoPib();
        String custoAddress = custoBean.getCustoAddress();

        Statement st = conn.createStatement();
        st.execute("insert into custo(custoName,custoPib,custoAddress) values('" + custoName + "','" + custoPin + "','" + custoAddress + "')");

    }

    public List getAllCusto() throws SQLException {
        conn = ConnDb.Connection.getConnection();
        List custo = new ArrayList();
        try {

            String sql = "SELECT * FROM custo";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Custo custoBean = new Custo();
                custoBean.setId(rs.getInt("custoID"));
                custoBean.setCustoName(rs.getString("custoName"));
                custoBean.setCustoPib(rs.getString("custoPib"));
                custoBean.setCustoAddress(rs.getString("custoAddress"));
                custo.add(custoBean);

            }
        } catch (SQLException e) {
        }

        return custo;
    }

    public void deleteCusto(int custoID) throws SQLException {
        conn = ConnDb.Connection.getConnection();
        try {
            String sql = "DELETE FROM custo WHERE custoID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, custoID);
            ps.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public void updateCusto(Custo custoBean) throws SQLException {
        conn = ConnDb.Connection.getConnection();

        int id = custoBean.getCustoId();
        String custoName = custoBean.getCustoName();
        String custoPib = custoBean.getCustoPib();
        String custoAddress = custoBean.getCustoAddress();

        Statement st = conn.createStatement();
        st.execute("UPDATE custo SET custoName='" + custoName + "' , custoPib='" + custoPib + "' , custoAddress='" + custoAddress + "'   where custoId ='" + id + "'");

        JOptionPane.showMessageDialog(null, "Customer Updated");

    }
   
}
