package pl.piomin.services.rest.customer.bootData;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.model.Image;
import pl.piomin.services.rest.customer.repository.ImageRepository;
import pl.piomin.services.rest.customer.utils.ImageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RequiredArgsConstructor
@Component
public class BootData implements CommandLineRunner {

    private final ImageRepository imageRepository;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        uploadImage();
        System.out.println("___add initial user___");
    }

    public void uploadImage() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:testImage.jpg");
        InputStream inputStream = resource.getInputStream();

        byte[] imageData = StreamUtils.copyToByteArray(inputStream);

        byte[] compressedImageData = ImageUtils.compressImage(imageData);
        var imageToSave = Image.builder()
                .name("testImage.jpg")
                .type("image/jpeg")
                .imageData(compressedImageData)
                .build();
        imageRepository.save(imageToSave);
    }

    public static byte[] getByteArray(File file) throws IOException {
        FileInputStream fl = new FileInputStream(file);
        byte[] arr = new byte[(int) file.length()];
        fl.read(arr);
        fl.close();
        return arr;
    }
}
