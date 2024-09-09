package br.ifba.turisty.web.controllers.user;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.turisty.domain.user.dtos.UserRequestDTO;
import br.ifba.turisty.domain.user.dtos.UserResponseDTO;
import br.ifba.turisty.domain.user.dtos.UserUpdateDataDTO;
import br.ifba.turisty.domain.user.model.User;
import br.ifba.turisty.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper objectMapperUtil;

    @GetMapping // Mapeia requisições HTTP GET para o caminho "/user"
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(this.userService.findAll(), UserResponseDTO.class));
    }

    @GetMapping("/{id}") // Mapeia requisições HTTP GET para o caminho "/user/{id}"
    public ResponseEntity<String> findById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            UserResponseDTO userResponseDTO = objectMapperUtil.map(user, UserResponseDTO.class);
            
            return ResponseEntity.status(HttpStatus.OK).body("User found: " + userResponseDTO.getName());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found by id: " + id);
        }
    }

    @PostMapping("/auth") // Mapeia requisições HTTP POST para "/user/auth"
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO data) {
        User createUser = objectMapperUtil.map(data, User.class);
        User user = userService.save(createUser);
        UserResponseDTO responseDTO = objectMapperUtil.map(user, UserResponseDTO.class);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}") // Mapeia requisições HTTP PUT para "/user/{id}"
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDataDTO data) {
        User updatedUser = objectMapperUtil.map(data, User.class);
        User user = userService.update(id, updatedUser);
        UserResponseDTO responseDTO = objectMapperUtil.map(user, UserResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}") // Mapeia requisições HTTP DELETE para "/user/{id}"
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("User with id: " + id + " was successfully deleted!");
    }
}
