package ru.motherfucker.entity
/**
 * @author NGorelov
 */
public class Post {
    String username
    int number

    boolean isEmpty() {
        return !(username && number)
    }
}
