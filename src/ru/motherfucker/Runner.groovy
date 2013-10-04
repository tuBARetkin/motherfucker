package ru.motherfucker
/**
 * @author NGorelov
 */
class Runner {
    static main(args){
        new Runner().run()
    }

    def run(){
        List<Post> posts = new PageParser().createPosts()
        Map<String, ProcessResult> results = new Processor().processPosts(posts)
        results.each {
            result ->
                String user = result.getKey()
                int points = result.getValue().getSelfPoints()
                println "$user $points"
        }
    }

}
