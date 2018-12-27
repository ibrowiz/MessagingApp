package org.calminfotech.utilities;

import java.util.Calendar;
import java.util.Random;

public class AccountNoGen {
	
	Calendar calendar = Calendar.getInstance();
	public String genAccNo() {
		String strNum = "";
		
		try {
			Random randomNumber = new Random();
			int i = randomNumber.nextInt(10);
			int j = randomNumber.nextInt(100);
			int k = randomNumber.nextInt(100);
			int l = randomNumber.nextInt(40);
			int m = randomNumber.nextInt(10);
			strNum ="100"+(calendar.get(Calendar.YEAR))+ i + j + m + k + l;
			
			System.out.println( strNum);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strNum;

}
	public static void main(String[] gen) {
		new AccountNoGen().genAccNo();
		//new AutoGen().genCorpNum();
	}
}
