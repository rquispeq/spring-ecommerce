/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Oghma
 */
@Service
public class UploadFileService {

    private String folder = "/opt/images/";

    private final Path root = Paths.get("/opt/images");

    public UploadFileService() {
        System.out.println("constructor FileService");
        if (!Files.exists(root)) {
            try {
                System.out.println("no se encontró folder images, creando....");
                Files.createDirectory(root);

            } catch (IOException e) {
                System.out.println("No se creó el directorio");
                e.printStackTrace();
            }
        }
    }

    public String saveImage(MultipartFile file) throws IOException {
        createFolder();
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder + file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        } else {
            File fileSource = new File("/opt/default.jpg");
            File fileDest = new File("/default.jpg");
            InputStream in = new FileInputStream(fileSource);
            Path path = Paths.get(folder + fileDest);
            Files.write(path, in.readAllBytes());
            return "default.jpg";
        }

    }

    public void deleteImage(String fileName) {

        String path = "/opt/images/";
        File file = new File(folder + fileName);

        file.delete();
    }
    
    public void createFolder(){
        if (!Files.exists(root)) {
            try {
                Files.createDirectory(root);

            } catch (IOException e) {
                System.out.println("No se creó el directorio");
                e.printStackTrace();
            }
        }
    }

}
