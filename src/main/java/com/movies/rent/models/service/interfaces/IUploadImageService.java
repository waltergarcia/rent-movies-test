package com.movies.rent.models.service.interfaces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadImageService {
	Resource load(String imageName) throws MalformedURLException;
	String copy(MultipartFile imageFile) throws IOException;
	boolean delete(String imageName);
	Path getPath(String imageName);
}
