package com.github.vickyrai01.salesmanagement;

import com.github.vickyrai01.salesmanagement.model.auth.Rol;
import com.github.vickyrai01.salesmanagement.model.auth.User;
import com.github.vickyrai01.salesmanagement.model.enums.RolName;
import com.github.vickyrai01.salesmanagement.repository.RolRepository;
import com.github.vickyrai01.salesmanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SalesmanagementapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesmanagementapiApplication.class, args);
	}

	/*
	@Bean
	CommandLineRunner init(UserRepository userRepository, RolRepository rolRepository){

		return args -> {

			// Create ROLES
			Rol adminRol = Rol.builder()
					.rolName(RolName.ADMIN)
					.build();

			Rol sellerRol = Rol.builder()
					.rolName(RolName.SELLER)
					.build();

			Rol stock_managerRol = Rol.builder()
					.rolName(RolName.STOCK_MANAGER)
					.build();

			rolRepository.saveAll(List.of(adminRol, sellerRol, stock_managerRol));
			// Create users

			User adminUser = User.builder()
					.username("Victoria")
					.password("2003")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(List.of(adminRol))
					.build();

			User sellerUser = User.builder()
					.username("Osvaldo")
					.password("2003")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(List.of(sellerRol))
					.build();

			User stock_managerUser = User.builder()
					.username("Donna")
					.password("2003")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(List.of(stock_managerRol))
					.build();

			userRepository.saveAll(List.of(adminUser, sellerUser, stock_managerUser));
		};



	}
	*/
}