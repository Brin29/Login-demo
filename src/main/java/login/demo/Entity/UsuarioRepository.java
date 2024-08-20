package login.demo.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    public UsuarioModel findByNombre(String nombre);

}
