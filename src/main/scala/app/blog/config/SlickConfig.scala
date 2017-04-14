package app.blog.config

import javax.sql.DataSource

import app.blog.config.SlickConfig.SlickDatabase
import org.springframework.context.annotation.{Bean, Configuration}
import slick.jdbc.H2Profile.api._

@Configuration
class SlickConfig {

	// Data source is created by spring boot jdbc
	@Bean
	def database(dataSource: DataSource): SlickDatabase = Database.forDataSource(dataSource, None)

}

object SlickConfig {

	type SlickDatabase = slick.jdbc.H2Profile.api.Database

	val SlickDatabaseApi = slick.jdbc.H2Profile.api

}
