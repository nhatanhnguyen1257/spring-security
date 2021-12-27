package spring_security_tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenManager tokenManager;

	@PostMapping("/login")
	public ResponseEntity<?> createToken(@RequestBody JwtRequestModel request) throws Exception {

		String role = "admin";
		userDetailsService.setRole(role);		
		final String jwtToken = tokenManager.generateJwtToken(userDetailsService.loadUserByUsername(request.getUsername()), role);
		return ResponseEntity.ok(new JwtResponseModel(jwtToken));
	}
	
	@GetMapping("/role")
	public String role() {
		return "role";
	}
}