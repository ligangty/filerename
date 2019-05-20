package com.github.ligangty.refile.util;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class LocalHelplerTest {

	LocaleHelper localHelper = LocaleHelper.getInstance();

	@Test
	public void testGetLocalString() {
		// en_US
		localHelper = LocaleHelper.getInstance(UIConstants.RESOURCES_NAME, new Locale("en", "US"));
		Assert.assertEquals("Batch File Rename", localHelper.getLocaleString(UIConstants.MAIN_WINDOW_TITLE));
		// zh_CN
		localHelper = LocaleHelper.getInstance(UIConstants.RESOURCES_NAME, new Locale("zh", "cn"));
		System.out.println(localHelper.getLocaleString(UIConstants.MAIN_WINDOW_TITLE));

	}
}
