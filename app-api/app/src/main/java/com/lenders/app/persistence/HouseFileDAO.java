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

    Map<Integer, House> houseApplications; // local cache of all house applications (to be approved/declined)

    private ObjectMapper objectMapper;

    private static int nextIdHouses; // help assign next id to new houses

    private static int nextIDApplications;

    private String houseFilename; // json filename to read and write from

    private String applFilename;

    /**
     * Constructor to instantiate the HouseFileDAO
     * @param filename the filename containing all house info
     * @param objectMapper the object mapper between House objects and JSON
     * @throws IOException if an error occurs when instantiating the file
     */
    public HouseFileDAO(@Value("${houses.file}") String houseFilename,
    @Value("${houseApplications.file}") String applFilename, ObjectMapper objectMapper) throws IOException {
        this.houseFilename = houseFilename;
        this.applFilename = applFilename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generate a new id for a new user when initially created
     * @return the next id a new user can use
     */
    private synchronized static int getNextId() {
        int id = nextIdHouses;
        ++nextIdHouses;
        return id;
    }

    private synchronized static int getNextApplId() {
        int id = nextIDApplications;
        ++nextIDApplications;
        return id;
    }

    /**
     * Load all info on the house file
     *
     * @throws IOException if an error occurs when reading the file
     */
    private void load() throws IOException {
        houses = new TreeMap<>();
        nextIdHouses = 0;

        House[] houseArray = objectMapper.readValue(new File(houseFilename), House[].class);
        House[] applArray = objectMapper.readValue(new File(applFilename), House[].class);

        for (House h: houseArray) {
            houses.put(h.getId(), h);

            // TODO is this necessary? test out later and see if its needed
            // TODO or some variation on it
            if (h.getId() > nextIdHouses) {
                nextIdHouses = h.getId();
            }
        }

        nextIdHouses++;

        for (House h: applArray) {
            houseApplications.put(h.getId(), h);

            if (h.getId() > nextIDApplications) {
                nextIDApplications = h.getId();
            }
        }

        nextIDApplications++;
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
        House[] applications = getHouses();
        objectMapper.writeValue(new File(houseFilename), houses);
        objectMapper.writeValue(new File(applFilename), applications);
        
        return true;
    }

    private boolean saveHouses() throws IOException {
        House[] houses = getHouses();
        objectMapper.writeValue(new File(houseFilename), houses);
        return true;
    }

    private boolean saveAppls() throws IOException {
        House[] applications = getHouses();
        objectMapper.writeValue(new File(applFilename), applications);
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
            saveHouses();
            return newH;
        }
    }


    public House createHouseApplication(House h) throws IOException {
        synchronized (houseApplications) {
            House newH = new House(getNextApplId(), h.getAddress(), h.getZipcode(),
                    h.getCity(), h.getSqft(), h.isClosed_on(), h.getClosing_date(),
                    h.getLoan_amount(), h.getLtv_percent(), h.getRehab_cost(),
                    h.getRehab_loan(), h.getRehab_overview(), h.getTurn_around_date(),
                    h.getGross_rent_estimate(), h.getCondition(), h.getExit_strategy(),
                    0);
            houseApplications.put(newH.getId(), newH);
            saveAppls();
            return newH;
        }
    }


    public House acceptApplication(int applId, float interest) throws IOException {

        House accHouse;

        synchronized (houseApplications) {
            accHouse = houseApplications.get(applId);
            deleteAppl(applId);
        }

        synchronized (houses) {
            House newH = new House(getNextId(), accHouse.getAddress(), accHouse.getZipcode(),
            accHouse.getCity(), accHouse.getSqft(), accHouse.isClosed_on(), accHouse.getClosing_date(),
            accHouse.getLoan_amount(), accHouse.getLtv_percent(), accHouse.getRehab_cost(),
            accHouse.getRehab_loan(), accHouse.getRehab_overview(), accHouse.getTurn_around_date(),
            accHouse.getGross_rent_estimate(), accHouse.getCondition(), accHouse.getExit_strategy(),
            interest);
            houses.put(newH.getId(), newH);
            saveHouses();
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

    public boolean deleteAppl(int id) throws IOException {
        synchronized (houseApplications) {
            if (houseApplications.containsKey(id)) {
                houseApplications.remove(id);
                return saveAppls();
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

    public House[] getApplHouses() {
        ArrayList<House> applArrayList = new ArrayList<>(houseApplications.values());

        House[] applList = new House[applArrayList.size()];
        applArrayList.toArray(applList);
        return applList;
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
