package municipios.dao;

import java.util.List;

import municipios.modelo.entity.Comunidade;
import municipios.modelo.entity.Municipio;
import municipios.modelo.entity.Provincia;

/**
 * Interfaz de consultas a la base de datos
 */
public interface BeanDaoConsultas {
	
	public List<Comunidade> getComunidades() throws Exception, IllegalStateException, IllegalArgumentException;

	public Comunidade getComunidade(int idComunidad) throws Exception, IllegalStateException, IllegalArgumentException;
	
	public Provincia getProvincia(short idProvincia) throws Exception, IllegalStateException, IllegalArgumentException;
	
	public List<Provincia> getProvincias() throws Exception, IllegalStateException, IllegalArgumentException;
	
	public String getNombreMun(short idProvincia, String dato) throws Exception, IllegalStateException, IllegalArgumentException;
	
	public String getNombreMun(String dato) throws Exception, IllegalStateException, IllegalArgumentException;
	
	public String getNombreCCAA(String nombreMun) throws Exception, IllegalStateException, IllegalArgumentException;
	
	public Municipio getMunicipio(String nombre) throws Exception, IllegalStateException, IllegalArgumentException;
}
