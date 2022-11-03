package by.academy.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class CustomAspect {
    private static final Logger log = Logger.getLogger(CustomAspect.class);

    @Pointcut("execution(* by.academy.controller.springdata.*.*(..))")
    public void aroundRepositoryPointcut() {
    }

    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Method " + joinPoint.getSignature().getName() + " start");
        log.info("Parameters count = " + joinPoint.getArgs().length);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();

        log.info("Method " + joinPoint.getSignature().getName() + " finished");
        log.info("Total execution time StopWatch in nanoseconds: " + stopWatch.getTotalTimeNanos());

        return proceed;
    }
}
