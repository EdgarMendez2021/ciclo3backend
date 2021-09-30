package co.edu.ciclo3backend;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.DAO.ciclo3backend.UsuarioDAO;
import co.edu.DTO.ciclo3backend.*;

@RestController
@CrossOrigin(origins= {"http://localhost:8081"})
public class UsuarioController {
	/**
	 * recibe la peticion para el listado de clientes
	 * @return
	 */
	@RequestMapping("/listaUsuarios")
	public ArrayList<UsuarioVO> listaDeUsuarios(){
		UsuarioDAO dao = new UsuarioDAO();
		
		ArrayList<UsuarioVO>listado = dao.listaDeUsuarios();
		
		return listado;
	}
	
	/**
	 * Peticion para buscar un cliente por su ID
	 * @param id
	 * @return
	 */
	@RequestMapping("/traerUsuario")
	public ArrayList<UsuarioVO> buscarUsuario(String cedula_usuario){
		UsuarioDAO dao = new UsuarioDAO();
		return dao.buscarUsuarioCedula(cedula_usuario);
	}
	
	/**
	 * Crea un nuevo cliente en la base de datos
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @return
	 */
	@RequestMapping("/crearUsuario")
	public boolean crearUsuario(String cedula_usuario, String nombre_usuario, String email_usuario,String usuario,String password) {
		UsuarioVO usuarioC = new UsuarioVO();
		usuarioC.setCedula_usuario(Long.parseLong(cedula_usuario));
		usuarioC.setNombre_usuario(nombre_usuario);
		usuarioC.setEmail_usuario(email_usuario);
		usuarioC.setUsuario(usuario);
		usuarioC.setContrasena(password);
				
		UsuarioDAO dao = new UsuarioDAO();
		boolean rta =dao.crearUsuario(usuarioC);
		return rta;
	}
	
	/**
	 * Actualiza el nombre,email,usuario y Contraseña de un usuario, de acuerdo a su cedula
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @return
	 */
	@RequestMapping("/actualizarUsuario")
	public boolean actualizarUsuario(String cedula_usuario,String nombre_usuario, String email_usuario,String usuario,String password) {
		UsuarioDAO dao = new UsuarioDAO();	
		
		UsuarioVO usuarioA = new UsuarioVO();
		usuarioA.setCedula_usuario(Long.parseLong(cedula_usuario));
		usuarioA.setNombre_usuario(nombre_usuario);
		usuarioA.setEmail_usuario(email_usuario);
		usuarioA.setUsuario(usuario);
		usuarioA.setContrasena(password);
		
		boolean rta=dao.actualizarUsuario(usuarioA);
		return rta;
	}
	
	/**
	 * Elimina un usuario de acuerdo a su cedula
	 * @param Cedula
	 * @return
	 */
	@RequestMapping("/borrarUsuario")
	public boolean borrarUsuario(String cedula_usuario) {
		UsuarioDAO dao = new UsuarioDAO();	
		return dao.borrarUsuario(Long.parseLong(cedula_usuario));
	}
}

