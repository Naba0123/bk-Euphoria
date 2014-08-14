package euphoria.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFile {

	/** Configファイル */
	private Properties config;
	/** Configファイルの名前 */
	private String configFilename;

	/**
	 * SettingFileのコンストラクタ。<br>
	 * Propertiesクラスのインスタンスconfigを定義する。
	 */
	public ConfigFile() {
		config = new Properties();
	}

	/**
	 * XMLファイルの読み込みを行いPropertiesクラスのインスタンスconfに格納する
	 *
	 * @param filename 読み込むXMLファイル名
	 * @return XMLの読み込みに成功した場合はtrue，失敗した場合はfalse
	 */
	public boolean loadConf(String filename) {
		configFilename = new String(filename);
		if (new File(filename).exists()) {
			try {
				config.loadFromXML(new FileInputStream(filename));
				System.out.println("設定ファイル\"" + filename + "\"を開きました。");
				return true;
			} catch (IOException e) {
				System.err.println("設定ファイル\"" + filename + "\"が開けません。");
				e.printStackTrace();
				return false;
			}
		} else {
			System.err.println("設定ファイル\"" + filename + "\"が見つかりません。");
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
		if (config.containsKey(key))
			return config.getProperty(key);
		else {
			System.err.println("キー\"" + key + "\"が見つかりません。");
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
		config.setProperty(key, value);
	}

	/**
	 * setPropertyでセットされた値をファイルに書き出す。
	 *
	 * @param comments Configファイルのコメント文。XMLファイル内に記述される。
	 * @return 書き込みに成功すればtrue，失敗するとfalse。
	 */
	public boolean storeToXML(String comments) {
		try {
			config.storeToXML(new FileOutputStream(configFilename), comments);
		} catch (IOException e) {
			System.err.println("設定ファイル\"" + configFilename + "\"が開けません。");
			e.printStackTrace();
			return false;
		}
		System.out.println("設定ファイル\"" + configFilename + "\"に設定を書き込みました。");
		return true;
	}

	/**
	 * 設定ファイルのファイル名を返す
	 *
	 * @return 設定ファイル名
	 */
	public String getConfigFilename() {
		return configFilename;
	}

	/**
	 * Configファイルが存在しない場合、このメソッドを呼び出せばconfFilenameの名前で新しいファイルを生成することができる。<br>
	 * 既にファイルが存在している場合は生成しない。
	 *
	 * @return ファイルの生成に成功すればtrue，失敗すればfalse。
	 */
	public boolean createNewFile() {
		File file = new File(configFilename);
		if (file.exists()) {
			System.err.println("ファイル\"" + configFilename + "\"は既に存在しています。");
			return false;
		} else {
			try {
				file.createNewFile();
				System.out.println("ファイル\"" + configFilename + "\"が生成されました。");
				return true;
			} catch (IOException e) {
				System.err.println("ファイル\"" + configFilename + "\"が生成できませんでした。");
				e.printStackTrace();
				return false;
			}
		}
	}

}
