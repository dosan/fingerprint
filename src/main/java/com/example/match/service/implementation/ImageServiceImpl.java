package com.example.match.service.implementation;

import com.example.match.model.Image;
import com.example.match.repository.ImageRepository;
import com.example.match.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public void saveImage(String fileName, String fileDownloadUri, String type, int size) {
        Image img = new Image();
        img.setName(fileName);
        img.setPath(fileDownloadUri);
        img.setType(type);
        img.setSize(size);
        imageRepository.save(img);
    }
}
