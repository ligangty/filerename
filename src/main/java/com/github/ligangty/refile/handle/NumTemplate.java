package com.github.ligangty.refile.handle;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.ligangty.refile.util.FileRenameUtil;

/**
 * This Template is used to change the file name string using the number
 * template. when the template string contains the "?", it will replaced by the
 * digit character like "1,2,3...." which started by the specified number.
 * example: when the template string is ""
 *
 * Note: when the template string contains "<X-Y>", please use the RangeTemplate
 * first to replace it first.
 *
 * @author Jacky Li
 * @date Apr 6, 2009
 */
public class NumTemplate extends AbstractTemplate {

    private static final Logger log = Logger.getLogger(FileRenameUtil.class.getName());
    /**
     * number which will replace the "?" in the template, may be added with
     * zeroes
     */
    private int changeTo;
    /**
     * the limitation of the number digit charaters, which means the characters
     * of the number must not be more than this limitation, now are 16
     */
    protected static final int MAX_NUM_LIMIT_CHARS = 16;
    /**
     * regexp pattern for this match
     */
    private static final String PATTERN = "(\\?+)";

    /**
     * change the file name using the this template, replace the "?" with number
     * character which specified in "changeTo". Note that when the "?" is a
     * continuous string like "????", it will be changed to "0001" when the
     * "changeTo" is one.
     *
     * @param fileName - file name which does not include the file path
     * @param templateStr - the template string used to change this file name
     * @param changeTo - used to replace the file name which match the "?" in
     * template string
     * @return changed file name
     * @throws my.li.runan.refile.handle.TemplateException
     */
    public String getFileNameFromTemplate(String templateStr, int changeTo) throws TemplateException {
        setChangeTo(changeTo);
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(templateStr);
        StringBuffer sb = new StringBuffer(256);
        while (m.find()) {
            String subTemplate = m.group(1);
            if (subTemplate.indexOf("?") < 0) {
                throw new TemplateException("this template does not contain \"?\"", this);
            }
            String replacePart = addZerosByNumLimit(subTemplate.length());
            m.appendReplacement(sb, replacePart);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * when the "?" in template string are more than one and be continuous like
     * "????", add zeroes when the number is less than it. e.g: when contains
     * "????", and now the changeTo is 1, it will return "0001"; or the changeTo
     * is 12, it will return "0012"
     *
     * @param numLimit - limit the numbers of continuous number characters. e.g:
     * when "????", this is 4;
     * @return number characters which will replace the match part
     * @throws my.li.runan.refile.handle.TemplateException
     */
    protected String addZerosByNumLimit(int numLimit) throws TemplateException {
        if (numLimit > MAX_NUM_LIMIT_CHARS) {
            throw new TemplateException("the num of ? in number temlate was more than the limit", this);
        }
        StringBuffer sb = new StringBuffer(numLimit + 1);
        String changeToStr = "" + changeTo;
        if (changeToStr.length() < numLimit) {
            for (int i = 0; i < (numLimit - changeToStr.length()); i++) {
                sb.append("0");
            }
        }
        sb.append(changeTo);
        return sb.toString();
    }

    protected void setChangeTo(int changeTo) {
        this.changeTo = changeTo;
    }
}
