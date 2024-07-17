package com.lenders.app.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenders.app.model.Investment;

public class InvestmentFileDAO implements InvestmentDAO {

    private static final Logger LOG = Logger.getLogger(InvestmentFileDAO.class.getName());

    Map<Integer, Investment> allInvestments;

    Map<Integer, ArrayList<Investment>> userInvestments;

    Map<Integer, ArrayList<Investment>> houseInvestments;

    private final Object lock = new Object();

    private ObjectMapper objectMapper;

    private static int nextId;

    private String allInvestmentFilename;

    private String userInvestmentFilename;

    private String houseInvestmentFilename;

    public InvestmentFileDAO(@Value("${allInvestments.file}") String allInvestmentsFilename,
    @Value("${userInvestmentsFilename.file}") String userInvestmentsFilename,
    @Value("${houseInvestmentsFilename.file}") String houseInvestmentFilename,
    ObjectMapper objectMapper) throws IOException {
        this.allInvestmentFilename = allInvestmentsFilename;
        this.userInvestmentFilename = userInvestmentsFilename;
        this.houseInvestmentFilename = houseInvestmentFilename;
        this.objectMapper = objectMapper;
        load();
    }

        /**
     * Generate a new id for a new house when initially created
     * @return the next id a new house can use
     */
    private synchronized static int getNextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    private void load() throws IOException {

        TypeReference<Map<Integer, Investment>> allTypeRef = new TypeReference<Map<Integer,Investment>>() {};
        TypeReference<Map<Integer, ArrayList<Investment>>> typeRef = new TypeReference<Map<Integer, ArrayList<Investment>>>() {};

        allInvestments = objectMapper.readValue(allInvestmentFilename, allTypeRef);
        userInvestments = objectMapper.readValue(userInvestmentFilename, typeRef);
        houseInvestments = objectMapper.readValue(houseInvestmentFilename, typeRef);
        nextId = Collections.max(allInvestments.keySet());
        nextId++;
        

    }

    private boolean saveAll() throws IOException {
        saveAllArray();
        saveUserInvests();
        saveHouseInvests();
        return true;
    }

    private boolean saveAllArray() throws IOException {
        objectMapper.writeValue(new File(allInvestmentFilename), allInvestments);
        return true;
    }

    private boolean saveUserInvests() throws IOException {
        objectMapper.writeValue(new File(userInvestmentFilename), userInvestments);
        return true;
    }

    private boolean saveHouseInvests() throws IOException {
        objectMapper.writeValue(new File(houseInvestmentFilename), userInvestments);
        return true;
    }


    /**
     * Create and save a new {@linkplain Investment investment} to the system
     * @param user_id the user id associated with the investment
     * @param house_id the house id associated with the investment
     * @param date the date of the investment
     * @param money_invested the amount of money invested 
     * @return new {@linkplain Investment investment} object if successful
     * @throws IOException if there is an error with storage
     */
    @Override
    public Investment createInvestment(int user_id, int house_id, String date, float money_invested)
            throws IOException {

        synchronized (lock) {
            Investment newI = new Investment(getNextId(), user_id, house_id, date, money_invested);
            allInvestments.put(newI.getId(), newI);

            if (!userInvestments.containsKey(user_id)) {
                userInvestments.put(user_id, new ArrayList<Investment>());
            }

            userInvestments.get(user_id).add(newI);

            if (!houseInvestments.containsKey(house_id)) {
                houseInvestments.put(house_id, new ArrayList<Investment>());
            }

            houseInvestments.get(house_id).add(newI);

            saveAll();

            return newI;
        }


    }

    /**
     * Create and save a new {@linkplain Investment investment} to the system using an investment object
     * @param investment the investment object to save
     * @return the investment object if successful
     * @throws IOException if there is an error with storage
     */
    @Override
    public Investment createInvestment(Investment investment) throws IOException {
        synchronized (lock) {
            int uid = investment.getUserId();
            int hid = investment.getHouseId();

            Investment newI = new Investment(investment.getId(), investment.getUserId(),
            investment.getHouseId(), investment.getDate(), investment.getMoneyInvested());
            allInvestments.put(newI.getId(), newI);

            if (!userInvestments.containsKey(uid)) {
                userInvestments.put(uid, new ArrayList<Investment>());
            }

            userInvestments.get(uid).add(newI);

            if (!houseInvestments.containsKey(hid)) {
                houseInvestments.put(hid, new ArrayList<Investment>());
            }

            houseInvestments.get(hid).add(newI);

            saveAll();

            return newI;
        }
    }

    /**
     * Accept an investment and officially add it to the system
     * @param id the id of the investment
     * @return the investment object if successful
     * @throws IOException if there is an error with storage
     */
    @Override
    public Investment acceptInvestment(int id) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acceptInvestment'");
    }

    /**
     * Remove an investment from storage
     * @param id the id of the investment
     * @return true if successful, false otherwise
     */
    @Override
    public boolean removeInvestment(int id) throws IOException {
        synchronized (lock) {
            if (!allInvestments.containsKey(id)) {
                return false;
            }
            Investment i = allInvestments.get(id);
            int uid = i.getUserId();
            int hid = i.getHouseId();

            allInvestments.remove(id);
            userInvestments.remove(uid);
            houseInvestments.remove(hid);

            return saveAll();
        }
    }

    /**
     * Get every investment object
     * @return an array of all investments, can be empty if none exist
     */
    @Override
    public Investment[] getInvestments() {
        ArrayList<Investment> investmentArrayList = new ArrayList<>(allInvestments.values());
        Investment[] investmentList = new Investment[investmentArrayList.size()];
        investmentArrayList.toArray(investmentList);
        return investmentList;
    }

    /**
     * Get all the investments associated with a specific user
     * @param user_id the user id to search for
     * @return an array of all investments made by the user
     */
    @Override
    public ArrayList<Investment> getUserInvestments(int user_id) {
        if (!userInvestments.containsKey(user_id)) {
            return null;
        }

        ArrayList<Investment> userIs = userInvestments.get(user_id);

        return userIs;
    }

    /**
     * Get all investments associated with a specific housing unit
     * @param house_id the house id to search for
     * @return an array of all investments with the house unit
     */
    @Override
    public ArrayList<Investment> getHouseInvestments(int house_id) {
        if (!houseInvestments.containsKey(house_id)) {
            return null;
        }

        ArrayList<Investment> houseIs = houseInvestments.get(house_id);

        return houseIs;
    
    }

    /**
     * Get a specific investment with its id
     * @param id the investment id
     * @return the investment of the respective id
     * @throws IOException if there is an error with storage
     */
    @Override
    public Investment getInvestment(int id) throws IOException {
        synchronized (allInvestments) {
            return allInvestments.getOrDefault(id, null);
        }
    }

    @Override
    public Investment updateInvestment(Investment investment) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInvestment'");
    }
    
}
