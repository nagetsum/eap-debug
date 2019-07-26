package jbeap

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class StressSessionRepl extends Simulation {

  var httpProtocol = http
    .baseUrl("http://localhost:8080/session-repl-bench")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")

  val scn = scenario("StressSessionRepl")
    .during(120) {   // exec during 120 seconds per a user
      repeat(100) {  // call continuously http...//session-repl-bench/session 100 times without interval
        exec(
          http("session")
            .get("/session")
            .check(status.is(200))
        )
      }
      .exec(          // call http...//session-repl-bench/invalidate after caling /session 100 times
        http("invalidate")
          .get("/invalidate")
          .check(status.is(200)))
      .pause(1)      // then sleep 1 second
    }

  setUp(
    scn.inject(rampUsers(1000) during (10 seconds)) // concurrent access users 1000, ramp-up time 10 sec
  ).protocols(httpProtocol)
}
