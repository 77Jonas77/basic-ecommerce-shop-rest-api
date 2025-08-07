package dev.jsojka.basic_ecommerce_shop.products;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

@Repository
public class ProductFileSystemRepositoryImpl implements IProductFileSystemRepository {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public String saveProductFile(MultipartFile file) {
        ensureUploadDirExists();
        String filePath = uploadDir + File.separator + file.getOriginalFilename();
        try {
            // save file in fs (file system)
            file.transferTo(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ProductUploadFileException("File upload failed: " + e.getMessage());
        }
        return file.getOriginalFilename();
    }

    @Override
    public FileSystemResource findUploadedFileByFilename(String filename) throws FileNotFoundException {
        String filePath = uploadDir + File.separator + filename;
        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("File not found: " + filename);
        }
        return new FileSystemResource(file);
    }

    private String[] getFiles() {
        File dir = new File(uploadDir);
        String[] files = dir.list();
        return files != null ? files : new String[0];
    }

    private void ensureUploadDirExists() {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

}
