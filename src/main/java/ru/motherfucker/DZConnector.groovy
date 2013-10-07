package ru.motherfucker

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author NGorelov
 */
@Component
@Scope("singleton")
class DZConnector {

    @Value("\${connector.dz.core.url}")
    private String url

    @Value("\${connector.dz.core.cookie.key}")
    private String cookieKey

    @Value("\${connector.dz.core.cookie.value}")
    private String cookieValue

    @Value("\${connector.dz.core.param.theme.key}")
    private String themeKey

    @Value("\${connector.dz.param.theme.value}")
    private String themeValue

    @Value("\${connector.dz.core.param.from.key}")
    private String shiftKey

    Document getPage(int shift){
        return Jsoup.connect(url)
                .cookie(cookieKey, cookieValue)
                .data(themeKey, themeValue)
                .data(shiftKey, (shift).toString())
                .get()
    }
}
