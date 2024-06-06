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
            Investment: [id=%d, house=%s, money_invested=%.2f]
            """;

    @JsonProperty("id") private int id;
    @JsonProperty("house") private House house;
    @JsonProperty("money_invested") private float money_invested;

    /**
     * Create a new Investment Entity with the required JSON Properties
     * @param id the id of the investment
     * @param house the house being invested in
     * @param money_invested the amount of money invested in the house
     */
    public Investment(@JsonProperty("id") int id,
                      @JsonProperty("house") House house,
                      @JsonProperty("money_invested") float money_invested) {
        this.id = id;
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
}
