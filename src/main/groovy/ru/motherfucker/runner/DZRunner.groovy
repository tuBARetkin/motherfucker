package ru.motherfucker.runner
import groovy.util.logging.Slf4j
import ru.motherfucker.parser.PageParser
import ru.motherfucker.process.Processor

/**
 * @author NGorelov
 */
@Slf4j
class DZRunner extends AbstractRunner {
    static main(args){
        new DZRunner().run()
    }

    def run(){
        initContext()

        try{
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
