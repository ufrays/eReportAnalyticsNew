package org.sap.era.main;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CaculateUtil {
	private DecimalFormat df;

	/**
	 * Double A divide Double B, the String result be formated as XX%
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static String divideToString(Double divisor, Double dividend) {
		BigDecimal result = divide(divisor, dividend);
		String r = "";
		if (null != result) {
			r = result.multiply(new BigDecimal(100)).toString().replace(".00", "%");
			if (r.equals("0%"))
				r = "";
		}
		return r;
	}

	/**
	 * Double A divide Integer B, the String result be formated as XX%
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static String divideToString(Integer divisor, Integer dividend) {
		BigDecimal result = divide(divisor, dividend);
		String r = "";
		if (null != result) {
			r = result.multiply(new BigDecimal(100)).toString().replace(".00", "%");
			if (r.equals("0%"))
				r = "";
		}

		return r;
	}

	/**
	 * Double A divide Double B
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static BigDecimal divide(Double divisor, Double dividend) {
		if (divisor != null && dividend != null)
			if (isNotZero(divisor) && isNotZero(dividend))
				return new BigDecimal(divisor).divide(new BigDecimal(dividend), 2, BigDecimal.ROUND_HALF_UP);
		return null;
	}

	/**
	 * Double A divide Double B
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static BigDecimal divide(Integer divisor, Integer dividend) {
		if (divisor != null && dividend != null)
			if (isNotZero(divisor) && isNotZero(dividend))
				return new BigDecimal(divisor).divide(new BigDecimal(dividend), 2, BigDecimal.ROUND_HALF_UP);
		return null;
	}

	private static boolean isNotZero(BigDecimal value) {
		if (value.compareTo(BigDecimal.ZERO) != 0)
			return true;
		return false;
	}

	public static boolean isNotZero(Double value) {
		return isNotZero(new BigDecimal(value));
	}

	public static boolean isNotZero(Integer value) {
		return isNotZero(new BigDecimal(value));
	}

	public static void main(String[] arg) {
		CaculateUtil c = new CaculateUtil();
		// System.out.println(c.divideToString(1, 3));
		BigDecimal b = new BigDecimal(6655.3464);
		// System.out.println(c.formateNum( 6, b));
		// System.out.println(c.getNumberByPattern("0.2235", "#N1.3%"));
		System.out.println(c.formateNumber("343.32121", "#N2.2"));

	}

	/**
	 * Format the Percent
	 * 
	 * @param label
	 * @param pattern
	 * @return
	 */
	public static String formateNumber(String label, String pattern) {
		String result = label;
		if (pattern.indexOf("%") != -1) {// ��Ҫ��ʽ��Ϊ�ٷ���
			BigDecimal d = new BigDecimal(label);
			String l = String.valueOf(d.multiply(new BigDecimal(100)));
			result = getNumberByPattern(l, pattern) + "%";
		} else {// ��ٷ����������
			result = getNumberByPattern(label, pattern);
		}
		return result;
	}

	/**
	 * Format the Percent
	 * 
	 * @param label
	 * @param pattern
	 * @return
	 */
	public static String getNumberByPattern(String label, String pattern) {
		String result = label;
		if (pattern.indexOf(".") != -1) {
			int i = pattern.indexOf(".");
			String backNo = pattern.substring(i + 1);
			backNo = backNo.replace("%", "");
			Integer bn = Integer.parseInt(backNo);
			BigDecimal b = new BigDecimal(label);
			result = formateNum(bn, b);
			if (result.indexOf(".") == 0) {
				result = "0" + result;
			}
		}
		return result;
	}

	/**
	 * format the value behind decimal point
	 * 
	 * @param back
	 * @param value
	 * @return
	 */
	public static String formateNum(int back, BigDecimal value) {
		String backNo = "##.";
		for (int i = 0; i < back; i++) {
			backNo = backNo + "0";
		}
		DecimalFormat df = new DecimalFormat(backNo);
		df.format(value);
		return df.format(value);
	}

}
