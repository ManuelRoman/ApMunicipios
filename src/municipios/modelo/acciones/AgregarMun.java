package municipios.modelo.acciones;

import java.io.IOException;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import municipios.controlador.Accion;
import municipios.dao.BeanDaoConsultasImpl;
import municipios.dao.BeanDaoInsercionImpl;
import municipios.modelo.beans.BeanError;
import municipios.modelo.beans.ModeloAjax;
import municipios.modelo.entity.Comunidade;
import municipios.modelo.entity.Provincia;

/**
 * Añade un municipio
 * Participa en la acción 3
 */
public class AgregarMun implements Accion{
	
	/**
	 * Unidad de persistencia que se empleará para acceder a la base de datos.
	 * @uml.property  name="UP"
	 */
	private String UP = null;
	/**
	 * Bean de error para situaciones en los que el método ejecutar() devuelve false.
	 * @uml.property  name="error"
	 * @uml.associationEnd  
	 */
	private BeanError error = null;
	/**
	 * Objeto que encapsula el modelo que procesará la vista.
	 * @uml.property  name="modelo"
	 */
	private Object modelo = null;
	/**
	 * Página JSP que se devuelve como "vista" del procesamiento de la acción.
	 * @uml.property  name="vista"
	 */
	private String vista = null;
	
	/**
	 * Contexto de aplicación.
	 */
	private ServletContext Sc;

	/** 
	 * Ejecuta el proceso asociado a la acción.
	 * @param request Objeto que encapsula la petición.
	 * @param response Objeto que encapsula la respuesta.
	 * @return true o false en función de que no se hayan producido errores o lo contrario.
	 * @see fotperdido.controlador.Accion#ejecutar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean estado = true;
		ModeloAjax modelo = new ModeloAjax();
		HttpSession sesion = request.getSession();
		String idComunidad = request.getParameter("selComunidad");
		String idProvincia = request.getParameter("selProvincia");
		String nomMunicipio = request.getParameter("nomMun");
		Comunidade comunidad = null;
		Provincia provincia = null;
		System.out.println("Pretición para añadir un municipio: " +idComunidad + idProvincia + nomMunicipio);
		String json = null;
		this.error = null;
		BeanDaoInsercionImpl daoInsercion = (BeanDaoInsercionImpl) sesion.getAttribute("daoInsercion");
		if (daoInsercion == null){
			daoInsercion = new BeanDaoInsercionImpl(this.UP);
			sesion.setAttribute("daoInsercion", daoInsercion);
		}
		BeanDaoConsultasImpl daoConsultas = (BeanDaoConsultasImpl) sesion.getAttribute("daoConsultas");
		if (daoConsultas == null){
			daoConsultas = new BeanDaoConsultasImpl(this.UP);
			sesion.setAttribute("daoConsultas", daoConsultas);
		}
		try {
			daoConsultas.getConexion();
			daoInsercion.getConexion();
			comunidad = daoConsultas.getComunidade(Integer.parseInt(idComunidad));
			provincia = daoConsultas.getProvincia(Short.parseShort(idProvincia));
			daoInsercion.insertaMunicipio(comunidad, provincia, nomMunicipio);
		} catch (RollbackException e) {
			error = new BeanError(3, "El Municipio ya existe.");
			e.printStackTrace();
		} catch (EntityExistsException e) {
			error = new BeanError(e.getMessage(),e);
			e.printStackTrace();
		} catch (IllegalStateException e) {
			error = new BeanError(e.getMessage(),e);
			e.printStackTrace();
		} catch (Exception e) {
			error = new BeanError(e.getMessage(),e);
			e.printStackTrace();
		} finally {
			try {
				daoInsercion.close();
				daoConsultas.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar la conexión.");
				e.printStackTrace();
			}
		}
		modelo.setContentType("application/json;charset=UTF-8");
		json = new Gson().toJson(nomMunicipio);
		modelo.setRespuesta(json);
		this.setModelo(modelo);
		System.out.println("json a enviar: "+json.toString());
		return estado;
	}

	/**
	 * Devuelve el error asociado a la acción, si lo hubiera.
	 * @return
	 * @uml.property  name="error"
	 */
	@Override
	public Exception getError() {
		return error;
	}

	/**
	 * Devuelve el objeto modelo
	 * @return
	 * @uml.property  name="modelo"
	 */
	@Override
	public Object getModelo() {
		return modelo;
	}
	
	/**
	 * Método setter para la propiedad modelo.
	 * @param modelo  El modelo a establecer.
	 * @uml.property  name="modelo"
	 */
	private void setModelo(Object modelo) {
		this.modelo = modelo;
	}	

	/**
	 * Devuelve la vista que debe procesar el modelo. En caso de ser
	 * una petición AJAX, la vista deberá ser null.
	 * @return
	 * @uml.property  name="vista"
	 */
	@Override
	public String getVista() {
		// La vista devuelta por una petición AJAX es null
		return vista;
	}
	
	/**
	 * Método setter para la propiedad vista.
	 * @param vista  La vista a establecer.
	 * @uml.property  name="vista"
	 */
	private void setVista(String vista) {
		this.vista = vista;
	}
	
	/**
	 * Método getter para la propiedad UP (unidad de persistencia).
	 * @return  La unidad de persistencia UP.
	 * @uml.property  name="UP"
	 */
	private String getUP() {
		return UP;
	}
	
	/**
	 * Establece el valor de la unidad de persistencia
	 * @param up
	 * @uml.property  name="UP"
	 */
	@Override
	public void setUP(String up) {
		this.UP = up;
	}

	/**
	 * Establece el contexto de aplicación
	 * @param sc
	 * @uml.property  name="sc"
	 */
	@Override
	public void setSc(ServletContext sc) {
		this.Sc = sc;
	}

}
