package app.blog.util

import reactor.core.publisher.Mono

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

object ReactiveConversions {

	implicit def mono2Future[T](mono: Mono[T]): Future[T] = {
		val promise = Promise[T]()
		mono.doOnSuccess(value => promise.success(value))
			.doOnError(e => promise.failure(e))
		promise.future
	}

	implicit def future2Mono[T](future: Future[T]): Mono[T] = {
		Mono.create { sink =>
			future.onComplete {
				case Success(value) => sink.success(value)
				case Failure(e) => sink.error(e)
			}
		}
	}

}
