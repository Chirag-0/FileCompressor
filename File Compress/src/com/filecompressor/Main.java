package com.filecompressor;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "D:\\FileCompressor\\File Compress\\src\\com\\filecompressor\\myFile.txt";
        String outputFilePath = "D:\\FileCompressor\\File Compress\\src\\com\\filecompressor\\compressed.txt";

        FileCompressor fc = new FileCompressor();
        try {

            // Read contents of input file into a String
            String inputContent = fc.readFileToString(inputFilePath);

            // Compress or decompress the content using your Huffman coding class
            HuffmanCoding huffman = new HuffmanCoding(inputContent);
            String compressedContent = huffman.encode(inputContent); // For compression
            String decompressedContent = huffman.decode(compressedContent); // For decompression

            // Write the result to an output file
            // For compression
            fc.writeFile(compressedContent, outputFilePath);
            // For decompression
            //fc.writeFileForDecompressed(decompressedContent, outputFilePath);

            System.out.println("File compression/decompression successful!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
