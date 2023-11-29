package plugins.nate.smpclaims.utils;

import plugins.nate.smpclaims.SMPClaims;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
    private Connection connection;


    public void initialize() {
        try {
            File dataFolder = SMPClaims.getPlugin().getDataFolder();
            if (!dataFolder.exists() && !dataFolder.mkdir()) {
                throw new IllegalStateException("Failed to create plugin data folder: " + dataFolder);
            }

            String url = "jdbc:sqlite:" + new File(SMPClaims.getPlugin().getDataFolder(), "bans.db").getAbsolutePath();
            connection = DriverManager.getConnection(url);

            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS bans (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "Banned TEXT," +
                        "BannedBy TEXT," +
                        "Reason TEXT," +
                        "StartTime INTEGER," +
                        "Duration INTEGER);";

                statement.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
