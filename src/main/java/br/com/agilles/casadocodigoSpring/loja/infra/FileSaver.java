package br.com.agilles.casadocodigoSpring.loja.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Component
public class FileSaver {

    @Autowired
    private HttpServletRequest request;

    private String pathname;
    private String realPath;

    public String write(String baseFolder, MultipartFile file) {
        try {
            realPath = request.getServletContext().getRealPath("/"+baseFolder);
            pathname = realPath + "" + file.getOriginalFilename();
            file.transferTo(new File(pathname));

            return baseFolder+"/"+file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
