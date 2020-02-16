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
import model.ObjectModel;
import model.Permission;
import model.PermissionRole;
import model.RoleModel;

/**
 *
 * @author namcu
 */
public class PermissionRoleDAO extends BaseDAO<PermissionRole>{
    
    @Override
    public int insert(PermissionRole model) {
        try {
            String sql = "INSERT INTO PermissionRoles (PermissionID, RoleID)\n" +
                        "VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, model.getPermissionID());
            statement.setInt(2, model.getRoleID());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermissionRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
    
    public int insertByPermission(Permission permission) {
        try {
            String baseStr = "INSERT INTO PermissionRoles (PermissionID, RoleID)\n" +
                        "VALUES (?, ?);";
            
            
            RoleDAO roleDB = new RoleDAO();
            ArrayList<Integer> roleIDs = roleDB.getByCondtion(permission);
            
            String sql = new String(new char[roleIDs.size()]).replace("\0", baseStr);
            int baseNum = 1;
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            for(Integer roleID:roleIDs) {
                statement.setInt(baseNum ++, permission.getPermissionID());
                statement.setInt(baseNum ++, roleID);
//                System.out.println("PermissionID: " + permission.getPermissionID() + " RoleID: " + roleID);
            }
            statement.execute();
//            System.out.println("helloooo: i'm inserting");
        } catch (SQLException ex) {
            System.out.println("Ops! Inserting to PermissionRoles by Permission failed." + ex);
            return 0;
        }
        return 1;
    }
    
    public int insertByRole(RoleModel role) {
        try {
            String baseStr = "INSERT INTO PermissionRoles (PermissionID, RoleID)\n" +
                        "VALUES (?, ?);";
            
            
            PermissionDAO permissionDAO = new PermissionDAO();
            ArrayList<Integer> permissionIds = permissionDAO.getByCondtion(role);
            
            String sql = new String(new char[permissionIds.size()]).replace("\0", baseStr);
            int baseNum = 1;
            
            PreparedStatement statement = connection.prepareStatement(sql);
            for(Integer permissionID:permissionIds) {
                statement.setInt(baseNum ++, permissionID);
                statement.setInt(baseNum ++, role.getRoleID());
            }
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PermissionRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
    
    public int getCurrentID() {
        try{
            String sql = "SELECT IDENT_CURRENT('PermissionRoles') as MaxID";
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
    
    public ArrayList<PermissionRole> all() {
        ArrayList<PermissionRole> permissionRoles = new ArrayList<>();
        try{
            String sql = "SELECT RoleID, PermissionID FROM PermissionRoles";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                PermissionRole permissionRole = new PermissionRole();
                permissionRole.setRoleID(rs.getInt("RoleID"));
                permissionRole.setPermissionID(rs.getInt("PermissionID"));
                permissionRoles.add(permissionRole);
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get all of Permission Role" + err);
        }
        return permissionRoles;
    }
    
    public ArrayList<PermissionRole> getByRoleID(int roleID) {
        ArrayList<PermissionRole> permissionRoles = new ArrayList<>();
        try{
            String sql = "SELECT PermissionID FROM PermissionRoles WHERE RoleID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, roleID);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                PermissionRole permissionRole = new PermissionRole();
                permissionRole.setPermissionID(rs.getInt("PermissionID"));
                permissionRole.setRoleID(roleID);
                permissionRoles.add(permissionRole);
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get by roleID of PermissionRole" + err);
        }
        return permissionRoles;
    }
}
