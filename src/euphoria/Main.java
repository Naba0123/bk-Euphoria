package euphoria;

import euphoria.db.DB;
import euphoria.frame.MainFrame;
import euphoria.setting.SettingFile;

/**
 * Euphoriaのmainメソッドがあるクラス
 *
 * @author PenNanban
 *
 */
public class Main extends MainFrame {

	/** アプリケーション名 */
	public static final String applicationTitle = "Euphoria";

	/** 全般設定に関する設定ファイル */
	private static SettingFile generalConf; // 本来はこの設定を必要としているクラスに置く
	/** 全般設定に関する設定ファイルの名前 */
	private static final String generalConfFile = "generalConf.xml";
	/** データベース接続情報に関する設定ファイルの名前 */
	private static final String dbConfigFile = "dbConf.xml";

	public static void main(String[] args) {

		// 設定ファイルを読み込む
		loadConfig();

		// フレームに関する準備をする。
		readyFrame();

		// ##### この行からデバッグ #####

		// ##### この行までデバッグ #####

		// MainFrameの表示
		MainFrame.show();
	}

	public static void loadConfig() {
		// generalConfFileの読み込み
		generalConf = new SettingFile(); // この読み込み作業は本来は必要としているクラスに置く
		generalConf.loadConf(generalConfFile);

		// dbConfigFileの読み込み
		DB.CheckDBConfigFile(dbConfigFile);

	}

	public static void readyFrame() {
		// MainFrameのセット
		MainFrame.setFrame();
		// MainFrameのタイトルのセット
		MainFrame.setTitle(applicationTitle);
	}

}
