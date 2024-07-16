package com.lenders.app.persistence;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenders.app.model.Investment;

public class InvestmentFileDAO implements InvestmentDAO {

    private static final Logger LOG = Logger.getLogger(InvestmentFileDAO.class.getName());

    Map<Integer, Investment> allInvestments;

    Map<Integer, Investment> userInvestments;

    Map<Integer, Investment> houseInvestments;

    private ObjectMapper objectMapper;

    private static int nextId;

    private String allInvestmentFilename;

    private String userInvestmentFilename;

    private String houseInvestmentFilename;


    @Override
    public Investment createInvestment(int user_id, int house_id, String date, float money_invested)
            throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createInvestment'");
    }

    @Override
    public Investment createInvestment(Investment investment) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createInvestment'");
    }

    @Override
    public Investment acceptInvestment(int id) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acceptInvestment'");
    }

    @Override
    public boolean removeInvestment(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeInvestment'");
    }

    @Override
    public Investment[] getUserInvestments(int user_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserInvestments'");
    }

    @Override
    public Investment[] getHouseInvestments(int house_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHouseInvestments'");
    }

    @Override
    public Investment getInvestment(int id) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInvestment'");
    }

    @Override
    public Investment updateInvestment(Investment investment) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInvestment'");
    }
    
}
