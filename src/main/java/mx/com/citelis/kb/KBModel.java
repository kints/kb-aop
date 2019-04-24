package mx.com.citelis.kb;

import java.io.Serializable;
public class KBModel implements Serializable {

    private String title;
    private String description;
    private String code;

    public KBModel() {
    }

    public KBModel(String title, String description, String code) {
        this.title = title;
        this.description = description;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }
    @RavisaAudit
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    @RavisaAudit
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }
    @RavisaAudit
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "KBModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
