package gui;

import dal.ActionDAO;
import dal.GroupDAO;
import dal.ObjectDAO;
import dal.PermissionDAO;
import dal.PermissionRoleDAO;
import dal.RoleDAO;
import dal.UserDAO;
import java.awt.Color;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.Action;
import model.GroupModel;
import model.ObjectModel;
import model.Permission;
import model.RoleModel;
import model.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nam
 */
public class AdminForm extends javax.swing.JFrame {

    /**
     * Creates new form AdminForm
     */
    private LoginForm parent;
    private DefaultTableModel dtm1, dtm2, dtm3, dtmRolesAndPermissions, dtm4, dtm5;
    private JPanel activePanel;
    private JPanel activeBg;
    
    
    public AdminForm() {
        initComponents();
        initCustomComps();
    }

    public AdminForm(LoginForm p) {
        initComponents();
        initCustomComps();
        parent = p;
    }

    private void initCustomComps() {
        this.setTitle("Admin page");
        initDayPicker();
        initTimePicker();
        initCreateActionTab();
        initGroupCreation();
        initActionSet();
        initCreatePermission();
        initRolesAndPermissionsTab();
    }
    
    
    private void loadPermissionAndRoleInfo() {
//        tablePermissionInfo_permAndRoleInfo
        dtm4 = (DefaultTableModel) tablePermissionInfo_permAndRoleInfo.getModel();
        dtm5 = (DefaultTableModel) tableRoleInfo_permAndRoleInfo.getModel();
        
        while(dtm4.getRowCount() > 0)
            dtm4.removeRow(0);
        
        while(dtm5.getRowCount() > 0)
            dtm5.removeRow(0);
        
        PermissionDAO permissionDAO = new PermissionDAO();
        ArrayList<Permission> permissions = permissionDAO.all();
        
        ActionDAO actionDAO = new ActionDAO();
        ObjectDAO objectDAO = new ObjectDAO();
        
        for (Permission permission : permissions) {
            dtm4.addRow(new Object[]{permission.getPermissionID(), 
                actionDAO.getByID(permission.getActionID()).getActionName(),
                objectDAO.getByID(permission.getObjectID()).getObjectName()});
        }
        
        RoleDAO roleDAO = new RoleDAO();
        ArrayList<RoleModel> roles = roleDAO.all();
        
        for (RoleModel role : roles) {
            dtm5.addRow(new Object[]{role.getRoleID(), role.getRoleName()});
        }
        
    }
    
    private void initGroupCreation() {
        cbAllGroup_createGroup.setVisible(false);
        listShow_createGroup.setVisible(false);
        btnList_createGroup.setVisible(false);
        dtm1 = (DefaultTableModel) tableGroupCreate_createGroup.getModel();
        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = userDAO.all();
        for (User user : users) {
            dtm1.addRow(new Object[]{user.getUsername(), false});
        }
    }
    
    private void reloadUserInGroupCreation() {
        dtm1 = (DefaultTableModel) tableGroupCreate_createGroup.getModel();
        while(dtm1.getRowCount() > 0)
            dtm1.removeRow(0);
        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = userDAO.all();
        for (User user : users) {
            dtm1.addRow(new Object[]{user.getUsername(), false});
        }
    }
    
    private void reloadAction() {
//        dtm2 = (DefaultTableModel) tableActionSetCreate_createActionSet.getModel();
        while(dtm2.getRowCount() > 0)
            dtm2.removeRow(0);
        ActionDAO actionDAO = new ActionDAO();
        ArrayList<Action> actions = actionDAO.all();
        for (Action action : actions) {
            dtm2.addRow(new Object[]{action.getActionName(), false});
        }
    }
    
    private void initActionSet() {
//        cbAllActionSet_createActionSet.setVisible(false);
//        listShow_createActionSet.setVisible(false);
//        btnList_createActionSet.setVisible(false);
//        dtm2 = (DefaultTableModel) tableActionSetCreate_createActionSet.getModel();
//        while(dtm2.getRowCount() > 0)
//            dtm2.removeRow(0);
//        ActionDAO actionDAO = new ActionDAO();
//        ArrayList<Action> actions = actionDAO.all();
//        for (Action action : actions) {
//            dtm2.addRow(new Object[]{action.getActionName(), false});
//        }
    }

    private void initCreateActionTab() {
        cbConflictedAction_createAction.removeAllItems();
        cbConflictedAction_createAction.addItem("None");
        ActionDAO actionDB = new ActionDAO();
        ArrayList<Action> actions = actionDB.all();
        for (Action action : actions) {
            cbConflictedAction_createAction.addItem(action.getActionName());
        }
    }
    
    private void initRolesAndPermissionsTab() {
//        RoleDAO roleDAO = new RoleDAO();
//        cbRoleName_rolesAndPermissions.removeAllItems();
//        cbPermission_rolesAndPermissions.removeAllItems();
//        ArrayList<RoleModel> roles = roleDAO.all();
//        for (RoleModel role : roles) {
//            cbRoleName_rolesAndPermissions.addItem(role.getRoleName());
//        }
//        // load permissions
//        PermissionDAO permissionDAO = new  PermissionDAO();
//        ObjectDAO objectDAO = new ObjectDAO();
//        ActionDAO actionDAO = new ActionDAO();
//        ArrayList<Permission> permissions = permissionDAO.all();
//        
//        for (Permission permission : permissions) {
//            ObjectModel obj = objectDAO.getByID(permission.getObjectID());
//            Action action = actionDAO.getByID(permission.getActionID());
//            cbPermission_rolesAndPermissions.addItem(obj.getObjectName() + " -- " + action.getActionName());
//        }
    }
    
    private void loadPermission() {
        PermissionDAO permissionDAO = new PermissionDAO();
        ObjectDAO objectDAO = new ObjectDAO();
        ActionDAO actionDAO = new ActionDAO();
        while(dtm3.getRowCount() > 0) {
            dtm3.removeRow(0);
        }
        ArrayList<Permission> permissions = permissionDAO.all();
        for (Permission permission : permissions) {
            dtm3.addRow(new String[]{ objectDAO.getByID(permission.getObjectID()).getObjectName(),
                                      actionDAO.getByID(permission.getActionID()).getActionName()});
        }
        
        
    }
    
    private void initCreatePermission() {
//        dtm3 = (DefaultTableModel) tablePermissionShow_createPermission.getModel();
//        loadPermission();
//        ContainerDAO containerDAO = new ContainerDAO();
//        ActionSetDAO setDAO = new ActionSetDAO();
//        ActionDAO actionDAO = new ActionDAO();
//        ObjectDAO objectDAO = new ObjectDAO();
//        ArrayList<ContainerModel> containers = containerDAO.all();
//        ArrayList<ActionSet> sets = setDAO.all();
//        ArrayList<Action> actions = actionDAO.all();
//        ArrayList<ObjectModel> objects = objectDAO.all();
//        cbContainer_createPermissionM1.removeAllItems();
//        cbContainer_createPermissionM2.removeAllItems();
//        cbSet_createPermissionM1.removeAllItems();
//        cbSet_createPermissionM3.removeAllItems();
//        cbObject_createPermissionM3.removeAllItems();
//        cbObject_createPermissionM4.removeAllItems();
//        cbAction_createPermissionM2.removeAllItems();
//        cbAction_createPermissionM4   .removeAllItems();     
//        for (ContainerModel container : containers) {
//            cbContainer_createPermissionM1.addItem(container.getContainerName());
//            cbContainer_createPermissionM2.addItem(container.getContainerName());
//        }
//        for (ActionSet set : sets) {
//            cbSet_createPermissionM1.addItem(set.getActionSetName());
//            cbSet_createPermissionM3.addItem(set.getActionSetName());
//        }
//        for (ObjectModel object : objects) {
//            cbObject_createPermissionM3.addItem(object.getObjectName());
//            cbObject_createPermissionM4.addItem(object.getObjectName());
//        }
//        for (Action action : actions) {
//            cbAction_createPermissionM2.addItem(action.getActionName());
//            cbAction_createPermissionM4.addItem(action.getActionName());
//        }

    }

    private void initTimePicker() {

        Date date = new Date();
        SpinnerDateModel sdmFrom_createUser = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        SpinnerDateModel sdmTo_createUser = new SpinnerDateModel(date, null, null, Calendar.MINUTE);

        // initilize time picker for create user tab
        spinnerFromTime__createUser.setModel(sdmFrom_createUser);
        spinnerToTime_createUser.setModel(sdmTo_createUser);
        JSpinner.DateEditor deFrom_createUser = new JSpinner.DateEditor(spinnerFromTime__createUser, "hh:mm:ss a");
        JSpinner.DateEditor deTo_createUser = new JSpinner.DateEditor(spinnerToTime_createUser, "hh:mm:ss a");
        deFrom_createUser.getTextField().setEditable(false);
        deTo_createUser.getTextField().setEditable(false);
        spinnerFromTime__createUser.setEditor(deFrom_createUser);
        spinnerToTime_createUser.setEditor(deTo_createUser);

        
        SpinnerDateModel sdmFrom_createObject = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        SpinnerDateModel sdmTo_createObject = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        
        // initilize time picker for create object tab
        spinnerFromTime_createObject.setModel(sdmFrom_createObject);
        spinnerToTime_createObject.setModel(sdmTo_createObject);
        JSpinner.DateEditor deFrom_createObject = new JSpinner.DateEditor(spinnerFromTime_createObject, "hh:mm:ss a");
        JSpinner.DateEditor deTo_createObject = new JSpinner.DateEditor(spinnerToTime_createObject, "hh:mm:ss a");
        deFrom_createObject.getTextField().setEditable(false);
        deTo_createObject.getTextField().setEditable(false);
        spinnerFromTime_createObject.setEditor(deFrom_createObject);
        spinnerToTime_createObject.setEditor(deTo_createObject);

        SpinnerDateModel sdmFrom_createRole = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        SpinnerDateModel sdmTo_createRole = new SpinnerDateModel(date, null, null, Calendar.MINUTE);        
        
        // initilize time picker for create role tab
        spinnerFromTime_createRole.setModel(sdmFrom_createRole);
        spinnerToTime_createRole.setModel(sdmTo_createRole);
        JSpinner.DateEditor deFrom_createRole = new JSpinner.DateEditor(spinnerFromTime_createRole, "hh:mm:ss a");
        JSpinner.DateEditor deTo_createRole = new JSpinner.DateEditor(spinnerToTime_createRole, "hh:mm:ss a");
        deFrom_createRole.getTextField().setEditable(false);
        deTo_createRole.getTextField().setEditable(false);
        spinnerFromTime_createRole.setEditor(deFrom_createRole);
        spinnerToTime_createRole.setEditor(deTo_createRole);

    }

    private void initDayPicker() {
        DateFormatSymbols dfs = new DateFormatSymbols();
        cbFromDay__createUser.removeAllItems();
        cbToDay_createUser.removeAllItems();
        cbFromDay_createObject.removeAllItems();
        cbToDay_createObject.removeAllItems();
        cbFromDay_createRole.removeAllItems();
        cbToDay_createRole.removeAllItems();
        for (String day : dfs.getWeekdays()) {
            if (day.equals("")) {
                continue;
            }
            String upperDay = day.toUpperCase();
            cbFromDay__createUser.addItem(upperDay);
            cbToDay_createUser.addItem(upperDay);
            cbFromDay_createObject.addItem(upperDay);
            cbToDay_createObject.addItem(upperDay);
            cbFromDay_createRole.addItem(upperDay);
            cbToDay_createRole.addItem(upperDay);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bg0 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        bg1 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        bg2 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        bg3 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        bg4 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        bg5 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        tabbed = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtUsername_createUser = new javax.swing.JTextField();
        cbFromDay__createUser = new javax.swing.JComboBox<>();
        cbToDay_createUser = new javax.swing.JComboBox<>();
        spinnerFromTime__createUser = new javax.swing.JSpinner();
        spinnerToTime_createUser = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPassword__createUser = new javax.swing.JPasswordField();
        txtIpAddress__createUser = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnCreate__createUser = new javax.swing.JButton();
        btnRefresh__createUser = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        txtObjName_createObject = new javax.swing.JTextField();
        cbFromDay_createObject = new javax.swing.JComboBox<>();
        cbToDay_createObject = new javax.swing.JComboBox<>();
        lblPath_createObject = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnBrowse_FileAttachment = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtIpAddress_createObject = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        spinnerFromTime_createObject = new javax.swing.JSpinner();
        spinnerToTime_createObject = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        btnCreate_createObject = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        btnCreate_createAction = new javax.swing.JButton();
        txtActionName_createAction = new javax.swing.JTextField();
        cbConflictedAction_createAction = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtRoleName_createRole = new javax.swing.JTextField();
        btnCreate_createRole = new javax.swing.JButton();
        txtIpAddress_createRole = new javax.swing.JTextField();
        spinnerFromTime_createRole = new javax.swing.JSpinner();
        spinnerToTime_createRole = new javax.swing.JSpinner();
        jLabel47 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cbFromDay_createRole = new javax.swing.JComboBox<>();
        cbToDay_createRole = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtGroupName_createGroup = new javax.swing.JTextField();
        btnCreate_createContainer = new javax.swing.JButton();
        chkShowList_createGroup = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableGroupCreate_createGroup = new javax.swing.JTable();
        cbAllGroup_createGroup = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        listShow_createGroup = new javax.swing.JList<>();
        btnList_createGroup = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePermissionInfo_permAndRoleInfo = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableRoleInfo_permAndRoleInfo = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(71, 120, 197));
        jPanel14.setPreferredSize(new java.awt.Dimension(966, 50));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 21)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setText("<html> COI In hybrid <br>Access Control Model </html>");

        bg0.setBackground(new java.awt.Color(120, 168, 252));
        bg0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bg0.setForeground(new java.awt.Color(255, 255, 255));
        bg0.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 24)); // NOI18N
        bg0.setPreferredSize(new java.awt.Dimension(375, 50));
        bg0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg0MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg0MousePressed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("User Creation");

        javax.swing.GroupLayout bg0Layout = new javax.swing.GroupLayout(bg0);
        bg0.setLayout(bg0Layout);
        bg0Layout.setHorizontalGroup(
            bg0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg0Layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(80, 80, 80))
        );
        bg0Layout.setVerticalGroup(
            bg0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg0Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 255, 255));
        jLabel11.setText("Administration Page");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setText("Logout");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bg1.setBackground(new java.awt.Color(120, 168, 252));
        bg1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bg1.setForeground(new java.awt.Color(255, 255, 255));
        bg1.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 24)); // NOI18N
        bg1.setPreferredSize(new java.awt.Dimension(375, 50));
        bg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg1MousePressed(evt);
            }
        });

        jLabel48.setBackground(new java.awt.Color(255, 255, 255));
        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel48.setText("Object Creation");

        javax.swing.GroupLayout bg1Layout = new javax.swing.GroupLayout(bg1);
        bg1.setLayout(bg1Layout);
        bg1Layout.setHorizontalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addGap(75, 75, 75))
        );
        bg1Layout.setVerticalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addContainerGap())
        );

        bg2.setBackground(new java.awt.Color(120, 168, 252));
        bg2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bg2.setForeground(new java.awt.Color(255, 255, 255));
        bg2.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 24)); // NOI18N
        bg2.setPreferredSize(new java.awt.Dimension(250, 50));
        bg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg2MousePressed(evt);
            }
        });

        jLabel49.setBackground(new java.awt.Color(255, 255, 255));
        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel49.setText("Action Creation");

        javax.swing.GroupLayout bg2Layout = new javax.swing.GroupLayout(bg2);
        bg2.setLayout(bg2Layout);
        bg2Layout.setHorizontalGroup(
            bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel49)
                .addGap(72, 72, 72))
        );
        bg2Layout.setVerticalGroup(
            bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel49)
                .addContainerGap())
        );

        bg3.setBackground(new java.awt.Color(120, 168, 252));
        bg3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bg3.setForeground(new java.awt.Color(255, 255, 255));
        bg3.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 24)); // NOI18N
        bg3.setPreferredSize(new java.awt.Dimension(250, 50));
        bg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg3MousePressed(evt);
            }
        });

        jLabel52.setBackground(new java.awt.Color(255, 255, 255));
        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel52.setText("Role Creation");

        javax.swing.GroupLayout bg3Layout = new javax.swing.GroupLayout(bg3);
        bg3.setLayout(bg3Layout);
        bg3Layout.setHorizontalGroup(
            bg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addGap(80, 80, 80))
        );
        bg3Layout.setVerticalGroup(
            bg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addContainerGap())
        );

        bg4.setBackground(new java.awt.Color(120, 168, 252));
        bg4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bg4.setForeground(new java.awt.Color(255, 255, 255));
        bg4.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 24)); // NOI18N
        bg4.setPreferredSize(new java.awt.Dimension(250, 50));
        bg4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg4MousePressed(evt);
            }
        });

        jLabel53.setBackground(new java.awt.Color(255, 255, 255));
        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel53.setText("Grouping");

        javax.swing.GroupLayout bg4Layout = new javax.swing.GroupLayout(bg4);
        bg4.setLayout(bg4Layout);
        bg4Layout.setHorizontalGroup(
            bg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg4Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jLabel53)
                .addGap(86, 86, 86))
        );
        bg4Layout.setVerticalGroup(
            bg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bg5.setBackground(new java.awt.Color(120, 168, 252));
        bg5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        bg5.setForeground(new java.awt.Color(255, 255, 255));
        bg5.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 24)); // NOI18N
        bg5.setPreferredSize(new java.awt.Dimension(250, 50));
        bg5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bg5MousePressed(evt);
            }
        });

        jLabel54.setBackground(new java.awt.Color(255, 255, 255));
        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel54.setText("Permission and Role View");

        javax.swing.GroupLayout bg5Layout = new javax.swing.GroupLayout(bg5);
        bg5.setLayout(bg5Layout);
        bg5Layout.setHorizontalGroup(
            bg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel54)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        bg5Layout.setVerticalGroup(
            bg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bg3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addComponent(bg0, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bg2, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(bg1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bg5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(bg4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bg0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bg5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(bg4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tabbed.setBackground(new java.awt.Color(255, 255, 255));
        tabbed.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tabbed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabbedFocusGained(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("User Creation Panel");

        jPanel9.setBackground(new java.awt.Color(242, 247, 247));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel5.setText("User Name");

        txtUsername_createUser.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        cbFromDay__createUser.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        cbFromDay__createUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbToDay_createUser.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        cbToDay_createUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        spinnerFromTime__createUser.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        spinnerFromTime__createUser.setToolTipText("");

        spinnerToTime_createUser.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel13.setText("To");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel10.setText("From Time");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel12.setText("To");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel9.setText("From Day");

        txtPassword__createUser.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        txtIpAddress__createUser.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel8.setText("IP Address");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel6.setText("Password");

        btnCreate__createUser.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnCreate__createUser.setForeground(new java.awt.Color(102, 102, 102));
        btnCreate__createUser.setText("Create");
        btnCreate__createUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate__createUserActionPerformed(evt);
            }
        });

        btnRefresh__createUser.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnRefresh__createUser.setForeground(new java.awt.Color(102, 102, 102));
        btnRefresh__createUser.setText("Refresh");
        btnRefresh__createUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh__createUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(20, 20, 20)
                                .addComponent(txtUsername_createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel9)
                                .addGap(17, 17, 17)
                                .addComponent(cbFromDay__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addGap(20, 20, 20)
                                .addComponent(txtPassword__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jLabel12)
                                .addGap(16, 16, 16)
                                .addComponent(cbToDay_createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(355, 355, 355)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(spinnerFromTime__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(22, 22, 22)
                                .addComponent(txtIpAddress__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(spinnerToTime_createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(btnCreate__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnRefresh__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtUsername_createUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(cbFromDay__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtPassword__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel12))
                    .addComponent(cbToDay_createUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(spinnerFromTime__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtIpAddress__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel13))
                    .addComponent(spinnerToTime_createUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreate__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh__createUser, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(13, 13, 13)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );

        tabbed.addTab("", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Object creation");

        jPanel8.setBackground(new java.awt.Color(242, 247, 247));

        jLabel55.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel55.setText("Object Name");

        txtObjName_createObject.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        cbFromDay_createObject.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        cbFromDay_createObject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbToDay_createObject.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        cbToDay_createObject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblPath_createObject.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        lblPath_createObject.setText("Path to your file");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel15.setText("File path");

        btnBrowse_FileAttachment.setText("Browse");
        btnBrowse_FileAttachment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowse_FileAttachmentActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel19.setText("IP Address");

        txtIpAddress_createObject.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel18.setText("From Time");

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel17.setText("From Day");

        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel34.setText("To");

        spinnerFromTime_createObject.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        spinnerFromTime_createObject.setToolTipText("");

        spinnerToTime_createObject.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel35.setText("To");

        btnCreate_createObject.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnCreate_createObject.setForeground(new java.awt.Color(102, 102, 102));
        btnCreate_createObject.setText("Create");
        btnCreate_createObject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_createObjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel55)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtIpAddress_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(152, 152, 152)
                                .addComponent(jLabel35))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(btnBrowse_FileAttachment)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblPath_createObject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtObjName_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGap(105, 105, 105)
                                            .addComponent(jLabel17))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                            .addGap(152, 152, 152)
                                            .addComponent(jLabel34))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbToDay_createObject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbFromDay_createObject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spinnerFromTime_createObject)
                            .addComponent(spinnerToTime_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(btnCreate_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtObjName_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel17)
                    .addComponent(cbFromDay_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPath_createObject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel34)
                        .addComponent(cbToDay_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(spinnerFromTime_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse_FileAttachment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(txtIpAddress_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinnerToTime_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35)))
                .addGap(25, 25, 25)
                .addComponent(btnCreate_createObject, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(445, 578, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 302, Short.MAX_VALUE))))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        tabbed.addTab("", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel3FocusGained(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Action Creation Panel");

        jPanel7.setBackground(new java.awt.Color(242, 247, 247));

        btnCreate_createAction.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnCreate_createAction.setForeground(new java.awt.Color(102, 102, 102));
        btnCreate_createAction.setText("Create");
        btnCreate_createAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_createActionActionPerformed(evt);
            }
        });

        txtActionName_createAction.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        cbConflictedAction_createAction.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        cbConflictedAction_createAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel27.setText("Conflicted Action");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel3.setText("Action Name");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel3))
                .addGap(56, 56, 56)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtActionName_createAction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbConflictedAction_createAction, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(btnCreate_createAction, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtActionName_createAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(cbConflictedAction_createAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnCreate_createAction, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
        );

        tabbed.addTab("", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Role Creation Panel");

        jPanel10.setBackground(new java.awt.Color(242, 247, 247));

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel22.setText("Role Name");

        txtRoleName_createRole.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        btnCreate_createRole.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnCreate_createRole.setForeground(new java.awt.Color(102, 102, 102));
        btnCreate_createRole.setText("Create");
        btnCreate_createRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_createRoleActionPerformed(evt);
            }
        });

        txtIpAddress_createRole.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        spinnerFromTime_createRole.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        spinnerFromTime_createRole.setToolTipText("");

        spinnerToTime_createRole.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        jLabel47.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel47.setText("To");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel24.setText("From Time");

        cbFromDay_createRole.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        cbFromDay_createRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbToDay_createRole.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        cbToDay_createRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel46.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel46.setText("To");

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel25.setText("IP Address");

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel23.setText("From Day");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addComponent(txtRoleName_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIpAddress_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel46)
                                .addComponent(jLabel23))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbToDay_createRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbFromDay_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(39, 39, 39)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel24)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addComponent(jLabel47)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(spinnerToTime_createRole)
                                .addComponent(spinnerFromTime_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25)
                .addComponent(btnCreate_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtRoleName_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtIpAddress_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(spinnerFromTime_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(spinnerToTime_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel47)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(cbFromDay_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel46)
                                    .addComponent(cbToDay_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnCreate_createRole, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        tabbed.addTab("", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel6FocusGained(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Group Creation Panel");

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Group Name");

        txtGroupName_createGroup.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        btnCreate_createContainer.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnCreate_createContainer.setForeground(new java.awt.Color(102, 102, 102));
        btnCreate_createContainer.setText("Create");
        btnCreate_createContainer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_createContainerActionPerformed(evt);
            }
        });

        chkShowList_createGroup.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        chkShowList_createGroup.setForeground(new java.awt.Color(102, 102, 102));
        chkShowList_createGroup.setText("Show List");
        chkShowList_createGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowList_createGroupActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("UserName");

        tableGroupCreate_createGroup.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        tableGroupCreate_createGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "Add to container"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableGroupCreate_createGroup);

        cbAllGroup_createGroup.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N

        listShow_createGroup.setFont(new java.awt.Font("Segoe UI Historic", 0, 13)); // NOI18N
        jScrollPane5.setViewportView(listShow_createGroup);

        btnList_createGroup.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnList_createGroup.setForeground(new java.awt.Color(51, 51, 51));
        btnList_createGroup.setText("List");
        btnList_createGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnList_createGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(btnCreate_createContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(308, 308, 308))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtGroupName_createGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                            .addComponent(btnList_createGroup, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(cbAllGroup_createGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chkShowList_createGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(44, 44, 44))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAllGroup_createGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkShowList_createGroup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGroupName_createGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(btnList_createGroup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreate_createContainer))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        tabbed.addTab("", jPanel6);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setText("Permissions and Roles Info");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 29, -1, -1));
        jPanel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 62, 430, 10));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tablePermissionInfo_permAndRoleInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Permission ID", "Action Name", "Object Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablePermissionInfo_permAndRoleInfo.setGridColor(new java.awt.Color(0, 0, 0));
        tablePermissionInfo_permAndRoleInfo.getTableHeader().setResizingAllowed(false);
        tablePermissionInfo_permAndRoleInfo.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablePermissionInfo_permAndRoleInfo);
        if (tablePermissionInfo_permAndRoleInfo.getColumnModel().getColumnCount() > 0) {
            tablePermissionInfo_permAndRoleInfo.getColumnModel().getColumn(0).setPreferredWidth(25);
        }

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 137, 410, 296));

        tableRoleInfo_permAndRoleInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Role ID", "Role Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableRoleInfo_permAndRoleInfo);
        if (tableRoleInfo_permAndRoleInfo.getColumnModel().getColumnCount() > 0) {
            tableRoleInfo_permAndRoleInfo.getColumnModel().getColumn(0).setPreferredWidth(25);
        }

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 137, 287, 296));

        jButton2.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jButton2.setText("Reload");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(673, 90, 110, -1));

        tabbed.addTab("", jPanel5);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 808, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        this.dispose();
        parent.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void setColor(JPanel panel){
        panel.setBackground(new Color(71,120,197));
    }
    private void resetColor(JPanel panel){
        panel.setBackground(new Color(120,168,252));
    }
    
    private void setPanel(JPanel panel) {
        panel.setVisible(true);
    }
    
    private void hidePanel(JPanel panel) {
        panel.setVisible(false);
    }
    
    private void bg0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg0MouseClicked
        tabbed.setSelectedIndex(0);
        setColor(bg0);
        resetColor(bg1);
        resetColor(bg2);
        resetColor(bg3);
        resetColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg0MouseClicked

    private void bg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg1MouseClicked
        tabbed.setSelectedIndex(1);
        resetColor(bg0);
        setColor(bg1);
        resetColor(bg2);
        resetColor(bg3);
        resetColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg1MouseClicked

    private void bg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg2MouseClicked
        tabbed.setSelectedIndex(2);
        resetColor(bg0);
        resetColor(bg1);
        setColor(bg2);
        resetColor(bg3);
        resetColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg2MouseClicked

    private void bg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg3MouseClicked
        tabbed.setSelectedIndex(3);
        resetColor(bg0);
        resetColor(bg1);
        resetColor(bg2);
        setColor(bg3);
        resetColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg3MouseClicked

    private void bg4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg4MouseClicked
        tabbed.setSelectedIndex(4);
        resetColor(bg0);
        resetColor(bg1);
        resetColor(bg2);
        resetColor(bg3);
        setColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg4MouseClicked

    private void bg0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg0MousePressed
        tabbed.setSelectedIndex(0);
        setColor(bg0);
        resetColor(bg1);
        resetColor(bg2);
        resetColor(bg3);
        resetColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg0MousePressed

    private void bg1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg1MousePressed
        tabbed.setSelectedIndex(1);
        resetColor(bg0);
        setColor(bg1);
        resetColor(bg2);
        resetColor(bg3);
        resetColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg1MousePressed

    private void bg2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg2MousePressed
        tabbed.setSelectedIndex(2);
        resetColor(bg0);
        resetColor(bg1);
        setColor(bg2);
        resetColor(bg3);
        resetColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg2MousePressed

    private void bg3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg3MousePressed
        tabbed.setSelectedIndex(3);
        resetColor(bg0);
        resetColor(bg1);
        resetColor(bg2);
        setColor(bg3);
        resetColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg3MousePressed

    private void bg4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg4MousePressed
        tabbed.setSelectedIndex(4);
        resetColor(bg0);
        resetColor(bg1);
        resetColor(bg2);
        resetColor(bg3);
        setColor(bg4);
        resetColor(bg5);
//        resetColor(bg6);
//        resetColor(bg7);
    }//GEN-LAST:event_bg4MousePressed

    private void tabbedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabbedFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tabbedFocusGained

    private void jPanel6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel6FocusGained
        initGroupCreation();
    }//GEN-LAST:event_jPanel6FocusGained

    private void chkShowList_createGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowList_createGroupActionPerformed
        if (chkShowList_createGroup.isSelected()) {
            cbAllGroup_createGroup.setVisible(true);
            listShow_createGroup.setVisible(true);
            btnList_createGroup.setVisible(true);

            // reset data
            listShow_createGroup.setModel(new DefaultListModel());
            cbAllGroup_createGroup.removeAllItems();
            loadContainerShowList();
        } else {
            cbAllGroup_createGroup.setVisible(false);
            listShow_createGroup.setVisible(false);
            btnList_createGroup.setVisible(false);
        }
    }//GEN-LAST:event_chkShowList_createGroupActionPerformed

    private void btnCreate_createContainerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_createContainerActionPerformed
        if (isValidString(txtGroupName_createGroup.getText())) {
            ArrayList<User> selectedUsers = new ArrayList<>();
            UserDAO userDAO = new UserDAO();
            //            System.out.println("total row: " + dtm.getRowCount() + " column: " + dtm.getColumnCount());
            for (int i = 0; i < dtm1.getRowCount(); i++) {
                if ((boolean) dtm1.getValueAt(i, 1) == true) {
                    selectedUsers.add(userDAO.getByUsername((String) dtm1.getValueAt(i, 0)));
                }
            }

            if (selectedUsers.size() == 0) {
                JOptionPane.showMessageDialog(this,
                    "You need to add at least one user to this group.",
                    "Group Information",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                GroupDAO groupDAO = new GroupDAO();
                GroupModel group = new GroupModel();

                group.setGroupName(txtGroupName_createGroup.getText());
                group.setUsers(selectedUsers);

                int status = groupDAO.insert(group);
                group.setGroupID(groupDAO.getCurrentID());

                if (status == 1) {
                    //                    initCreateActionTab();
                    JOptionPane.showMessageDialog(this,
                        "Group has been successfully added.",
                        "Group creation result",
                        JOptionPane.INFORMATION_MESSAGE);
                    initCreatePermission();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Group has not been added.",
                        "Group creation result",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "You need to validate all fields.",
                "Group fiels validation",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCreate_createContainerActionPerformed

    private void btnCreate_createRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_createRoleActionPerformed
        if (isValidString(txtRoleName_createRole.getText())
            && isValidIpAddress(txtIpAddress_createRole.getText())) {
            RoleDAO roleDB = new RoleDAO();
            RoleModel role = new RoleModel();
            role.setRoleID(roleDB.getCurrentID() + 1);
            role.setIpAddress(txtIpAddress_createRole.getText());
            role.setRoleName(txtRoleName_createRole.getText());
            role.setFromDay((String) cbFromDay_createRole.getSelectedItem());
            role.setToDay((String) cbToDay_createRole.getSelectedItem());
            role.setFromTime(toDateString((Date) spinnerFromTime_createRole.getValue()));
            role.setToTime(toDateString((Date) spinnerToTime_createRole.getValue()));

            System.out.println("-------" + role.getFromTime());

            int status = roleDB.insert(role);
            role.setRoleID(roleDB.getCurrentID());

            PermissionRoleDAO permissionRoleDAO = new PermissionRoleDAO();
            permissionRoleDAO.insertByRole(role);

            if (status == 1) {
                JOptionPane.showMessageDialog(this,
                    "Role has been successfully added.",
                    "Role creation result",
                    JOptionPane.INFORMATION_MESSAGE);
                initRolesAndPermissionsTab();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Role has not been added.",
                    "Role creation result",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "You need to validate all fields.",
                "Role fiels validation",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCreate_createRoleActionPerformed

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained
        initCreateActionTab();
    }//GEN-LAST:event_jPanel3FocusGained

    private void btnCreate_createActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_createActionActionPerformed
        if (isValidString(txtActionName_createAction.getText())) {
            ActionDAO actionDAO = new ActionDAO();
            Action action = new Action();
            action.setActionName(txtActionName_createAction.getText());
            String conflictedAction = (String) cbConflictedAction_createAction.getSelectedItem();

            if (!conflictedAction.equals("None")) {
                action.setConflictedAction(actionDAO.getByName(conflictedAction));
            }
            int status = actionDAO.insert(action);
            action.setActionID(actionDAO.getCurrentID());

            PermissionDAO permissionDAO = new PermissionDAO();
            permissionDAO.insertByActionID(action.getActionID());

            if (status == 1) {
                initCreateActionTab();
                initActionSet();
                JOptionPane.showMessageDialog(this,
                    "Action has been successfully added.",
                    "Action creation result",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Action has not been added.",
                    "Action creation result",
                    JOptionPane.INFORMATION_MESSAGE);
                reloadAction();
                initCreatePermission();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "You need to validate all fields.",
                "Action fiels validation",
                JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_btnCreate_createActionActionPerformed

    private void btnBrowse_FileAttachmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowse_FileAttachmentActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            lblPath_createObject.setText(fc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnBrowse_FileAttachmentActionPerformed

    private void btnCreate_createObjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_createObjectActionPerformed
        if (isValidString(txtObjName_createObject.getText())
            && isValidIpAddress(txtIpAddress_createObject.getText())) {

            ObjectDAO objDB = new ObjectDAO();
            ObjectModel obj = new ObjectModel();
            obj.setObjectID(objDB.getCurrentID() + 1);
            obj.setObjectName(txtObjName_createObject.getText());
            obj.setFromDay((String) cbFromDay_createObject.getSelectedItem());
            obj.setToDay((String) cbToDay_createObject.getSelectedItem());
            obj.setFromTime(toDateString((Date) spinnerFromTime_createObject.getValue()));
            obj.setToTime(toDateString((Date) spinnerToTime_createObject.getValue()));
            obj.setIpAddress(txtIpAddress_createObject.getText());
            obj.setPath(lblPath_createObject.getText());

            int status = objDB.insert(obj);
            obj.setObjectID(objDB.getCurrentID());

            PermissionDAO permissionDAO = new PermissionDAO();
            permissionDAO.insertByObjectID(obj.getObjectID());

            if (status == 1) {
                JOptionPane.showMessageDialog(this,
                    "Object has been successfully added.",
                    "Object creation result",
                    JOptionPane.INFORMATION_MESSAGE);
                reloadUserInGroupCreation();
                initCreatePermission();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Object has not been added.",
                    "Object creation result",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "You need to validate all fields.",
                "Object fiels validation",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCreate_createObjectActionPerformed

    private void btnRefresh__createUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh__createUserActionPerformed
        txtUsername_createUser.setText("");
        txtPassword__createUser.setText("");
        txtIpAddress__createUser.setText("");
        cbFromDay__createUser.setSelectedIndex(0);
        cbToDay_createUser.setSelectedIndex(0);
    }//GEN-LAST:event_btnRefresh__createUserActionPerformed

    private void btnCreate__createUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate__createUserActionPerformed
        if (isValidString(txtUsername_createUser.getText())
            && isValidIpAddress(txtIpAddress__createUser.getText())) {
            User user = new User();
            user.setUsername(txtUsername_createUser.getText());
            user.setPassword(txtPassword__createUser.getText());
            user.setFromDay(cbFromDay__createUser.getSelectedItem().toString());
            user.setToDay(cbToDay_createUser.getSelectedItem().toString());
            user.setFromTime(toDateString((Date) spinnerFromTime__createUser.getValue()));
            user.setToTime(toDateString((Date) spinnerToTime_createUser.getValue()));
            user.setIpAddress(txtIpAddress__createUser.getText());
            UserDAO userDB = new UserDAO();
            int status = userDB.insert(user);
            if (status == 1) {
                JOptionPane.showMessageDialog(this,
                    "User has been successfully added.",
                    "User creation result",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "User has not been added.",
                    "User creation result",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this,
                "You need to validate all fields.",
                "User fiels validation",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCreate__createUserActionPerformed

    private void btnList_createGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnList_createGroupActionPerformed
        GroupDAO groupDAO = new GroupDAO();
        ArrayList<String> listGroup = groupDAO.getAllUsernameInGroup((String) cbAllGroup_createGroup.getSelectedItem());
        System.out.println("selected: " + (String) cbAllGroup_createGroup.getSelectedItem());
        DefaultListModel dlm_createContainer = new DefaultListModel();
        for (String username : listGroup) {
            System.out.println("in " + (String) cbAllGroup_createGroup.getSelectedItem() + ":" + username);
            dlm_createContainer.addElement(username);
        }
        listShow_createGroup.setModel(dlm_createContainer);
    }//GEN-LAST:event_btnList_createGroupActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadPermissionAndRoleInfo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bg5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg5MouseClicked
        tabbed.setSelectedIndex(5);
        resetColor(bg0);
        resetColor(bg1);
        resetColor(bg2);
        resetColor(bg3);
        resetColor(bg4);
        setColor(bg5);
    }//GEN-LAST:event_bg5MouseClicked

    private void bg5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg5MousePressed
        tabbed.setSelectedIndex(5);
        resetColor(bg0);
        resetColor(bg1);
        resetColor(bg2);
        resetColor(bg3);
        resetColor(bg4);
        setColor(bg5);
    }//GEN-LAST:event_bg5MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg0;
    private javax.swing.JPanel bg1;
    private javax.swing.JPanel bg2;
    private javax.swing.JPanel bg3;
    private javax.swing.JPanel bg4;
    private javax.swing.JPanel bg5;
    private javax.swing.JButton btnBrowse_FileAttachment;
    private javax.swing.JButton btnCreate__createUser;
    private javax.swing.JButton btnCreate_createAction;
    private javax.swing.JButton btnCreate_createContainer;
    private javax.swing.JButton btnCreate_createObject;
    private javax.swing.JButton btnCreate_createRole;
    private javax.swing.JButton btnList_createGroup;
    private javax.swing.JButton btnRefresh__createUser;
    private javax.swing.JComboBox<String> cbAllGroup_createGroup;
    private javax.swing.JComboBox<String> cbConflictedAction_createAction;
    private javax.swing.JComboBox<String> cbFromDay__createUser;
    private javax.swing.JComboBox<String> cbFromDay_createObject;
    private javax.swing.JComboBox<String> cbFromDay_createRole;
    private javax.swing.JComboBox<String> cbToDay_createObject;
    private javax.swing.JComboBox<String> cbToDay_createRole;
    private javax.swing.JComboBox<String> cbToDay_createUser;
    private javax.swing.JCheckBox chkShowList_createGroup;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblPath_createObject;
    private javax.swing.JList<String> listShow_createGroup;
    private javax.swing.JSpinner spinnerFromTime__createUser;
    private javax.swing.JSpinner spinnerFromTime_createObject;
    private javax.swing.JSpinner spinnerFromTime_createRole;
    private javax.swing.JSpinner spinnerToTime_createObject;
    private javax.swing.JSpinner spinnerToTime_createRole;
    private javax.swing.JSpinner spinnerToTime_createUser;
    private javax.swing.JTabbedPane tabbed;
    private javax.swing.JTable tableGroupCreate_createGroup;
    private javax.swing.JTable tablePermissionInfo_permAndRoleInfo;
    private javax.swing.JTable tableRoleInfo_permAndRoleInfo;
    private javax.swing.JTextField txtActionName_createAction;
    private javax.swing.JTextField txtGroupName_createGroup;
    private javax.swing.JTextField txtIpAddress__createUser;
    private javax.swing.JTextField txtIpAddress_createObject;
    private javax.swing.JTextField txtIpAddress_createRole;
    private javax.swing.JTextField txtObjName_createObject;
    private javax.swing.JPasswordField txtPassword__createUser;
    private javax.swing.JTextField txtRoleName_createRole;
    private javax.swing.JTextField txtUsername_createUser;
    // End of variables declaration//GEN-END:variables

    private boolean isValidString(String str) {
        String pattern = "^(?=.{4,100}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (!m.find()) {
            System.out.println("Wrong format!");
            return false;
        }
        return true;
    }

    private boolean isValidIpAddress(String str) {
        String pattern = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (!m.find()) {
            System.out.println("Wrong IP format!");
            return false;
        }
        return true;
    }

    private String toDateString(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss a");
        return formater.format(date);
    }
    // load group show list
    private void loadContainerShowList() {
        GroupDAO groupDAO = new GroupDAO();
        ArrayList<GroupModel> groups = groupDAO.all();
        for (GroupModel group : groups) {
            cbAllGroup_createGroup.addItem(group.getGroupName());
        }
    }

    private void loadActionSetShowList() {
//        ActionSetDAO setDB = new ActionSetDAO();
//        ArrayList<ActionSet> sets = setDB.all();
//        for (ActionSet set : sets) {
//            cbAllActionSet_createActionSet.addItem(set.getActionSetName());
//        }
    }
}
