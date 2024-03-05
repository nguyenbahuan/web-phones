package com.example.springmvc.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ConvertSlug {

	public static String generateSlug(String input) {
		if (input == null) {
			return "";
		}
		String slug = StringUtils.trimAllWhitespace(input);
		slug = StringUtils.replace(slug, " ", "-");
		slug = slug.toLowerCase();
		return slug;
	}
}
