package euphoria.frame;

import java.awt.BorderLayout;
import java.awt.Component;
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

	/** MainFrameのタイトル */
	private String mainFrameTitle = "Euphoria";

	/** MainFrameのJFrame */
	private JFrame frame;
	/** MainFrameの幅 */
	private final int width = 960;
	/** MainFrameの高さ */
	private final int height = 540;
	/** MainFrameのウィンドウサイズ変更の可否 */
	private final boolean canResize = false;

	/** MainFrameのJTabbed */
	private SetTabbed tabbed;

	/**
	 * MainFrameのコンストラクタ。
	 */
	public MainFrame() {
		// frameの定義
		frame = new JFrame();
		// tabbedの定義
		tabbed = new SetTabbed();
	}

	/**
	 * MainFrameのタイトルを変更する
	 *
	 * @param title 変更後のタイトル
	 */
	public void setFrameTitle(String title) {
		frame.setTitle(title);
	}

	/**
	 * MainFrameを可視状態にする。
	 */
	public void showFrame() {
		frame.setVisible(true);
	}

	/**
	 * MainFrameを不可視状態にする。
	 */
	public void hideFrame() {
		frame.setVisible(false);
	}

	/**
	 * プログラム実行に必要なFrameの設定を行う。<br>
	 * このメソッドを実行すれば可視以外の準備が整う。
	 */
	public void readyFrame() {
		// フレームの設定

		// MainFrameのタイトルの設定
		frame.setTitle(mainFrameTitle);
		// フレーム初期サイズの設定
		frame.setSize(new Dimension(width, height));
		// フレームのサイズを変更できないようにする
		frame.setResizable(canResize);
		// フレーム終了処理の設定
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// フレームの表示位置の設定
		frame.setLocationRelativeTo(null);
		// フレームのUIの設定
		setLookAndFeel(frame);

		// タブの設定

		// 全体のタブ
		tabbed.readyTabbed();
		// タブをフレームに追加
		frame.getContentPane().add(tabbed.getTabbed(), BorderLayout.CENTER);
	}

	/**
	 * FrameやTabbedやPanelのUIをWindowsライクに変更する
	 * @param c UIを変更するComponent
	 */
	public static void setLookAndFeel(Component c) {
		String lafClassName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lafClassName);
			SwingUtilities.updateComponentTreeUI(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
