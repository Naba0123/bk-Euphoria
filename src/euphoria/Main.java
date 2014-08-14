package euphoria;

import euphoria.config.Config;
import euphoria.frame.MainFrame;

/**
 * Euphoriaのmainメソッドがあるクラス
 *
 * @author PenNanban
 *
 */
public class Main {

	public static void main(String[] args) {

		// 設定ファイルを読み込む
		Config.loadConf();

		// フレームに関する準備をする。
		MainFrame.readyFrame();

		// ##### この行からデバッグ #####

		new Test();

		// ##### この行までデバッグ #####

		// MainFrameの表示
		MainFrame.showFrame();
	}

}
