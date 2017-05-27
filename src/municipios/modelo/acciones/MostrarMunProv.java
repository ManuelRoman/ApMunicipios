package municipios.modelo.acciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import municipios.controlador.Accion;
import municipios.dao.BeanDaoConsultasImpl;
import municipios.modelo.beans.BeanError;
import municipios.modelo.beans.ModeloAjax;
import municipios.modelo.entity.Municipio;
import municipios.modelo.entity.Provincia;

/**
 * Devuelve una lista de todos los municipios de una provincia
 * Participa en la acción 1
 */
public class MostrarMunProv implements Accion{
	
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
		String idProvincia = request.getParameter("selProvincia");
		System.out.println("Provincia seleccionada: " + idProvincia);
		String json = null;
		HashMap<Integer, ArrayList> listaMunicipios = new HashMap<Integer, ArrayList>();
		Provincia provincia = new Provincia();
		this.error = null;
		BeanDaoConsultasImpl daoConsultas = (BeanDaoConsultasImpl) sesion.getAttribute("daoConsultas");
		if (daoConsultas == null){
			daoConsultas = new BeanDaoConsultasImpl(this.UP);
			sesion.setAttribute("daoConsultas", daoConsultas);
		}
		try {
			daoConsultas.getConexion();
			provincia = daoConsultas.getProvincia(Short.parseShort(idProvincia));
		} catch (IllegalStateException e) {
			estado = false;
			error = new BeanError(e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			estado = false;
			error = new BeanError(e.getMessage(),e);
			e.printStackTrace();
		} catch (Exception e) {
			estado = false;
			error = new BeanError(e.getMessage(),e);
			e.printStackTrace();
		} finally {
			try {
				daoConsultas.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar la conexión.");
				e.printStackTrace();
			}
		}
		for ( Iterator iterador = provincia.getMunicipios().listIterator(); iterador.hasNext(); ) {
		      Municipio municipio = (Municipio) iterador.next();
		      ArrayList<String> datosMunicipio = new ArrayList<String>();
		      datosMunicipio.add(String.valueOf(municipio.getCodMunicipio()));
		      datosMunicipio.add(municipio.getNombre());
		      datosMunicipio.add(String.valueOf(municipio.getDc()));
		      listaMunicipios.put((int) municipio.getIdMunicipio(), datosMunicipio);
		}
		modelo.setContentType("application/json;charset=UTF-8");
		//GsonBuilder gsonBuilder = new GsonBuilder();
		//Gson gson = gsonBuilder.registerTypeAdapter(Municipio.class, new MunicipioAdapter()).create();
		json = new Gson().toJson(listaMunicipios);
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
