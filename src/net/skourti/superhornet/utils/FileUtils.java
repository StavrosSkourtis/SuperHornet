/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skourti.superhornet.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stavros
 */
public class FileUtils {
    
    /**
     * Reads a file and returns the content as a big String.
     * @param path
     * @return 
     */
    public static String readFile(String path){
        StringBuilder buffer = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            
            String line;
            while( (line = reader.readLine())!=null)
                buffer.append(line).append('\n');
            
        } catch (FileNotFoundException ex) {
            System.err.println("File "+path+" was not found...");
            System.exit(1);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        
        
        return buffer.toString();
    }
}
