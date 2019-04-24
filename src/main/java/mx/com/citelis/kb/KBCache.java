package mx.com.citelis.kb;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class KBCache {

    private Map<String, Object> map = new HashMap<>();

    @Around("execution(@mx.com.citelis.kb.CitelisCache * *(..))")
    public Object cache(ProceedingJoinPoint jp) throws Throwable {

        if(map.containsKey("1"))
            return map.get("1");

        Object  result = jp.proceed(jp.getArgs());
        map.put("1",result);

        return result;
    }
}
