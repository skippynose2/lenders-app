package com.lenders.app.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenders.app.model.House;
import com.lenders.app.model.Investment;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Map;

/**
 * Implements the classes and functionality for JSON based Investment persistence
 *
 * @author Matthew Morrison
 */
public class InvestmentFileDAO implements InvestmentDAO{

    private Map<Integer, Investment> investHistory;
    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;

    public InvestmentFileDAO(@Value("${investments.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
    }

    @Override
    public Investment createInvestment(int userId, String date, House house, float amount) throws IOException {
        return null;
    }


    @Override
    public Investment[] findUserInvestments(int userId) throws IOException {
        return new Investment[0];
    }

    @Override
    public Investment[] findLastMonthInvestments(int userId) throws IOException {
        return new Investment[0];
    }

    @Override
    public Investment getOrder(int id) {
        return null;
    }
}
