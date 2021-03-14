package ua.com.alevel.service.impl;

import ua.com.alevel.service.CalcService;

import java.math.BigInteger;

//@Deprecated
public class DefaultCalcService implements CalcService {

    public DefaultCalcService() {
        System.out.println("DefaultCalcService.DefaultCalcService");
    }

    @Override
    public BigInteger sum(BigInteger a, BigInteger b) {
        return a.add(b);
    }
}
