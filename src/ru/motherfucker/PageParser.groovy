package ru.motherfucker

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * @author NGorelov
 */
class PageParser {
    private DZConnector connector = DZConnector.getInstance()
    private List<Post> result = new ArrayList<Post>()
    private int pageSize = Integer.parseInt(PropertyReader.getInstance().get("ipb.page.size"))

    List<Post> createPosts(){
        int shift = 0
        while(true){
            Document document = connector.getPage(shift)
            parse(document.select(".ipbtable"))
            if(result.size() % pageSize != 0){
                break
            }
            shift += pageSize
        }

        return result
    }

    def parse(Elements posts){
        posts.each {
            Element element ->
                Elements usernameNode = element.getElementsByClass("normalname")
                Elements postNumberNode = element.select(".postdetails a")

                String username
                int postNumber

                if (usernameNode != null && !usernameNode.text().isEmpty()){
                    username = usernameNode.text()
                }
                if (postNumberNode != null && postNumberNode.size() > 0 && !postNumberNode.get(0).text().isEmpty()){
                    postNumber = Integer.parseInt(postNumberNode.get(0).text().substring(1))
                }

                if(username != null && postNumber != null){
                    result.add(new Post(username: username, number: postNumber))
                }
        }
    }
}
