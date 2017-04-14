package app.blog.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.http.codec.json.{Jackson2JsonDecoder, Jackson2JsonEncoder}
import org.springframework.web.reactive.function.server.HandlerStrategies

// https://jira.spring.io/browse/SPR-15247
// http://stackoverflow.com/questions/43195987/configured-objectmapper-not-used-in-spring-boot-webflux
@Configuration
class JacksonConfig {

	@Bean
	def jackson2JsonEncoder(mapper: ObjectMapper): Jackson2JsonEncoder = new Jackson2JsonEncoder(mapper)

	@Bean
	def jackson2JsonDecoder(mapper: ObjectMapper): Jackson2JsonDecoder = new Jackson2JsonDecoder(mapper)

	@Bean
	def handlerStrategiesBuilder(encoder: Jackson2JsonEncoder, decoder: Jackson2JsonDecoder): HandlerStrategies.Builder =
		HandlerStrategies.builder().defaultCodecs(configurer => {
			configurer.jackson2Encoder(encoder)
			configurer.jackson2Decoder(decoder)
		})

	@Bean
	def scalaJacksonModule = DefaultScalaModule

}
