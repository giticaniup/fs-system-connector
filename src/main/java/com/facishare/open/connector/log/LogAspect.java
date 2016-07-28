package com.facishare.open.connector.log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * log
 * Created by zhongcy on 2016/6/20.
 */
public class LogAspect {

    private static final Logger RUN_LOG = LoggerFactory.getLogger(LogAspect.class);

    public Object around(ProceedingJoinPoint point) throws Throwable {

        String className = point.getTarget().getClass().getName();
        String methedName = point.getSignature().getName();

        StopWatch totalStopWatch = new StopWatch();
        totalStopWatch.start();

        Object result = null;

        try {
            result = point.proceed();

        } catch (Exception e) {
            RUN_LOG.error("classname:{}, methedName:{}, args:{}, exception={}",
                    className, methedName, point.getArgs(), e);
        }

        totalStopWatch.stop();

        RUN_LOG.info("classname:{}, methedName:{}, args:{}, result:{}, timecost:{} ",
                className, methedName, point.getArgs(), result, totalStopWatch.getTotalTimeMillis());

        return result;
    }

}
