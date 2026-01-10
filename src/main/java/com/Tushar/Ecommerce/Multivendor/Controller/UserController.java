package com.Tushar.Ecommerce.Multivendor.Controller;

import com.Tushar.Ecommerce.Multivendor.Modal.User;
import com.Tushar.Ecommerce.Multivendor.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getUser(@RequestHeader ("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(user);
    }

}
