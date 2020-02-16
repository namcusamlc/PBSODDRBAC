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
import model.Permission;
import model.RoleModel;

/**
 *
 * @author Nam
 */
public class RoleDAO extends BaseDAO<RoleModel>{

    @Override
    public int insert(RoleModel model) {
        try{
            String sql = "INSERT INTO Roles (RoleName, FromDay, ToDay, FromTime, ToTime, IpAddress)\n"
                    + " VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getRoleName());
            statement.setString(2, model.getFromDay());
            statement.setString(3, model.getToDay());
            statement.setString(4, model.getFromTime());
            statement.setString(5, model.getToTime());
            statement.setString(6, model.getIpAddress());
            statement.execute();
        }
        catch(SQLException err){
            System.out.println("Ops! " + err);
            return 0;
        }
        return 1;
    }
    
    public ArrayList<Integer> getByCondtion(Permission permissionModel) {
        ArrayList<Integer> roleIds = new ArrayList<>();
        try{
            String sql = "WITH H AS (\n" +
                            "	SELECT * \n" +
                            "	FROM Objects as o \n" +
                            "	WHERE o.ObjectID=?\n" +
                            ")\n" +
                            "SELECT rTable.RoleID \n" +
                            "FROM Roles as rTable, H\n" +
                            "WHERE rTable.IpAddress = H.IpAddress "
                    + "AND rTable.FromDay=H.FromDay "
                    + "AND rTable.FromTime=H.FromTime "
                    + "AND rTable.ToDay=H.ToDay "
                    + "AND rTable.ToTime=H.ToTime";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, permissionModel.getObjectID());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                roleIds.add(rs.getInt("RoleID"));
            }
        }
        catch(SQLException err){
            System.out.println("Ops! Error get by condtion  in RoleDAO" + err);
        }
        return roleIds;
    }
    
    public int getCurrentID() {
        try{
            String sql = "SELECT IDENT_CURRENT('Roles') as MaxID";
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
    
    public ArrayList<RoleModel> all() {
        ArrayList<RoleModel> roles = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Roles";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                RoleModel role = new RoleModel();
                role.setRoleID(rs.getInt("RoleID"));
                role.setRoleName(rs.getString("RoleName"));
                role.setFromDay(rs.getString("FromDay"));
                role.setToDay(rs.getString("ToDay"));
                role.setFromTime(rs.getString("FromTime"));
                role.setToTime(rs.getString("ToTime"));
                role.setIpAddress(rs.getString("IpAddress"));
                roles.add(role);
            }
        }
        catch(SQLException err){
            System.out.println("Ops! " + err);
        }
        return roles;
    }
    
}
