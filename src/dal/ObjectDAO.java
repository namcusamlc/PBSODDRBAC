/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ObjectModel;
import model.RoleModel;

/**
 *
 * @author Nam
 */
public class ObjectDAO extends BaseDAO<ObjectModel>{

    @Override
    public int insert(ObjectModel model) {
        try{
            String sql = "INSERT INTO Objects (ObjectName, FromDay, ToDay, FromTime, ToTime, IpAddress, Path)\n"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getObjectName());
            statement.setString(2, model.getFromDay());
            statement.setString(3, model.getToDay());
            statement.setString(4, model.getFromTime());
            statement.setString(5, model.getToTime());
            statement.setString(6, model.getIpAddress());
            statement.setString(7, model.getPath());
            statement.execute();
            
        }
        catch(SQLException err){
            System.out.println("Ops! " + err);
            return 0;
        }
        return 1;
    }
    
    //Get Object having criterias matches the role's criterias
    public ArrayList<ObjectModel> getByRole(RoleModel role) {
        ArrayList<ObjectModel> objects = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM Objects WHERE IpAddress=? AND FromDay=? "
                    + "AND ToDay=? AND FromTime=? AND ToTime=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role.getIpAddress());
            statement.setString(2, role.getFromDay());
            statement.setString(3, role.getToDay());
            statement.setString(4, role.getFromTime());
            statement.setString(5, role.getToTime());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                ObjectModel obj = new ObjectModel();
                obj.setObjectID(rs.getInt("ObjectID"));
                obj.setObjectName(rs.getString("ObjectName"));
                obj.setFromDay(rs.getString("FromDay"));
                obj.setToDay(rs.getString("ToDay"));
                obj.setFromTime(rs.getString("FromTime"));
                obj.setToTime(rs.getString("ToTime"));
                obj.setIpAddress(rs.getString("IpAddress"));
                objects.add(obj);
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get all of Object" + err);
        }
        
        return objects;
    }
    
    public int getCurrentID() {
        try{
            String sql = "SELECT IDENT_CURRENT('Objects') as MaxID";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                return rs.getInt("MaxID");
            }
        }
        catch(SQLException err){
            System.out.println("Ops! " + err);
            return 0;
        }
        return 0;
    }
    
    public ObjectModel getByName(String name) {
        try{
            String sql = "SELECT * FROM Objects\n"
                    + "WHERE ObjectName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                ObjectModel obj = new ObjectModel();
                obj.setObjectID(rs.getInt("ObjectID"));
                obj.setObjectName(rs.getString("ObjectName"));
                obj.setFromDay(rs.getString("FromDay"));
                obj.setToDay(rs.getString("ToDay"));
                obj.setFromTime(rs.getString("FromTime"));
                obj.setToTime(rs.getString("ToTime"));
                obj.setIpAddress(rs.getString("IpAddress"));
                obj.setPath(rs.getString("Path"));
                return obj;
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error getByName of ObjectModel" + err);
        }
        return null;
    }
    
    public ObjectModel getByID(int id) {
        try{
            String sql = "SELECT * FROM Objects\n"
                    + "WHERE ObjectID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                ObjectModel obj = new ObjectModel();
                obj.setObjectID(rs.getInt("ObjectID"));
                obj.setObjectName(rs.getString("ObjectName"));
                obj.setFromDay(rs.getString("FromDay"));
                obj.setToDay(rs.getString("ToDay"));
                obj.setFromTime(rs.getString("FromTime"));
                obj.setToTime(rs.getString("ToTime"));
                obj.setIpAddress(rs.getString("IpAddress"));
                obj.setPath(rs.getString("Path"));
                return obj;
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error getByName of ObjectModel" + err);
        }
        return null;
    }
    
    public ArrayList<ObjectModel> all() {
        ArrayList<ObjectModel> objects = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Objects";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                ObjectModel obj = new ObjectModel();
                obj.setObjectID(rs.getInt("ObjectID"));
                obj.setObjectName(rs.getString("ObjectName"));
                obj.setFromDay(rs.getString("FromDay"));
                obj.setToDay(rs.getString("ToDay"));
                obj.setFromTime(rs.getString("FromTime"));
                obj.setToTime(rs.getString("ToTime"));
                obj.setIpAddress(rs.getString("IpAddress"));
                obj.setPath(rs.getString("Path"));
                objects.add(obj);
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get all of Object" + err);
        }
        return objects;
    }
}
