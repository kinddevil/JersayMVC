package luna.tmm.rosettastone.interceptors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
//@Component(value="baseInterceptor")
public class BaseInterceptor {
	
	public BaseInterceptor(){
		System.out.println("=======BaseInterceptor========");
	}
	
	@Pointcut("execution(public * luna.tmm.rosettastone..*.*(..))")
	public void baseMethod() {
	}

	@Around("baseMethod()")
	public void before(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("method before");
		pjp.proceed();
	}

	@AfterReturning(value = "baseMethod()" , returning = "val")
	public void afterReturning(JoinPoint joinPoint, Object val) throws Throwable {

	}

	@After("baseMethod()")
	public void afterFinily() throws Throwable {
		
	}
	
	@AfterThrowing(value = "baseMethod()",throwing = "ex")
	public void throwMethod(Throwable ex) {
		System.out.println(ex.getMessage());
	}
}
