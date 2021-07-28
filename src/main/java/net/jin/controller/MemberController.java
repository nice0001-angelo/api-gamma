/**
 * 
 */
package net.jin.controller;

import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.slf4j.*;

/**
 * @author njh
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="", method = RequestMethod.GET)
public interface MemberController {

	private final MemberService memberService;
}
