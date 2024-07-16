package com.lenders.app.persistence;

import com.lenders.app.model.House;

import java.io.IOException;

/**
 * Defines the interface and methods for the House object persistence
 *
 * @author Matthew Morrison
 */
public interface HouseDAO {


    /**
     * Create and save a new {@linkplain House house} to the system
     * @param house House object to be saved. A unique id will be assigned to it
     * @return new {@linkplain House house} if successful
     * @throws IOException if there is an issue with storage
     */
    House createHouse(House house) throws IOException;

    /**
     * Create and save a new {@linkplain House house} application to the system
     * @param house House object to be saved. A unique id will be assigned to it
     * @return new {@linkplain House house} if successful
     * @throws IOException if there is an issue with storage
     */
    House createHouseApplication(House h) throws IOException;

    /**
     * Accept a house application and apply a fixed interest to it
     * @param applId the id of the house application
     * @param interest the set interest of the house
     * @return new {@linkplain House house} if successful
     * @throws IOException if there is an issue with storage
     */
    House acceptApplication(int applId, float interest) throws IOException;

    /**
     * Delete a {@linkplain House house} from the system with its id
     * @param id the id of the house to delete
     * @return true if the house was deleted, false otherwise
     * @throws IOException if there is an issue with underlying storage
     */
    boolean deleteHouse(int id) throws IOException;

    /**
     * Delete a {@linkplain House house} application from the system with its id
     * @param id the id of the house application to delete
     * @return true if the house application was deleted, false otherwise
     * @throws IOException if there is an issue with underlying storage
     */
    boolean deleteAppl(int id) throws IOException;

    /**
     * Generate an array of all {@linkplain House houses} from the system
     * @return an array of all houses, can be empty if no houses exist
     */
    House[] getHouses();

    /**
     * Generate an array of all {@linkplain House house} applications from the system
     * @return an array of all house applications, can be empty if none exist
     */
    House[] getApplHouses();

    /**
     * Get a single {@linkplain House house} using its id
     * @param id the id of the house to fine
     * @return the {@linkplain House house} of the respective id
     * @throws IOException if there is an issue with underlying storage
     */
    House getHouse(int id) throws IOException;

    /**
     * Get a single {@linkplain House house} application using its id
     * @param id the id of the house appliation to fine
     * @return the {@linkplain House house} application of the respective id
     * @throws IOException if there is an issue with underlying storage
     */
    House getAppl(int id) throws IOException;

    /**
     * Update and save a {@linkplain House house}
     * @param house updated house object to update in the system
     * @return the updated house object if successful, null if the house is not found
     * @throws IOException if there is an issue with underlying storage
     */
    House updatehouse(House house) throws IOException;


    // TODO methods for searching based on different attributes?
}
