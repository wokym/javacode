package util;

import java.util.Date;

public interface XLSCallBack<T>{
	/**
	 * 标题头
	 * @return
	 */
	String getTitle();
	/**
	 * 列名
	 * @return
	 */
	String[] getColumnsName();
	/**
	 * 列宽
	 * @return
	 */
	int[] getColumnsWidth();
	/**
	 * 每一列填充的值
	 * @param t
	 * @return
	 */
	Object[] getValues(T t);
	String getstart(Date start);//迟到时间
	String getend(Date end);//早退时间
}