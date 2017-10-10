package com.reusecompany.oslc.utils;

/**
 * This is a helper class for printing complex objects.
 */
public class ToStringHelper {

	public static String arrayToString(Object[] objs, String separator) {
			if (objs == null) {
				return "[null]";
			}
			
			StringBuffer result = new StringBuffer();
			if (objs.length > 0) {
				result.append(objs[0] != null?objs[0].toString():objs[0]);
				for (int i=1; i<objs.length; i++) {
					result.append(separator);
					result.append(objs[i] != null?objs[i].toString():objs[i]);
				}
			}        
			return result.toString();
		}
		
		public static String arrayToString(Object[] objs) {
			final String separator =", ";
			return arrayToString(objs, separator);
		}
		
	}

