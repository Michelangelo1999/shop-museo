package jana60.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
//import java.nio.channels.ReadableByteChannel;
//
//import javax.swing.text.Document;
//
//import org.springframework.stereotype.Service;
//
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.tool.xml.XMLWorkerHelper;
//
//@Service
//public class FatturaService {
//	
//	public void generatePDFFromHTML(String filename) throws DocumentException, IOException {
//		Document document = new Document();
//		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/output/html.pdf"));
//		document.open();
//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(filename));
//		document.close();
//	}
//	
//	public void DownloadFile() throws IOException {
//
//	        URL fetchWebsite = new URL("http://localhost8080/fattura/{id}.pdf");
//	        ReadableByteChannel readableByteChannel = Channels.newChannel(fetchWebsite.openStream());
//
//	        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Downloads\\Fattura.pdf")) {
//	            fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
//	        }
//	    }
//	}