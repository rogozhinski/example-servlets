package test.jsp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		HandlerCollection handlers = new HandlerCollection();

		WebAppContext webapp = new WebAppContext();
		webapp.setResourceBase(Paths.get("src", "main", "webapp").toString());
		webapp.setDescriptor(Paths.get("target", "web.xml").toString());
		webapp.setContextPath("/");
		handlers.addHandler(webapp);

		ClassList classlist = ClassList.setServerDefault(server);
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

		server.setHandler(handlers);

		server.start();
		server.join();
	}
}
