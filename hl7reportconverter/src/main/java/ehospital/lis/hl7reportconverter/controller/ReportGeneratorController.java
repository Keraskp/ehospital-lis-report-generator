package ehospital.lis.hl7reportconverter.controller;

import ehospital.lis.hl7reportconverter.model.HL7Message;
import ehospital.lis.hl7reportconverter.service.ReportGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
@RestController
@RequestMapping("/pdf")
public class ReportGeneratorController {
	@Autowired
	private ReportGeneratorService reportGeneratorService;

	@PostMapping(value = "/generate")
	public ResponseEntity<InputStreamResource> generatePDF(@RequestBody HL7Message msg) throws IOException {
		ByteArrayInputStream pdf = reportGeneratorService.export(msg.getMsg());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition", "inline;file=report.pdf");
		return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
	}
}
