package municipios.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;

import municipios.modelo.entity.Comunidade;
import municipios.modelo.entity.Municipio;
import municipios.modelo.entity.Provincia;

/**
 * Encapsula los procesos de inserción y actualización con la base de datos.
 */
public class BeanDaoInsercionImpl extends BeanDaoConexionImpl implements BeanDaoInsercion{

	public BeanDaoInsercionImpl(String unidadPersistencia) {
		super(unidadPersistencia);
	}

	@Override
	public void insertaMunicipio(Comunidade comunidad, Provincia provincia, String nombre)
			throws Exception, IllegalStateException, RollbackException, EntityExistsException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		Municipio municipio = new Municipio();
		municipio.setNombre(nombre);
		municipio.setProvincia(provincia);
		municipio.setCodMunicipio((int) (Math.random()*200+1));
		municipio.setDc((int) (Math.random()*200+1));
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(municipio);
			tx.commit();
		} finally {
			if (tx.isActive()){
				tx.rollback();
			}
			if (conexionNula) {
				close();
			}
		}
		
	}

	@Override
	public void cambiarNomMun(Municipio municipioExistente, String nombreNuevo)
			throws Exception, IllegalStateException, RollbackException, EntityExistsException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		Municipio municipioAct = null;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			municipioAct = em.find(Municipio.class, municipioExistente.getIdMunicipio());
			em.detach(municipioAct); //Desvincula la entidad de la tabla
			municipioAct.setNombre(nombreNuevo);
			em.merge(municipioAct); //Se vincula otra vez a la tabla
			tx.commit();
		} finally {
			if (tx.isActive()){
				tx.rollback();
			}
			if (conexionNula) {
				close();
			}
		}
	}

}
