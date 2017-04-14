package app.blog

import app.blog.config.BeanInitializer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

object Application {

	def main(args: Array[String]): Unit = {
		val application = new SpringApplication(classOf[Application])
		application.addInitializers(new BeanInitializer)
		application.run(args: _*)
	}

}

@SpringBootApplication
class Application
