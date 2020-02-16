/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.GroupUser;

/**
 *
 * @author namcu
 */
public class GroupUserDAO extends BaseDAO<GroupUser>{

    @Override
    public int insert(GroupUser model) {
        try{
            String sql = "INSERT INTO GroupUsers (GroupID, Username) VALUES (?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, model.getGroupID());
            statement.setString(2, model.getUsername());
            statement.execute();
        }
        catch(SQLException err){
            System.out.println("Ops! Error whilte inserting to GroupUser table " + err);
            return 0;
        }
        return 1;
    }
    
}
