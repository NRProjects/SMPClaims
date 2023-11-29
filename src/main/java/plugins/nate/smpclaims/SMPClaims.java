package plugins.nate.smpclaims;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import plugins.nate.smpclaims.utils.CommandRegistration;
import plugins.nate.smpclaims.utils.EventRegistration;

import java.io.File;
import java.sql.*;

public final class SMPClaims extends JavaPlugin {
    private static SMPClaims plugin;
    private static Connection connection;


    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;

        initializeDatabase();

        EventRegistration.registerEvents(this);
        CommandRegistration.registerCommands(this);

    }

    @Override
    public void onDisable() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                getLogger().severe("Error closing database connection: " + e.getMessage());
            }
        }
    }

    public static SMPClaims getPlugin() {
        return plugin;
    }

    private static void initializeDatabase() {
        try {
            File dataFolder = getPlugin().getDataFolder();

            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }

            File dbFile = new File(dataFolder, "claims.db");
            String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();

            connection = DriverManager.getConnection(url);
            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS claims (" +
                        "Claimant TEXT PRIMARY KEY," +
                        "Size INTEGER," +
                        "MinX INTEGER," +
                        "MaxX INTEGER," +
                        "MaxY INTEGER," +
                        "MinY INTEGER," +
                        "MaxZ INTEGER," +
                        "MinZ INTEGER);";
                statement.execute(sql);
            }
        } catch (SQLException e) {
            getPlugin().getLogger().severe("Error initializing the database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                initializeDatabase();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @SafeVarargs
    @NotNull
    public static <T> ResultSet queryDB(final String query, final T... args) {
        try {
            PreparedStatement statement = getPlugin().getConnection().prepareStatement(query);
            for (int i = 0; i < args.length; i++) {
                T arg = args[i];
                if (arg instanceof Integer) {
                    statement.setInt(i + 1, (Integer) arg);
                } else if (arg instanceof Double) {
                    statement.setDouble(i + 1, (Double) arg);
                } else if (arg instanceof String) {
                    statement.setString(i + 1, (String) arg);
                } else if (arg instanceof Long) {
                    statement.setLong(i + 1, (Long) arg);
                } else if (arg instanceof Timestamp) {
                    statement.setTimestamp(i + 1, (Timestamp) arg);
                }
            }
            if (statement.execute()) {
                return statement.getResultSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
