package me.koenn.LTPSA.API;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
    
    private ResultSet resultSet;

    private Date now;
    private SimpleDateFormat stamp;


    public Data(ResultSet resultSet) {
        this.resultSet = resultSet;
        now = new Date();
        stamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    }
    
    public Object getData(String collumn){
        try {
            if(resultSet.next()){
                return resultSet.getObject(collumn);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTimeStamp(){
        return stamp.format(now);
    }
}
