package euphoria.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingFile {

	/** Configファイル */
	private Properties confFile;
	private String confFilename;

	/**
	 * SettingFileのコンストラクタ。<br>
	 * Propertiesクラスのインスタンスconfを定義する。
	 */
	public SettingFile() {
		confFile = new Properties();
	}

	/**
	 * XMLファイルの読み込みを行いPropertiesクラスのインスタンスconfに格納する
	 *
	 * @param filename 読み込むXMLファイル名
	 * @return XMLの読み込みに成功した場合はtrue，失敗した場合はfalse
	 */
	public boolean loadConf(String filename) {
		if (new File(filename).exists()) {
			try {
				confFile.loadFromXML(new FileInputStream(filename));
				confFilename = new String(filename);
				System.out.println("Open " + filename + ".");
				return true;
			} catch (IOException e) {
				System.err.println("Cannot open " + filename + ".");
				e.printStackTrace();
				return false;
			}
		} else {
			System.err.println("Cannot found \"" + filename + "\".");
			return false;
		}
	}

	/**
	 * 与えられたkeyから対応する値を返す
	 *
	 * @param key XMLの要素
	 * @return 対応する値がある場合はその値，ない場合は""(空白)を返す
	 */
	public String getProperty(String key) {
		if (confFile.containsKey(key))
			return confFile.getProperty(key);
		else {
			System.err.println("Key not found: " + key);
			return "";
		}
	}

	/**
	 * 与えられたキーに与えられた値をセットする。<br>
	 * この時点ではまだ保存はされてない。
	 *
	 * @param key キー
	 * @param value キーに代入する値
	 */
	public void setProperty(String key, String value) {
		confFile.setProperty(key, value);
	}

	/**
	 * setPropertyでセットされた値をファイルに書き出す。
	 *
	 * @param comments Configファイルのコメント文。XMLファイル内に記述される。
	 */
	public void storeToXML(String comments) {
		try {
			confFile.storeToXML(new FileOutputStream(confFilename), comments);
		} catch (IOException e) {
			System.err.println("Cannot open " + confFilename + ".");
			e.printStackTrace();
		}
	}

}
