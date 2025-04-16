package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;

import lk.ijse.voaestheticlounge.dto.AuthDTO;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.dto.UserDTO;
import lk.ijse.voaestheticlounge.service.UserService;
import lk.ijse.voaestheticlounge.service.impl.UserServiceImpl;
import lk.ijse.voaestheticlounge.util.JwtUtil;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userServiceImpl;

    //constructor injection
    public UserController(UserService userService, JwtUtil jwtUtil, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userServiceImpl = userServiceImpl;
    }
    @PostMapping(value = "/register")
/*    @PreAuthorize("hasAnyAuthority('ADMIN' ,'USER')")*/
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO userDTO/*,@RequestHeader("Authorization") String token*/) {
      /*  jwtUtil.getUserRoleCodeFromToken(token.substring(7));*/
            try {
                int res = userService.saveUser(userDTO);
                AuthDTO authDTO = null;

                if (res == VarList.Created) {
                    String token1 = jwtUtil.generateToken(userDTO);
                    authDTO = new AuthDTO(userDTO.getEmail(), userDTO.getRole(), token1);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                } else if (res == VarList.Not_Acceptable) {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
            }
    }


    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN' ,'USER')")
    public ResponseEntity <ResponseDTO> deleteUser(@PathVariable Long id,@RequestHeader("Authorization") String token) {
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        userServiceImpl.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", null));
    }
    @PutMapping("/update/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> updateUserRole(@PathVariable String email, @RequestBody Map<String, String> request ,@RequestHeader("Authorization") String token) {
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        String newRole = request.get("role");
        System.out.println(newRole);
        if (newRole == null || newRole.isEmpty()) {
            return ResponseEntity.badRequest().body("Role cannot be empty");
        }

        userService.updateUserRole(email, newRole);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "User role updated successfully", null));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllUsers(@RequestHeader(value = "Authorization", required = false) String token) {
        System.out.println("Received Token: " + token);
        String jwt = token.substring(7);
        String username = jwtUtil.getUsernameFromToken(jwt);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, "Invalid Token", null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", userService.getAll()));
    }
    @GetMapping("/get/{email}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token) {
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));

        UserDTO user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(VarList.Not_Found, "User not found", null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", user));
    }

}
