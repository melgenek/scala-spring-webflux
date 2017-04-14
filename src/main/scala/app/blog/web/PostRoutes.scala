package app.blog.web

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.reactive.function.server.RequestPredicates.{GET, POST}
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.{RouterFunction, ServerResponse}

@Configuration
class PostRoutes(postHandler: PostHandler) {

	def routes() = Array(
		route(GET("/posts"), postHandler.getAll),
		route(GET("/posts/{author}"), postHandler.getPostByAuthor),
		route(POST("/posts"), postHandler.create)
	)

	@Bean
	def routerFunction: RouterFunction[ServerResponse] = routes().reduce(_.and(_))

}
