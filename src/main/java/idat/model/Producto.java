package idat.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproducto;
	private String nombre;
	private String descripcion;
	private Double precio;
	private Integer stock;
	
	@OneToOne
	private Proveedor proveedor;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name="producto_cliente",
			joinColumns=@JoinColumn(
					name="idproducto",
					nullable = false,
					unique = true,
					foreignKey =  @ForeignKey(foreignKeyDefinition ="foreign key(idproducto) references productos(idproducto)" )),
		    inverseJoinColumns= @JoinColumn(
		    		name="idcliente",
					nullable = false,
					unique = true,
					foreignKey =  @ForeignKey(foreignKeyDefinition ="foreign key(idcliente) references clientes(idcliente)" ))
	)
	private List<Cliente> clientes= new ArrayList<>();
	
	public Integer getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(Integer id) {
		this.idproducto = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Producto(Integer id, String nombre, String descripcion, Double precio, Integer stock) {
		super();
		this.idproducto = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}
	public Producto() {
		super();
	}
	
	
}
