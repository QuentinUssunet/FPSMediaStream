package launcher;

import application.Config;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

public class Lanceur extends AbstractVerticle{
	
	public static void main(String[] args) {
		System.out.println("App lancée !!");
		Runner.runExample(Lanceur.class);
	}
	
	@Override
	public void start() throws Exception {
		
		final long lastTime[] = new long [] {0};
		
		HttpServer httpServer = vertx.createHttpServer(new HttpServerOptions().setMaxWebsocketFrameSize(100655360));
		httpServer.requestHandler(req -> {
			if (req.uri().equals("/"))
				req.response().sendFile("index.html");
			else if (req.uri().equals("/js/main.js"))
				req.response().sendFile("main.js");
		});
		
		Config config = new Config();
		httpServer.websocketHandler(sws -> {
			
			sws.handler(buffer -> {
				long startTime = System.currentTimeMillis();
				long deltaT = startTime - lastTime[0];
				long FPS = 1000/deltaT;
				lastTime[0] = startTime;
				System.out.println("starttime = "+startTime+" lastTime = "+lastTime[0]+" deltaT = "+deltaT+" FPS = "+FPS);				
				sws.writeTextMessage("");
			});
		});
		httpServer.listen(8080);
	}
}
