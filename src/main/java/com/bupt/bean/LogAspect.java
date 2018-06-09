package com.bupt.bean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspect {


//    execution(<修饰符> <返回类型> <类路径> <方法名>(<参数列表>) <异常模式> )
    @Pointcut(value = "execution(public int com.bupt.bean.MathCalculator.div(int, int))")
    public void pointCut() { }


    @Before("@annotation(com.bupt.bean.NeedPreLog)")
    public void logStart(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + " is going to proces. param are" + Arrays.asList(joinPoint.getArgs()));
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + " has been done");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "ret")
    public void logReturn(JoinPoint joinPoint, Object ret) {
        System.out.println("function name is " + joinPoint.getSignature().getName() + " return is " + ret);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        System.out.println("function name is " + joinPoint.getSignature().getName() + e);
    }

}
