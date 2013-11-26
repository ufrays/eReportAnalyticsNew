package org.sap.era.service.validation;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sap.era.persistence.CellData;

import org.sap.era.main.CaculateUtil;

/**
 * @author I073799
 *
 */
public class PatternBasedValidator implements CellValidator {

	public static final Map<String,String> map = new HashMap<String,String>();
	public static final Map<String,String> formats = new HashMap<String,String>(); 
	
	//#-means mandatory,$-means not mandatory.
	static{
		map.put("#D", "[0-9]{4}-((0[1-9])|1[0-2])-(([0-2][0-9])|(3[0-1]))");
		map.put("#S", "\\S+");
		map.put("#N", "\\-?\\d*\\.?\\d*");
		map.put("#N(\\d+)", "\\-?\\d{__1__}");
		map.put("#N(\\d+).(\\d)+", "\\-?\\d{__1__}.\\d{__2__}");
		map.put("#N.(\\d)+", "\\-?\\d{1,}.\\d{__1__}");
		map.put("#N%", "\\-?\\d*\\.?\\d*%");
		map.put("#N(\\d+)%", "\\-?\\d{__1__}%");
		map.put("#N(\\d+).(\\d+)%", "\\-?\\d{__1__}.\\d{__2__}%");
		map.put("#N.(\\d+)%", "\\-?\\d{1,}.\\d{__1__}%");

		map.put("\\$D", "[0-9]{4}-((0[1-9])|1[0-2])-(([0-2][0-9])|(3[0-1]))");
		map.put("\\$S", "\\S+");
		map.put("\\$N", "\\-?\\d*\\.?\\d*");
		map.put("\\$N(\\d+)", "\\-?\\d{__1__}");
		map.put("\\$N(\\d+).(\\d)+", "\\-?\\d{__1__}.\\d{__2__}");
		map.put("\\$N.(\\d)+", "\\-?\\d{1,}.\\d{__1__}");
		map.put("\\$N%", "\\-?\\d*\\.?\\d*%");
		map.put("\\$N(\\d+)%", "\\-?\\d{__1__}%");
		map.put("\\$N(\\d+).(\\d+)%", "\\-?\\d{__1__}.\\d{__2__}%");	
		map.put("\\$N.(\\d+)%", "\\-?\\d{1,}.\\d{__1__}%");
		
		formats.put("#D", "yyyy-mm-dd");
		formats.put("#S", "General");
		formats.put("#N", "General");
		formats.put("#N(\\d+)", "General");
		formats.put("#N(\\d+).(\\d)+", "__1__.__2__");
		formats.put("#N.(\\d)+", "__1__");
		formats.put("#N%", "0%");
		formats.put("#N(\\d+)%", "__1__%");
		formats.put("#N(\\d+).(\\d+)%", "__1__.__2__%");
		formats.put("#N.(\\d+)%", "__1__%");
		
		formats.put("\\$D", "yyyy-mm-dd");
		formats.put("\\$S", "General");
		formats.put("\\$N", "General");
		formats.put("\\$N(\\d+)", "General");
		formats.put("\\$N(\\d+).(\\d)+", "__1__.__2__");
		formats.put("\\$N.(\\d)+", "__1__");
		formats.put("\\$N%", "0%");
		formats.put("\\$N(\\d+)%", "__1__%");
		formats.put("\\$N(\\d+).(\\d+)%", "__1__.__2__%");
		formats.put("\\$N.(\\d+)%", "__1__%");	
	}
	
	/**
	 * Judge if the Cell is for inputting the value.
	 */
	public boolean isInputCell(String label) {
		if(getValidPattern(label.trim()) == null){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param label
	 * @return
	 */
	protected Pattern getValidPattern(String label){
		if(label == null){
			return null;
		}
		for(Map.Entry<String, String> entry:map.entrySet()){
			Pattern pattern = Pattern.compile(entry.getKey());
			Matcher matcher = pattern.matcher(label);
			if(matcher.matches()){
				return pattern;
			}
		}
		return null;
	}

	public boolean isValid(String value, String pattern) {
		Pattern srcPattern = getValidPattern(pattern);
		if(srcPattern == null){
			return false;
		}
		Matcher matcher = srcPattern.matcher(pattern);
		String target = map.get(srcPattern.toString());
		if(matcher.find()){
			int g = matcher.groupCount();
			for(int i=1;i<=g;i++){
				target = target.replace("__"+i+"__", matcher.group(i));
			}
		}
		Pattern targetPattern = Pattern.compile(target);
		Matcher m = targetPattern.matcher(value);
		return m.matches();
	}
	/**
	 * Format the value of the cell.
	 * @param cell
	 * @param pattern
	 * @return
	 */
	public void dateFormate(CellData cell, String pattern) {
		String label = cell.getDataText();
		if(null!=label&&!"".equals(label)){
			cell.setDataText(label.replace("\n", ""));
		}
        if(null!=label&&!"".equals(label)&&pattern.indexOf("N")!=-1){
        	try{
        		cell.setDataText(CaculateUtil.formateNumber(label, pattern));
        	}catch(Exception e){
        	}
        }
	}
	
	
	public String getFormatString(String pattern){
		Pattern p = getValidPattern(pattern);
		if(p == null){
			return "General";
		}
		Matcher matcher = p.matcher(pattern);
		String target = formats.get(p.toString());
		if(matcher.find()){
			int g = matcher.groupCount();
			for(int i=1;i<=g;i++){
				int n = Integer.parseInt(matcher.group(i));
				target = target.replace("__"+i+"__", getZero(n));
			}
		}
		if(pattern.indexOf(".")!=-1&&target.indexOf(".")==-1){
			target = "0."+target;
		}
		return target;
	}
	
	public String getZero(int num){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<num;i++){
			sb.append("0");
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		PatternBasedValidator v = new PatternBasedValidator();
//		System.out.println(v.getFormatString("$N.2%"));
//		System.out.println(v.getFormatString("$N3"));
//		System.out.println(v.getFormatString("$D"));
		//System.out.println(v.getFormatString("$S"));
		DecimalFormat d = new DecimalFormat();
		//System.out.println(v.getValidPattern("#N0.3"));
		
		Pattern p = Pattern.compile("\\d{1,}.\\d{2}");
		Matcher m = p.matcher("224.22");
		System.out.println(m.matches());
		//System.out.println(v.isValid("-32.00","#N.2" ));
	}
	
}
