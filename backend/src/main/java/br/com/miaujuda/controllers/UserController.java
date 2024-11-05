package br.com.miaujuda.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.miaujuda.model.User;
import br.com.miaujuda.serices.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            User newUser = userService.register(user);
            response.put("status", "success");
            response.put("message", "Usuário registrado com sucesso");
            response.put("user", newUser); 
            return ResponseEntity.status(201).body(response); 
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Erro ao registrar usuário: " + e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user) {
        System.out.println("USER: " + user);
        Map<String, Object> response = new HashMap<>();
        try {
            User loggedInUser = userService.login(user.getUsername(), user.getPassword());
            if (loggedInUser != null) {
                response.put("status", "success");
                response.put("message", "Login realizado com sucesso");
                response.put("id", loggedInUser.getId()); // Inclui o ID do usuário na resposta
                response.put("username", loggedInUser.getUsername()); // Inclui o username ou outros campos desejados

                return ResponseEntity.status(200).body(response); 
            } else {
                response.put("status", "error");
                response.put("message", "Credenciais inválidas");
                return ResponseEntity.status(401).body(response); 
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Erro ao processar login: " + e.getMessage());
            return ResponseEntity.status(500).body(response); 
        }
    }

}
