package Carm;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import sun.misc.Signal;

public class GpRegCalculator {

	DaoCarmAdmin dca = null;
	DaoCarmPersonal dcp = null;
	Integer userId = null;
	Calendar today = null;
	Calendar cal = null;
	Calendar trcCal = null;
	Calendar cal2 = null;
	List<EntityRegPayment> erpList = null; // 定期支払の全レコードを格納
	EntityLoginHistory elh = null; // 最新のログインレコードを格納

	public GpRegCalculator(DaoCarmAdmin dca, DaoCarmPersonal dcp, int userId) {
		this.dca = dca;
		this.dcp = dcp;
		this.userId = userId;
		this.today = DateUtils.truncate(Calendar.getInstance(), Calendar.DATE);
		this.cal = Calendar.getInstance();
		this.cal2 = Calendar.getInstance();
		this.erpList = dcp.getRegPaymentEntity();
		this.elh = dca.getLoginHistoryMaxRecord(userId);
	}

	// 本日1回目のログインであればtrueを返す
	public boolean checkNewLogin() {
		cal.setTime(elh.getLoginDate());
		trcCal = DateUtils.truncate(cal, Calendar.DATE);
		return (trcCal.compareTo(today) != 0);
	}

	// ある日付を与えると、該当する定期支払いを全て実行し更新行数を返す
	public int executeReg(Date date) {
		int count = 0;
		GpTagModule gtm = new GpTagModule();

		// 定期支払レコードを一つずつ取り出して、引数の日付が該当するか判定する
		for (int i = 0; i < erpList.size(); i++) {
			EntityRegPayment erp = erpList.get(i);
			boolean pmJudge = true;
			cal2.setTime(date);

			// 支払い周期が年単位の場合、「月」部分が一致するか判定
			if (erp.getCycleUnit() == 1) {
				pmJudge = (erp.getRegMonth() - 1 == (cal2.get(Calendar.MONTH)));
			}

			// 「日」部分が一致するか判定
			if (pmJudge) {
				if (erp.getRegDay() == 0) {
					cal2.add(Calendar.DAY_OF_MONTH, 1);
					pmJudge = (cal2.get(Calendar.DAY_OF_MONTH) == 1);
				} else {
					pmJudge = (cal2.get(Calendar.DAY_OF_MONTH) == erp
							.getRegDay());
				}
			}

			// 判定をクリアした場合、利用明細レコードを登録する
			if (pmJudge) {
				Date paymentDate = new GpGetPaymentInfo().getPaymentDate(date,
						erp.getCardId(), dcp);
				int result = dcp.setUses(date, erp.getRegPayment(),
						erp.getCardId(), erp.getSummary(), paymentDate);

				if (result == 1) {
					List<String> tags = gtm.getTag(erp.getSummary(), '#');
					for (int j = 0; j < tags.size(); j++) {
						dcp.setTags(dcp.getUseMaxId(), tags.get(j));
					}
				}
				count++;
			}
		}
		return count;
	}

	// 前回ログインの翌日から本日までの全日付を対象に判定・更新を行い、更新行数を返す
	public int executeReg() {
		int count = 0;
		Date indexDate = elh.getLoginDate();
		cal.setTime(indexDate);
		trcCal = DateUtils.truncate(cal, Calendar.DATE);
		while (trcCal.compareTo(today) < 0) {
			trcCal.add(Calendar.DAY_OF_MONTH, 1);
			count += executeReg(new java.sql.Date(trcCal.getTimeInMillis()));
		}
		return count;
	}

}
