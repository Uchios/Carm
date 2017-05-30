package Carm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//日付の形式を変換する
public class GpDateConversion {

	public Date date;
	public String year;
	public String month;
	public String day;
	public Date otherDate;
	public String otherYear;
	public String otherMonth;
	public String otherDay;
	SimpleDateFormat sdfY;
	SimpleDateFormat sdfD;
	SimpleDateFormat sdfM;

	public GpDateConversion(Date date) {
		this.date = date;
		sdfY = new SimpleDateFormat("yyyy");
		sdfM = new SimpleDateFormat("MM");
		sdfD = new SimpleDateFormat("dd");
		year = sdfY.format(date);
		month = sdfM.format(date);
		day = sdfD.format(date);
	}

	// コンストラクタ引数の日付からメソッド引数の値だけ月数を足した日付を算出
		public void getOtherDate(int month) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH,month);
			otherDate = cal.getTime();
			otherYear = sdfY.format(otherDate);
			otherMonth = sdfM.format(otherDate);
			otherDay = sdfD.format(otherDate);
		}
		
}
