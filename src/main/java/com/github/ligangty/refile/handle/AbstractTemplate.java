package com.github.ligangty.refile.handle;

/**
 * AbstractTemplate is an abstract template which implements Template interface
 * and supply default implementation of the method of the interface.
 *
 * @author ligangty@github.com
 * @date Apr 12, 2009
 */
public class AbstractTemplate implements Template {

    /**
     * the errorMessage which the sub template will show
     */
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
