package ru.motherfucker.process

import org.springframework.stereotype.Component
import ru.motherfucker.entity.Post
import ru.motherfucker.entity.ProcessResult

/**
 * @author NGorelov
 */
@Component
class Processor {
    static Map<String, ProcessResult> processPosts(List<Post> posts) {
        Map<String, ProcessResult> results = [:].withDefault { username ->
            new ProcessResult(
                    username: username,
                    postsCount: 0,
                    selfPoints: 0
            )
        }

        posts.eachWithIndex { post, i ->
            results[post.username].postsCount++
            def max = results
                    .grep { it.value.username != post.username }
                    .max { it.value.postsCount }

            if (results[post.username].postsCount > (max?.value?.postsCount ?: 0)) {
                results[post.username].selfPoints = 0
            }

            if (i + 1 < posts.size()) {
                results[posts[i].username].selfPoints++
            }
        }

        return results
    }

}
