package ru.motherfucker.runner
import ru.motherfucker.parser.PageParser
import ru.motherfucker.entity.Post
import ru.motherfucker.entity.ProcessResult
import ru.motherfucker.process.Processor

/**
 * @author NGorelov
 */
class DZRunner extends AbstractRunner {

    static main(args){
        new DZRunner().run()
    }

    def run(){
        initContext()

        List<Post> posts = ctx.getBean(PageParser.class).createPosts()
        Map<String, ProcessResult> results = ctx.getBean(Processor.class).processPosts(posts)
        results.each {
            result ->
                String user = result.getKey()
                int points = result.getValue().getSelfPoints()
                println "$user $points"
        }
    }

}
