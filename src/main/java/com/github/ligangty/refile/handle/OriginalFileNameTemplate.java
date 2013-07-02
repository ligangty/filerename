package com.github.ligangty.refile.handle;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.ligangty.refile.util.FileUtil;

/**
 * This Template is used to change the file name string using the asterisk. It
 * will return the original file name.
 *
 * @author ligangty@github.com
 * @date Apr 6, 2009
 */
public class OriginalFileNameTemplate extends AbstractTemplate {

    private static final Logger log = Logger.getLogger(OriginalFileNameTemplate.class
            .getName());
    /**
     * regexp pattern for this match
     */
    private static final String PATTERN = "(\\*)";

    /**
     *
     *
     * @param fileName
     * @param templateStr
     * @return
     * @throws com.github.ligangty.refile.handle.TemplateException
     */
    public String getFileNameFromTemplate(String fileName, String templateStr)
            throws TemplateException {
        String fileNameWithoutPostFix = FileUtil
                .getFileNameWithoutPostFix(fileName);
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(templateStr);
        StringBuffer sb = new StringBuffer(256);
        while (m.find()) {
            m.appendReplacement(sb, fileNameWithoutPostFix);
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
