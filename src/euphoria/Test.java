package euphoria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import euphoria.config.DB;

public class Test {

	public Test() {
		Do();
	}

	public void Do() {

		// Databaseとの接続
		DB.connectDB();

		// Queryの取得
		System.out.print("SQL文の入力をしてください ＞ ");
		String query = inputFromKeyboard();
		ResultSet rs = DB.executeSQLQuery(query);
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
		DB.closeDB();
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
