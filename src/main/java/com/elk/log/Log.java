package com.elk.log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* *.get*(..))")
	public void allGetterMethods_pointcut(){}
	
	@Pointcut("execution(* *.set*(..))")
	public void allSetterMethods_pointcut(){}
	
	@Pointcut("within(com.elk.model..*)")
	public void allModelPackageMethods_pointcut(){}
	
	//Getters inside com.elk.model
	@Around("allGetterMethods_pointcut() && allModelPackageMethods_pointcut()")
	public Object ModelGetters(ProceedingJoinPoint joinPoint){
		Object returnedVal=null;
		try {
			log.debug("Door-in Getter : "+ joinPoint.getSignature());
			returnedVal=joinPoint.proceed();
			log.debug("Door-out Getter : "+ joinPoint.getSignature() 
			+ " : without error : returned :" + returnedVal);
		} catch (Throwable e) {
			log.error("Door-out Getter : "+ joinPoint.getSignature() + " : with error : " + 
					ExceptionUtils.getStackTrace(e));
		}
		return returnedVal;
		
	}
	
	//Setters inside com.elk.model
	@Around("allSetterMethods_pointcut() && allModelPackageMethods_pointcut()")
	public Object ModelSetters(ProceedingJoinPoint joinPoint){
		Object returnedVal=null;
		
		Object[] signatureArgs = joinPoint.getArgs();
		String Args="";
		for (Object signatureArg: signatureArgs) {
		    Args=Args + "[" + signatureArg + "] ";
		}
		try {
			log.debug("Door-in Setter : "+ joinPoint.getSignature());
			returnedVal=joinPoint.proceed();
			log.debug("Door-out Setter : "+ joinPoint.getSignature() 
			+ " : without error : returned : " + returnedVal +
			" : with Arguments : "+Args
					);
		} catch (Throwable e) {
			log.error("Door-out Setter : "+ joinPoint.getSignature() + " : with error : " + 
					ExceptionUtils.getStackTrace(e) + " : with Arguments : "+Args);
		}
		return returnedVal;
			
	}
	
	
	@Pointcut("within(com.elk.util..*)")
	public void allUtilPackageMethods_pointcut(){}
	
	@Pointcut("within(com.elk.filter..*)")
	public void allFilterPackageMethods_pointcut(){}
	
	@Pointcut("within(com.elk.config..*)")
	public void allConfigPackageMethods_pointcut(){}
	
	@Pointcut("within(com.elk..*)")
	public void allApplicationPackageMethods_pointcut(){}
	
	@Around("allConfigPackageMethods_pointcut() && allFilterPackageMethods_pointcut() && "
			+ "allUtilPackageMethods_pointcut() && allApplicationPackageMethods_pointcut()")
	public Object ProducerMethods(ProceedingJoinPoint joinPoint){
		Object returnedVal=null;
	
		Object[] signatureArgs = joinPoint.getArgs();
		String Args="";
		for (Object signatureArg: signatureArgs) {
		     Args=Args + "[" + signatureArg + "] ";
		}
		try {
			log.debug("Door-in : "+ joinPoint.getSignature());
			returnedVal=joinPoint.proceed();
			log.debug("Door-out : "+ joinPoint.getSignature() 
			+ " : without error : returned : " + returnedVal +
			" : with Arguments : "+Args
					);
		} catch (Throwable e) {
			log.error("Door-out : "+ joinPoint.getSignature() + " : with error : " + 
					ExceptionUtils.getStackTrace(e) + " : with Arguments : "+Args);
		}
		return returnedVal;
			
	}
	
	
}
