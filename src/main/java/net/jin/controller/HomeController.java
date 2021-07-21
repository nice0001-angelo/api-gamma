/**
 * 
 */
package net.jin.controller;

import java.time.*;
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
		
	}

}
