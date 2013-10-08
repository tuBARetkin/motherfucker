package ru.motherfucker.web

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author NGorelov
 */
@Component
class DZConnector {
    @Value('${connector.dz.core.url}') String url
    @Value('${connector.dz.core.cookie.key}') String cookieKey
    @Value('${connector.dz.core.cookie.value}') String cookieValue
    @Value('${connector.dz.core.param.theme.key}') String themeKey
    @Value('${connector.dz.param.theme.value}') String themeValue
    @Value('${connector.dz.core.param.from.key}') String shiftKey

    Document getPage(int shift) {
        Jsoup.connect(url)
                .cookie(cookieKey, cookieValue)
                .data(themeKey, themeValue)
                .data(shiftKey, shift as String)
                .get()
    }
}
