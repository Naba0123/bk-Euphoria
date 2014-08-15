package euphoria.frame;

import javax.swing.JPanel;

public class TabbedWhatsNew extends TabbedPanel {

	/**
	 * WhatsNewのコンストラクタ。
	 */
	TabbedWhatsNew() {
		panelTitle = "What's New";
		panel = new JPanel();
	}

	/**
	 * What's Newパネルの準備をする
	 */
	public void readyPanel() {
		MainFrame.setLookAndFeel(panel);
	}



}
