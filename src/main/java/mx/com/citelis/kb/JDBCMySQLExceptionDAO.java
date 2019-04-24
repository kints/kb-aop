package mx.com.citelis.kb;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class JDBCMySQLExceptionDAO implements RepositoryDAO {
    private DataSource dataSource;
    Logger log = Logger.getLogger("[ExceptionsJDBC]");

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Object record) {
        ApplicationException exception = (ApplicationException) record;
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("EXCEPCIONSLOG");
        Timestamp sqlTime;
        sqlTime = Timestamp.valueOf(exception.getDateTime());
        //Connection conn = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CLASSNAME",exception.getClassName());
        parameters.put("MESSAGE",exception.getMessage());
        parameters.put("THROWEDBYMETHOD",exception.getThrowedByMethod());
        parameters.put("ERRORNO",exception.getErrorNo());
        parameters.put("SERVERITY",exception.getSeverity());
        parameters.put("DATE_TIME",sqlTime);
        simpleJdbcInsert.execute(parameters);
       /* String sql = "INSERT INTO EXCEPCIONSLOG (CLASSNAME,MESSAGE,THROWEDBYMETHOD,ERRORNO,SERVERITY,DATE_TIME)"+
                " VALUES (?,?,?,?,?,?)";

        try {
            /*DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/Pueba_Spring");
            dataSource.setUsername("Any");
            dataSource.setPassword("1234abcd");*//*
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,exception.getClassName());
            ps.setString(2,exception.getMessage());
            ps.setString(3,exception.getThrowedByMethod());
            ps.setInt(4,exception.getErrorNo());
            ps.setString(5,exception.getSeverity());
            ps.setTimestamp(6,sqlTime);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
            {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }*/
    }
}
