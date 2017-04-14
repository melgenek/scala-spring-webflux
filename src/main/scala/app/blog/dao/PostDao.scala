package app.blog.dao

import app.blog.entity.Post
import org.reactivestreams.Publisher

import scala.concurrent.Future

trait PostDao {

	def save(post: Post): Future[Int]

	def findAll(): Publisher[Post]

}
