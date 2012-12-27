package org.maxfridbe.tests.servlets

import net.liftweb.json.{NoTypeHints, Serialization}
import org.scalatra.ScalatraServlet
import grizzled.slf4j.Logging


class APIScalatraServlet extends ScalatraServlet with Logging {
	implicit val formats = Serialization.formats(NoTypeHints)

	case class Item(name: String, desc: String)

	//   /api/...


	get("/items/:id") {
		logger.debug("Getting ID")
		contentType = "application/json"

		val lst = (0 to 100) map (a => Item("name " + a, "desc " + a))

		Serialization.write(lst)
	}

}