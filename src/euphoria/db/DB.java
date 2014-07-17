package euphoria.db;

import euphoria.setting.SettingFile;

public class DB {

	/** データベース接続情報に関する設定ファイル */
	private static SettingFile dbConfig;

	/**
	 * DBのコンストラクタ。
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
	public static boolean CheckDBConfigFile(String dbConfigFile) {
		dbConfig = new SettingFile();
		if (!dbConfig.loadConf(dbConfigFile)) {
			createNewConfigFile();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * データベース用設定ファイルが存在しない場合に新しく作りなおす。<br>
	 * 初期値が設定され自動的に保存される。
	 *
	 * @return 設定ファイルの作成に成功すればtrue，失敗すればfalse
	 */
	public static boolean createNewConfigFile() {
		dbConfig.setProperty("Host", "hostname");
		dbConfig.setProperty("User", "username");
		dbConfig.setProperty("Pass", "password");
		dbConfig.createNewFile();
		if (dbConfig.storeToXML("自動作成されたMySQL用設定ファイル")) {
			System.out.println("新しい設定ファイル\"" + dbConfig.getConfigFileName() + "\"を作成しました。");
			return true;
		} else {
			return false;
		}
	}
}
