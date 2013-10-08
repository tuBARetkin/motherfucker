package ru.motherfucker.process

import org.springframework.stereotype.Component
import ru.motherfucker.entity.Post
import ru.motherfucker.entity.ProcessResult

/**
 * @author NGorelov
 */
@Component
class Processor {
    Map<String, Integer> processPosts(List<Post> posts){
        Map<String, ProcessResult> results = [:].withDefault { key ->
            new ProcessResult(
                    username: key,
                    postsCount: 0,
                    selfPoints: 0
            )
        }

        posts.eachWithIndex{ Post curPost, int curPostNumber ->
            results[curPost.username]?.postsCount++

            //Проверяем максимальное ли у пользователя количество сообщений
            def max = results
                .grep{ it.value.username != curPost.username }
                .max{ it.value.postsCount }

            //Если да, то обнуляем ему счетчик
            if (results[curPost.username].postsCount > (max?.value?.postsCount ?: 0)) {
                results[curPost.username].selfPoints = 0
            }

            //Добавляем слудеющему очко
            if (curPostNumber + 1 < posts.size()) {
                results[posts[curPostNumber + 1].username].selfPoints++
            }
        }

        return results
    }
}
