package net.jin.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import net.jin.common.security.jwt.credential.*;
import net.jin.response.*;

@RestController
@Api(tags = "Authentication API")
public class AuthenticationController {
	
	@ApiOperation("Authenticate to receive token")
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody Credential req) throws Exception {
		return ResponseEntity.ok(new AuthenticationResponse());
	}

}
