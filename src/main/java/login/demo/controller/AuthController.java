package login.demo.controller;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import login.demo.Entity.RolModel;
import login.demo.Entity.UsuarioModel;
import login.demo.Entity.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @PostMapping(value = "/Usuario/Nuevo")
    public String nuevo(@Valid @ModelAttribute("formData") UsuarioFormBindingResult binding,
                        Model model){
        if(binding.hasErrors()){
            return "usuarios/nuevo";
        }
        try {
            UsuarioModel usuario = formData.toModel();
            usuario.setRol(new RolModel(2L,"Miembro"));
            usuarioService.guardar(usuario);
            return "redirect:/Usuarios";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            binding.rejectValue("nombre", "error.user", "Nombre de usuario ya existe");
            return "usuarios/nuevo";
        }
    }

}
