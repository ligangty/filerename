package gang.li.refile.handle;

public class TemplateException extends Exception {

    static final long serialVersionUID = -3387584195684229948L;
    private Template sourceTemplate;

    public TemplateException(Template sourceTemplate) {
        this.sourceTemplate = sourceTemplate;
    }

    public TemplateException(String errorMessage, Template sourceTemplate) {
        super(errorMessage);
        this.sourceTemplate = sourceTemplate;
        this.sourceTemplate.setErrorMessage(errorMessage);
    }
}
