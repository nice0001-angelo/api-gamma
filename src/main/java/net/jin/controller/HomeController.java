/**
 * 
 */
package net.jin.controller;

import java.time.*;
import java.time.format.*;
import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author njh
 *
 */
@Controller
public class HomeController {
	
	@RequestMapping(value="/", method=RequestMethod.GET )
	public String home(Locale locale, Model model) {
		LocalDateTime now = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h시 m분 s초");
		String formattedNow = now.format(formatter);
		
		return "home";
	}

}
