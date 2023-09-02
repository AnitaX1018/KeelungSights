package ntou.q5.KeelungSights;

import ntou.q5.KeelungSights.repository.KeelungSightsCrawler;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeelungSightsApplication implements ApplicationRunner {

	private final KeelungSightsCrawler crawler;
	public KeelungSightsApplication(KeelungSightsCrawler crawler) {
		this.crawler = crawler;
	}

	public static void main(String[] args) {
		SpringApplication.run(KeelungSightsApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//將data存進MongoDB
		crawler.saveItemsToMongoDB();
		System.out.println("ok");
	}
}
