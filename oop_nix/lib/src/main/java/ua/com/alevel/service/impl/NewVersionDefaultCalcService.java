package ua.com.alevel.service.impl;

import ua.com.alevel.service.CalcService;

import java.math.BigInteger;

//@Deprecated
public class NewVersionDefaultCalcService implements CalcService {

    public NewVersionDefaultCalcService() {
        System.out.println("NewVersionDefaultCalcService.NewVersionDefaultCalcService");
    }

    @Override
    public BigInteger sum(BigInteger a, BigInteger b) {
        return a.add(b);
    }
}
