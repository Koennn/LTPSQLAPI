package me.koenn.LTPSA.API;

import me.koenn.LTPSA.SQLAPI;
import org.bukkit.plugin.Plugin;

import java.sql.*;
import java.util.List;

public class DataInput {

    private String defaultIp = "81.169.151.227";
    private String url;
    private String database;
    private String table;
    private String username = "Proxy";
    private String password = "WVT2ZjVUjiauoB4Xd";

    public DataInput(String database, String table){
        this.database = database;
        this.table = table;
        this.url = "jdbc:mysql://" + defaultIp + ":" + 3306 + "/" + database;
    }

    public DataInput(String ip, Integer port, String database, String table, String username, String password){
        this.database = database;
        this.table = table;
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
    }

    public String getDefaultIp() {
        return defaultIp;
    }

    public void setDefaultIp(String defaultIp) {
        this.defaultIp = defaultIp;
    }

    public void sendDataInput(Plugin pl, List<String> values, List<String> collumns, String primaryKey){
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + this.table + " WHERE " + primaryKey + "=?;");
            ps.setString(1, values.get(0));
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                ps = con.prepareStatement("INSERT INTO " + this.table + " (" + collumns.get(0) + ") VALUES (?);");
                ps.setString(1, values.get(0));
                ps.execute();
                for(int i = 1; i < collumns.size(); i++){
                    ps = con.prepareStatement("UPDATE " + this.table + " SET " + collumns.get(i) + "=? WHERE " + primaryKey + "=?;");
                    ps.setString(1, values.get(i));
                    ps.setString(2, values.get(0));
                    ps.execute();
                }
            } else {
                for(int i = 0; i < collumns.size(); i++){
                    ps = con.prepareStatement("UPDATE " + this.table + " SET " + collumns.get(i) + "=? WHERE " + primaryKey + "=?;");
                    ps.setString(1, values.get(i));
                    ps.setString(2, values.get(0));
                    ps.execute();
                }
            }
            SQLAPI.getInstance().log("Plugin '" + pl.getName() + "' send data to database '" + this.database + "'");
        }catch (Exception e){
            SQLAPI.getInstance().log("Failed to send data packet from plugin '" + pl.getName() + "':");
            e.printStackTrace();
        }
    }
}