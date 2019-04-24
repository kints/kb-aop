package mx.com.citelis.kb;

import java.time.LocalDateTime;

public class ApplicationException {
    private String className;
    private String message;
    private String throwedByMethod;
    private int errorNo;
    private String severity;
    private LocalDateTime dateTime;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThrowedByMethod() {
        return throwedByMethod;
    }

    public void setThrowedByMethod(String throwedByMethod) {
        this.throwedByMethod = throwedByMethod;
    }

    public int getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ApplicationException() {
    }

    public ApplicationException(String className, String message, String throwedByMethod, int errorNo, String severity, LocalDateTime dateTime) {
        this.className = className;
        this.message = message;
        this.throwedByMethod = throwedByMethod;
        this.errorNo = errorNo;
        this.severity = severity;
        this.dateTime = dateTime;
    }
}
