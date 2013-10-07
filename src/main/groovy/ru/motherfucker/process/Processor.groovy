package ru.motherfucker.process

import org.springframework.stereotype.Component
import ru.motherfucker.entity.Post
import ru.motherfucker.entity.ProcessResult

/**
 * @author NGorelov
 */
@Component
class Processor {
    private Map<String, ProcessResult> results = new HashMap<String, Integer>()

    private prepare(List<Post> posts){
        posts.each {
            post -> results.put(post.getUsername(),
                    new ProcessResult(
                            username: post.getUsername(),
                            postsCount: 0,
                            selfPoints: 0
                    ))
        }
    }

    Map<String, Integer> processPosts(List<Post> posts){
        prepare(posts)

        for(int curPostNumber = 0; curPostNumber < posts.size(); curPostNumber++){
            Post curPost = posts.get(curPostNumber)
            ProcessResult curUserResult = results.get(curPost.getUsername())
            curUserResult.setPostsCount(curUserResult.getPostsCount() + 1)

            //Обнуляем если у юзера количество постов стало максимальным
            if(checkMaxPostsCount(curPost.getUsername())){
                results.get(curPost.getUsername()).setSelfPoints(0)
            }

            //Добавляем слудеющему очко
            if (curPostNumber + 1 < posts.size()){
                Post nextPost = posts.get(curPostNumber + 1)
                ProcessResult nextUserResult = results.get(nextPost.getUsername())
                nextUserResult.setSelfPoints(nextUserResult.getSelfPoints() + 1)
            }
        }

        return results;
    }

    boolean checkMaxPostsCount(String username){
        int max = 0;
        int current = results.get(username).getPostsCount()
        results.each {
            result ->
                if (username != result.getKey()){
                    int count = result.getValue().getPostsCount()
                    max = count > max ? count : max
                }
        }

        return current > max
    }
}
