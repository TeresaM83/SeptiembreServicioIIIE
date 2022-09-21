package idat.service;

import java.util.List;
import idat.model.Producto;

public interface ProductoService {
	void save(Producto p);
	void actualizar(Producto p);
	void eliminar(Integer codigo);
	Producto buscar(Integer codigo);
	List<Producto> listar();
}
