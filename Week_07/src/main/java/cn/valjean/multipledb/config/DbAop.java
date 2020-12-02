package cn.valjean.multipledb.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class DbAop implements Ordered {
    @Pointcut("@annotation(cn.valjean.multipledb.config.CurDb)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        CurDb ds = method.getAnnotation(CurDb.class);
        if (ds == null) {
            DynamicDbSource.setDataSource(DbType.FIRST);
            log.debug("set datasource is " + DbType.FIRST);
        } else {
            DynamicDbSource.setDataSource(ds.name());
            log.debug("set datasource is " + ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDbSource.clearDataSource();
            log.debug("clean datasource");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
