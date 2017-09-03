import akka.actor.ActorSystem
import akka.http.scaladsl.{Http, server}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn

object Server {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val routes =
      path("blocks") {
        get {
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "GET blocks"))
        } ~
        post {
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "POST blocks"))
        }
      } ~
        path("peers") {
          get {
            complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "GET peers"))
          } ~
          post {
            complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "POST peers"))
          }
        }


    val bindingFuture = Http().bindAndHandle(routes, "localhost", 8080)
    println(s"simple-chain listening at http://localhost:8080/ \nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ ⇒ system.terminate())
  }

}