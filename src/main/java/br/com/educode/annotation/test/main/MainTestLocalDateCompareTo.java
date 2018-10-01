package br.com.educode.annotation.test.main;

import java.time.LocalDate;

/**
 *
 * @author eduardo
 */
public class MainTestLocalDateCompareTo {

    public static void main(String[] args) {
        
        LocalDate dn = LocalDate.of(1987, 6, 9);
        LocalDate now = LocalDate.now();
        
        System.out.println("dn->compareTo->now: " + dn.compareTo(now));
        System.out.println("now->compareTo->dn: " + now.compareTo(dn));
    }
    
}
