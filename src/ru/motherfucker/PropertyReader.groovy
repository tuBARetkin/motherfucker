package ru.motherfucker

/**
 * @author NGorelov
 */
class PropertyReader {
    private static final PropertyReader instance = new PropertyReader()
    private Properties props = new Properties()

    PropertyReader(){
        new File("resources/app.properties").withInputStream {
            stream -> props.load(stream)
        }
    }

    static PropertyReader getInstance(){
        return instance
    }

    String get(key){
        return props[key]
    }
}
