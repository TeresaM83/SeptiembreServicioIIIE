package idat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "proveedores")
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproveedor;
	private String proveedo;
	private String direccion;
	
	@OneToOne
	private Producto producto;
	
	public Integer getId() {
		return idproveedor;
	}
	public void setId(Integer id) {
		this.idproveedor = id;
	}
	public String getProveedo() {
		return proveedo;
	}
	public void setProveedo(String proveedo) {
		this.proveedo = proveedo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
