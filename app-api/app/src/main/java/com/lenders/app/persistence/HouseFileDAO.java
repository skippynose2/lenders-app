package com.lenders.app.persistence;

import com.lenders.app.model.House;

import java.io.IOException;

/**
 * Implements the classes and functionality for JSON based House persistence
 *
 * @author Matthew Morrison
 */
public class HouseFileDAO implements HouseDAO{

    /**
     * Create and save a new {@linkplain House house} to the system
     * @param house House object to be saved. A unique id will be assigned to it
     * @return new {@linkplain House house} if successful
     * @throws IOException if there is an issue with storage
     */
    @Override
    public House createHouse(House house) throws IOException {
        return null;
    }

    /**
     * Delete a {@linkplain House house} from the system with its id
     * @param id the id of the house to delete
     * @return true if the user was deleted, false otherwise
     * @throws IOException if there is an issue with underlying storage
     */
    @Override
    public boolean deleteHouse(int id) throws IOException {
        return false;
    }

    /**
     * Generate an array of all {@linkplain House houses} from the system
     * @return an array of all houses, can be empty if no houses exist
     */
    @Override
    public House[] getHouses() {
        return new House[0];
    }

    /**
     * Get a single {@linkplain House house} using its id
     * @param id the id of the house to fine
     * @return the {@linkplain House house} of the respective id
     * @throws IOException if there is an issue with underlying storage
     */
    @Override
    public House getHouse(int id) throws IOException {
        return null;
    }

    /**
     * Update and save a {@linkplain House house}
     * @param house updated house object to update in the system
     * @return the updated house object if successful, null if the house is not found
     * @throws IOException if there is an issue with underlying storage
     */
    @Override
    public House updatehouse(House house) throws IOException {
        return null;
    }
}
