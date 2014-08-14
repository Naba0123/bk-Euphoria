package euphoria.config;

public class ConfigDB extends Config {

	/** データベースに関する設定ファイル */
	private ConfigFile dbConfig;
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

	/**
	 * データベースに関する設定ファイルの読み込みを行う
	 */
	public void loadDBConf() {
		isNeededFix = false;
		dbConfig = new ConfigFile();
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

}
