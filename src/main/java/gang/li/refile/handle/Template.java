package gang.li.refile.handle;

/**
 * The super interface of the templates. Implemention of this interface show
 * that it is a template for renaming.
 *
 * @author Jacky Li
 * @date Apr 12, 2009
 */
public interface Template {

    public String getErrorMessage();

    public void setErrorMessage(String errorMessage);
}
