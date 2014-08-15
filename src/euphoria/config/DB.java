package euphoria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	// Connection
	/** Connection */
	private Connection conn = null;
	/** Queryを実行するためのStatement */
	private Statement stat = null;

	/**
	 * DBのコンストラクタ
	 */
	public DB() {
	}

	/**
	 * 与えられたSQL文を実行して結果をResultSetのオブジェクトとして返す。<br>
	 *
	 * @param queryStr 実行したいSQL文
	 * @return 実行結果。実行できない、もしくは結果が無い場合はnullを返す。
	 */
	public ResultSet executeSQLQuery(String queryStr) {
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
	public boolean connectDB(ConfigDB configDB) {
		try {
			conn = DriverManager.getConnection("" +
			        "jdbc:mysql://" + configDB.dbHost +
			        ":" + configDB.dbPort +
			        "/" + configDB.dbName +
			        "?user=" + configDB.dbUsername +
			        "&password=" + configDB.dbPassword
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
	public void closeDB() {
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
	public boolean isConnectDB() {
		if (conn == null) {
			return false;
		} else {
			return true;
		}
	}
}
