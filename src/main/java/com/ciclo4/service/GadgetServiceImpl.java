package com.ciclo4.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclo4.model.Gadget;
import com.ciclo4.repository.GadgetRepository;

@Service
public class GadgetServiceImpl {
	@Autowired
	private GadgetRepository repo;

	/**
	 * Metodo para listar todos los productos
	 * 
	 * @return List
	 */
	public List<Gadget> getAll() {
		return repo.findAll();
	}

	/**
	 * Regresa los productos que estan disponibles
	 * 
	 * @return List
	 */
	public List<Gadget> getAllAvailables() {
		return repo.findByAvailabilityTrue();
	}

	/**
	 * Metodo para crear un Producto
	 * 
	 * @param gadget
	 * @return Gadget
	 */
	public Gadget newGadget(Gadget gadget) {
		if (gadget.getId() != null && gadget.getBrand() != null && gadget.getCategory() != null
				&& gadget.getName() != null && gadget.getDescription() != null && gadget.getPrice() != null
				&& gadget.getAvailability() != null && gadget.getQuantity() != null
				&& gadget.getPhotography() != null) {

			Gadget savedGadget = repo.save(gadget);

			return savedGadget;
		}
		return gadget;
	}

	/**
	 * Metodo para actualizar Producto
	 * 
	 * @param gadget
	 * @return Gadget
	 */
	public Gadget editGadget(Gadget gadget) {
		if (gadget.getId() != null) {
			Optional<Gadget> exist = repo.findById(gadget.getId());
			if (gadget.getBrand() != null) {
				exist.get().setBrand(gadget.getBrand());
			}
			if (gadget.getCategory() != null) {
				exist.get().setCategory(gadget.getCategory());
			}
			if (gadget.getName() != null) {
				exist.get().setName(gadget.getName());
			}
			if (gadget.getDescription() != null) {
				exist.get().setDescription(gadget.getDescription());
			}
			if (gadget.getPrice() != null) {
				exist.get().setPrice(gadget.getPrice());
			}
			if (gadget.getAvailability() != null) {
				exist.get().setAvailability(gadget.getAvailability());
			}
			if (gadget.getQuantity() != null) {
				exist.get().setQuantity(gadget.getQuantity());
			}
			if (gadget.getPhotography() != null) {
				exist.get().setPhotography(gadget.getPhotography());
			}
			return repo.save(exist.get());
		}
		return gadget;
	}

	/**
	 * Metodo para eliminar Programa
	 * 
	 * @param idGadget
	 */
	public void deleteGadget(Integer idGadget) {
		Optional<Gadget> gadget = repo.findById(idGadget);
		if (gadget.isPresent()) {
			repo.deleteById(idGadget);
		}
	}

	/**
	 * Retorna un gadget si lo encuentra
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<Gadget> getGadgetById(Integer id) {
		return repo.findById(id);
	}

	public Map<Boolean, String> getAvailability() {
		Map<Boolean, String> map = new LinkedHashMap<Boolean, String>();
		map.put(true, "SI");
		map.put(false, "NO");
		return map;
	}

}
