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

import com.lenders.app.model.Admin;
import com.lenders.app.persistence.AdminDAO;

@RestController
@RequestMapping("admin")
public class AdminController {
    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());

    private AdminDAO adminDAO;

    public AdminController(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> createNewAdmin(@RequestBody Admin admin) throws IOException {
        LOG.info("POST /create " + admin);
        try {
            Admin newAdmin = adminDAO.createAdmin(admin.getPassword(), admin.getEmail(), admin.getPhone_number());
            if (newAdmin == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/newAdmin")
    public ResponseEntity<Admin> createNewAdmin(@RequestParam String password, @RequestParam String email, @RequestParam String number) throws IOException {
        LOG.info("POST /newAdmin ");
        try {
            Admin newAdmin = adminDAO.createAdmin(password, email, number);
            if (newAdmin == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Admin> deleteAdmin(@RequestParam int id) {
        LOG.info("DELETE /delete");

        try {
            boolean status = adminDAO.deleteAdmin(id);
            if (!status) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allAdmins")
    public ResponseEntity<Admin[]> getAllAdmins() throws IOException {
        LOG.info("get /addAdmins");
        Admin[] admins = adminDAO.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable int id) {
        LOG.info("GET /get/" + id);
        try {
            Admin a = adminDAO.getAdmin(id);
            if (a != null) {
                return new ResponseEntity<>(a, HttpStatus.OK);
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
    public ResponseEntity<Admin> updatePassword(@PathVariable int id, @RequestParam String oldPass, @RequestParam String newPass) throws IOException {
        LOG.info("PUT /update/password/" + id);

        try {
            Admin updatedA = adminDAO.updatePassword(id, oldPass, newPass);

            if (updatedA == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(updatedA, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/email/{id}")
    public ResponseEntity<Admin> updateEmail(@PathVariable int id, @RequestParam String newEmail) throws IOException {
        LOG.info("PUT /update/email/" + id);

        try {
            Admin updatedA = adminDAO.updateEmail(id, newEmail);

            if (updatedA == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(updatedA, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/number/{id}")
    public ResponseEntity<Admin> updateNumber(@PathVariable int id, @RequestParam String newNumber) throws IOException {
        LOG.info("PUT /update/number/" + id);

        try {
            Admin updatedA = adminDAO.updatePhoneNum(id, newNumber);

            if (updatedA == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(updatedA, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/info")
    public ResponseEntity<Admin> updateAdminInfo (@RequestBody Admin admin) throws IOException {
        LOG.info("PUT /update/info/" + admin);

        try {
            int id = admin.getId();

            Admin updatedA = adminDAO.updateAdminInfo(id, admin);

            if (updatedA == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(updatedA, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
