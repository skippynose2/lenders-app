package com.lenders.app.controller;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lenders.app.model.House;
import com.lenders.app.persistence.HouseDAO;


/**
 * Controller to handle all actions to be done with houses.
 * This class provides endpoints to add, get, update, delete, and initialize House objects in the application.
 * 
 * @author Matthew Morrison
 */
@RestController
@RequestMapping("houses")
public class HouseController {
    private static final Logger LOG = Logger.getLogger(HouseController.class.getName());

    private HouseDAO houseDAO;


    public HouseController(HouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }


    @GetMapping("/allHouses")
    public ResponseEntity<House[]> getAllHouses() throws IOException{
        LOG.info("GET /allHouses");
        House[] houses = houseDAO.getHouses();
        return new ResponseEntity<>(houses, HttpStatus.OK);
    
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<House> getHouse(@PathVariable int id) {
        LOG.info("GET /getById/" + id);

        try {
            House h = houseDAO.getHouse(id);
            if (h != null){
                return new ResponseEntity<>(h, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO search feature for houses, learn how to
    

    @PostMapping("/createHouseRequest")
    public ResponseEntity<House> createHouseRequest(@RequestBody House house) throws IOException {
        LOG.info("POST /createHouserequest" + house);
        house.setInterest(0);
        try {
            House createdHouseRequest = houseDAO.createHouse(house);
            if (createdHouseRequest == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(createdHouseRequest, HttpStatus.CREATED);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/acceptedHouse")
    public ResponseEntity<House> acceptHouse(@RequestBody House house) throws IOException {
        LOG.info("POST /acceptedHouse/" + house);
        try {
            House createdHouseRequest = houseDAO.createHouse(house);
            if (createdHouseRequest == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(createdHouseRequest, HttpStatus.CREATED);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/declinedHouse/{id}") 
    public ResponseEntity<House> declineHouse(@PathVariable int id) {
        LOG.info("DELETE /declinedHouse/" + id);

        try {
            boolean status = houseDAO.deleteHouse(id);
            if (!status) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("removeHouse/{id}")
    public ResponseEntity<House> removeHouse(@PathVariable int id) {
        LOG.info("DELETE /removeHouse/" + id);

        try {
            boolean status = houseDAO.deleteHouse(id);
            if (!status) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<House> updateHouse(@RequestBody House house) {
        LOG.info("PUT /house " + house);
        try {
            House status = houseDAO.updatehouse(house);
            if (status == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(status, HttpStatus.OK);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
