package org.dga.backend.portfolio.async.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
public class TracerData {

	private static final Logger log = LoggerFactory.getLogger(TracerData.class);

    //@Around("@annotation(ErrorTraceAnnotation)")

    @Pointcut("execution(* com.nttdata.banking.caixa..*(..))")
    public void allMethods() {}

    @Around("allMethods()")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endtime = System.currentTimeMillis();
        log.info("TIME EXECUTION - Class Name: "+ point.getSignature().getDeclaringTypeName() +". Method Name: "+ point.getSignature().getName() + ". Time taken for Execution is : " + (endtime-startTime) +"ms");

        return object;
    }
}
