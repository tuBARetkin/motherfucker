package ru.motherfucker.parser

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
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
    @Autowired DZConnector connector
    @Value('${ipb.page.size}') int pageSize

    List<Post> createPosts() {
        List<Post> result = []
        pageSize.times {
            Document document = connector.getPage(it)
            document.select(".ipbtable").each {
                def post = new Post()
                post.username = it.getElementsByClass("normalname")?.text()

//                Elements postNumberNode = it.select(".postdetails a")
//                if (postNumberNode && postNumberNode.size() > 0 && !postNumberNode.get(0).text()) {
//                    post.number = Integer.parseInt(postNumberNode.get(0).text().substring(1))
//                }

//                if (post.inNotEmpty()) {
                    result << post
//                }
            }
        }
        return result
    }
}
