package ru.motherfucker.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.motherfucker.entity.Post

/**
 * @author NGorelov
 */
interface PostRepository extends MongoRepository<Post, String> {
}
