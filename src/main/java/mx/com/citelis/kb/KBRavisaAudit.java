package mx.com.citelis.kb;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Logger;


@Aspect
public class KBRavisaAudit {
    RepositoryDAO repositoryDAOBB;
    RepositoryDAO repositoryDAOBP;

    //Variable used for the max time a method can take
    @Value("${maxTime}")
    private int maxTime;

    public KBRavisaAudit() {
    }

    public KBRavisaAudit(RepositoryDAO repositoryDAOBB) {
        this.repositoryDAOBB = repositoryDAOBB;
    }

    public KBRavisaAudit(ArrayList<RepositoryDAO> replist) {
        //ArrayList<RepositoryDAO>  list = replist;
        this.repositoryDAOBB = replist.get(0);
        this.repositoryDAOBP = replist.get(1);
    }

    Logger log = Logger.getLogger("[Exceptions]");
   // @AfterThrowing(pointcut = "execution( @mx.com.citelis.kb.RavisaAudit * *(..)) && @annotation(audit)", throwing = "e")
   //public void logExceptions(JoinPoint jp, KBRavisaAudit audit, Exception e) throws Throwable
    @AfterThrowing(value = "execution(@mx.com.citelis.kb.RavisaAudit * *(..))", throwing = "e")
    public  void logExceptions(JoinPoint jp, Exception e)
    {
        ApplicationException exceptionThrowed = new ApplicationException(
                jp.getSignature().getClass().getName(),
                e.getMessage(),
                jp.getSignature().getName(),
                100,
                "asdf",
                LocalDateTime.now());
        /*RepositoryDAO sendDB = new JDBCMySQLExceptionDAO();
        sendDB.insert(exceptionThrowed);*/
        log.info("Exception throw at: "+jp.getSignature().getName()+" Exception: "+e.getMessage());
        repositoryDAOBB.insert(exceptionThrowed);
    }
   /* @After("execution( * *(..))")
    public void entre()
    {
        Logger log = Logger.getLogger("[TEST]");
        log.info("Entre");
    }*/
    @Around("execution(@mx.com.citelis.kb.RavisaAudit * mx.com.citelis.kb.*.set*(*)) && target(instance)")
    //@Around("execution(* set*(*)) && target(instance)")
    public Object logSetMethods(ProceedingJoinPoint jp, Object instance) throws Throwable{
        log.info("Entre a logSetMethods");
      /*  String methodName = jp.getSignature().getName();
        String prevValue = "";
        String curValue = (String) jp.getArgs().toString();
        try {
            prevValue = (String) instance
                    .getClass()
                    .getMethod(methodName.replaceFirst("set","get"))
                    .invoke(instance);
            return jp.proceed(jp.getArgs());
        }
        finally {
            log.info(String.format("A value was changed, previous: %s and the current: %s",prevValue,curValue));
        }*/
        return jp.proceed(jp.getArgs());
    }



    @Around("execution(@mx.com.citelis.kb.RavisaAudit * *(..))")
    public Object logBadPerf(ProceedingJoinPoint jp) throws Throwable
    {
        long start = System.currentTimeMillis();
        try {
            return jp.proceed(jp.getArgs());
        }
        finally {
            long end = System.currentTimeMillis();
            long totalTime = end - start;
           // log.info(""+Long.parseLong(PERFORMANCE_MAXMS));
            if (totalTime >= maxTime)//Long.parseLong(PERFORMANCE_MAXMS))
            {

                log.info("The method: "+jp.getSignature().getName()+" ran in "+totalTime+" ms");
                AppBadPerformace appBP =new AppBadPerformace(
                        jp.getSignature().getClass().getName(),
                        jp.getSignature().getName(),
                        totalTime,
                        LocalDateTime.now()
                );
                repositoryDAOBP.insert(appBP);

            }
        }
    }
}
