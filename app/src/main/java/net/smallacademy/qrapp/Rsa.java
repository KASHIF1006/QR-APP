package net.smallacademy.qrapp;

import java.util.*;
import java.math.*;
import java.util.Scanner;
//Rsa Class
public class Rsa {

    private static final Scanner sc = new Scanner(System.in);

    private int p, q, n, z, d = 0, e, i;

    public Rsa() {
        // initializing p & q
        p=5;
        q=7;
        // multipling p *q
        n = p * q;

        z = (p - 1) * (q - 1);

        for (e = 2; e < z; e++) {
            if (gcd(e, z) == 1) // e is for public key exponent
            {
                break;
            }
        }
        //e should be in the range 1-z

        // calculate d
        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * z);
            if (x % e == 0) //d is for private key exponent
            {
                d = x / e;
                break;
            }
        }

    }

    private static int gcd(int e, int z) {
        if (e == 0) {
            return z;
        } else {
            return gcd(z % e, e);
        }
    }

    double encrypt(int msg) {
        //Encrypting  C = msg ^e mod n
        return (Math.pow(msg, e)) % n;
    }

    //encrypting function
    double[] encrypt(String msg) {
        int[] charactersAsNumbers = new int[msg.length()];
        for(int i = 0; i < msg.length(); i++) {
            //equalizing with ascii value
            charactersAsNumbers[i] = msg.codePointAt(i);
        }
        double[] encryptedMsg = new double[msg.length()];
        for(int i = 0; i < charactersAsNumbers.length; i++) {
            encryptedMsg[i] = encrypt(charactersAsNumbers[i]);
        }
        return encryptedMsg;
    }
}

