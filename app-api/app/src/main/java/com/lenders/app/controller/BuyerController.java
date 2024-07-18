package com.lenders.app.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lenders.app.model.Buyer;
import com.lenders.app.persistence.BuyerDAO;

@RestController
@RequestMapping("buyer")
public class BuyerController {
    private static final Logger LOG = Logger.getLogger(BuyerController.class.getName());
    private BuyerDAO buyerDAO;

    public BuyerController(BuyerDAO buyerDAO) {
        this.buyerDAO = buyerDAO;
    }

    @PostMapping("/create")
    public ResponseEntity<Buyer> createNewBuyer(@RequestBody Buyer buyer) throws IOException {
        LOG.info("POST /create " + buyer);
        try {
            Buyer newBuyer = buyerDAO.createBuyer(buyer);
            if (newBuyer == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(newBuyer, HttpStatus.CREATED);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Buyer> deleteBuyer(@RequestParam int id) {
        LOG.info("DELETE /delete");

        try {
            boolean status = buyerDAO.deleteBuyer(id);
            if (!status) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allBuyers")
    public ResponseEntity<Buyer[]> getAllBuyers() throws IOException {
        LOG.info("GET /allBuyers");
        Buyer[] buyers = buyerDAO.getAllBuyers();
        return new ResponseEntity<>(buyers, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Buyer> getBuyer(@PathVariable int id) {
        LOG.info("GET /get/" +id);
        try {
            Buyer b = buyerDAO.getBuyer(id);
            if (b != null) {
                return new ResponseEntity<>(b, HttpStatus.FOUND);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/password/{id}")
    public ResponseEntity<Buyer> updatePassword(@PathVariable int id, @RequestParam String oldPass, @RequestParam String newPass) throws IOException {
        LOG.info("PUT /update/password/" + id);

        try {
            Buyer updatedB = buyerDAO.updatePassword(id, oldPass, newPass);

            if (updatedB == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(updatedB, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateInfo")
    public ResponseEntity<Buyer> updateBuyerInfo (@RequestBody Buyer updatedB) throws IOException {
        LOG.info("PUT /updateInfo " + updatedB);

        try {
            int id = updatedB.getId();

            Buyer b = buyerDAO.updateBuyerInfo(id, updatedB);

            if (b == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(b, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
