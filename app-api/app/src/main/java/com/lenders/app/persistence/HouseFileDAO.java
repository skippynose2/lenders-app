package com.lenders.app.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenders.app.model.House;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * Implements the classes and functionality for JSON based House persistence
 *
 * @author Matthew Morrison
 */
public class HouseFileDAO implements HouseDAO{

    private static final Logger LOG = Logger.getLogger(HouseFileDAO.class.getName());

    Map<Integer, House> houses; // local cache of all houses

    private ObjectMapper objectMapper;

    private static int nextId; // help assign next id to new houses

    private String filename; // json filename to read and write from

    /**
     * Constructor to instantiate the HouseFileDAO
     * @param filename the filename containing all house info
     * @param objectMapper the object mapper between House objects and JSON
     * @throws IOException if an error occurs when instantiating the file
     */
    public HouseFileDAO(@Value("${houses.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generate a new id for a new user when initially created
     * @return the next id a new user can use
     */
    private synchronized static int getNextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Load all info on the house file
     *
     * @throws IOException if an error occurs when reading the file
     */
    private void load() throws IOException {
        houses = new TreeMap<>();
        nextId = 0;

        House[] houseArray = objectMapper.readValue(new File(filename), House[].class);

        for (House h: houseArray) {
            houses.put(h.getId(), h);

            // TODO is this necessary? test out later and see if its needed
            // TODO or some variation on it
            if (h.getId() > nextId) {
                nextId = h.getId();
            }
        }

        nextId++;
    }

    /**
     * Saves the house map into the file as an array of JSON objects
     *
     * @return true if the houses were written successfully
     *
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        House[] houses = getHouses();
        objectMapper.writeValue(new File(filename), houses);
        return true;
    }

    /**
     * Create and save a new {@linkplain House house} to the system
     * @param h House object to be saved. A unique id will be assigned to it
     * @return new {@linkplain House house} if successful
     * @throws IOException if there is an issue with storage
     */
    @Override
    public House createHouse(House h) throws IOException {
        synchronized (houses) {
            House newH = new House(getNextId(), h.getAddress(), h.getZipcode(),
                    h.getCity(), h.getSqft(), h.isClosed_on(), h.getClosing_date(),
                    h.getLoan_amount(), h.getLtv_percent(), h.getRehab_cost(),
                    h.getRehab_loan(), h.getRehab_overview(), h.getTurn_around_date(),
                    h.getGross_rent_estimate(), h.getCondition(), h.getExit_strategy(),
                    h.getInterest());
            houses.put(newH.getId(), newH);
            save();
            return newH;
        }
    }

    /**
     * Delete a {@linkplain House house} from the system with its id
     * @param id the id of the house to delete
     * @return true if the user was deleted, false otherwise
     * @throws IOException if there is an issue with underlying storage
     */
    @Override
    public boolean deleteHouse(int id) throws IOException {
        synchronized (houses) {
            if (houses.containsKey(id)) {
                houses.remove(id);
                return save();
            }
            return false;
        }
    }

    /**
     * Generate an array of all {@linkplain House houses} from the system
     * @return an array of all houses, can be empty if no houses exist
     */
    @Override
    public House[] getHouses() {
        ArrayList<House> houseArrayList = new ArrayList<>(houses.values());

        House[] houseList = new House[houseArrayList.size()];
        houseArrayList.toArray(houseList);
        return houseList;
    }

    /**
     * Get a single {@linkplain House house} using its id
     * @param id the id of the house to fine
     * @return the {@linkplain House house} of the respective id
     * @throws IOException if there is an issue with underlying storage
     */
    @Override
    public House getHouse(int id) throws IOException {
        synchronized (houses) {
            return houses.getOrDefault(id, null);
        }
    }

    /**
     * Update and save a {@linkplain House house}
     * @param house updated house object to update in the system
     * @return the updated house object if successful, null if the house is not found
     * @throws IOException if there is an issue with underlying storage
     */
    @Override
    public House updatehouse(House house) throws IOException {
        synchronized (houses) {
            if (!houses.containsKey(house.getId())) {
                return null;
            }

            houses.put(house.getId(), house);
            save();
            return house;
        }
    }
}
