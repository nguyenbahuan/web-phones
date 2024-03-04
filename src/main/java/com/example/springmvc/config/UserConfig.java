package com.example.springmvc.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springmvc.model.Categories;
import com.example.springmvc.model.Products;
import com.example.springmvc.model.Role;
import com.example.springmvc.model.User;
import com.example.springmvc.repository.CategoriesRepository;
import com.example.springmvc.repository.ProductsRepository;
import com.example.springmvc.repository.RoleRepository;
import com.example.springmvc.repository.UserRepository;

@Configuration
public class UserConfig {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository, ProductsRepository productsRepository,
			CategoriesRepository categoriesRepository, RoleRepository roleRepository ) {
		return arg -> {
			Categories category1 = new Categories("Apple");
			Categories category2 = new Categories("Android");
			Role role1 = new Role("ADMIN");
			Role role2 = new Role("USER");
			Role role3 = new Role("STAFF");
//			List<Phones> phones = new ArrayList<Phones>();
			List<Categories> categories = new ArrayList<>();
			List<Role> roles  = new ArrayList<>();
			categories.add(category1);
			categories.add(category2);
			roles.add(role1);
			roles.add(role2);
			roles.add(role3);
			categoriesRepository.saveAll(categories);
			roleRepository.saveAll(roles);
			repository.saveAll(List.of(
					new User(1, "Huan", "123@gmail.com", passwordEncoder.encode("123456"),
							LocalDate.of(2002, Month.JANUARY, 20),role1),
					new User(2, "Huan", "12@gmail.com", passwordEncoder.encode("123456"),
							LocalDate.of(2006, Month.JANUARY, 20), role3)));

			
			try {
				productsRepository.saveAll(List.of( new Products("SamSung", 100000,category2), new Products("IP15", 100000,category1)));
			} catch (Exception e) {
				e.printStackTrace();
			}

		};
	}
}
