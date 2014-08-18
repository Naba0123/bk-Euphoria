package euphoria.frame;

import javax.swing.JPanel;

/**
 * タブ内に配置するパネルのクラス。このクラスを継承して実際のパネルを作成する。
 *
 * @author PenNanban
 *
 */
public class TabbedPanel {

	/** panel */
	protected JPanel panel;
	/** panelのタイトル */
	protected String panelTitle;

	/**
	 * Panelの定義とデフォルトTitleの設定
	 */
	public TabbedPanel() {
		panel = new JPanel();
		panelTitle = "No Panel Title";
	}

	/**
	 * Panelを返す
	 *
	 * @return Panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * PanelのTitleを返す
	 *
	 * @return PanelのTitle
	 */
	public String getPanelTitle() {
		return panelTitle;
	}

	/**
	 * PanelのTitleを設定する
	 *
	 * @param title
	 */
	public void setPanelTitle(String title) {
		if (!title.isEmpty()) {
			panelTitle = title;
		}
	}

	/**
	 * TabbedPanelの準備をする
	 */
	public void setPanel() {
		// UIの変更
		MainFrame.setLookAndFeel(panel);
		// コンテンツの定義
		defineContents();
		// コンテンツの追加
		addContents();
	}

	protected void defineContents() {

	}

	protected void addContents() {
	}

}
