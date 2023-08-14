package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.DayOfWeek;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }
    private static Connection getCon() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }
    @SneakyThrows
    public static DataHelper.VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth.codes ORDER BY created DESC LIMIT 1";
        var conn = getCon();
        var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.VerificationCode(code);
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getCon();
        runner.execute(connection, "DELETE FROM auth.codes");
        runner.execute(connection, "DELETE FROM card_transaction");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");


    }
}
