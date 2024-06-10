package com.lenders.app.persistence;

import com.lenders.app.model.House;
import com.lenders.app.model.Investment;

import java.io.IOException;

/**
 * Implements the classes and functionality for JSON based Investment persistence
 *
 * @author Matthew Morrison
 */
public class InvestmentFileDAO implements InvestmentDAO{
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
