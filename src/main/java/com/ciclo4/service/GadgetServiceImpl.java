package com.ciclo4.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ciclo4.model.Gadget;
import com.ciclo4.repository.GadgetRepository;

/**
 * @author CarlinGebyte
 */
@Service
public class GadgetServiceImpl {
	/**
	 * Atributo gadget repository
	 */
	private GadgetRepository gadgetRepository;

	/**
	 * Método constructor
	 * 
	 * @param gadgetRepository
	 */
	public GadgetServiceImpl(GadgetRepository gadgetRepository) {
		this.gadgetRepository = gadgetRepository;
	}

	/**
	 * Método para listar todos los productos
	 * 
	 * @return
	 */
	public List<Gadget> getAll() {
		return gadgetRepository.findAll();
	}

	/**
	 * Método para crear un Producto
	 * 
	 * @param gadget
	 * @return
	 */
	public Gadget newGadget(Gadget gadget) {
		if (gadget.getId() != null && gadget.getBrand() != null && gadget.getCategory() != null
				&& gadget.getName() != null && gadget.getDescription() != null && gadget.getPrice() != null
				&& gadget.getAvailability() != null && gadget.getQuantity() != null
				&& gadget.getPhotography() != null) {

			Gadget savedGadget = gadgetRepository.save(gadget);

			return savedGadget;
		}
		return gadget;
	}

	/**
	 * Método para actualizar Producto
	 * 
	 * @param gadget
	 * @return
	 */
	public Gadget editGadget(Gadget gadget) {
		if (gadget.getId() != null) {
			Optional<Gadget> exist = gadgetRepository.findById(gadget.getId());
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
			return gadgetRepository.save(exist.get());
		}
		return gadget;
	}

	/**
	 * Método para eliminar Programa
	 * 
	 * @param idGadget
	 */
	public void deleteGadget(Integer idGadget) {
		Optional<Gadget> gadget = gadgetRepository.findById(idGadget);
		if (gadget.isPresent()) {
			gadgetRepository.deleteById(idGadget);
		}
	}

	/**
	 * Retorna un gadget si lo encuentra
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<Gadget> getGadgetById(Integer id) {
		return gadgetRepository.findById(id);
	}

	public Map<Boolean, String> getAvailability() {
		Map<Boolean, String> map = new LinkedHashMap<Boolean, String>();
		map.put(true, "SI");
		map.put(false, "NO");
		return map;
	}

}
