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

    @Pointcut("execution(* by.academy.repository.jdbctemplate.JdbcTemplateUserRepository.*(..))")
    public void aroundRepositoryPointcut() {
    }

    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println(joinPoint.getArgs().length);

        log.info("Method " + joinPoint.getSignature().getName() + " start");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();

        log.info("Method " + joinPoint.getSignature().getName() + " finished");
        log.info("Total execution time StopWatch in nanoseconds: " + stopWatch.getTotalTimeNanos());

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();

//        if (SpringTest.statistic.containsKey(methodName)){
//            Long count = SpringTest.statistic.get(methodName);
//            SpringTest.statistic.put(methodName,++count);
//        } else
//        {
//            SpringTest.statistic.put(methodName, 1L);
//        }
        return proceed;
    }
}
