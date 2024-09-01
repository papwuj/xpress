package com.ruoyaya.xpress.commons.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Stream;

public class EncodingConverter {

    public static void convertGbkToUtf8(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be an existing directory");
        }

        try (Stream<Path> filePathStream = Files.walk(path)) {
            filePathStream.filter(Files::isRegularFile)
                          .forEach(filePath -> {
                              try {
                                  convertFileEncoding(filePath, Charset.forName("GBK"), StandardCharsets.UTF_8);
                              } catch (IOException e) {
                                  System.err.println("Failed to convert file: " + filePath.toString());
                                  e.printStackTrace();
                              }
                          });
        }
    }

    private static void convertFileEncoding(Path filePath, Charset sourceCharset, Charset targetCharset) throws IOException {
        byte[] contentBytes = Files.readAllBytes(filePath);
        String content = new String(contentBytes, sourceCharset);
        Files.write(filePath, content.getBytes(targetCharset));
    }

    public static void main(String[] args) {


        String directoryPath = "E:\\Workspaces\\product\\opensite\\opensite-module-base\\src\\main\\java\\";
        try {
            convertGbkToUtf8(directoryPath);
            System.out.println("Conversion completed successfully.");
        } catch (IOException e) {
            System.err.println("Error during conversion: " + e.getMessage());
        }
    }
}
