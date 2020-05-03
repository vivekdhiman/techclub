package com.contentful.demo;

import com.contentful.demo.constants.SystemConstants;
import com.contentful.java.cda.CDAClient;
import com.github.mjeanroy.springmvc.view.mustache.configuration.autoconfiguration.SpringMustacheAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;

@SpringBootApplication
public class DemoApplication {
	public static CDAClient client;
	public static void main(String[] args) {
		client = CDAClient.builder()
				.setSpace(SystemConstants.SPACE_ID)
				.setToken(SystemConstants.ACCESS_TOKEN)
				.build();

		SpringApplication app = new SpringApplication(DemoApplication.class);
		Banner banner = (environment, sourceClass, out) -> {
			out.println("  ______   ____            _             _    __       _   ____             _              ______  ");
			out.println(" / / / /  / ___|___  _ __ | |_ ___ _ __ | |_ / _|_   _| | / ___| _ __  _ __(_)_ __   __ _  \\ \\ \\ \\ ");
			out.println("/ / / /  | |   / _ \\| '_ \\| __/ _ \\ '_ \\| __| |_| | | | | \\___ \\| '_ \\| '__| | '_ \\ / _` |  \\ \\ \\ \\");
			out.println("\\ \\ \\ \\  | |__| (_) | | | | ||  __/ | | | |_|  _| |_| | |_ ___) | |_) | |  | | | | | (_| |  / / / /");
			out.println(" \\_\\_\\_\\  \\____\\___/|_| |_|\\__\\___|_| |_|\\__|_|  \\__,_|_(_)____/| .__/|_|  |_|_| |_|\\__, | /_/_/_/ ");
			out.println("                                                                |_|                 |___/          ");
			out.println();
		};

		app.setBanner(banner);
		app.run(DemoApplication.class, args);
	}

}
