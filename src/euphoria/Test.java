package euphoria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import euphoria.config.ConfigDB;
import euphoria.db.DB;

public class Test {

	private DB db;
	private ConfigDB configDB;

	public Test(ConfigDB config) {
		configDB = config;
		db = new DB();
	}

	public void doAction() {
		// Databaseとの接続
		db.connectDB(configDB);

		// Queryの取得
		System.out.print("SQL文の入力をしてください ＞ ");
		String query = inputFromKeyboard();
		ResultSet rs = db.executeSQLQuery(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						System.out.print(rs.getString(i) + " ");
					}
					System.out.println("\n");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Databaseの切断
		db.closeDB();
	}

	/**
	 * テスト用にStringを入力する必要がある時に使えばいいと思うよ
	 *
	 * @return キーボードから入力された文字列。入力に失敗するとnullを返す。
	 */
	private String inputFromKeyboard() {
		String str = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		try {
			str = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

}
