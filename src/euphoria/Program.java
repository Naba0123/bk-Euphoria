package euphoria;

import euphoria.config.SetConfig;
import euphoria.frame.MainFrame;

/**
 * プログラムの実行に必要な操作を実行する
 *
 * @author PenNanban
 *
 */
public class Program {

	/** Configクラスのインスタンス */
	private SetConfig config;

	/** MainFrameクラスのインスタンス */
	private MainFrame frame;

	public Program() {
		// Configクラスのインスタンスの定義
		config = new SetConfig();
		// MainFrameクラスのインスタンスの定義
		frame = new MainFrame();
	}

	public void doAction() {
		// 設定ファイルを読み込む
		config.loadConf();

		// フレームに関する準備をする。
		frame.readyFrame();

		// ##### この行からデバッグ #####

		Test test = new Test(config.configDB);
		test.doAction();

		// ##### この行までデバッグ #####

		// MainFrameの表示
		frame.showFrame();
	}

}
