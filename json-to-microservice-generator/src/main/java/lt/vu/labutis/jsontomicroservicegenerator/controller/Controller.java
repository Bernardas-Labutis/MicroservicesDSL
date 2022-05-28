package lt.vu.labutis.jsontomicroservicegenerator.controller;

import lt.vu.labutis.jsontomicroservicegenerator.json.Root;
import lt.vu.labutis.jsontomicroservicegenerator.service.GenerateProjectsService;
import lt.vu.labutis.jsontomicroservicegenerator.utils.ZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

@RestController
public class Controller {
    @Autowired
    private GenerateProjectsService service;

    @GetMapping("/test")
    public ResponseEntity<Void> test() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/generateZip", produces = "application/zip")
    public void generateZip(HttpServletResponse response, @RequestBody Root root) throws IOException {
        service.generateProjects(root);

        //setting headers
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"generated.zip\"");

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        ZipUtils.zipFile(new File("./generated-sources"), "generated", zipOutputStream);
        zipOutputStream.close();
    }
}
