package spring_security_tutorial;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService  {

	private String[] mRole;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		Arrays.stream(mRole) .forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
		
		return User
			.withUsername(username)
			.authorities(mRole)
			.password("$2a$10$slYQmyNdGzTn7ZterwwLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6")
			.build();
	   }

	
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.mRole = new String[] { "ROLE_"+role};
	}		
	
}
