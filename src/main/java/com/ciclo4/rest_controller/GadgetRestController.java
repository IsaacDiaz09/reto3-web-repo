package com.ciclo4.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ciclo4.model.Gadget;
import com.ciclo4.service.GadgetServiceImpl;

/**
 * @author CarlinGebyte
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/gadget")
public class GadgetRestController {

	@Autowired
	private GadgetServiceImpl gadgetService;

    /**
     * Método para listar productos
     * @return
     */
    @GetMapping("/all")
    public List<Gadget> getAll() {
        return gadgetService.getAll();
    }

    /**
     * Método para crear un producto
     * @param gadget
     * @return
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Gadget newGadget(@RequestBody Gadget gadget) {
        return gadgetService.newGadget(gadget);
    }

    /**
     * Método para actualizar un producto
     * @param gadget
     * @return
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Gadget editGadget(@RequestBody Gadget gadget) {
        return gadgetService.editGadget(gadget);
    }

    /**
     * Método para eliminar un producto
     * @param idGadget
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGadget(@PathVariable("id") Integer idGadget) {
        gadgetService.deleteGadget(idGadget);
    }
}
