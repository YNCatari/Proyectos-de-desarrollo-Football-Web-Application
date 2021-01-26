package App.Football.Helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class AppFileUpload {
    public static String Upload(MultipartFile file, String path, String name) throws IOException {
        String fileName = "";
        if (!file.getOriginalFilename().isEmpty())
        {
            String fullName = name.replace(" ", "-");
            fileName = fullName.toLowerCase() + "." + getFileExtension(file);
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/uploads/"+path, fileName)));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        return fileName;
    }

    private static String getFileExtension(MultipartFile file)
    {
        String fileName = file.getOriginalFilename();
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i+1);
        }
        return "";
    }
}
