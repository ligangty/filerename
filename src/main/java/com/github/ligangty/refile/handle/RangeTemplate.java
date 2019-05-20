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
 *
 *
 * @author ligangty@github.com
 * @date Apr 6, 2009
 */
public class RangeTemplate extends AbstractTemplate {

    /**
     * regexp pattern for this match
     */
    private static final String PATTERN = "(<\\d+-{0,1}\\d*>)";

    public String getFileNameFromTemplate(String fileName, String templateStr)
            throws TemplateException {
        String fileNameWithoutPostFix = FileUtil
                .getFileNameWithoutPostFix(fileName);
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(templateStr);
        StringBuffer sb = new StringBuffer(256);
        while (m.find()) {
            String replacePart = getRealTxtWithTemplateFromFileName(
                    fileNameWithoutPostFix, m.group(1));
            m.appendReplacement(sb, replacePart);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    String getRealTxtWithTemplateFromFileName(String fileName,
            String subTemp) throws TemplateException {
        if (!subTemp.contains("<")) {
            throw new TemplateException("this template do not conain a range template string.", this);
        }
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(subTemp);
        int[] range = new int[2];
        int i = 0;
        while (m.find() && i < 2) {
            range[i] = Integer.parseInt(m.group(1));
            i++;
        }
        if (range[1] == 0) {
            return fileName.charAt(range[0] - 1) + "";
        } else if (range[1] > fileName.length()) {
            return fileName.substring(range[0] - 1);
        } else {
            return fileName.substring(range[0] - 1, range[1]);
        }
    }
}
