package excelDao;

import java.util.Comparator;
import java.util.Date;

public class Peo {
	private String name_code;//人员编码
	private Date startDate;//开始打卡时间
	private Date endDate;//最后打卡时间
	public String getName_code() {
		return name_code;
	}
	public void setName_code(String name_code) {
		this.name_code = name_code;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public boolean equals(Object obj) {
		Peo dd=(Peo)obj;
		if(dd.name_code.equals(name_code))
			return true;
		return false;
	}
}
