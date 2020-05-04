package org.yb.securityservice;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.yb.securityservice.entities.AppRole;
import org.yb.securityservice.entities.Status;
import org.yb.securityservice.service.AccountService;

@SpringBootApplication
public class SecurityServiceApplication {

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start() {
		return args -> {
			accountService.deleteRole();
			accountService.deleteUsers();
			accountService.deleteStatus();
			AppRole admin = new AppRole();
			admin.setRole("ADMIN");
			AppRole user = new AppRole();
			user.setRole("USER");
			Stream.of(admin, user).forEach((e) -> accountService.saveRole(e));

			// LIST OF USERS

			Stream.of("younes", "user", "Youssef").forEach(

					(u) -> accountService.saveUser(u, "123456", "123456")

			);
			accountService.addRoleToUser("ADMIN", "younes");
		};
	}

	@Bean
	public BCryptPasswordEncoder getBean() {
		return new BCryptPasswordEncoder();
	}
}
