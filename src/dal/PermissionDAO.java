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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Action;
import model.ObjectModel;
import model.Permission;
import model.PermissionRole;
import model.RoleModel;

/**
 *
 * @author Nam
 */
public class PermissionDAO extends BaseDAO<Permission>{

    @Override
    public int insert(Permission model) {
        try {
            String sql = "INSERT INTO Permissions (ObjectID, ActionID)\n" +
                        "VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, model.getObjectID());
            statement.setInt(2, model.getActionID());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
    
    public int insertByObjectID(int objectID) {
        try {
            String baseStr = "INSERT INTO Permissions (ObjectID, ActionID)\n" +
                        "VALUES (?, ?);";
            
            
            ActionDAO actionDB = new ActionDAO();
            ArrayList<Action> allActions = actionDB.all();
            
            String sql = new String(new char[allActions.size()]).replace("\0", baseStr);
            int baseNum = 1;
            
            int curId = getCurrentID();
            
            ArrayList<Permission> permissions = new ArrayList<>();
            PermissionRoleDAO permissionRoleDAO = new PermissionRoleDAO();
            
            PreparedStatement statement = connection.prepareStatement(sql);
            for(Action action:allActions) {
                statement.setInt(baseNum ++, objectID);
                statement.setInt(baseNum ++, action.getActionID());
                
                Permission permission = new Permission();
                permission.setPermissionID(++ curId);
                permission.setActionID(action.getActionID());
                permission.setObjectID(objectID);
                
                permissions.add(permission);
                
//                permissionRoleDAO.insertByPermission(permission);
            }
            statement.execute();
            
            for(Permission permission:permissions) {
                permissionRoleDAO.insertByPermission(permission);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermissionRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
    
    public int insertByActionID(int actionID) {
        try {
            String baseStr = "INSERT INTO Permissions (ObjectID, ActionID)\n" +
                        "VALUES (?, ?);";
            
            
            ObjectDAO objectDAO = new ObjectDAO();
            ArrayList<ObjectModel> allObjects = objectDAO.all();
            
            String sql = new String(new char[allObjects.size()]).replace("\0", baseStr);
            int baseNum = 1;
            
            int curId = getCurrentID();
            PermissionRoleDAO permissionRoleDAO = new PermissionRoleDAO();
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ArrayList<Permission> permissions = new ArrayList<>();
            
            for(ObjectModel obj:allObjects) {
                statement.setInt(baseNum ++, obj.getObjectID());
                statement.setInt(baseNum ++, actionID);
                
                Permission permission = new Permission();
                permission.setPermissionID(++ curId);
                permission.setActionID(actionID);
                permission.setObjectID(obj.getObjectID());
                
//                System.out.println("hello: " + permission);
                
                permissions.add(permission);
                
//                permissionRoleDAO.insertByPermission(permission);
//                System.out.println("whattttt");
            }
            statement.execute();
            
            for(Permission permission:permissions) {
                permissionRoleDAO.insertByPermission(permission);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermissionRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
    
    public ArrayList<Integer> getByCondtion(RoleModel roleModel) {
        ArrayList<Integer> permissionIds = new ArrayList<>();
        try{
            String sql = "SELECT DISTINCT(PermissionID) FROM Permissions, Objects\n" +
                        "WHERE Permissions.ObjectID = Objects.ObjectID \n" +
                        "AND Objects.IpAddress=? \n" +
                        "AND Objects.FromDay=? \n" +
                        "AND Objects.FromTime=? \n" +
                        "AND Objects.ToDay=?\n" +
                        "AND Objects.ToTime=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roleModel.getIpAddress());
            statement.setString(2, roleModel.getFromDay());
            statement.setString(3, roleModel.getFromTime());
            statement.setString(4, roleModel.getToDay());
            statement.setString(5, roleModel.getToTime());
            
//            System.out.println("--------------------: " + statement.toString());
            
            ResultSet rs = statement.executeQuery();
            
//            System.out.println("====================: " + "huhu");
            while(rs.next()) {
                permissionIds.add(rs.getInt("PermissionID"));
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get by condtion in PermissionDAO " + err);
        }
        return permissionIds;
    }
    
    public int getCurrentID() {
        try{
            String sql = "SELECT IDENT_CURRENT('Permissions') as MaxID";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                return rs.getInt("MaxID");
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error getCurrentID of Action" + err);
            return 0;
        }
        return 0;
    }
    
    public Permission getByID(int id) {
        try{
            String sql = "SELECT ObjectID, ActionID FROM Permissions WHERE PermissionID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, id);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Permission permission = new Permission();
                permission.setActionID(rs.getInt("ActionID"));
                permission.setObjectID(rs.getInt("ObjectID"));
                return permission;
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get by id of Permission" + err);
        }
        return null;
    }
    
    public ArrayList<Permission> all() {
        ArrayList<Permission> permissions = new ArrayList<>();
        try{
            String sql = "SELECT PermissionID, ActionID, ObjectID FROM Permissions";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Permission permission = new Permission();
                permission.setPermissionID(rs.getInt("PermissionID"));
                permission.setActionID(rs.getInt("ActionID"));
                permission.setObjectID(rs.getInt("ObjectID"));
                permissions.add(permission);
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get all of Action" + err);
        }
        return permissions;
    }
    
}
