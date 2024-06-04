package com.lenders.app.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the House Model Class
 *
 * @author Matthew Morrison
 */
@Tag("Model-Tier")
public class HouseTest {

    private House testHouse;

    @BeforeEach
    public void houseSetUp() {

        ArrayList<String> test_tags =new ArrayList<>();
        test_tags.add("fair_condition");
        test_tags.add("under_contract");

        testHouse = new House(
                100,
                "123 Name St",
                12345,
                "City Name",
                123456.00f,
                2003.68f,
                100000.00f,
                test_tags
        );
    }

    @Test
    public void testNewHouse() {
        //Expected Values
        int id = 200;
        String address = "111 Roch street";
        int zipcode = 23456;
        String city = "This City";
        float property_value = 200000.00f;
        float money_raised = 100022.00f;
        float asking_price = 250000.00f;

        ArrayList<String> tags =new ArrayList<>();
        tags.add("great_condition");
        tags.add("prospective_buy");

        House h = new House(id, address, zipcode, city,
                property_value, money_raised, asking_price, tags);

        assertEquals(id, h.getId());
        assertEquals(address, h.getAddress());
        assertEquals(zipcode, h.getZipcode());
        assertEquals(city, h.getCity());
        assertEquals(property_value, h.getPropertyValue());
        assertEquals(money_raised, h.getMoneyRaised());
        assertEquals(asking_price, h.getAskingPrice());

        assertTrue(h.getTags().contains("great_condition"));
        assertTrue(h.getTags().contains("prospective_buy"));
    }


    @Test
    public void testAddNewTagPasses() {
        String tag = "needs_repairs";
        boolean a = testHouse.addNewTag(tag);

        assertTrue(a);
        assertTrue(testHouse.getTags().contains("needs_repairs"));
        assertEquals(3, testHouse.getTags().size());
    }

    @Test
    public void testAddNewTagFails() {
        String tag = "fair_condition";
        boolean a = testHouse.addNewTag(tag);

        assertFalse(a);
        assertTrue(testHouse.getTags().contains("fair_condition"));
        assertEquals(2, testHouse.getTags().size());
    }

    @Test
    public void testDeleteTagSuccess() {
        String tag = "fair_condition";
        testHouse.deleteTag(tag);

        assertFalse(testHouse.getTags().contains("fair_condition"));
        assertEquals(1, testHouse.getTags().size());
    }

    @Test
    public void testDeleteTagDNE() {
        String tag = "does_not_exist";
        testHouse.deleteTag(tag);

        assertFalse(testHouse.getTags().contains("does_not_exist"));
        assertEquals(2, testHouse.getTags().size());
    }

    @Test
    public void testSetMoneyRaised() {
        float money_raised = 232323.00f;

        testHouse.setMoneyRaised(money_raised);
        assertEquals(money_raised, testHouse.getMoneyRaised());
    }

    @Test
    public void testSetPropertyValue() {
        float property_value = 1000000.00f;
        testHouse.setPropertyValue(property_value);
        assertEquals(property_value, testHouse.getPropertyValue());
    }

    @Test
    public void testSetAskingPrice() {
        float asking_price = 2000000.00f;
        testHouse.setAskingPrice(asking_price);
        assertEquals(asking_price, testHouse.getAskingPrice());
    }

    @Test
    public void testToString() {
        String expected_string = String.format(House.STRING_FORMAT,
                testHouse.getId(), testHouse.getAddress(),
                testHouse.getZipcode(), testHouse.getCity(),
                testHouse.getPropertyValue(), testHouse.getMoneyRaised(),
                testHouse.getAskingPrice(), testHouse.getTags());

        String actual_string = testHouse.toString();

        assertEquals(expected_string, actual_string);
    }
}
