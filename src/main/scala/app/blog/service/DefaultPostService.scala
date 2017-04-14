package app.blog.service

import java.util.UUID

import app.blog.dao.PostDao
import app.blog.entity.Post
import app.blog.util.ReactiveConversions._
import reactor.core.publisher.{Flux, Mono}

class DefaultPostService(postDao: PostDao) extends PostService {

	override def createPost(post: Post): Mono[Post] = {
		val postWithId = if (post.id == null) post.copy(id = UUID.randomUUID().toString) else post
		val saveResult: Mono[Int] = postDao.save(postWithId)
		saveResult.flatMap {
			case 0 => Mono.error[Post](new IllegalStateException("Was not able to create post"))
			case _ => Mono.just(postWithId)
		}
	}

	override def findAllPosts(): Flux[Post] = Flux.from(postDao.findAll())

	override def findPostsByAuthor(author: String): Flux[Post] = findAllPosts().filter(_.author == author)

}
