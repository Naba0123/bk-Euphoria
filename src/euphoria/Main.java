package euphoria;

import euphoria.frame.MainFrame;
import euphoria.setting.Config;

/**
 * Euphoriaのmainメソッドがあるクラス
 *
 * @author PenNanban
 *
 */
public class Main extends MainFrame {

	public static void main(String[] args) {

		// 設定ファイルを読み込む
		Config.loadConf();

		// フレームに関する準備をする。
		MainFrame.setFrame();

		// ##### この行からデバッグ #####

		//		Test.Do();

		// ##### この行までデバッグ #####

		// MainFrameの表示
		MainFrame.show();
	}

}
