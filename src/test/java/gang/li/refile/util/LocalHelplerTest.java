package gang.li.refile.util;

import java.util.Locale;

import junit.framework.TestCase;

public class LocalHelplerTest extends TestCase {

    LocaleHelper localHelper = LocaleHelper.getInstance();

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetLocalString() {
        // en_US
        localHelper = LocaleHelper.getInstance(UIConstants.RESOURCES_NAME,
                new Locale("en", "US"));
        assertEquals("Batch File Rename", localHelper
                .getLocaleString(UIConstants.MAIN_WINDOW_TITLE));
        // zh_CN
        localHelper = LocaleHelper.getInstance(UIConstants.RESOURCES_NAME,
                new Locale("zh", "cn"));
        System.out.println(localHelper
                .getLocaleString(UIConstants.MAIN_WINDOW_TITLE));

    }
}
