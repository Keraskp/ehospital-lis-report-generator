package ehospital.lis.hl7reportconverter.controller;

import ehospital.lis.hl7reportconverter.service.ReportGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
@RestController
@RequestMapping("/pdf")
public class ReportGeneratorController {
	@Autowired
	private ReportGeneratorService reportGeneratorService;

	@GetMapping("/generate")
	public ResponseEntity<InputStreamResource> generatePDF() throws IOException {
		ByteArrayInputStream pdf = reportGeneratorService.export();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition", "inline;file=report.pdf");
		return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
	}
}
