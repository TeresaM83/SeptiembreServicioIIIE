package idat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idat.model.Producto;
import idat.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	Producto producto;
	@Autowired
	private ProductoService service;

	@RequestMapping(method = RequestMethod.GET, path = "/")
	//@GetMapping("")
	public ResponseEntity<List<Producto>> listar() {
		List<Producto> listaProductos=service.listar();		
		return new ResponseEntity<List<Producto>>(listaProductos,HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{codigo}")
	//@GetMapping("/{codigo}")
	public ResponseEntity<Producto> buscar(@PathVariable Integer codigo) {	
		/*if (codigo==0) {
			Optional<Producto> pro= service.buscarPrimero();
			if (pro.isPresent()) {
				return new ResponseEntity<>(pro,HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else if(codigo==-1) {
			return new ResponseEntity<>(service.buscarUltimo(),HttpStatus.OK);
		}else {
			producto = service.buscar(codigo);
			if (producto!=null) {
				return new ResponseEntity<>(producto,HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}*/
		producto = service.buscar(codigo);
		if (producto!=null) {
			return new ResponseEntity<Producto>(producto,HttpStatus.OK);
		}else {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/")
	//@PostMapping("")
	public ResponseEntity<Producto> create(@RequestBody Producto p) {
		service.save(p);	
		return new ResponseEntity<>(p,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{codigo}")
	//@PutMapping("/{codigo}")
	public ResponseEntity<?> actualizar(@RequestBody Producto p, @PathVariable Integer codigo) {
		producto= service.buscar(codigo);
		if (producto!=null) {
			p.setIdproducto(codigo);
			service.actualizar(p);
			return new ResponseEntity<>(p,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{codigo}")
	//@DeleteMapping("/{codigo}")
	public ResponseEntity<?> eliminar(@PathVariable Integer codigo) {
		producto= service.buscar(codigo);
		if (producto!=null) {
			service.eliminar(codigo);	
			return new ResponseEntity<>(producto,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

}
