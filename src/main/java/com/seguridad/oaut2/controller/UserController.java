package com.seguridad.oaut2.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
}
