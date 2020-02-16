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
import model.ContainerModel;
import model.GroupModel;
import model.ObjectModel;
import model.User;

/**
 *
 * @author namcu
 */
public class GroupDAO extends BaseDAO<GroupModel>{

    @Override
    public int insert(GroupModel model) {
        try{
            String sql = "INSERT INTO Groups (GroupName) VALUES (?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getGroupName());
            statement.execute();
            
             for (User user : model.getUsers()) {
                insertToGroupUser(user);
             }
        }
        catch(SQLException err){
            System.out.println("Ops! Error while inserting group" + err);
            return 0;
        }
        return 1;
    }
    public int insertToGroupUser(User model) {
        try{
            String sql = "INSERT INTO GroupDetails (Username, GroupID)\n"
                    + " VALUES (?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getUsername());
            statement.setInt(2, getCurrentID());
            statement.execute();
        }
        catch(SQLException err){
            System.out.println("Ops! Error insert to insertToContainerDetail" + err);
            return 0;
        }
        return 1;
    }
    //    public int insert(ContainerModel model) {
//        try{
//            String sql = "INSERT INTO ObjectContainers (ContainerName)\n"
//                    + " VALUES (?);";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, model.getContainerName());
//            statement.execute();
//            
//            for (ObjectModel object : model.getObjects()) {
//                insertToContainerDetail(object);
//            }
//        }
//        catch(SQLException err){
//            System.out.println("Ops! Error insert to ObjectContainer" + err);
//            return 0;
//        }
//        return 1;
//    }
    
    public GroupModel getByName(String name) {
        try{
            String sql = "SELECT * FROM Groups\n"
                    + "WHERE GroupName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                GroupModel group = new GroupModel();
                group.setGroupName(rs.getString("GroupName"));
                group.setGroupID(rs.getInt("GroupID"));
                return group;
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error getByName of GroupModel" + err);
        }
        return null;
    }
    
    public int getCurrentID() {
        try{
            String sql = "SELECT IDENT_CURRENT('Groups') as MaxID";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                return rs.getInt("MaxID");
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error getCurrentID of GroupDAO" + err);
            return 0;
        }
        return 0;
    }
    
    
    public ArrayList<GroupModel> all() {
        ArrayList<GroupModel> groups = new ArrayList<>();
        try{
            String sql = "SELECT GroupID, GroupName FROM Groups";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                GroupModel group = new GroupModel();
                group.setGroupID(rs.getInt("GroupID"));
                group.setGroupName(rs.getString("GroupName"));
                groups.add(group);
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get all of GroupModel" + err);
        }
        return groups;
    }
    
    public ArrayList<String> getAllUsernameInGroup(String name) {
        ArrayList<String> username = new ArrayList<>();
        try{
            String sql = "SELECT o.Username \n" +
                "FROM GroupDetails as ocd, Users as o, Groups as oc\n" +
                "WHERE oc.GroupID = ocd.GroupID AND ocd.Username = o.Username AND oc.GroupName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                username.add(rs.getString("Username"));
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get all of usrename" + err);
        }
        return username;
    }
    
}
