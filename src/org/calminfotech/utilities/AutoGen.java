package org.calminfotech.utilities;
import java.util.Calendar;
import java.util.Random;

public class AutoGen {
	Calendar calendar = Calendar.getInstance();
	public String genPass() {
		String strNum = "";
		
		try {
			Random randomNumber = new Random();
			int i = randomNumber.nextInt(10);
			int j = randomNumber.nextInt(100);
			int k = randomNumber.nextInt(100);
			int l = randomNumber.nextInt(40);
			int m = randomNumber.nextInt(10);
			strNum ="PASS"+ i + j + m + k + l;
			
			System.out.println( strNum);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strNum;
	}
	

	public String genCorpNum() {
		String strNum = "";
		try {
			Random randomNumber = new Random();
			int i = randomNumber.nextInt(10);
			int j = randomNumber.nextInt(100);
			int k = randomNumber.nextInt(100);
			int l = randomNumber.nextInt(40);
			int m = randomNumber.nextInt(10);
			strNum ="CORP"+(calendar.get(Calendar.YEAR))+"/"+ i + "" + m +"/"+ k + "" + l;
			
			System.out.println( strNum);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strNum;
	}
	public static void main(String[] gen) {
		new AutoGen().genPass();
		new AutoGen().genCorpNum();
	}
	

}
