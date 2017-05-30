package Carm;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class GpGetPaymentInfo {

	public Date getPaymentDate(Date useDate, int cardId, DaoCarmPersonal dcp) {

		EntityCard ec = dcp.getCardEntityByCardId(cardId);
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(useDate);

		if (ec.getCutoffDay() == 0
				|| calDate.get(Calendar.DAY_OF_MONTH) <= ec.getCutoffDay()) {
			// 締め日が利用日以降の場合
			calDate.add(Calendar.MONTH, ec.getPaymentMonth());
		} else {
			// 締め日が利用日以前の場合
			calDate.add(Calendar.MONTH, ec.getPaymentMonth() + 1);
		}

		if (ec.getPaymentDay() != 0) {
			// 支払日が月末ではない場合
			calDate.set(Calendar.DAY_OF_MONTH, ec.getPaymentDay());
		} else {
			// 支払日が月末の場合
			calDate.add(Calendar.MONTH, 1);
			calDate.set(Calendar.DAY_OF_MONTH, 1);
			calDate.add(Calendar.DAY_OF_MONTH, -1);
		}

		// カードに紐づく口座情報を取得
		EntityAccount ea = dcp.getAccountEntityByAccountId(ec.getAccountId());

		if (ea.getRegHolidayFlg() == 1) {
			// 口座の定休日フラグが1であれば、支払日が土日の場合に翌営業日に振り替える処理を行う
			if (calDate.get(Calendar.DAY_OF_WEEK) == 1) {
				// 支払日が日曜日の場合、翌日に引き落とし
				calDate.add(Calendar.DAY_OF_MONTH, 1);
			} else if (calDate.get(Calendar.DAY_OF_WEEK) == 7) {
				// 支払日が土曜日の場合、2日後に引き落とし
				calDate.add(Calendar.DAY_OF_MONTH, 2);
			}
		}

		// sql.dateに変換するため、Calendar型を標準化
		calDate.set(Calendar.HOUR_OF_DAY, 0);
		calDate.set(Calendar.MINUTE, 0);
		calDate.set(Calendar.SECOND, 0);
		calDate.set(Calendar.MILLISECOND, 0);
		return new java.sql.Date(calDate.getTimeInMillis());
	}

}