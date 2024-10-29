package config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("../") // ajuste o caminho conforme necessário
            .load();

    public static String getDbHost() {
        return dotenv.get("DB_HOST");
    }

    public static String getDbPort() {
        return dotenv.get("DB_PORT");
    }

    public static String getDbUser() {
        return dotenv.get("DB_USER");
    }

    public static String getDbName() {
        return dotenv.get("DB_NAME");
    }

    public static String getDbPass() {
        return dotenv.get("DB_PASS");
    }
}
