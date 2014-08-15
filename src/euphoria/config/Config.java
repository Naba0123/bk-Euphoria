package euphoria.config;

/**
 * 設定ファイルなどを扱うクラス
 *
 * @author PenNanban
 *
 */
public class Config {

	/** 設定ファイル */
	protected ConfigFile config;

	/** 設定ファイルの修復が必要かどうかを判別するフラグ */
	protected boolean isNeededFix;

	/**
	 * キーの読み込みを行う。<br>
	 * 読み込めない（キーが無い，またはキーの値が""（空白））場合はキーを修復するフラグを立てる。
	 *
	 * @param key 読み込むキー
	 * @param value 読み込む値のデフォルト値
	 * @return 値が読み込めたら値を，読み込めない場合はデフォルト値を返す
	 */
	protected String readValue(String key, String value) {
		if (config.getProperty(key).equals("")) {
			// キーが読み込めない場合は修復するフラグを立てる
			config.setProperty(key, value);
			isNeededFix = true;
			System.out.println("キー\"" + key + "\"を修正します。");
			return value;
		} else {
			return config.getProperty(key);
		}
	}

	/**
	 * readValueの後処理を行う。<br>
	 * 設定ファイルの修復が必要な場合は修復を行う。
	 */
	protected void endReadValue() {
		if (isNeededFix) {
			config.storeToXML("自動修復された設定ファイル");
			System.out.println("設定ファイルの一部を修復しました。");
		}
		System.out.println("データベース用設定ファイル\"" + config.getConfigFilename() + "\"の読み込みが完了しました。");
	}

	/**
	 * 設定ファイルが存在しない場合に新たにファイルを作成する。
	 *
	 * @param comment 設定ファイルに記述されるコメント
	 */
	protected void createConfigFile(String comment) {
		config.createNewFile();
		config.storeToXML(comment);
		System.out.println("データベース用設定ファイル\"" + config.getConfigFilename() + "\"の読み込みに失敗したため，新しい設定ファイルを作成しました。");
	}

}
