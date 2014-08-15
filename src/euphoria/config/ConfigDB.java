package euphoria.config;

/**
 * データベースに関する設定を行うクラス
 *
 * @author PenNanban
 *
 */
public class ConfigDB extends Config {

	/** データベースに関する設定ファイルの名前 */
	private final String dbConfigFilename = "dbConf.xml";
	/** Hostのキー */
	private final String dbHostKey = "Host";
	/** Hostの値 */
	public String dbHost = "host";
	/** Port番号のキー */
	private final String dbPortKey = "Port";
	/** Port番号の値 */
	public String dbPort = "3306";
	/** Usernameのキー */
	private final String dbUsernameKey = "User";
	/** Usernameの値 */
	public String dbUsername = "username";
	/** Passwordのキー */
	private final String dbPasswordKey = "Pass";
	/** Passwordの値 */
	public String dbPassword = "password";
	/** データベース名のキー */
	private final String dbNameKey = "DB";
	/** データベース名の値 */
	public String dbName = "database";

	/** 自動で生成される設定ファイルに記載するコメント */
	private String configFileComment = "自動作成されたデータベース用設定ファイル";

	/**
	 * コンストラクタ。<br>
	 * ConfigFileのインスタンスの定義を行う。
	 */
	public ConfigDB() {
		config = new ConfigFile();
	}

	/**
	 * データベースに関する設定ファイルの読み込みを行う。
	 */
	public void loadDBConf() {
		isNeededFix = false;
		if (config.loadConf(dbConfigFilename)) {
			dbHost = readValue(dbHostKey, dbHost);
			dbPort = readValue(dbPortKey, dbPort);
			dbUsername = readValue(dbUsernameKey, dbUsername);
			dbPassword = readValue(dbPasswordKey, dbPassword);
			dbName = readValue(dbNameKey, dbName);
			endReadValue();
		} else {
			config.setProperty(dbHostKey, dbHost);
			config.setProperty(dbUsernameKey, dbUsername);
			config.setProperty(dbPasswordKey, dbPassword);
			config.setProperty(dbPortKey, dbPort);
			config.setProperty(dbNameKey, dbName);
			createConfigFile(configFileComment);
		}
	}

}
