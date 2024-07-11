package com.comcast.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility
{
	public int getRandomNumber()
	{
		Random ran=new Random();
		int ranNum=ran.nextInt();
		return ranNum;
	}
	public String getSystemDateYYYYMMDD()
	{
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(d);
		return date;
	}
	public String getREquiredDate(int days)
	{
		Date d=new Date();
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");		
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, days);
		String requiredDate = sim.format(c.getTime());	
		return requiredDate;
	}
}
