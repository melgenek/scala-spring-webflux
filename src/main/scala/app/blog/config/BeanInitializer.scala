package app.blog.config

import app.blog.config.SlickConfig.SlickDatabase
import app.blog.dao.{DefaultPostDao, PostDao}
import app.blog.service.DefaultPostService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext

// https://jira.spring.io/browse/SPR-14832
// https://jira.spring.io/browse/SPR-13779
class BeanInitializer extends ApplicationContextInitializer[GenericApplicationContext] {

	override def initialize(context: GenericApplicationContext) {
		context.registerBean(classOf[DefaultPostDao], () => new DefaultPostDao(context.getBean(classOf[SlickDatabase])))
		context.registerBean(classOf[DefaultPostService], () => new DefaultPostService(context.getBean(classOf[PostDao])))
	}

}
