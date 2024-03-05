package com.example.springmvc.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.example.springmvc.util.ConvertSlug;

@Configuration
public class UserConfig {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	ConvertSlug convertSlug;

	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository, ProductsRepository productsRepository,
			CategoriesRepository categoriesRepository, RoleRepository roleRepository) {
		return arg -> {
			Categories category1 = new Categories("Điện thoại", LocalDateTime.now(), true);
			Categories category2 = new Categories("Ti vi", LocalDateTime.now(), true);
			Categories category3 = new Categories("Tủ Lạnh", LocalDateTime.now(), true);
			Role role1 = new Role("ADMIN");
			Role role2 = new Role("USER");
			Role role3 = new Role("STAFF");
//			List<Phones> phones = new ArrayList<Phones>();
			List<Categories> categories = new ArrayList<>();
			List<Role> roles = new ArrayList<>();
			categories.add(category1);
			categories.add(category2);
			categories.add(category3);
			roles.add(role1);
			roles.add(role2);
			roles.add(role3);
			categoriesRepository.saveAll(categories);
			roleRepository.saveAll(roles);
			repository.saveAll(List.of(
					new User(1, "Huan", "123@gmail.com", passwordEncoder.encode("123456"),
							LocalDate.of(2002, Month.JANUARY, 20), role1),
					new User(2, "Huan", "12@gmail.com", passwordEncoder.encode("123456"),
							LocalDate.of(2006, Month.JANUARY, 20), role3)));

			try {
				productsRepository.saveAll(List.of(
						new Products("SamSung", "Máy mới 100% , chính hãng Apple Việt Nam.\r\n"
								+ "CellphoneS hiện là đại lý bán lẻ uỷ quyền iPhone chính hãng VN/A của Apple Việt Nam",
								100000, category1, 100, LocalDateTime.now(), true),
						new Products("IP15","Máy mới 100% , chính hãng Apple Việt Nam.\r\n"
								+ "CellphoneS hiện là đại lý bán lẻ uỷ quyền iPhone chính hãng VN/A của Apple Việt Nam", 100000, category1,100, LocalDateTime.now(), true),
						new Products("Aqua 130 lít AQR-T150FA(BS)", "Máy mới 100% , chính hãng Apple Việt Nam.\r\n"
								+ "CellphoneS hiện là đại lý bán lẻ uỷ quyền iPhone chính hãng VN/A của Apple Việt Nam",4590000, category2,100, LocalDateTime.now(), true),
						new Products("Samsung Smart TV UA65AU7002", "Máy mới 100% , chính hãng Apple Việt Nam.\r\n"
								+ "CellphoneS hiện là đại lý bán lẻ uỷ quyền iPhone chính hãng VN/A của Apple Việt Nam",13400000, category3,100, LocalDateTime.now(), true)));
			} catch (Exception e) {
				e.printStackTrace();
			}

		};
	}
}
