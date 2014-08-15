package euphoria.config;


/**
 * 設定ファイルなどを扱うクラス
 *
 * @author PenNanban
 *
 */
public class Config {

	/** データベース用のコンフィグ */
	public ConfigDB configDB;

	/** 設定ファイルの修復が必要かどうかを判別するフラグ */
	protected boolean isNeededFix = false;

	/**
	 * Configクラスのコンストラクタ。各設定クラスのインスタンスを生成する。
	 */
	public Config() {
		// データベースの設定クラスのインスタンス
		configDB = new ConfigDB();
	}

	/**
	 * 全体的なConfigの読み込みを行う
	 */
	public void loadConf() {
		// データベースに関する設定ファイルの読み込み
		configDB.loadDBConf();
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
	protected String readValue(ConfigFile setting, String key, String value) {
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
	protected void endReadValue(ConfigFile setting) {
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
	protected void createConfigFile(ConfigFile setting, String comment) {
		setting.createNewFile();
		setting.storeToXML(comment);
		System.out.println("データベース用設定ファイル\"" + setting.getConfigFilename() + "\"の読み込みに失敗したため，新しい設定ファイルを作成しました。");
	}

}
