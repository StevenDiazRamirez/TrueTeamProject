
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class Blank {

    private int blankID;
    private String status;
    private int employeeID;
    private int blankType;
    private Date receiveDate;
    private Date assignedDate;

    public Blank(int blankType, String status, Date receiveDate) {
        this.status = status;
        this.blankType = blankType;
        this.receiveDate = receiveDate;
    }

    public Blank(int blankType, String status, Date receiveDate, Date assignedDate, int employeeID) {
        this.status = status;
        this.blankType = blankType;
        this.receiveDate = receiveDate;
        this.assignedDate = assignedDate;
        this.employeeID = employeeID;
    }

    private static Connection con = DBSConnection.getConnection();

    public static String checkBlankStatus(int blankID, int blankType) {
        try {

            Statement stm = con.createStatement();

            String searchStatement = "SELECT status FROM blanks WHERE blankID = " + blankID +
                    " AND blankType = " + blankType;

            ResultSet rs = stm.executeQuery(searchStatement);
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void changeStatus(int blankID, int blankType, String status){
        try {
            String searchBlank = "UPDATE blanks SET status = " + "'" + status + "'" + " WHERE `blankID` = " + blankID
                    + " AND blankType = " + blankType;
            PreparedStatement stm = con.prepareStatement(searchBlank);
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void orderBlanks(int amount, int type){
        try {
            Connection con = DBSConnection.getConnection();

            String searchQuery = "SELECT MAX(BlankID) FROM blanks WHERE blankType = " + type;
            PreparedStatement highestblankID = con.prepareStatement(searchQuery);


            ResultSet rs = highestblankID.executeQuery(searchQuery);
            int highestBlank = 1;
            while (rs.next()) {
                highestBlank = rs.getInt(1) + 1;
            }

            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

            for (int i = 0; i < amount; i++) {
                String addQuery = "INSERT INTO blanks (`blankID`, `blankType`, `status`, `dateRecieved`) " +
                        "VALUES ('" + (highestBlank + i) + "' , '" + type + "', 'Received', '" + sqlDate + "');";

                highestblankID.executeUpdate(addQuery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Blank checkIfExists(int blankID, int type){
        try{

            String query = "SELECT * FROM blanks WHERE blankID = "+blankID+" AND blankType = "+type;

            PreparedStatement stm = con.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Blank blank = new Blank(rs.getInt("blankType"),rs.getString("status"),rs.getDate("dateRecieved"),rs.getDate("dateAssigned"),rs.getInt("employeeID"));
                blank.setBlankID(rs.getInt(1));
                return blank;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void allocateBlank(int type, int amount, int employeeID, int blankID){
        try{

            boolean enoughBlanks = false;

            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

            String amountOfBlanks = "SELECT COUNT(blankID) FROM blanks WHERE blankType = "+'"'+ type +'"'+" AND status='Received';";
            PreparedStatement stm = con.prepareStatement(amountOfBlanks);

            ResultSet count = stm.executeQuery(amountOfBlanks);
            while(count.next()) {
                if (count.getInt(1) < amount) {
                    enoughBlanks = false;
                    JOptionPane.showMessageDialog(null, "Not enough blanks, enter a lower amount");
                    return;
                } else {
                    enoughBlanks = true;
                }
            }
            int highestBlank = 1;

            if(blankID == 0){
                String highestBlankID = "SELECT MIN(blankID) FROM blanks WHERE blankType = "+ type +" AND status = \"Received\"";
                ResultSet rs = stm.executeQuery(highestBlankID);


                while(rs.next()){
                    highestBlank = rs.getInt(1);
                    break;
                }

                if(enoughBlanks) {
                    for (int i = 0; i < amount; i++) {
                        Blank blank = checkIfExists(highestBlank+i, type);
                        if(blank.getStatus().equals("Received")) {
                            String addStatement = "UPDATE blanks SET status = 'Assigned', dateAssigned = " + '"' + sqlDate + '"' + ", employeeID = " + '"' + employeeID + '"' + " WHERE blankID = " + (highestBlank + i) + " AND blankType = "+type;
                            stm.executeUpdate(addStatement);
                        }else{
                            amount++;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Blanks have been allocated");
                }
            }else{
                Blank blank = checkIfExists(blankID,type);
                if(blank.getStatus().equals("Received")){
                    highestBlank = blankID;
                    if(enoughBlanks){
                        for(int i =0; i<amount; i++){
                            Blank blank2 = checkIfExists(highestBlank+i,type);
                            if(blank.getStatus().equals("Received")) {
                                String addStatement = "UPDATE blanks SET status = 'Assigned', dateAssigned = " + '"' + sqlDate + '"' + ", employeeID = " + '"' + employeeID + '"' + " WHERE blankID = " + (highestBlank + i)  + " AND blankType = "+type;;
                                stm.executeUpdate(addStatement);
                            }else{
                                amount++;
                            }
                        }
                        //AlertWindow.showInformationAlert("Success!","The blanks have been allocated");
                    }
                }else{
                    //AlertWindow.showInformationAlert("Error!","These blanks have already been allocated, please select a different blank");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteBlank(int blankID , int blankType){
        try{
            String delete = "DELETE FROM `ats`.`blanks` WHERE `blankID` = '"+ blankID +"' AND `blankType` = "+ blankType;
            PreparedStatement stm = con.prepareStatement(delete);
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Blank has been returned");
        return true;
    }

        public int getBlankID() {
            return blankID;
        }

        public void setBlankID(int blankID) {
            this.blankID = blankID;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeID(int employeeID) {
            this.employeeID = employeeID;
        }

        public int getBlankType() {
            return blankType;
        }

        public void setBlankType(int blankType) {
            this.blankType = blankType;
        }

        public Date getReceiveDate() {
            return receiveDate;
        }

        public void setReceiveDate(Date receiveDate) {
            this.receiveDate = receiveDate;
        }

        public Date getAssignedDate() {
            return assignedDate;
        }

        public void setAssignedDate(Date assignedDate) {
            this.assignedDate = assignedDate;
        }

}

