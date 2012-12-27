package org.maxfridbe.tests.filters

import java.net.URL
import org.scalatra._

class MyScalatraFilter extends ScalatraFilter{
  
  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Saying hello to Scalatra from Maven.
      </body>
    </html>
  }


}
