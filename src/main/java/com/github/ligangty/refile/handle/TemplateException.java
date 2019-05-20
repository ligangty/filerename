package com.github.ligangty.refile.handle;

public class TemplateException extends Exception {

    static final long serialVersionUID = -3387584195684229948L;
    private final Template sourceTemplate;

    public TemplateException(Template sourceTemplate) {
        this.sourceTemplate = sourceTemplate;
    }

    TemplateException(String errorMessage, Template sourceTemplate) {
        this(errorMessage, sourceTemplate, null);
    }

    TemplateException(String errorMessage, Template sourceTemplate, Throwable cause){
        super(errorMessage, cause);
        this.sourceTemplate = sourceTemplate;
        this.sourceTemplate.setErrorMessage(errorMessage);
    }
}
