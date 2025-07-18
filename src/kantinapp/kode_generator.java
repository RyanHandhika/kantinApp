/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kantinapp;
import java.util.Random;

/**
 *
 * @author User
 */
public class kode_generator {
    public static String generate_kode(String prefix, int panjang)
    {
        String karakter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for(int i=0;i<panjang;i++)
        {
            int index = random.nextInt(karakter.length());
            sb.append(karakter.charAt(index));
        }
        
        return prefix + sb.toString();
    }
}
