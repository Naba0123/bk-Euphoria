package euphoria.frame;

import java.awt.BorderLayout;
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
	/** MainFramenoJTabbed */
	private Tabbed tabbed;
	/** MainFrameの幅 */
	private final int width = 960;
	/** MainFrameの高さ */
	private final int height = 540;
	/** MainFrameのウィンドウサイズ変更の可否 */
	private final boolean canResize = false;

	/**
	 * MainFrameのコンストラクタ。
	 */
	public MainFrame() {
		// frameの定義
		frame = new JFrame();
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
	 * プログラム実行に必要なFrameの設定を行う。
	 */
	public void readyFrame() {
		// MainFrameの初期設定
		setFrameDefault();
		// MainFrameのコンテンツの設定
		setFrameContents();
	}

	/**
	 * MainFrameの初期設定を行う。
	 */
	private void setFrameDefault() {
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
		String lafClassName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lafClassName);
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Frameのコンテンツの設定を行う
	 */
	private void setFrameContents() {
		// 全体のタブ
		tabbed.readyTabbed();
		// タブをフレームに追加
		frame.getContentPane().add(tabbed.getTabbed(), BorderLayout.CENTER);
	}

}
