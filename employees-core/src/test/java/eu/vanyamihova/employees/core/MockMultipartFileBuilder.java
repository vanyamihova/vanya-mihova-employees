package eu.vanyamihova.employees.core;

import lombok.experimental.UtilityClass;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class MockMultipartFileBuilder {

    public static MultipartFile getFile(String data) {
        byte[] dataAsBytes = data.getBytes();
        return new MockMultipartFile("multipart-file.txt", dataAsBytes);
    }

}
