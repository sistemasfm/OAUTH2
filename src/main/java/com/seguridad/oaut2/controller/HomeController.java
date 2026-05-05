package com.seguridad.oaut2.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@GetMapping("/")
	@ResponseBody
	public Map<String, String> home() {
		return Map.of(
			"mensaje", "Inicio publico",
			"login", "/oauth2/authorization/google",
			"dashboard", "/dashboard"
		);
	}

	@GetMapping("/public/ping")
	@ResponseBody
	public Map<String, String> ping() {
		return Map.of("estado", "ok");
	}

	@GetMapping("/login")
	@ResponseBody
	public String login(@RequestParam(defaultValue = "false") boolean error) {
		String mensajeError = error ? "<p>La autenticacion con Google fallo.</p>" : "";
		return """
			<!DOCTYPE html>
			<html lang="es">
			<head>
			    <meta charset="UTF-8">
			    <title>Login OAuth2</title>
			</head>
			<body>
			    <h1>Login con Google</h1>
			    %s
			    <a href="/oauth2/authorization/google">Iniciar sesion con Google</a>
			</body>
			</html>
			""".formatted(mensajeError);
	}
}
