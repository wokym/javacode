package test;

import java.util.ArrayList;
import java.util.List;

public class PPG {
	static int size=0;
	static int listp=0;
	static int listg=0;
	static int p_h=2;
	static int g_h=5;
	public static void main(String[] args) {
		int he=5;
		getH(he);
		System.out.println("喝了="+size);
		System.out.println("瓶子剩下="+listp);
		System.out.println("盖子剩下="+listg);
	}
	
	public static int getH(int p){
		if(p==0) return 0;
		for(int i=0;i<p;i++){
			size++;
			listp++;
			listg++;
		}
		int re=listg/g_h+listp/p_h;
		listp=listp%p_h;
		listg=listg%g_h;
		return getH(re);
	}
}
