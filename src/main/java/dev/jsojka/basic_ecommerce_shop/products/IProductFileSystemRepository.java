package dev.jsojka.basic_ecommerce_shop.products;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface IProductFileSystemRepository {
    String saveProductFile(MultipartFile file);
    FileSystemResource findUploadedFileByFilename(String filename) throws FileNotFoundException;
}
