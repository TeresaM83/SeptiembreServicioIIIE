package idat.repository;

import java.util.List;
import java.util.Optional;

import idat.model.Producto;

public interface ProductoRepository {
	void save(Producto p);
	void actualizar(Producto p);
	void eliminar(Integer codigo);
	Producto buscar(Integer codigo);
	Optional<Producto> buscarPrimero();
	Producto buscarUltimo();
	Optional<Producto> buscarAleatorio();
	List<Producto> listar();
}
