package excelDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateJob {
	private Date newDate;//天
	private List<Peo> lsitpeo;//人员信息
	public DateJob(){
		lsitpeo=new ArrayList<Peo>();
	}
	public Date getNewDate() {
		return newDate;
	}
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	public List<Peo> getLsitpeo() {
		return lsitpeo;
	}
	public void setLsitpeo(List<Peo> lsitpeo) {
		this.lsitpeo = lsitpeo;
	}
	@Override
	public boolean equals(Object obj) {
		DateJob dd=(DateJob)obj;
		if(dd.getNewDate().equals(newDate))
			return true;
		return false;
	}
}
