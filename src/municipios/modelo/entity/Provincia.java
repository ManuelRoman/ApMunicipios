package municipios.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the provincias database table.
 * 
 */
@Entity
@Table(name="provincias")
@NamedQuery(name="Provincia.findAll", query="SELECT p FROM Provincia p")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TODAS = "Provincia.findAll";
	
	@Id
	@Column(name="id_provincia")
	private short idProvincia;

	private String provincia;

	//bi-directional many-to-one association to Municipio
	@OneToMany(mappedBy="provincia", fetch=FetchType.EAGER)
	private List<Municipio> municipios;

	//bi-directional many-to-one association to Comunidade
	@ManyToOne
	@JoinColumn(name="id_comunidad")
	private Comunidade comunidade;

	public Provincia() {
	}

	public short getIdProvincia() {
		return this.idProvincia;
	}

	public void setIdProvincia(short idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public List<Municipio> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public Municipio addMunicipio(Municipio municipio) {
		getMunicipios().add(municipio);
		municipio.setProvincia(this);

		return municipio;
	}

	public Municipio removeMunicipio(Municipio municipio) {
		getMunicipios().remove(municipio);
		municipio.setProvincia(null);

		return municipio;
	}

	public Comunidade getComunidade() {
		return this.comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}

}