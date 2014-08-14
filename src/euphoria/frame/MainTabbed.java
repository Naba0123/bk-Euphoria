package euphoria.frame;

import javax.swing.JTabbedPane;

import euphoria.frame.panel.WhatsNew;

public class MainTabbed {

	/** Tabbed */
	private static JTabbedPane mainTabbed;
	/** What's New タブ */
	private static WhatsNew whatsNew;

	/**
	 * タブの設定を行う
	 */
	public static void readyTabbed() {
		// タブの定義
		mainTabbed = new JTabbedPane();

		// Panelの定義
		whatsNew = new WhatsNew();

		// Panelの追加
		addPanel();
	}

	/**
	 * タブにPanelを追加する
	 */
	private static void addPanel() {
		mainTabbed.addTab(whatsNew.panelTitle, whatsNew.getPanel());
	}

	/**
	 * タブ自体を返す
	 * @return
	 */
	public static JTabbedPane getTabbed() {
		return mainTabbed;
	}

}
