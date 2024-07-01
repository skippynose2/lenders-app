package com.lenders.app.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.logging.Logger;

/**
 * Represents an investment by a user within the lenders application
 *
 * @author Matthew Morrison
 */
public class Investment {

    private static final Logger LOG = Logger.getLogger(Investment.class.getName());

    static final String STRING_FORMAT = """
            Investment: [id=%d, user_id=%d house=%s, money_invested=%.2f]
            """;

    @JsonProperty("id") private int id;
    @JsonProperty("user_id") private int user_id;

    @JsonProperty("date") private String date;
    @JsonProperty("house") private House house;
    @JsonProperty("money_invested") private float money_invested;

    /**
     * Create a new Investment Entity with the required JSON Properties
     * @param id the id of the investment
     * @param house the house being invested in
     * @param money_invested the amount of money invested in the house
     */
    public Investment(@JsonProperty("id") int id,
                      @JsonProperty("user_id") int user_id,
                      @JsonProperty("date") String date,
                      @JsonProperty("house") House house,
                      @JsonProperty("money_invested") float money_invested) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.house = house;
        this.money_invested = money_invested;
    }

    /**
     * Get the investment entity's id
     * @return the investment's id value
     */
    public int getId() {
        return id;
    }

    /**
     * Get the user id associated with the investment
     * @return the user id
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * Get the date of the transaction
     * @return the date of the transaction
     */
    public String getDate() {
        return date;
    }

    /**
     * Get the investment's house
     * @return the investment's house
     */
    public House getHouse() {
        return house;
    }

    /**
     * Get the money invested in the investment entity
     * @return the amount of money invested
     */
    public float getMoneyInvested() {
        return money_invested;
    }

    /**
     * Set the user id of the investment
     * @param user_id the user id of the investment
     */
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Set the date the investment took place
     * @param date the date of the investment
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Set the house being invested to
     * @param house the updated house to be invested in
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Set the amount of money invested
     * @param money_invested the updated amount of money invested
     */
    public void setMoneyInvested(float money_invested) {
        this.money_invested = money_invested;
    }

    /**
     * Return if a specific order is associated with a user
     * @param user_id the specific user to check for
     * @return true if user id's match, false otherwise
     */
    public boolean isPurchasedBy(int user_id) {
        return user_id == this.user_id;
    }
}
