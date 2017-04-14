package app.blog.dao

import javax.annotation.PostConstruct

import app.blog.config.SlickConfig.SlickDatabase
import app.blog.config.SlickConfig.SlickDatabaseApi._
import app.blog.entity.Post
import slick.basic.DatabasePublisher

import scala.concurrent.Future

class DefaultPostDao(db: SlickDatabase) extends PostDao {

	private class Posts(tag: Tag) extends Table[Post](tag, "posts") {

		def id = column[String]("id", O.PrimaryKey)

		def author = column[String]("author")

		def text = column[String]("text")

		def * = (id, author, text) <> (Post.tupled, Post.unapply)

	}

	private val posts = TableQuery[Posts]

	@PostConstruct
	def createTable: Unit = {
		db.run(posts.schema.create)
	}

	override def save(post: Post): Future[Int] = {
		db.run(posts += post)
	}

	override def findAll(): DatabasePublisher[Post] = {
		db.stream(posts.result)
	}

}
