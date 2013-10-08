package ru.motherfucker.parser
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.motherfucker.entity.Post
import ru.motherfucker.web.DZConnector
/**
 * @author NGorelov
 */
@Component
class PageParser {
    @Autowired
    private DZConnector connector

    @Value("\${ipb.page.size}")
    private int pageSize

    private List<Post> result = []

    List<Post> createPosts(){
        int shift = 0
        def pageNavNode = connector.getPage(shift).getElementById("page-jump");
        int pagesCount = pageNavNode?.text()?.find(/[1-9]+/)?.toInteger()

        pagesCount?.times{
            Document document = connector.getPage(shift)
            parse(document.select(".ipbtable"))
            shift += pageSize
        }

        return result
    }

    def parse(Elements posts){
        posts.each {
            Post post = new Post()
            post.username = it.getElementsByClass("normalname")?.text()

            Elements postNumberNode = it.select(".postdetails a")
            if (postNumberNode && postNumberNode.size() > 0 && postNumberNode.get(0).text()) {
                post.number = postNumberNode.get(0).text().substring(1).toInteger()
            }

            if(!post.isEmpty()){
                result << post
            }
        }

        return result
    }
}
