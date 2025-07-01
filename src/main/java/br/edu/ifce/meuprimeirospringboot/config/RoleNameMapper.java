package br.edu.ifce.meuprimeirospringboot.config;

import java.util.LinkedHashMap;
import java.util.Map;

public class RoleNameMapper {
	
	public static Map<String, String> getRoleDisplayNames() {
		Map<String, String> map = new LinkedHashMap<>();
		
		map.put("ROLE_ADMIN", "Administrador");
		map.put("ROLE_USER", "Usuário");
		
		return map;
	}
}
