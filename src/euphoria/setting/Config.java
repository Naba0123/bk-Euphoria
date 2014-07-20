package euphoria.setting;

/**
 * 設定ファイルなどを扱うクラス
 *
 * @author PenNanban
 *
 */
public class Config {

	/** 全般に関する設定ファイル */
	private static SettingFile genConfig;
	/** 全般に関する設定ファイルの名前 */
	private static final String genConfigFilename = "generalConf.xml";

	// データベースに関するの設定
	/** データベースに関する設定ファイル */
	private static SettingFile dbConfig;
	/** データベースに関する設定ファイルの名前 */
	private static final String dbConfigFilename = "dbConf.xml";
	/** Hostのキー */
	private static final String dbHostKey = "Host";
	/** Hostの値 */
	public static String dbHost = "host";
	/** Port番号のキー */
	private static final String dbPortKey = "Port";
	/** Port番号の値 */
	public static String dbPort = "3306";
	/** Usernameのキー */
	private static final String dbUsernameKey = "User";
	/** Usernameの値 */
	public static String dbUsername = "username";
	/** Passwordのキー */
	private static final String dbPasswordKey = "Pass";
	/** Passwordの値 */
	public static String dbPassword = "password";
	/** データベース名のキー */
	private static final String dbNameKey = "DB";
	/** データベース名の値 */
	public static String dbName = "database";

	/** 設定ファイルの修復が必要かどうかを判別するフラグ */
	private static boolean isNeededFix = false;

	/**
	 * 全体的なConfigの読み込みを行う
	 */
	public static void loadConf() {
		// データベースに関する設定ファイルの読み込み
		loadDBConf();
	}

	/**
	 * データベースに関する設定ファイルの読み込みを行う
	 */
	private static void loadDBConf() {
		isNeededFix = false;
		dbConfig = new SettingFile();
		if (dbConfig.loadConf(dbConfigFilename)) {
			dbHost = readValue(dbConfig, dbHostKey, dbHost);
			dbPort = readValue(dbConfig, dbPortKey, dbPort);
			dbUsername = readValue(dbConfig, dbUsernameKey, dbUsername);
			dbPassword = readValue(dbConfig, dbPasswordKey, dbPassword);
			dbName = readValue(dbConfig, dbNameKey, dbName);
			endReadValue(dbConfig);
		} else {
			dbConfig.setProperty(dbHostKey, dbHost);
			dbConfig.setProperty(dbUsernameKey, dbUsername);
			dbConfig.setProperty(dbPasswordKey, dbPassword);
			dbConfig.setProperty(dbPortKey, dbPort);
			dbConfig.setProperty(dbNameKey, dbName);
			createConfigFile(dbConfig, "自動作成されたデータベース用設定ファイル");
		}
	}

	/**
	 * キーの読み込みを行う。<br>
	 * 読み込めない（キーが無い，またはキーの値が""（空白））場合はキーを修復するフラグを立てる。
	 *
	 * @param setting 読み込む設定ファイル
	 * @param key 読み込むキー
	 * @param value 読み込む値のデフォルト値
	 * @return 値が読み込めたら値を，読み込めない場合はデフォルト値を返す
	 */
	private static String readValue(SettingFile setting, String key, String value) {
		if (setting.getProperty(key).equals("")) {
			// キーが読み込めない場合は修復するフラグを立てる
			setting.setProperty(key, value);
			isNeededFix = true;
			System.out.println("キー\"" + key + "\"を修正します。");
			return value;
		} else {
			return setting.getProperty(key);
		}
	}

	/**
	 * readValueの後処理を行う。<br>
	 * 設定ファイルの修復が必要な場合は修復を行う。
	 *
	 * @param setting 対象の設定ファイル
	 */
	private static void endReadValue(SettingFile setting) {
		if (isNeededFix) {
			setting.storeToXML("自動修復された設定ファイル");
			System.out.println("設定ファイルの一部を修復しました。");
		}
		System.out.println("データベース用設定ファイル\"" + setting.getConfigFilename() + "\"の読み込みが完了しました。");
	}

	/**
	 * 設定ファイルが存在しない場合に新たにファイルを作成する。
	 *
	 * @param setting 対象となる設定ファイル
	 * @param comment 設定ファイルに記述されるコメント
	 */
	private static void createConfigFile(SettingFile setting, String comment) {
		setting.createNewFile();
		setting.storeToXML(comment);
		System.out.println("データベース用設定ファイル\"" + setting.getConfigFilename() + "\"の読み込みに失敗したため，新しい設定ファイルを作成しました。");
	}

}
