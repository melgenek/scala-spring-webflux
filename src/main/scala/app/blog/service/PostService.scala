package app.blog.service

import app.blog.entity.Post
import reactor.core.publisher.{Flux, Mono}

trait PostService {

	def createPost(post: Post): Mono[Post]

	def findAllPosts(): Flux[Post]

	def findPostsByAuthor(author: String): Flux[Post]

}
