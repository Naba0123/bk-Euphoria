package euphoria.frame;

import javax.swing.JTabbedPane;

public class Tabbed {

	/** Tabbed */
	private JTabbedPane mainTabbed;
	/** What's New タブ */
	private TabbedWhatsNew whatsNew;

	/**
	 * タブの設定を行う
	 */
	public void readyTabbed() {
		// タブの定義
		mainTabbed = new JTabbedPane();
		// Panelの定義
		whatsNew = new TabbedWhatsNew();
		// Panelの追加
		addPanel();
	}

	/**
	 * タブにPanelを追加する
	 */
	private void addPanel() {
		mainTabbed.addTab(whatsNew.panelTitle, whatsNew.getPanel());
	}

	/**
	 * タブ自体を返す
	 * @return
	 */
	public JTabbedPane getTabbed() {
		return mainTabbed;
	}

}
