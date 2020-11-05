package com.movies.rent.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.annotation.PreDestroy;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movies.rent.models.service.interfaces.IUploadImageService;
import com.movies.rent.utilities.constants.ApiRestConstants;
import com.movies.rent.utilities.constants.Messages;

@Service
public class UploadImageServiceImpl implements IUploadImageService {

	private final Logger logger = LoggerFactory.getLogger(UploadImageServiceImpl.class);

	@Override
	public Resource load(String imageName) throws MalformedURLException {
		Resource resource = null;
		Path dirFile = getPath(imageName);
		
		logger.info(dirFile.toString());
		
		resource = new UrlResource(dirFile.toUri());

		if(!resource.exists() && !resource.isReadable()) {
			dirFile = Paths.get(ApiRestConstants.DIR_IMAGES).resolve(ApiRestConstants.NO_MOVIE_IMAGE).toAbsolutePath();
			resource = new UrlResource(dirFile.toUri());
			logger.info(Messages.ERROR_LOADING_IMAGE, imageName);
		}
		
		return resource;
	}

	@Override
	public String copy(MultipartFile imageFile) throws IOException {
		File dirImages = new File(ApiRestConstants.DIR_IMAGES);
		String nameFile = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename().replace(" ", "");
		Path dirFile = getPath(nameFile);
		Path pathImages = dirImages.toPath();
		
		if(!Files.isDirectory(pathImages)) {
			logger.info(Messages.CREATING_DIR_IMAGES, dirImages);
			Files.createDirectory(pathImages);
		}
		
		logger.info(Messages.SAVING_MOVIE_IMAGE, dirFile.toString());
		
		Files.copy(imageFile.getInputStream(), dirFile);
	
		return nameFile;
	}

	@Override
	public boolean delete(String imageName) {
		if(imageName != null && imageName.length() > 0) {
			Path dirOldFile = Paths.get(ApiRestConstants.IMAGE_UPLOADS).resolve(imageName).toAbsolutePath();
			File oldFile = dirOldFile.toFile();
			if(oldFile.exists() && oldFile.canRead()) {
				oldFile.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String imageName) {
		return Paths.get(ApiRestConstants.DIR_IMAGES).resolve(imageName).toAbsolutePath();
	}
	
	@PreDestroy
    public void cleanUp() throws Exception {
		File dirImages = new File(ApiRestConstants.DIR_IMAGES);		
		if(Files.isDirectory(dirImages.toPath())) {
			FileUtils.deleteDirectory(dirImages);
			logger.info(Messages.DELETING_DIR_IMAGES, dirImages);
		}
    }
}
