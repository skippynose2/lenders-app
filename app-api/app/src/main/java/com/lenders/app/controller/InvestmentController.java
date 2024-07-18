package com.lenders.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lenders.app.model.Investment;
import com.lenders.app.persistence.InvestmentDAO;

/**
 * Controller to handle all actions to be done with investments.
 * This class provides endpoints to add, get, update, delete, and initialize investment objects in the application.
 * 
 * @author Matthew Morrison
 */
@RestController
@RequestMapping("investments")
public class InvestmentController {
        private static final Logger LOG = Logger.getLogger(InvestmentController.class.getName());

        private InvestmentDAO investmentDAO;

        public InvestmentController (InvestmentDAO investmentDAO) {
            this.investmentDAO = investmentDAO;
        }

        @GetMapping("/allInvestments")
        public ResponseEntity<Investment[]> getAllInvestments() throws IOException {
            LOG.info("GET /allInvestments");
            Investment[] investments = investmentDAO.getInvestments();

            return new ResponseEntity<>(investments, HttpStatus.OK);
        }

        @PostMapping("/newInvestment")
        public ResponseEntity<Investment> createInvestment(@RequestBody Investment investment) throws IOException {
            LOG.info("POST /newInvestment " + investment);
            try {
                Investment createdI = investmentDAO.createInvestment(investment);
                if (createdI == null) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
                return new ResponseEntity<>(createdI, HttpStatus.CREATED);
            } catch (IOException e) {
                LOG.log(Level.SEVERE,e.getLocalizedMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }   
        }

        @GetMapping("/users/{uid}")
        public ResponseEntity<Investment[]> getUserInvestments(@PathVariable int uid) {
            LOG.info("GET /users/" + uid);
            ArrayList<Investment> userInvests = investmentDAO.getUserInvestments(uid);
            Investment[] userInvestsArray = userInvests.toArray(new Investment[0]);
            return new ResponseEntity<>(userInvestsArray, HttpStatus.OK);       
        }

        @GetMapping("/houses/{hid}")
        public ResponseEntity<Investment[]> getHouseInvestments(@PathVariable int hid) {
            LOG.info("GET /houses/" + hid);
            ArrayList<Investment> houseInvests = investmentDAO.getHouseInvestments(hid);
            Investment[] houseInvestsArray = houseInvests.toArray(new Investment[0]);
            return new ResponseEntity<>(houseInvestsArray, HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Investment> deleteInvestment(@PathVariable int id) {
            LOG.info("DELETE /delete/" + id);

            try {
                boolean status = investmentDAO.removeInvestment(id);
                if (!status) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (IOException e) {
                LOG.log(Level.SEVERE,e.getLocalizedMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}


