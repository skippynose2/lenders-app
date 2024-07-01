package com.lenders.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.time.LocalDate;

/**
 * Represents a House Entity within the lenders application
 *
 * @author Matthew Morrison
 */
public class House {

    private static final Logger LOG = Logger.getLogger(House.class.getName());

    //TODO fix
    static final String STRING_FORMAT = """
    House: [id=%d, address=%s, zipcode=%d, city=%s, sqft
    property_value=%.2f, money_raised=%.2f, asking_price=%.2f, tags=%s]
    """;

    public enum ExitStrategy {
        FIX_AND_RENT,
        FIX_AND_SELL
    }

    public enum Condition {
        POOR,
        FAIR,
        GOOD
    }

    @JsonProperty("id") private int id;
    @JsonProperty("address") private String address;
    @JsonProperty("zipcode") private int zipcode;
    @JsonProperty("city") private String city;
    @JsonProperty("sqft") private int sqft;
    @JsonProperty("closed_on") private boolean closed_on;
    @JsonProperty("closing_date") private LocalDate closing_date;
    @JsonProperty("loan_amount") private float loan_amount;
    @JsonProperty("ltv_percent") private float ltv_percent; // loan to value in percentage
    @JsonProperty("rehab_cost") private float rehab_cost;
    @JsonProperty("rehab_loan") private float rehab_loan;
    @JsonProperty("rehad_overview") private String rehab_overview;
    @JsonProperty("turn_around_date") private LocalDate turn_around_date;
    @JsonProperty("gross_rent_estimate") private float gross_rent_estimate;
    @JsonProperty("condition") private Condition condition;
    @JsonProperty("exit_strategy") private ExitStrategy exit_strategy;

    /**
     * Create a new House Entity with the required JSON properties
     * @param id id of house
     * @param address address of house
     * @param zipcode zipcode of house
     * @param city city where house is located
     * @param sqft square footage of house
     * @param closed_on is house closed on
     * @param closing_date closing date of house (null if N/A)
     * @param loan_amount requested loan amount
     * @param ltv_percent loan to value in percentage
     * @param rehab_cost rehad cost of house
     * @param rehab_loan rehab loan requested
     * @param rehab_overview overview of rehab needs of property
     * @param turn_around_date turn around date for house
     * @param gross_rent_estimate gross rent estimate monthly
     * @param condition condition of house
     * @param exit_strategy exit strategy of the hosue
     */
    public House(@JsonProperty("id") int id,
                 @JsonProperty("address") String address,
                 @JsonProperty("zipcode") int zipcode,
                 @JsonProperty("city") String city,
                 @JsonProperty("sqft") int sqft,
                 @JsonProperty("closed_on") boolean closed_on,
                 @JsonProperty("closing_date") LocalDate closing_date,
                 @JsonProperty("loan_amount") float loan_amount,
                 @JsonProperty("ltv_percent") float ltv_percent,
                 @JsonProperty("rehab_cost") float rehab_cost,
                 @JsonProperty("rehab_loan") float rehab_loan,
                 @JsonProperty("rehad_overview") String rehab_overview,
                 @JsonProperty("turn_around_date") LocalDate turn_around_date,
                 @JsonProperty("gross_rent_estimate") float gross_rent_estimate,
                 @JsonProperty("condition") Condition condition,
                 @JsonProperty("exit_strategy") ExitStrategy exit_strategy) {
        this.id = id;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.sqft = sqft;
        this.closed_on = closed_on;
        this.closing_date = closing_date;
        this.loan_amount = loan_amount;
        this.ltv_percent = ltv_percent;
        this.rehab_cost = rehab_cost;
        this.rehab_loan = rehab_loan;
        this.rehab_overview = rehab_overview;
        this.turn_around_date = turn_around_date;
        this.gross_rent_estimate = gross_rent_estimate;
        this.condition = condition;
        this.exit_strategy = exit_strategy;
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, address, zipcode, city, sqft,
        closed_on, closing_date, loan_amount, ltv_percent, rehab_cost, rehab_loan,
        rehab_overview, turn_around_date, gross_rent_estimate, condition, exit_strategy);
    }

}
