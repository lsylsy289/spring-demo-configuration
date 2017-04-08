package com.kdev.app;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("{}", "JstlView Response - last");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public @ResponseBody Object json(){
		logger.info("{}", "JsonView Response");
		Map<String, Object> response = new HashMap<String, Object>();
		
		List<String> list = new ArrayList<String>();
		list.add("Spring Framework Core 4.3.4");
		list.add("Spring Framework WebMvc 4.3.4");
		list.add("Spring Framework JDBC 4.3.4");
		
		response.put("items", list);
		return response;
	}
	
	@RequestMapping(value = "/freemarker", method = RequestMethod.GET)
	public String freemarker(){
		logger.info("{}", "Freemarker Template Engine Example - first");
		return "freemarker";
	}
	
}
