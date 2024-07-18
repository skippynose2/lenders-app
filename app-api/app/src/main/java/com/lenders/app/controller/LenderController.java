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

import com.lenders.app.model.Lender;
import com.lenders.app.persistence.LenderDAO;

@RestController
@RequestMapping("lender")
public class LenderController {
    private static final Logger LOG = Logger.getLogger(LenderController.class.getName());
    private LenderDAO lenderDAO;

    public LenderController(LenderDAO lenderDAO) {
        this.lenderDAO = lenderDAO;
    }

    @PostMapping("/create")
    public ResponseEntity<Lender> createNewLender(@RequestBody Lender lender) throws IOException {
        LOG.info("Post /create " + lender);
        try {
            Lender newLender = lenderDAO.createLender(lender);
            if (newLender == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(newLender, HttpStatus.CREATED);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Lender> deleteLender(@RequestParam int id) {
        LOG.info("DELETE /delete " + id);
        try {
            boolean status = lenderDAO.deleteLender(id);
            if (!status) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allLenders")
    public ResponseEntity<Lender[]> getAllLenders() throws IOException {
        LOG.info("GET /allLenders");
        Lender[] lenders = lenderDAO.getAllLenders();
        return new ResponseEntity<>(lenders, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Lender> getLender(@PathVariable int id) {
        LOG.info("GET /get/" + id);
        try {
            Lender l = lenderDAO.getLender(id);
            if (l == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(l, HttpStatus.FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<Lender> updatePassword(@PathVariable int id, @RequestParam String oldPass, @RequestParam String newPass) throws IOException {
        LOG.info("PUT /update/password/" + id);

        try {
            Lender updatedL = lenderDAO.updatePassword(id, oldPass, newPass);

            if (updatedL == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(updatedL, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateInfo")
    public ResponseEntity<Lender> updateBuyerInfo (@RequestBody Lender updatedL) throws IOException {
        LOG.info("PUT /updateInfo " + updatedL);

        try {
            int id = updatedL.getId();

            Lender l = lenderDAO.updateLenderInfo(id, updatedL);

            if (l == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(l, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
