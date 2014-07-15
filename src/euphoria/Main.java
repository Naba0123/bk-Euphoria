package euphoria;

import euphoria.frame.MainFrame;
import euphoria.xml.SettingFile;

/**
 * Euphoriaのmainメソッドがあるクラス
 *
 * @author PenNanban
 *
 */
public class Main extends MainFrame {

	/** アプリケーション名 */
	public static final String applicationTitle = "Euphoria";

	/** 全般設定を記述した設定ファイル */
	private static SettingFile generalConf;
	/** 全般設定を記述した設定ファイルの名前 */
	private static final String generalConfFile = "generalConf.xml";

	public static void main(String[] args) {

		// MainFrameのセット
		MainFrame.setFrame();
		// MainFrameのタイトルのセット
		MainFrame.setTitle(applicationTitle);

		// generalConfFileの読み込み
		generalConf = new SettingFile();
		generalConf.loadConf(generalConfFile);

		// MainFrameの表示
		MainFrame.show();
	}

}
