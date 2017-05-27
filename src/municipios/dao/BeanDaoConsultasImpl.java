package municipios.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import municipios.modelo.entity.Comunidade;
import municipios.modelo.entity.Municipio;
import municipios.modelo.entity.Provincia;

/**
 * Encapsula las consultas con la base de datos
 */
public class BeanDaoConsultasImpl extends BeanDaoConexionImpl implements BeanDaoConsultas{
	
	/**
	 * Constructor recibe la unidad e persistencia y llama la clase padre (BeanDaoConsultas)
	 * @param unidadPersistencia
	 */
	public BeanDaoConsultasImpl(String unidadPersistencia){
		super(unidadPersistencia);
	}

	@Override
	public List<Comunidade> getComunidades() throws Exception, IllegalStateException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Comunidade> listaComunidades = new ArrayList();
		try {
			Query query = em.createNamedQuery(Comunidade.TODAS);
			listaComunidades = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaComunidades;
	}

	@Override
	public Comunidade getComunidade(int idComunidad) throws Exception, IllegalStateException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		Comunidade comunidad = new Comunidade();
		try {
			comunidad = em.find(Comunidade.class, idComunidad); 
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return comunidad;
	}

	@Override
	public Provincia getProvincia(short idProvincia) throws Exception, IllegalStateException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		Provincia provincia = new Provincia();
		try {
			provincia = em.find(Provincia.class, idProvincia); 
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return provincia;
	}

	@Override
	public List<Provincia> getProvincias() throws Exception, IllegalStateException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Provincia> listaProvincias = new ArrayList<Provincia>();
		try {
			Query query = em.createNamedQuery(Provincia.TODAS);
			listaProvincias = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaProvincias;
	}

	@Override
	public String getNombreMun(short idProvincia, String dato) throws Exception, IllegalStateException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		String nombreMunicipio = "";
		Query query = null;
		List<Municipio> listaMunicipios = new ArrayList<Municipio>();
		Municipio municipio = null;
		try {
			Provincia provincia = em.find(Provincia.class, idProvincia);
			String jpql = "SELECT m FROM Municipio m WHERE m.nombre like ?1 and m.provincia like ?2 order by m.nombre";
			query = em.createQuery(jpql);
			query.setParameter(1, dato+"%");
			query.setParameter(2, provincia);
			listaMunicipios = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		//System.out.println("Número de municipios: " + listaMunicipios.size());
		if(!listaMunicipios.isEmpty()){
			municipio = listaMunicipios.get(0);
			nombreMunicipio = municipio.getNombre();
		}
		return nombreMunicipio;
	}

	@Override
	public String getNombreCCAA(String nombreMun) throws Exception, IllegalStateException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		Query query = null;
		Municipio municipio = null;
		String nombreCCAA = null;
		try {
			String jpql = "SELECT m FROM Municipio m WHERE m.nombre like ?1";
			query = em.createQuery(jpql);
			query.setParameter(1, nombreMun);
			municipio = (Municipio) query.getSingleResult();
			nombreCCAA = municipio.getProvincia().getComunidade().getNombre();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return nombreCCAA;
	}

	@Override
	public String getNombreMun(String dato) throws Exception, IllegalStateException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		String nombreMunicipio = "";
		Query query = null;
		List<Municipio> listaMunicipios = new ArrayList<Municipio>();
		Municipio municipio = null;
		try {
			String jpql = "SELECT m FROM Municipio m WHERE m.nombre like ?1 order by m.nombre";
			query = em.createQuery(jpql);
			query.setParameter(1, dato+"%");
			listaMunicipios = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		if(!listaMunicipios.isEmpty()){
			municipio = listaMunicipios.get(0);
			nombreMunicipio = municipio.getNombre();
		}
		return nombreMunicipio;
	}

	@Override
	public Municipio getMunicipio(String nombre) throws Exception, IllegalStateException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		Query query = null;
		Municipio municipio = new Municipio();
		try {
			String jpql = "SELECT m FROM Municipio m WHERE m.nombre like ?1";
			query = em.createQuery(jpql);
			query.setParameter(1, nombre);
			municipio = (Municipio) query.getSingleResult();
		}catch (NoResultException e){
			System.out.println("No hay ningún municipio con el nombre: " + nombre);
			municipio.setNombre("No existe");
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return municipio;
	}

}
