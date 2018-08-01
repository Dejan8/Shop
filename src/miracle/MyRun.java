/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miracle;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CustomerDao;
import model.Person;

/**
 *
 * @author Dejan
 */
public class MyRun extends Thread{
    Person person;
    CustomerDao dao;
    
@Override
public void run(){
   person = new Person();
    for (int i = 0; i < 1000000; i++) {
        try {
            parseUserList();
            System.out.println(Thread.currentThread().getId() + "Value" + i);
            Thread.sleep(2000);
        } catch (InterruptedException | SQLException ex) {
            Logger.getLogger(MyRun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }try{
    Thread.sleep(1000);
    }catch(InterruptedException e){
    }
}
 List<Person> parseUserList() throws SQLException {
        dao = new CustomerDao();
        List<Person> List = dao.getAllCustomers();
        return List;
    }
}
