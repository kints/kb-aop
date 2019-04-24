package mx.com.citelis.kb;

import ch.qos.logback.core.db.DriverManagerConnectionSource;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
//@PropertySource("classpath:/dev.parameters")
@EnableAspectJAutoProxy
@EnableCaching
public class KBConfiguration {
    //protected static final String PERFORMANCE_MAXMS = "performance.maxms";
   // @Autowired Environment env;

    @Bean
    public KBService service(Repository repository){
        return new KBService(repository);
    }

    @Bean
    public Repository repository(){
        return new InMemoryKBRepository();
    }

    @Bean
    public List<RepositoryDAO> repositoryDAO(){

        RepositoryDAO repositoryDAOException =  new JDBCMySQLExceptionDAO();
        repositoryDAOException.setDataSource(dataSource());

        RepositoryDAO repositoryDAOBadPerformace= new JDBCMYSQLBadPerformaceDAO();
        repositoryDAOBadPerformace.setDataSource(dataSource());


        return new ArrayList()
        {{
            add(repositoryDAOException);
            add(repositoryDAOBadPerformace);
        }};
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.1.20.39:3306/Prueba_Spring");
        dataSource.setUsername("Any");
        dataSource.setPassword("1234abcd");
        return dataSource;
    }

    @Bean
    public KBRavisaAudit ravisaAudit(ArrayList<RepositoryDAO> repositoryDAO){ return new KBRavisaAudit(repositoryDAO); }

    @Bean
    public KBLogs logs(){
        return new KBLogs();
    }

    @Bean
    public KBCache cache(){
        return new KBCache();
    }

    @Bean
    public KBModel model() { return new KBModel();}

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(600))
                .disableCachingNullValues();
        return cacheConfig;
    }

    @Bean
    public RedisCacheManager cacheManager(){

        RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfiguration())
                .transactionAware()
                .build();
        return rcm;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        return new LettuceConnectionFactory(redisConf);
    }

    // https://www.concretepage.com/spring-boot/spring-boot-redis-cache

}
