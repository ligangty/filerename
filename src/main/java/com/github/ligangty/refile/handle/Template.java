package com.github.ligangty.refile.handle;

/**
 * The super interface of the templates. Implemention of this interface show
 * that it is a template for renaming.
 *
 * @author ligangty@github.com
 * @date Apr 12, 2009
 */
public interface Template {

    String getErrorMessage();

    void setErrorMessage(String errorMessage);
}
