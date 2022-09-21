package idat.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import idat.model.Producto;

@Repository
public class ProductoImpl implements ProductoRepository{

	public static List<Producto> listaProductos=new ArrayList<>();
	
	@Override
	public void save(Producto p) {
		listaProductos.add(p);
	}

	@Override
	public void actualizar(Producto p) {
		Producto oldp=buscar(p.getId());
		eliminar(oldp.getId());
		
		listaProductos.add(p);

	}

	@Override
	public void eliminar(Integer codigo) {
		Producto p=buscar(codigo);
		listaProductos.remove(p);

	}

	@Override
	public Producto buscar(Integer codigo) {
	    return listaProductos.stream().filter(p-> p.getId()==codigo).findFirst().orElse(null);
	}

	@Override
	public List<Producto> listar() {
		return listaProductos;
	}

	@Override
	public Optional<Producto> buscarPrimero() {
	    return listaProductos.stream().findFirst();
	}

	@Override
	public Producto buscarUltimo() {
	    return listaProductos.get(listaProductos.size()-1);
	}

	@Override
	public Optional<Producto> buscarAleatorio() {
	    return listaProductos.stream().findAny();
	}

}
