package ru.motherfucker

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * @author NGorelov
 */
class DZConnector {
    private PropertyReader properties
    private static final dzConnector = new DZConnector()

    DZConnector(){
        properties = PropertyReader.getInstance()
    }

    static DZConnector getInstance(){
        return dzConnector
    }

    Document getPage(int page){
        return Jsoup.connect(properties.get("connector.dz.core.url"))
                .cookie(properties.get("connector.dz.core.cookie.key"), properties.get("connector.dz.core.cookie.value"))
                .data(properties.get("connector.dz.core.param.theme.key"), properties.get("connector.dz.param.theme.value"))
                .data(properties.get("connector.dz.core.param.from.key"), (page * 30).toString())
                .get()
    }
}
