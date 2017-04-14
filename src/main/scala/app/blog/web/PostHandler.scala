package app.blog.web

import javax.inject.Named

import app.blog.entity.Post
import app.blog.service.PostService
import org.springframework.web.reactive.function.BodyInserters._
import org.springframework.web.reactive.function.server.ServerResponse._
import org.springframework.web.reactive.function.server.{ServerRequest, ServerResponse}
import reactor.core.publisher.Mono

@Named
class PostHandler(postService: PostService) {

	def create(request: ServerRequest): Mono[ServerResponse] = {
		val createdPost = for {
			post <- request.bodyToMono(classOf[Post])
			created <- postService.createPost(post)
		} yield created

		createdPost.flatMap(post => ok().body(fromObject(post)))
	}

	def getAll(request: ServerRequest): Mono[ServerResponse] = {
		ok().body(postService.findAllPosts(), classOf[Post])
	}

	def getPostByAuthor(request: ServerRequest): Mono[ServerResponse] = {
		val author = request.pathVariable("author")
		ok().body(postService.findPostsByAuthor(author), classOf[Post])
	}

}
