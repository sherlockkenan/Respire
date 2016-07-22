package respire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import antlr.Token;
import respire.Service.TimeManager;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
	new TimeManager();
    SpringApplication.run(Application.class, args);
  }

}
