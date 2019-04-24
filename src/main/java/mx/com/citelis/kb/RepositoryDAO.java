package mx.com.citelis.kb;

import javax.sql.DataSource;

public interface RepositoryDAO {
    public void insert(Object record);

    void setDataSource(DataSource dataSource);
}
