package ehospital.lis.hl7reportconverter.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ReportGeneratorService {
	private final Logger logger = LoggerFactory.getLogger(ReportGeneratorService.class);
	public void export(HttpServletResponse response) throws IOException {
		logger.info("PDF Report Generation Initialized");

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD);
		fontTitle.setSize(24);

		Paragraph paragraph = new Paragraph("REPORT", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(12);

		Paragraph paragraph2 = new Paragraph("This is a paragraph.", fontParagraph);
		paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

		document.add(paragraph);
		document.add(paragraph2);
		document.close();
	}

}
