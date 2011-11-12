package com.bukkit.JamesArchuleta.Warp;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Locations {
    private static String fileName = "warps.txt";

    public static void saveLocation(Location l, String name, String world) {

        FileOutputStream out;

        try {

            removeLineFromFile(name);
            out = new FileOutputStream(fileName,new File(fileName).exists());

            // Connect print stream to the output stream
            PrintStream p = new PrintStream( out );

            p.println (name + ":" + l.getX() + ":" + l.getBlockY() + ":" + l.getBlockZ() + ":" + l.getYaw() + ":" + l.getPitch() + ":" + world);
            p.close();

        } catch (FileNotFoundException e) {
            System.out.println("Warp:" + e.getMessage());
        }

    }

    public static Location getLocation(String name, Server s) {
        Location l = null;
        Scanner scanner;

        try {
            scanner = new Scanner(new FileReader(fileName));

            try {

                // first use a Scanner to get each line
                while (scanner.hasNextLine()) {

                    String[] elements = scanner.nextLine().split(":");

                    if (elements.length >= 5 && elements[0].toLowerCase().matches(name + ".*") ){
                        l = new Location(s.getWorlds().get(0),
                                Double.parseDouble(elements[1]),
                                Double.parseDouble(elements[2]),
                                Double.parseDouble(elements[3]));
                        l.setYaw(Float.parseFloat(elements[4]));

                        if (elements.length == 5){ // old warp format
                            return l;
                        } else if (elements.length == 6) { // hey0 format
                                l.setPitch(Float.parseFloat(elements[5]));
                                return l;
                        } else if (elements.length == 7) { // hey0 format + world
                                l.setYaw(Float.parseFloat(elements[4]));
                                l.setPitch(Float.parseFloat(elements[5]));
                                l.setWorld(s.getWorld(elements[6]));
                                return l;
                        }
                    }

                }

            } catch (Exception e){
                System.out.println("Warp:" + e.getMessage());
                e.printStackTrace();
            }finally {
                scanner.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Warp:" + e.getMessage());
        }

        return null;

    }

    public static Boolean removeLineFromFile(String nameToRemove) {
        Boolean brv = false;
        try {

            File inFile = new File(fileName);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return false;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                String[] elements = line.split(":");
                if (!elements[0].trim().equals(nameToRemove.trim())) {

                    pw.println(line);
                    pw.flush();
                    brv = true;
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return false;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        }
        catch (FileNotFoundException ex) {
            System.out.println("Warp:" + ex.getMessage());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return brv;
    }

    public static String[] getList(String name, boolean serverConsole) {
        Scanner scanner;
        List<String> list = new ArrayList<String>();
        try {
            scanner = new Scanner(new FileReader(fileName));

            try {

                // first use a Scanner to get each line
                int i = 0;
                while (scanner.hasNextLine()) {

                    String[] elements = scanner.nextLine().split(":");

                    if (elements[0].matches(".*" + name + ".*")) {
                        list.add(elements[0]);
                        i++;
                    }

                    if (!serverConsole && i >= 8) {
                        break;
                    }

                }

            } catch (Exception e) {
                System.out.println("Warp:" + e.getMessage());
                e.printStackTrace();
            } finally {

                scanner.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Warp:" + e.getMessage());
        }

        String[] locations = new String[list.size()];
        list.toArray(locations);

        return locations;
    }

}
