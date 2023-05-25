package ehospital.lis.hl7reportconverter.controller;

import ehospital.lis.hl7reportconverter.service.ReportGeneratorService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/pdf")
public class ReportGeneratorController {
	private final ReportGeneratorService reportGeneratorService;
	public ReportGeneratorController(ReportGeneratorService reportGeneratorService) {
		this.reportGeneratorService = reportGeneratorService;
	}

	@GetMapping("/generate")
	public void generatePDF(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		this.reportGeneratorService.export(response);
	}
}
