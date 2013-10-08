package ru.motherfucker.runner
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
/**
 * @author NGorelov
 */
abstract class AbstractRunner {
    private static final Logger logger = LoggerFactory.getLogger(AbstractRunner.class)
    ApplicationContext ctx;

    protected initContext(){
        try{
            ctx = new ClassPathXmlApplicationContext("application-context.xml")
        }
        catch (Exception e){
            logger.error("Error on initializing spring context", e)
        }
    }
}
