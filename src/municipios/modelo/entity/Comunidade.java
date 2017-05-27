package municipios.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the comunidades database table.
 * 
 */
@Entity
@Table(name="comunidades")
@NamedQuery(name="Comunidade.findAll", query="SELECT c FROM Comunidade c")
public class Comunidade implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TODAS = "Comunidade.findAll";
	
	@Id
	@Column(name="id_comunidad")
	private int idComunidad;

	private String nombre;

	//bi-directional many-to-one association to Provincia
	@OneToMany(mappedBy="comunidade", fetch=FetchType.EAGER)
	private List<Provincia> provincias;

	public Comunidade() {
	}

	public int getIdComunidad() {
		return this.idComunidad;
	}

	public void setIdComunidad(int idComunidad) {
		this.idComunidad = idComunidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Provincia> getProvincias() {
		return this.provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public Provincia addProvincia(Provincia provincia) {
		getProvincias().add(provincia);
		provincia.setComunidade(this);

		return provincia;
	}

	public Provincia removeProvincia(Provincia provincia) {
		getProvincias().remove(provincia);
		provincia.setComunidade(null);

		return provincia;
	}

}