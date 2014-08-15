package euphoria.config;

/**
 * 設定ファイルを行うための一覧の操作を行う。設定クラスのインスタンスの生成などを行う。
 *
 * @author PenNanban
 *
 */
public class SetConfig {

	/** データベースの設定 */
	public ConfigDB configDB;

	/**
	 * SetConfigクラスのコンストラクタ。必要な設定クラスのインスタンスを生成する
	 */
	public SetConfig() {
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

}
