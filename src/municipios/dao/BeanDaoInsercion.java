package municipios.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;

import municipios.modelo.entity.Comunidade;
import municipios.modelo.entity.Municipio;
import municipios.modelo.entity.Provincia;

/**
 * Interfaz que encapsula los procesos de inserción y actualización con la base de datos.
 */
public interface BeanDaoInsercion {
	
	public void insertaMunicipio(Comunidade comunidad, Provincia provincia, String nombre) throws Exception, IllegalStateException, RollbackException, EntityExistsException;
	
	public void cambiarNomMun(Municipio municipio, String nombreNuevo) throws Exception, IllegalStateException, RollbackException, EntityExistsException;

}
