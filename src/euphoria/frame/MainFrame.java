package euphoria.frame;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Euphoriaのメインフレームクラス
 *
 * @author PenNanban
 */
public class MainFrame {

	// MainFrameの定義

	/** MainFrameのJFrame */
	private static JFrame frame;
	/** MainFrameのタイトル */
	private static String title = new String("Euphoria");
	/** MainFrameの幅 */
	private static final int width = 960;
	/** MainFrameの高さ */
	private static final int height = 540;
	/** MainFrameのウィンドウサイズ変更の可否 */
	private static final boolean canResize = false;

	/**
	 * MainFrameのコンストラクタ。実質機能しないわね(´・ω・｀)
	 */
	public MainFrame() {
	}

	/**
	 * MainFrameのタイトルを変更する
	 *
	 * @param title
	 *            変更後のタイトル
	 */
	public static void setTitle(String title) {
		frame.setTitle(title);
	}

	/**
	 * MainFrameを可視状態にする。
	 */
	public static void show() {
		frame.setVisible(true);
	}

	/**
	 * MainFrameを不可視状態にする。
	 */
	public static void hide() {
		frame.setVisible(false);
	}

	/**
	 * MainFrameの初期設定を行う。 このメソッドはサブクラスからしかアクセスすることはできない。
	 */
	protected static void setFrame() {
		// frameの定義とタイトルの設定
		frame = new JFrame(title);
		// フレーム初期サイズの設定
		frame.setSize(new Dimension(width, height));
		// フレームのサイズを変更できないようにする
		frame.setResizable(canResize);
		// フレーム終了処理の設定
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// フレームの表示位置の設定
		frame.setLocationRelativeTo(null);
		// フレームのUIの設定
		String lafClassName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lafClassName);
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
