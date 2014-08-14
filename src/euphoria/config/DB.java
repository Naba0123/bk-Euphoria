package euphoria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	// Connection
	/** Connection */
	private static Connection conn = null;
	/** Queryを実行するためのStatement */
	private static Statement stat = null;

	/**
	 * DBのコンストラクタ。<br>
	 * staticだから実質機能しｎ（ｒｙ
	 */
	DB() {
	}

	/**
	 * 与えられたSQL文を実行して結果をResultSetのオブジェクトとして返す。<br>
	 *
	 * @param queryStr 実行したいSQL文
	 * @return 実行結果。実行できない、もしくは結果が無い場合はnullを返す。
	 */
	public static ResultSet executeSQLQuery(String queryStr) {
		ResultSet rs = null;
		if (isConnectDB()) {
			try {
				rs = stat.executeQuery(queryStr);
				System.out.println("指定されたクエリ\"" + queryStr + "\"は正常に処理されました。");
			} catch (SQLException e) {
				System.err.println("指定されたクエリ\"" + queryStr + "\"に問題があります。");
				e.printStackTrace();
			}
		} else {
			System.err.println("データベースに接続されていません。");
		}
		return rs;
	}

	/**
	 * データベースとの接続を試みる。
	 *
	 * @return 接続に成功すればture，失敗すればfalseを返す。
	 */
	public static boolean connectDB() {
		try {
			conn = DriverManager.getConnection("" +
			        "jdbc:mysql://" + Config.configDB.dbHost +
			        ":" + Config.configDB.dbPort +
			        "/" + Config.configDB.dbName +
			        "?user=" + Config.configDB.dbUsername +
			        "&password=" + Config.configDB.dbPassword
			        );
			stat = conn.createStatement();
			System.out.println("データベースに接続しました。");
			return true;
		} catch (SQLException e) {
			System.err.println("データベースに接続できませんでした。");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * データベースを切断する。<br>
	 * Statement, Connectionにはnullが入る。
	 */
	public static void closeDB() {
		// Statementの切断
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		stat = null;

		// Connectionの切断
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		conn = null;
	}

	/**
	 * データベースに接続しているかどうかの確認。
	 *
	 * @return 接続している状態ならtrue，接続されていない状態ならfalseを返す。
	 */
	public static boolean isConnectDB() {
		if (conn == null) {
			return false;
		} else {
			return true;
		}
	}
}
