	1. 定义切面类
	@Aspect
	@Compont
	2. 定义cutPoint
	@Pointcut("@annotation(com.linchuan.aop.Loggable)")
	publicvoidloggableMethods(){};
	
	此处可以使用annotation来分类joinPoint
		@Target({ElementType.METHOD})
		@Retention(RetentionPolicy.RUNTIME)
		@Documented
		public @interface Loggable {
		}
		 
		
	3. 实现Advice
    @Before(value = "loggableMethods()")
    public void logSomeThing(JoinPoint joinPoint) {
        Object[] tos = joinPoint.getArgs();
        logger.info(joinPoint.getSignature().toString());
        logger.info(tos[0].toString());
    }

    @AfterReturning(value = "loggableMethods()", returning = "obj")
    public void logReturnValue(Object obj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(request.getRequestURI());
        logger.info(request.getRequestURL().toString());
        logger.info(request.getContextPath());
        logger.info("Return " + obj.toString());
    }
	
	4. 引用annotation
	
	@RestController
	public class BasicController {
	    @GetMapping("/")
	    @Loggable
	    public String showHome(@RequestParam(required = false) String name) {
	        String echo = "Hello, this is home, and you are using get";
	        if (name==null)
	            return echo;
	        return echo + ", " + name;
	    }
	}
	 
	 

5. 完整的切片类：  
```java
@Aspect
@Component
public class MyLog {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Pointcut("@annotation(com.linchuan.aop.Loggable)")
    public void loggableMethods() {};
//  @Before("execution(public * com.linchuan.aop.BasicController.*(..))")
    @Before(value = "loggableMethods()")
    public void logSomeThing(JoinPoint joinPoint) {
        Object[] tos = joinPoint.getArgs();
        logger.info(joinPoint.getSignature().toString());
        logger.info(tos[0].toString());
    }

    @AfterReturning(value = "loggableMethods()", returning = "obj")
    public void logReturnValue(Object obj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(request.getRequestURI());
        logger.info(request.getRequestURL().toString());
        logger.info(request.getContextPath());
        logger.info("Return " + obj.toString());
    }
}
```




