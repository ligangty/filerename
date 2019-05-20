package com.github.ligangty.refile.util;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleHelper {

    private transient ResourceBundle resourceBundleForCurrentUserLocale = null;
    private static Locale currentUserLocale = null;
    private static final String DATE_PATTERN = "dd MMM yyyy";

    /**
     * Constructor for instantiating a LocalizationHelper
     *
     */
    protected LocaleHelper() {
        super();
    }

    private LocaleHelper(String resourceBundleBaseName) {
        // Retrieve resource bundle for the current user locale
        this.resourceBundleForCurrentUserLocale = ResourceBundle.getBundle(
                resourceBundleBaseName, currentUserLocale);
    }

    public static LocaleHelper getInstance() {
        currentUserLocale = Locale.getDefault();
        return new LocaleHelper(UIConstants.RESOURCES_NAME);
    }

    public static LocaleHelper getInstance(Locale specLocale) {
        currentUserLocale = specLocale;
        return new LocaleHelper(UIConstants.RESOURCES_NAME);
    }

    public static LocaleHelper getInstance(String resourceBundleBaseName,
            Locale specLocale) {
        currentUserLocale = specLocale;
        return new LocaleHelper(resourceBundleBaseName);
    }

    private Object getObject(String resourceKey) {
        return this.resourceBundleForCurrentUserLocale.getObject(resourceKey);
    }

    /**
     * Gets Content String
     *
     * @param contentKey -
     * @return -
     */
    public final String getLocaleString(String contentKey) {
        return (String) getObject(contentKey);
    }

    /**
     * This method returns a localized content string for the given key with
     * given string parameters substituted into the content string
     *
     * @param contentKey -
     * @param contentStringParameters -
     * @return a string representing the localized content associated with the
     * given content key
     */
    public final String getContentStringWithParameters(String contentKey,
            String[] contentStringParameters) {
        return MessageFormat.format(this.getLocaleString(contentKey),
                new Object[]{contentStringParameters});
    }

    /**
     * Gets Current User Locale
     *
     * @return -
     */
    public final Locale getCurrentUserLocale() {
        return currentUserLocale;
    }

    /**
     * Format's a Date object in IBM format using the user's locale
     *
     * @param aDate -
     * @return a String with the date in IBM format for the user's locale
     */
    public String formatDate(java.util.Date aDate) {
        if (null == aDate) {
            return "";
        }

        SimpleDateFormat ibmDateFormat = new SimpleDateFormat(DATE_PATTERN,
                currentUserLocale);
        return ibmDateFormat.format(aDate);
    }

    /**
     * This method is used to intialize the
     * <code>LocaleHelper</code>
     *
     * @since EAD4J 2.0.0
     */
    public void initialize() {
        // nothing to initialize
    }

    public static Locale getLocale(Locale locale) {
        String language = locale.getLanguage();
        String sLocale = locale.toString();
        try {
            if (sLocale.toLowerCase().equals("en_us")
                    || language.toLowerCase().equals("en")) {
                return new Locale("en", "us");
            } else if (sLocale.toLowerCase().equals("es_mx")
                    || language.toLowerCase().equals("mx")) {
                return new Locale("es", "mx");
            } else if (sLocale.toLowerCase().equals("fr_fr")
                    || language.toLowerCase().equals("fr")) {
                return new Locale("fr", "fr");
            } else if (sLocale.toLowerCase().equals("de_de")
                    || language.toLowerCase().equals("de")) {
                return new Locale("de", "de");
            } else if (sLocale.toLowerCase().equals("es_es")
                    || language.toLowerCase().equals("es")) {
                return new Locale("es", "es");
            } else if (sLocale.toLowerCase().equals("it_it")
                    || language.toLowerCase().equals("it")) {
                return new Locale("it", "it");
            } else if (sLocale.toLowerCase().equals("ja_jp")
                    || language.toLowerCase().equals("ja")) {
                return new Locale("ja", "jp");
            } else if (sLocale.toLowerCase().equals("nl_nl")
                    || language.toLowerCase().equals("nl")) {
                return new Locale("nl", "nl");
            } else if (sLocale.toLowerCase().equals("pt_br")
                    || language.toLowerCase().equals("pt")) {
                return new Locale("pt", "br");
            } else if (sLocale.toLowerCase().equals("zh_cn")
                    || language.toLowerCase().equals("zh")) {
                return new Locale("zh", "cn");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return getDefaultLocale();
    }

    public static Locale getDefaultLocale() {
        return new Locale("en", "us");
    }
}
