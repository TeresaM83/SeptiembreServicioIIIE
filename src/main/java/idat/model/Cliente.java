package idat.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcliente;
	private String cliente;
	private String celular;
	
	@ManyToMany(mappedBy = "clientes",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Producto> productos= new ArrayList<>();

	@OneToMany(mappedBy = "cliente")
	private List<Items> items= new ArrayList<>();

	public Integer getId() {
		return idcliente;
	}
	public void setId(Integer id) {
		this.idcliente = id;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	
}
