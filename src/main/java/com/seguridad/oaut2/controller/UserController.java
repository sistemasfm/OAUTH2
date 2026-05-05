package com.seguridad.oaut2.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/dashboard")
	public Map<String, Object> dashboard(@AuthenticationPrincipal OidcUser oidcUser) {
		String nombre = oidcUser.getFullName();
		String email = oidcUser.getEmail();
		String foto = oidcUser.getPicture();
		String googleId = oidcUser.getSubject();
		
		return Map.of(
			"nombre", nombre,
			"email", email,
			"foto", foto,
			"googleId", googleId
		);
	}

	/**
     * Ejemplo alternativo usando OAuth2User genérico.
     * Útil cuando no usas el scope "openid" o trabajas con
     * un proveedor que no implementa OIDC.
     */
    @GetMapping("/perfil")
    public Map<String, Object> perfil(@AuthenticationPrincipal OAuth2User oauth2User) {

        // getAttributes() retorna el JSON completo recibido del UserInfo endpoint
        Map<String, Object> atributos = oauth2User.getAttributes();

        return Map.of(
            "nombre", atributos.getOrDefault("name", "Desconocido"),
            "email", atributos.getOrDefault("email", ""),
            "autoridades", oauth2User.getAuthorities().toString()
        );
    }
}
