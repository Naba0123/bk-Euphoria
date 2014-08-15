package euphoria.frame;

import javax.swing.JPanel;

public class TabbedPanel {

	/** panel */
	protected JPanel panel;
	/** panelのタイトル */
	protected String panelTitle = "No Panel Title";

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
		panelTitle = title;
	}

}
