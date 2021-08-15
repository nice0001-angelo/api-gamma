/**
 * 
 */
package net.jin.controller;

import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/boards")
public class BoardController {
	
	private final BoardService boardService;
	
	

}
