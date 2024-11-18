package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"app", "api"})
public class ConnectHubWebServer {
    public static void main(String[] args) {
        final Repositories repositories = new Repositories();

        SpringApplication.run(ConnectHubWebServer.class, args);
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				repositories.closeDatabaseConnection();
				System.out.println("Discconected to the database.");
			}
		});
    }
}
