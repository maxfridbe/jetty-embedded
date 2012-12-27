package org.maxfridbe.tests

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.servlet.{DefaultServlet, ServletHolder}
import java.awt.Desktop
import java.net.URI
import grizzled.slf4j.Logging
import org.maxfridbe.tests.servlets.{APIScalatraServlet, HomeScalatraServlet}


object JettyEmbedded extends Logging {
	def main(args: Array[String]) {
		val server = new Server(8080)
		val context: WebAppContext = new WebAppContext()
		context.setContextPath("/")
		context.setServer(server)
		val defaultHolder = new ServletHolder(classOf[DefaultServlet])
		defaultHolder.setName("default")

		val webDir: String = this.getClass()
			.getClassLoader()
			.getResource("webapp")
			.toExternalForm()

		logger.info("URL is %s".format(webDir))
		context.setResourceBase(webDir)


		context.addServlet(defaultHolder, "/static/*")
		context.addServlet(classOf[APIScalatraServlet],"/api/*")
		context.addServlet(classOf[HomeScalatraServlet], "/*")
		server.setHandler(context)

		addShutdownHook(server)

		try {
			server.start()
			Desktop.getDesktop().browse(new URI("http://localhost:8080"))
			server.join()
		} catch {
			case e: Exception => {
				e.printStackTrace()
				System.exit(1)
			}
		}
	}

	def addShutdownHook(server:Server) {
		val shutdown = new Thread(new Runnable() {
			def run {
				logger.info("Shutting down cleanly :-|")
				server.stop()
			}
		}, "ShutdownHook")
		Runtime.getRuntime().addShutdownHook(shutdown)
	}
}

