package br.edu.ifce.meuprimeirospringboot.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
	
	public static List<String> getCurrentUserRoles() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
			return userDetails.getAuthorities().stream()
					.map(GrantedAuthority:: getAuthority)
					.collect(Collectors.toList());
		}
		
		return List.of();
	}
	
	public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return null;
    }
	
	public static boolean hasRole(String role) {
        return getCurrentUserRoles().contains(role);
    }
}
