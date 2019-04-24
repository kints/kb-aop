package mx.com.citelis.kb;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.logging.Logger;

@Aspect
public class KBLogs {
    Logger log = Logger.getLogger("[LOG]");

    @Before("execution(@mx.com.citelis.kb.CitelisLog * *(..)) && @annotation(clog)")
    public void beforeLog(JoinPoint jp,CitelisLog clog){
        if(clog.corto())
            log.info(clog.prefix() + jp.getSignature().getName());
        else
            log.info(clog.prefix() + jp.getSignature());
    }

    /*
    @Before("logging(clog)")
    public void beforeLog(JoinPoint jp, CitelisLog clog){

        if(clog.isShort())
            log.info("Method called: " + jp.getSignature().getName());
        else
            log.info("Method called: " + jp.getSignature());
    }

    @After("logging(clog)")
    public void afterLog(JoinPoint jp, CitelisLog clog){
        if(clog.isShort())
            log.info("Method executed: " + jp.getSignature().getName());
        else
            log.info("Method executed: " + jp.getSignature());
    }

    @Pointcut("execution(@mx.com.citelis.kb.CitelisLog * *(..)) && @annotation(clog)")
    public void logging(CitelisLog clog){}

    */

}


/*
    @Before("logging()")
    public void beforeLog(JoinPoint jp){
        log.info("Method called: " + jp.getSignature());
    }

    @After("logging()")
    public void afterLog(JoinPoint jp){
        log.info("Method executed: " + jp.getSignature());
    }

    @Pointcut("execution(* mx.com.citelis.kb.KBService.*(..))")
    public void logging(){}

    @Around("execution(* mx.com.citelis.kb.KBService.createKB(..))")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        args[0] = new KBModel("HEY","","");
        return pjp.proceed(args);
    }


    @Before("execution(@mx.com.citelis.kb.CitelisLog * *(..)) && @annotation(clog)")
    public void beforeLog(JoinPoint jp,CitelisLog clog){
        if(clog.isShort())
            log.info("Method called: " + jp.getSignature().getName());
        else
            log.info("Method called: " + jp.getSignature());
    }
 */