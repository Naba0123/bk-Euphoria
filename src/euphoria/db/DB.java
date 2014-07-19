package euphoria.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import euphoria.setting.SettingFile;

public class DB {

	// Default Configuration
	/** データベース接続情報に関する設定ファイル */
	private static SettingFile dbConfig;
	/** Hostのキー */
	private static final String dbHostKey = "Host";
	/** Hostの値 */
	private static String dbHost = "host";
	/** Port番号のキー */
	private static final String dbPortKey = "Port";
	/** Port番号の値 */
	private static String dbPort = "3306";
	/** Usernameのキー */
	private static final String dbUsernameKey = "User";
	/** Usernameの値 */
	private static String dbUsername = "username";
	/** Passwordのキー */
	private static final String dbPasswordKey = "Pass";
	/** Passwordの値 */
	private static String dbPassword = "password";
	/** データベース名のキー */
	private static final String dbNameKey = "DB";
	/** データベース名の値 */
	private static String dbName = "database";

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
	 * データベース用設定ファイルが存在するかどうかをチェック。<br>
	 * 存在する場合は必要なキーの値を読み取る。
	 *
	 * @param dbConfigFile データベース用設定ファイルの名前
	 * @return 読み込みと値の取得に成功すればtrue，失敗すればfalse
	 */
	public static void checkDBConfigFile(String dbConfigFile) {
		dbConfig = new SettingFile();
		if (!dbConfig.loadConf(dbConfigFile)) {
			createDBConfigFile();
			System.out.println("MySQL用設定ファイル\"" + dbConfigFile + "\"が見つからなかった為、新たに設定ファイルを生成しました。");
		} else {
			dbHost = dbConfig.getProperty(dbHostKey);
			dbUsername = dbConfig.getProperty(dbUsernameKey);
			dbPassword = dbConfig.getProperty(dbPasswordKey);
			dbPort = dbConfig.getProperty(dbPortKey);
			dbName = dbConfig.getProperty(dbNameKey);
			System.out.println("MySQL用設定ファイル\"" + dbConfigFile + "\"の読み込みが完了しました。");
		}
	}

	/**
	 * データベース用設定ファイルが存在しない場合に新しく作り直す。<br>
	 * 初期値が設定され自動的に設定ファイルに保存される。
	 *
	 * @return 設定ファイルの作成に成功すればtrue，失敗すればfalse
	 */
	public static void createDBConfigFile() {
		dbConfig.setProperty(dbHostKey, dbHost);
		dbConfig.setProperty(dbUsernameKey, dbUsername);
		dbConfig.setProperty(dbPasswordKey, dbPassword);
		dbConfig.setProperty(dbPortKey, dbPort);
		dbConfig.setProperty(dbNameKey, dbName);
		dbConfig.createNewFile();
		dbConfig.storeToXML("自動的に生成されたMySQL用設定ファイル");
		System.out.println("新しい設定ファイル\"" + dbConfig.getConfigFileName() + "\"を作成しました。");
	}

	/**
	 * 与えられたSQL文を実行して結果を返す
	 *
	 * @param queryStr 実行したいSQL文
	 * @return 実行結果。実行できなければnullを返す。
	 */
	public static ResultSet doSQLQuery(String queryStr) {
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
			conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword);
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
