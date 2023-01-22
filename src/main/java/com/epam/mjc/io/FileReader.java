package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (BufferedReader inputStream = new BufferedReader(new java.io.FileReader(file))) {
            String s;
            while ((s = inputStream.readLine()) != null) {
                int delimiter = s.indexOf(":");
                if (s.contains("Name")) {
                    profile.setName(s.substring(delimiter + 2));
                } else if (s.contains("Age")) {
                    try {
                        profile.setAge(Integer.parseInt(s.substring(delimiter + 2)));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else if (s.contains("Email")) {
                    profile.setEmail(s.substring(delimiter + 2));
                } else {
                    try {
                        profile.setPhone(Long.parseLong(s.substring(delimiter + 2)));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile(profile.getName(), profile.getAge(), profile.getEmail(), profile.getPhone());
    }
}
