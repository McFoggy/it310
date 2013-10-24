package fr.enseirb.it310.projects.cta.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.enseirb.it310.projects.cta.engine.DownloadListener.TerminationStatus;

public class Download implements Runnable {
	private static final Logger LOG = LoggerFactory.getLogger(Download.class);
	private URL url;
	private File outputDirectory;
	private String fileName;
	public String getFileName() {
		return fileName;
	}

	private DownloadListener downloadListener;
	private volatile Thread executingThread;
	
	public Download(URL url, File outputDirectory, DownloadListener listener) {
		this.url = url;
		this.downloadListener = listener;
		this.fileName = extractFileName(url);
		this.outputDirectory = outputDirectory;
	}
	@Override
	public void run() {
		InputStream is = null;
		executingThread = Thread.currentThread();
		downloadListener.started(this);
		try {
			is = url.openStream();
			File destFile = new File(outputDirectory, fileName);
			Copier.copy(is, new FileOutputStream(destFile));
			downloadListener.ended(this, TerminationStatus.NORMAL);
		} catch (IOException e) {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {}
			}
			LOG.info("failure downloading " + getFileName(), e);
			downloadListener.failed(this);
		} catch (InterruptedException e) {
			downloadListener.ended(this, TerminationStatus.INTERRUPTED);
		}
	}
	
    private String extractFileName(URL u) {
        String urlFullFile = u.getFile();
        if (urlFullFile.indexOf('/') != -1) {
            return urlFullFile.substring(urlFullFile.lastIndexOf('/')+1);
        }
        return urlFullFile;
    }
    
    public void stop() {
    	if (executingThread != null) {
    		executingThread.interrupt();
    	}
    }
}
