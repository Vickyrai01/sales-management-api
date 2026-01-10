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
}