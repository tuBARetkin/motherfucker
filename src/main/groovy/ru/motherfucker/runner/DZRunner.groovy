package ru.motherfucker.runner

import groovy.util.logging.Slf4j
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import ru.motherfucker.parser.PageParser
import ru.motherfucker.entity.Post
import ru.motherfucker.entity.ProcessResult
import ru.motherfucker.process.Processor

/**
 * @author NGorelov
 */
@Slf4j
class DZRunner {
    static main(args){
        try{
            ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml")
            def posts = ctx.getBean(PageParser.class).createPosts()
            def results = ctx.getBean(Processor.class).processPosts(posts)
            results.each {
                println "${it.key}: ${it.value.selfPoints}"
            }
        }
        catch (Exception e){
            log.error("Error on initializing spring context", e)
        }
    }
}
