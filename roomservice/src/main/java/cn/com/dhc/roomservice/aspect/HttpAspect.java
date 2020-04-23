package cn.com.dhc.roomservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * cn.com.dhc.roomservice.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("-----------------before-----------------");
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            logger.debug("URL : " + request.getRequestURL().toString());
            logger.debug("HTTP_METHOD : " + request.getMethod());
            logger.debug("IP : " + request.getRemoteAddr());
            logger.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.debug("ARGS : " + Arrays.toString(joinPoint.getArgs()));
            //获取所有参数方法一：
            Enumeration<String> enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String paraName = (String) enu.nextElement();
                System.out.println(paraName + ": " + request.getParameter(paraName));
            }
        }
    }

    @After("log()")
    public void doAfter() {
        logger.debug("请求耗时（毫秒）：" + (System.currentTimeMillis() - startTime.get()));
        logger.debug("-----------------after-----------------");
    }

    @AfterReturning(pointcut = "log()")
    public void doReturn() {
        logger.debug("-----------------return-----------------");
    }
}
