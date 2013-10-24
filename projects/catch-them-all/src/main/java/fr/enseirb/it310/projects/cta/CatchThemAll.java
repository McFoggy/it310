package fr.enseirb.it310.projects.cta;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.enseirb.it310.projects.cta.engine.DownloadManager;

/**
 * Hello world!
 * 
 */
public class CatchThemAll {
	private static final Logger LOG = LoggerFactory.getLogger(CatchThemAll.class);
	private final DownloadManager dm;
	
	private CatchThemAll()  {
		dm = new DownloadManager();
	}
	
	public static void main(String[] args) {
		LOG.info("{} started", CatchThemAll.class.getSimpleName());
		
		CatchThemAll application = new CatchThemAll();
		application.init(args);
		application.launch();
		LOG.info("{} loaded", CatchThemAll.class.getSimpleName());
		application.awaitUser();
	}

	private void awaitUser() {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Enter a download to stop or [RETURN] to exit");
			try {
				String read = r.readLine();
				if (read == null || "".equals(read.trim())) {
					dm.shutdown();
					break;
				}
				
				try {
					int downloadToStop = Integer.parseInt(read);
					dm.stop(downloadToStop);
				} catch (NumberFormatException  e) {
					LOG.error("input {} is not a number");
				}
				Integer.parseInt(read);
			} catch (IOException e) {}
		}
	}

	private void launch() {
		dm.download();
	}

	private void init(String[] args) {
		String outputDirectory = System.getProperty("user.dir");
		
		if (args.length > 0) {
			if ("-d".equals(args[0])) {
				outputDirectory = args[1];
			}
		}
		
		dm.setOutputDirectory(new File(outputDirectory));
		
		LOG.info("{} started with {} URLs to download", CatchThemAll.class.getSimpleName(), args.length);
		for (String url : args) {
			dm.addURL(url);
		}
	}
}
