package com.hb.comoencasa.adapters.primary;

import com.hb.comoencasa.domain.Producto;
import com.hb.comoencasa.domain.Role;
import com.hb.comoencasa.domain.User;
import com.hb.comoencasa.ports.primary.ProductoService;
import com.hb.comoencasa.ports.primary.RoleService;
import com.hb.comoencasa.ports.primary.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/producto")
public class ProductoRest {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    //@Autowired
    //private AuthService authService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    @PostMapping ("/registrar")
    public Producto registrar (@RequestBody Producto producto){
        Producto p= null;
        User u = new User();
        try{
            p  =producto;
            productoService.register(p);
            return p;

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede registrar un Producto");
        }
    }

    @Transactional
    @GetMapping("/listar")
    public List<Producto> listar (){
        return productoService.listProducts();
    }

    @GetMapping ("/obtener/{id}")
    public Producto obtenerProducto (@PathVariable(value="id") Long id){
        Producto p=null;
        try{
            p=productoService.getProduct(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontró producto");
        }
        return p;
    }

    @PutMapping("/actualizar/{id}")
    public Producto actualizarProducto(@PathVariable(value = "id") Long id, @RequestBody Producto producto) throws Exception{
        Producto p = null;
        try {
            p = productoService.updateProduct(id,producto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo concretar");
        }
        return p;
    }

    @DeleteMapping("/eliminar/{id}")
    public Producto eliminarProducto(@PathVariable(value = "id") Long id) throws Exception {
        Producto p = null;
        try {
            p = productoService.deleteProduct(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo concretar");
        }
        return p;
    }
}