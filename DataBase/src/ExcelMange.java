/**
 * 
 */

/**
 * @author Administrator
 *
 */
//读取Excel的类
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelMange {
	public static void excelToDB(String addr) {
		File file = new File(addr);
		MysqlConn conn = new MysqlConn();
		try {
			int k, j, i, row;
			String url;
			Workbook book = Workbook.getWorkbook(file);
			Connection connect = conn.connMysql();
			Statement statement = connect.createStatement();
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			row = sheet.getRows();
			for (j = 0; j < row; j++) {
				Cell[] cell = sheet.getRow(j);
				url = patterComile(cell[0].getContents());
				String sql = "INSERT INTO history(Url,RecordCount,ViewTime)VALUES("
						+ "'"
						+ url
						+ "','"
						+ cell[3].getContents()
						+ "','"
						+ cell[2].getContents() + "')";
				int rs = statement.executeUpdate(sql);
			}
			book.close();
			System.out.println("finished!");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static String patterComile(String url) {
		String regx = "//\\w+\\.\\w+\\.*\\w*/";
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(url);
		String result = "";
		if (matcher.find()) {
			result = matcher.group();
			result = result.replace("/", "");
			result.trim();
		}
		return result;

	}

	public static void main(String[] args) {
		// String addr = "C:\\Users\\Administrator\\Desktop\\history.xls";
		// excelToDB(addr);

	}

}
