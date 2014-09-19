package com.dharmik.myhell;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.net.ParseException;

public class DateHelper {
	static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

			public static Date GetUTCdatetimeAsDate()
			{
			    //note: doesn't check for null
			    return StringDateToDate(GetUTCdatetimeAsString());
			}

			public static String GetUTCdatetimeAsString()
			{
			    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
			    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			    final String utcTime = sdf.format(new Date());

			    return utcTime;
			}
			
			public static Date GetCurrentUTCDate()
			{
				 final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
				    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				    return new Date();
			}

			public static Date StringDateToDate(String StrDate)
			{
			    Date dateToReturn = null;
			    SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);

			    try
			    {
			        try {
						dateToReturn = (Date)dateFormat.parse(StrDate);
					} catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			    catch (ParseException e)
			    {
			        e.printStackTrace();
			    }

			    return dateToReturn;
			}

}
