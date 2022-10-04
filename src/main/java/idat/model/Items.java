package idat.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iditem;
	private String item;
	private Integer cantidad;
	private Double total;
	
	@ManyToOne
	@JoinColumn(
			name="idcliente",
			nullable = false,
			unique = true,
			foreignKey =  @ForeignKey(foreignKeyDefinition ="foreign key(idcliente) references clientes(idcliente)" )
			)
	private Cliente cliente;
	public Integer getId() {
		return iditem;
	}
	public void setId(Integer id) {
		this.iditem = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

}
