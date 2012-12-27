package org.maxfridbe.tests.servlets

import net.liftweb.json.{NoTypeHints, Serialization}
import org.scalatra.ScalatraServlet

class HomeScalatraServlet extends ScalatraServlet {
	//home page
	get("/") {
		contentType = "text/html"
		val resource = this.getClass()
			.getClassLoader()
			.getResourceAsStream("webapp/static/page/main.html")
		resource
	}

}
