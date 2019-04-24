package mx.com.citelis.kb;

import java.time.LocalDateTime;

public class AppBadPerformace {

    private String className;
    private String Method;
    private long ExecutionRunTime;
    private LocalDateTime date_Time;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public long getExecutionRunTime() {
        return ExecutionRunTime;
    }

    public void setExecutionRunTime(long executionRunTime) {
        ExecutionRunTime = executionRunTime;
    }

    public LocalDateTime getDate_Time() {
        return date_Time;
    }

    public void setDate_Time(LocalDateTime date_Time) {
        this.date_Time = date_Time;
    }

    public AppBadPerformace(String className, String method, long executionRunTime, LocalDateTime date_Time) {
        this.className = className;
        Method = method;
        ExecutionRunTime = executionRunTime;
        this.date_Time = date_Time;
    }

    public AppBadPerformace() {
    }
}
