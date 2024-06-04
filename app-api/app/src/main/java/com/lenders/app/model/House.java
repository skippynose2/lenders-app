package com.lenders.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Represents a House Entity within the lenders application
 *
 * @author Matthew Morrison
 */
public class House {

    private static final Logger LOG = Logger.getLogger(House.class.getName());

    static final String STRING_FORMAT = """
    House: [id=%d, address=%s, zipcode=%d, city=%s,
    property_value=%.2f, money_raised=%.2f, asking_price=%.2f, tags=%s
    """;

    @JsonProperty("id") private int id;
    @JsonProperty("address") private String address;
    @JsonProperty("zipcode") private int zipcode;
    @JsonProperty("city") private String city;
    @JsonProperty("property_value") private float property_value;
    @JsonProperty("money_raised") private float money_raised;
    @JsonProperty("asking_price") private float asking_price;
    @JsonProperty("tags") private ArrayList<String> tags;


    /**
     * Create a new House Entity with the required JSON properties
     * @param id the id of the house
     * @param address the address of the house
     * @param zipcode the zipcode of the house
     * @param city the city the house resides in
     * @param property_value the current property value of the house
     * @param money_raised the amount of money raised for the house
     * @param asking_price the current asking price for the house
     * @param tags any tags associated with the house
     */
    public House(@JsonProperty("id") int id,
                 @JsonProperty("address") String address,
                 @JsonProperty("zipcode") int zipcode,
                 @JsonProperty("city") String city,
                 @JsonProperty("property_value") float property_value,
                 @JsonProperty("money_raised") float money_raised,
                 @JsonProperty("asking_price") float asking_price,
                 @JsonProperty("tags") ArrayList<String> tags) {
        this.id = id;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.property_value = property_value;
        this.money_raised = money_raised;
        this.asking_price = asking_price;
        this.tags = tags;
    }

    /**
     * Get the house entity's id
     * @return the house entity's id value
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the house entity's address
     * @return the house entity's street address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Get the house entity's zipcode
     * @return the house entity's zipcode
     */
    public int getZipcode() {
        return this.zipcode;
    }

    /**
     * Get the house entity's city
     * @return the house entity's city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Get the house entity's current property value
     * @return the house entity's property value
     */
    public float getPropertyValue() {
        return this.property_value;
    }

    /**
     * Get the current amount raised for the house entity
     * @return the house entity's money raised
     */
    public float getMoneyRaised() {
        return this.money_raised;
    }

    /**
     * Get the asking price for the house entity
     * @return the house entity's asking price
     */
    public float getAskingPrice() {
        return this.asking_price;
    }

    /**
     * Get all the tags associated with the house entity
     * @return the house entity's tags
     */
    public ArrayList<String> getTags() {
        return this.tags;
    }

    /**
     * Update the house entity's tags to add a new one
     * @param tag the tag to add
     * @return True if operation was successful and false if otherwise
     */
    public boolean addNewTag(String tag) {
        tag = tag.toLowerCase();
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
            return true;
        }

        return false;

    }

    /**
     * Attempt to delete a tag from the house entity, whether or not it exists
     * @param tag the tag to delete
     */
    public void deleteTag(String tag) {
        tag = tag.toLowerCase();
        this.tags.remove(tag);
    }

    /**
     * Set the money_raised to a new value
     * @param money_raised the value to update the money raised to
     */
    public void setMoneyRaised(float money_raised) {
        this.money_raised = money_raised;
    }

    /**
     * Set the property value to a new value
     * @param property_value the updated property value
     */
    public void setPropertyValue(float property_value) {
        this.property_value = property_value;
    }

    /**
     * Set the asking price of the house to a new value
     * @param asking_price the value to update the asking price to
     */
    public void setAskingPrice(float asking_price) {
        this.asking_price = asking_price;
    }
}
