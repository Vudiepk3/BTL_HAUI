/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.nio.ByteBuffer;
import java.util.Random;

public class RSA {
    private long p, q;
    public long a, b;
    public long n;
    public RSA(long p, long q) {
        this.p = p;
        this.q = q;
        this.n = p * q;
        findB();
        findA();
    }
    public RSA() {
    }
    //Kiểm tả số nguyên tố
    public boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        long k = (long) Math.sqrt(n);
        for (long l = 2; l <= k; l++) {
            if (n % l == 0) {
                return false;
            }
        }
        return true;
    }
    //kiểm tra xem hai số nguyên tố p và q có khác nhau không
    public boolean checkUiquePQ() {
        return p == q ? false : true;
    }
    //ước chung lớn nhất cảu hai số nguyên
    private long GCD(long a, long b) {
        long r;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    //tính giá tri hàm Euler của n
    private long tinhPhiN() {
        return (p - 1) * (q - 1);
    }
    //tính giá trị b
    private void findB() {
        long result = longRandom(2, tinhPhiN(), new Random());
        while (GCD(result, tinhPhiN()) != 1) {
            result = longRandom(2, tinhPhiN(), new Random());
        }
        b = result;
    }
    //sinh số nguyên tó ngấu nhiên
    long longRandom(long min, long max, Random rand) {
        byte[] buf = new byte[256];
        rand.nextBytes(buf);
        ByteBuffer bb = ByteBuffer.wrap(buf);
        long longRand = bb.getLong();
             return (Math.abs(longRand % (max - min)) + min);
    }
    //tìm số  nghịch đảo cảu a trong modulo phi(n)
    private long findInverseNumber(long a, long phiN) {
        for (long l = 1; l < phiN; l++) {
            if ((a * l) % phiN == 1) {
                return l;
            }
        }
        return -1;
    }
    //ymf giá trị a sao cho (a*b)%phi(n) = 1;
    private void findA() {
        a = findInverseNumber(b, tinhPhiN());
    }
    //tính giá trị a^b mod n
    public long calculatePow(long a, long b, long n) {
        long result = 1L;
        for (long i = 0; i < b; i++) {
            result = (result * a) % n;
        }
        return result;
    }
    public static void main(String[] args) {
        
    }

}
