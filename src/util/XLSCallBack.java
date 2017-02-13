package util;

import java.util.Date;

public interface XLSCallBack<T>{
	/**
	 * ����ͷ
	 * @return
	 */
	String getTitle();
	/**
	 * ����
	 * @return
	 */
	String[] getColumnsName();
	/**
	 * �п�
	 * @return
	 */
	int[] getColumnsWidth();
	/**
	 * ÿһ������ֵ
	 * @param t
	 * @return
	 */
	Object[] getValues(T t);
	String getstart(Date start);//�ٵ�ʱ��
	String getend(Date end);//����ʱ��
}