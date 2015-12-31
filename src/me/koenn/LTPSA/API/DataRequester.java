package me.koenn.LTPSA.API;

import me.koenn.LTPSA.SQLAPI;
import org.bukkit.plugin.Plugin;

import java.sql.*;

public class DataRequester {

    private String defaultIp = "81.169.151.227";
    private String url;
    private String database;
    private String table;
    private String username = "Proxy";
    private String password = "WVT2ZjVUjiauoB4Xd";

    public DataRequester(String database, String table){
        this.database = database;
        this.table = table;
        this.url = "jdbc:mysql://" + defaultIp + ":" + 3306 + "/" + database;
    }

    public DataRequester(String ip, Integer port, String database, String table, String username, String password){
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

    public Data sendDataRequest(Plugin pl, String primaryKey, String value){
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + this.table + " WHERE " + primaryKey + "=?;");
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            SQLAPI.getInstance().log("Plugin '" + pl.getName() + "' requested data from database '" + this.database + "'");
            return new Data(rs);
        } catch (SQLException e) {
            SQLAPI.getInstance().log("Data request from plugin '" + pl.getName() + "' failed:");
            e.printStackTrace();
            return null;
        }
    }
}
