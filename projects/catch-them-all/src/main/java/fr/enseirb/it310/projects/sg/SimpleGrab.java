package fr.enseirb.it310.projects.sg;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.ByteStreams;

public class SimpleGrab {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleGrab.class);
	
	public static void main(String[] args) {
		LOG.info("{} started", SimpleGrab.class.getSimpleName());
		SimpleGrab application = new SimpleGrab();
		application.download(args);
	}

	private void download(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for (String url : args) {
			es.execute(new Grabber(url));
		}
		
		es.shutdown();
		LOG.info("All download started, awaiting termination");

		try {
			es.awaitTermination(5, TimeUnit.MINUTES);
			LOG.info("Program terminated");
		} catch (InterruptedException e) {
			LOG.info("5 minutes spent, some downloads are still running");
			LOG.info("Kill the JVM to stop");
		}
	}

	
	private class Grabber implements Runnable {
		private String url;
		
		Grabber(String url) {
			this.url = url;
		}
		
		public void run() {
			try {
				LOG.info("downloading {}", url);
				URL u = new URL(url);
				File outputFile = new File(extractFileName(u));
				ByteStreams.copy(u.openStream(), new FileOutputStream(outputFile));
				LOG.info("{} ended", url);
			} catch (Exception e) {
				LOG.error("error downloading " + url, e);
			}
		}
		
	    private String extractFileName(URL u) {
	        String urlFullFile = u.getFile();
	        if (urlFullFile.indexOf('/') != -1) {
	            return urlFullFile.substring(urlFullFile.lastIndexOf('/')+1);
	        }
	        return urlFullFile;
	    }
	}
}
