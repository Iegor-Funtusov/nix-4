package ua.com.alevel.service;

import java.math.BigInteger;

public class SumServiceImpl implements SumService {

    @Override
    public BigInteger sum(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger multi(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }
}
