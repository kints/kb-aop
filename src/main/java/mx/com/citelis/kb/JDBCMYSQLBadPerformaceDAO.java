package mx.com.citelis.kb;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class JDBCMYSQLBadPerformaceDAO implements RepositoryDAO{

    @Override
    public void insert(Object record) {
        AppBadPerformace appBP = (AppBadPerformace) record;
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("performancelog");
        Timestamp sqlTime;
        sqlTime= Timestamp.valueOf(appBP.getDate_Time());
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("className",appBP.getClassName());
        parameters.put("method",appBP.getMethod());
        parameters.put("executionRunTime",appBP.getExecutionRunTime());
        parameters.put("date_time",sqlTime);
        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    private DataSource dataSource;
    Logger log = Logger.getLogger("[BadPerformace]");




}

