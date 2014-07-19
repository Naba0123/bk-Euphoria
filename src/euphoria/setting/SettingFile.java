package euphoria.setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingFile {

	/** Configファイル */
	private Properties confFile;
	/** Configファイルの名前 */
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
		confFilename = new String(filename);
		if (new File(filename).exists()) {
			try {
				confFile.loadFromXML(new FileInputStream(filename));
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
	 * @return 書き込みに成功すればtrue，失敗するとfalse。
	 */
	public boolean storeToXML(String comments) {
		try {
			confFile.storeToXML(new FileOutputStream(confFilename), comments);
		} catch (IOException e) {
			System.err.println("Cannot open " + confFilename + ".");
			e.printStackTrace();
			return false;
		}
		System.out.println("設定ファイル\"" + confFilename + "\"に設定を書き込みました。");
		return true;
	}

	/**
	 * 設定ファイルのファイル名を返す
	 *
	 * @return 設定ファイル名
	 */
	public String getConfigFileName() {
		return confFilename;
	}

	/**
	 * Configファイルが存在しない場合、このメソッドを呼び出せばconfFilenameの名前で新しいファイルを生成することができる。<br>
	 * 既にファイルが存在している場合は生成しない。
	 *
	 * @return ファイルの生成に成功すればtrue，失敗すればfalse。
	 */
	public boolean createNewFile() {
		File file = new File(confFilename);
		if (file.exists()) {
			System.err.println("ファイル\"" + confFilename + "\"は既に存在しています。");
			return false;
		} else {
			try {
				file.createNewFile();
				System.out.println("ファイル\"" + confFilename + "\"が生成されました。");
				return true;
			} catch (IOException e) {
				System.err.println("ファイル\"" + confFilename + "\"が生成できませんでした。");
				e.printStackTrace();
				return false;
			}
		}
	}

}
