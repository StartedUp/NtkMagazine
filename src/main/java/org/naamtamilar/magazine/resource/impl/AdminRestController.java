package org.naamtamilar.magazine.resource.impl;

import org.naamtamilar.magazine.controller.admin.AdminRootController;
import org.naamtamilar.magazine.domain.User;
import org.naamtamilar.magazine.resource.GlobalRestMapping;
import org.naamtamilar.magazine.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by root on 2/8/18.
 */
@RestController
public class AdminRestController extends AdminRootController implements GlobalRestMapping {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/member-details-approval", method = RequestMethod.POST)
    public ResponseEntity memberDetailsApproval(@RequestParam Integer id) {
        try {
            Optional<User> optionalUser = userService.findById(Long.valueOf(id));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setAdminApproved(!user.isAdminApproved());
                User save = userService.saveOrUpdate(user);
            }
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(false);
    }
}
