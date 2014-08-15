package euphoria.frame;

import javax.swing.JTabbedPane;

/**
 * 複数のPanelで構成されるメインのタブ
 *
 * @author PenNanban
 *
 */
public class SetTabbed {

	/** Tabbed */
	private JTabbedPane mainTabbed;
	/** What's New タブ */
	private TabbedWhatsNew whatsNew;

	/**
	 * MainTabbedのコンストラクタ。<br>
	 * MainTabbedとMainTabbedに追加するPanelの定義をする。
	 */
	public SetTabbed() {
		// タブの定義
		mainTabbed = new JTabbedPane();
		// What's Newの定義
		whatsNew = new TabbedWhatsNew();
	}

	/**
	 * タブに追加するPanelの設定・タブへの追加を行う。
	 */
	public void readyTabbed() {
		MainFrame.setLookAndFeel(mainTabbed);
		// Panelの設定
		whatsNew.readyPanel();
		// Panelの追加
		mainTabbed.addTab(whatsNew.panelTitle, whatsNew.getPanel());
	}

	/**
	 * JTabbedPaneを返す
	 *
	 * @return Tabbedで使用されているJTabbedPane
	 */
	public JTabbedPane getTabbed() {
		return mainTabbed;
	}

}
