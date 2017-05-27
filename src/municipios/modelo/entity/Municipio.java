package municipios.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the municipios database table.
 * 
 */
@Entity
@Table(name="municipios")
@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m")
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_municipio")
	private int idMunicipio;

	@Column(name="cod_municipio")
	private int codMunicipio;

	@Column(name="DC")
	private int dc;

	private String nombre;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="id_provincia")
	private Provincia provincia;

	public Municipio() {
	}

	public int getIdMunicipio() {
		return this.idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public int getCodMunicipio() {
		return this.codMunicipio;
	}

	public void setCodMunicipio(int codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public int getDc() {
		return this.dc;
	}

	public void setDc(int dc) {
		this.dc = dc;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}