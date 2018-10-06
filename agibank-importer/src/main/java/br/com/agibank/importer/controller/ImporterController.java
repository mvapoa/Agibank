package br.com.agibank.importer.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.agibank.importer.service.ManagementService;

@Component
@EnableScheduling
public class ImporterController {

	private static final Logger log = LoggerFactory.getLogger(ImporterController.class);

	private static final String HOMEPATH = "user.home";

	@Value("${environment.file.extension}")
	private String extension;
	
	@Value("${environment.file.name.out}")
	private String filenameout;

	@Value("${environment.folder.in}")
	private String folderIn;

	@Value("${environment.folder.out}")
	private String folderOut;

	@Autowired
	private ManagementService service;

	@Scheduled(cron = "0 * * ? * *")
	public void importFiles() throws IOException {
		try {
			String directoryIn = System.getProperty(HOMEPATH) + folderIn;
			String directoryOut = System.getProperty(HOMEPATH) + folderOut;

			createDirectory(directoryIn);
			createDirectory(directoryOut);

			File[] files = getAllFilesInDirectory(directoryIn);
			String result = service.processFiles(files);
			removeAllFilesInDirectory(directoryIn);

			StringBuilder fileOut = new StringBuilder(directoryOut).append(filenameout).append(extension);
			FileWriter fileWriter = new FileWriter(fileOut.toString());
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print(result);
			fileWriter.close();
		} catch (Exception e) {
			log.error(">> {}", e.toString());
		}
	}

	private void createDirectory(String directory) {
		File file = new File(directory);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	private File[] getAllFilesInDirectory(String directory) {
		File file = new File(directory);
		return file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(extension);
			}
		});
	}

	private void removeAllFilesInDirectory(String directory) throws IOException {
		File file = new File(directory);
		FileUtils.cleanDirectory(file);
	}

}
