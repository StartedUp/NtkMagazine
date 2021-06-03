package org.naamtamilar.magazine.resource.impl;

import org.naamtamilar.magazine.domain.User;
import org.naamtamilar.magazine.resource.GlobalRestMapping;
import org.naamtamilar.magazine.resource.Resource;
import org.naamtamilar.magazine.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;
import java.util.Collection;
import java.util.Optional;

@RestController
public class UserRestController implements GlobalRestMapping{

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<User> getUsersByEmail(@RequestBody String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/users")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("ok");
    }
}
