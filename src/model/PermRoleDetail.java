/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author namcu
 */
public class PermRoleDetail {
    private ObjectModel obj;
    private Action act;
    private RoleModel role;

    @Override
    public String toString() {
        return role.getRoleName() + " " + act.getActionName() + " " + 
                obj.getObjectName();
    }
    
    public ObjectModel getObj() {
        return obj;
    }

    public void setObj(ObjectModel obj) {
        this.obj = obj;
    }

    public Action getAct() {
        return act;
    }

    public void setAct(Action act) {
        this.act = act;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }
    
    
    
}
