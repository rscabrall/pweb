/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.fatec.utilitarios;

/**
 *
 * @author Rafael
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Vinivius
 */
public class Criptografia {

    /**
     * @param args the command line arguments
     */
    public String criptografar(String senha) {
        try {
            //String original = "CAchorro quente nao";
            //SHA-2(SHA-256), MD5, SHA, SHA-1
            MessageDigest algorithm = 
                    MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = 
                    algorithm.digest
                    // tou usando o utf-8 pois eh uma
                    // das codificacoes mais basicas
                    // ALFABETO LATINO
                    (senha.getBytes("UTF-8"));

            // STRING BUILDER EH UM EQUIVALENTE
            // STRING BUILDER NAO GERA OBJETO A TOA
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                // PRA COLOCAR NO FORMATO HEXADECIMAL
                // USO O %X %x
                // x indica hexadecimal
                hexString.append(String.format("%02X", b));
            }
            //String senha = hexString.toString();
            //System.out.println(senha);
            return hexString.toString(); 
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Criptografia.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Criptografia.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
}

