package co.edu.DAO.ciclo3backend;

import java.sql.*;
import java.util.ArrayList;

import co.edu.DTO.ciclo3backend.*;

public class UsuarioDAO {
	
	/**
	 * Permite consultar el listado de Usuario
	 * @return
	 */
	public ArrayList<UsuarioVO> listaDeUsuarios(){
		ArrayList<UsuarioVO> misUsuarios = new ArrayList<UsuarioVO>();
		Conexion conex = new Conexion();
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios");
			ResultSet res = consulta.executeQuery();
			
			while(res.next()){
				UsuarioVO usuario = new UsuarioVO();
				
				usuario.setCedula_usuario(res.getLong("cedula_usuario"));
				usuario.setNombre_usuario(res.getString("nombre_usuario"));
				usuario.setEmail_usuario(res.getString("email_usuario"));
				usuario.setUsuario(res.getString("usuario"));
				usuario.setContrasena(res.getString("password"));
				misUsuarios.add(usuario);
			}
			res.close();
			consulta.close();
			conex.desconectar();
		}catch(Exception e) {
			System.out.println("No se pudo conectar");
		}
		return misUsuarios;
	}
	
	/**
	 * Busca un usuario por su Cedula
	 * @param id
	 * @return
	 */
	public ArrayList<UsuarioVO> buscarUsuarioCedula(String cedula_usuario){
		ArrayList<UsuarioVO> misUsuarios = new ArrayList<UsuarioVO>();
		Conexion conex = new Conexion();
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios WHERE cedula_usuario = ?");
			
			consulta.setLong(1, Long.parseLong(cedula_usuario));
			ResultSet res = consulta.executeQuery();
			while(res.next()){
				UsuarioVO usuario = new UsuarioVO();
				
				usuario.setCedula_usuario(res.getLong("cedula_usuario"));
				usuario.setNombre_usuario(res.getString("nombre_usuario"));
				usuario.setEmail_usuario(res.getString("email_usuario"));
				usuario.setUsuario(res.getString("usuario"));
				usuario.setContrasena(res.getString("password"));
				misUsuarios.add(usuario);
			}
			res.close();
			consulta.close();
			conex.desconectar();			
		}catch(Exception e) {
			System.out.println("No se pudo conectar");
		}
		return misUsuarios;
	}
	
	/**
	 * determina si un cliente existe segun el Documento
	 * @param documento
	 * @return
	 */
	public boolean existeUsuario(Long cedula_usuario) {
		boolean existe = false;
		Conexion conex = new Conexion();
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios WHERE cedula_usuario = ?");
			
			consulta.setLong(1,cedula_usuario);
			ResultSet res = consulta.executeQuery();
			if (res.next()) {
				existe = true;
			}
			res.close();
			consulta.close();
			conex.desconectar();				
		}catch(Exception e) {
			System.out.println("No se pudo conectar");
		}
		return existe;
	}
	
	public boolean crearUsuario(UsuarioVO usuario) {
		boolean creado=false;
		if(!existeUsuario(usuario.getCedula_usuario())) {
			Conexion conex = new Conexion();
			try {
				Statement consulta = conex.getConnection().createStatement();
				String sql = "INSERT INTO usuarios (cedula_usuario,nombre_usuario,email_usuario,usuario,password) VALUES "+
						"("+usuario.getCedula_usuario()+",'"+usuario.getNombre_usuario()+"','"+usuario.getEmail_usuario()+"','"+usuario.getUsuario()+"','"+usuario.getPassword()+"');";
				consulta.executeUpdate(sql);
				consulta.close();
				conex.desconectar();
				creado = true;
			}catch(SQLException e) {
				System.out.println("No se pudo crear el usuario.");
			}
		}else {
			System.out.println("El usuario ya exite.");
		}
		return creado;
	}
	
	public boolean actualizarUsuario(UsuarioVO usuario) {
		boolean actualizado=false;
		if(existeUsuario(usuario.getCedula_usuario())) {
			Conexion conex = new Conexion();
			try {
				Statement consulta = conex.getConnection().createStatement();
				String SQL = "UPDATE usuarios"+
						" SET nombre_usuario='"+usuario.getNombre_usuario()+"'"+ 
						",email_usuario='"+usuario.getEmail_usuario()+"'"+ 
						",usuario='"+usuario.getUsuario()+"'"+
						",password='"+usuario.getPassword()+"' "+
						"WHERE cedula_usuario = "+usuario.getCedula_usuario();
						
				consulta.executeUpdate(SQL);
				consulta.close();
				conex.desconectar();
				actualizado = true;
			}catch(SQLException e) {
				System.out.println("No se pudo actualizar el Usuario.");
			}
		}else {
			System.out.println("El usuario NO exite.");
		}
		return actualizado;		
	}
	
	public  boolean borrarUsuario(Long cedula_usuario) {
		boolean borrado=false;
		if(existeUsuario(cedula_usuario)){
			Conexion conex = new Conexion();
			try {
				Statement consulta = conex.getConnection().createStatement();
				String sql = "DELETE FROM usuarios WHERE cedula_usuario = "+cedula_usuario;
						
				consulta.executeUpdate(sql);
				consulta.close();
				conex.desconectar();
				borrado = true;
			}catch(SQLException e) {
				System.out.println("No se pudo eliminar el Usuario.");
			}
		}else {
			System.out.println("El Usuario NO exite.");
		}
		return borrado;			
	}

	
}
