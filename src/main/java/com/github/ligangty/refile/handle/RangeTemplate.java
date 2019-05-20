package com.github.ligangty.refile.handle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.ligangty.refile.util.FileUtil;

/**
 * This Template is used to change the file name string using the range
 * template. when the template string contains the "<X-Y>", it fetch the
 * characters from the index X to the index Y, and put these characters in the
 * position of the templalte where the "<X-Y>" appears. e.g: when the template
 * string is "abcd<5-6>" and the original file name is "mytestfile", the new
 * file name will be "abcdst".
 * @author ligangty@github.com
 * @date Apr 6, 2009
 */
public class RangeTemplate extends AbstractTemplate {

	/**
	 * regexp pattern for this match
	 */
	private static final String PATTERN = "(<\\d+-{0,1}\\d*>)|(<-\\d+>)";

	public String getFileNameFromTemplate(String fileName, String templateStr) throws TemplateException {
		String fileNameWithoutPostFix = FileUtil.getFileNameWithoutPostFix(fileName);
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(templateStr);
		StringBuffer sb = new StringBuffer(256);
		while (m.find()) {
			String template = m.group(1) != null ? m.group(1) : m.group(2);
			String replacePart = getRealTxtWithTemplateFromFileName(fileNameWithoutPostFix, template);
			m.appendReplacement(sb, replacePart);
		}
		m.appendTail(sb);
		return sb.toString();
	}

	String getRealTxtWithTemplateFromFileName(String fileName, String subTemp) throws TemplateException {
		String realRange = subTemp.substring(0, subTemp.length() - 1);
		realRange = realRange.substring(1);
		int start = 0;
		int end = fileName.length();
		boolean oneDigit = false;
		if (realRange.contains("-")) {
			String[] indexes = realRange.split("-");
			if (indexes[0] != null && !"".equals(indexes[0].trim())) {
				start = Integer.parseInt(indexes[0]) - 1;
			}

			if (indexes.length == 2 && !"".equals(indexes[1].trim())) {
				end = Integer.parseInt(indexes[1]);
			}
		} else {
			start = Integer.parseInt(realRange)-1;
			oneDigit = true;
		}

		try {
			if (oneDigit) {
				return fileName.charAt(start) + "";
			} else if (end > fileName.length()) {
				return fileName.substring(start);
			} else {
				return fileName.substring(start, end);
			}
		} catch (StringIndexOutOfBoundsException e) {
			final String error = String.format("Error in parsing range, file name: %s", fileName);
			final TemplateException ex = new TemplateException(error, this, e);
			ex.printStackTrace();
			throw ex;
		}
	}
}
