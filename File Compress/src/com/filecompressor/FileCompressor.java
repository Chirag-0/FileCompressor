package com.filecompressor;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class FileCompressor {
    public String readFileToString(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    // Function to write a String to a file
    public  void writeFile(String content, String filePath) throws IOException {
        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath))) {
            // Convert the binary string to bytes and write them to the output file
            for (int i = 0; i < content.length(); i += 8) {
                String byteString = content.substring(i, Math.min(i + 8, content.length()));
                writer.write(Integer.parseInt(byteString, 2)); // Parse the byte string as an integer
            }
        }
    }

    public  void writeFileForDecompressed(String content, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

}
