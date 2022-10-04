package idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.model.Producto;
import idat.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository repository;
	
	@Override
	public void save(Producto p) {
		repository.save(p);
	}

	@Override
	public void actualizar(Producto p) {
		repository.saveAndFlush(p);
	}

	@Override
	public void eliminar(Integer codigo) {
		repository.deleteById(codigo);
	}

	@Override
	public Producto buscar(Integer codigo) {
		return repository.findById(codigo).orElse(null);
	}

	@Override
	public List<Producto> listar() {
		return repository.findAll();
	}

}
