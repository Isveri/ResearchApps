package pl.piomin.services.rest.customer.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.model.Image;
import pl.piomin.services.rest.customer.repository.ImageRepository;
import pl.piomin.services.rest.customer.utils.ImageUtils;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public String uploadImage(MultipartFile imageFile) throws IOException {
        var imageToSave = Image.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(ImageUtils.compressImage(imageFile.getBytes()))
                .build();
        if (!imageRepository.existsByName(imageToSave.getName())) {
            imageRepository.save(imageToSave);
            return "file uploaded successfully : " + imageFile.getOriginalFilename();
        }
        return "file upload failed";
    }

    public byte[] downloadImage(String imageName) {
        Optional<Image> dbImage = imageRepository.findByName(imageName);
        imageRepository.deleteById(dbImage.get().getId());
        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("Image ID", image.getId())
                        .addContextValue("Image name", imageName);
            }
        }).orElse(null);
    }
}
