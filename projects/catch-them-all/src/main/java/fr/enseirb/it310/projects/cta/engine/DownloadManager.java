package fr.enseirb.it310.projects.cta.engine;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadManager implements DownloadListener {
	private static final Logger LOG = LoggerFactory.getLogger(DownloadManager.class);
	private final static int MAX_CONCURRENT_DOWNLOADS = 10;
	private List<URL> urls = new LinkedList<URL>();
	private ExecutorService downloadSlots = Executors.newFixedThreadPool(MAX_CONCURRENT_DOWNLOADS);
	private File outputDirectory;
	private Download[] activeDownloads = new Download[MAX_CONCURRENT_DOWNLOADS];

	public void addURL(String url) {
		try {
			urls.add(new URL(url));
		} catch (MalformedURLException e) {
			LOG.info("Cannot use given URL: " + url, e);
		}
	}

	public void download() {
		for (URL url : urls) {
			downloadSlots.execute(new Download(url, outputDirectory, this));
		}
	}

	public void setOutputDirectory(File outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	private Object lock = new Object();
	@Override
	public void started(Download d) {
		int nextFreeSlot;
		synchronized (lock) {
			nextFreeSlot = findFreeSlot();
			activeDownloads[nextFreeSlot] = d;
		}
		
		LOG.info("process [{}] downloading {}", nextFreeSlot, d.getFileName());
	}

	private int findFreeSlot() {
		int i = 0;
		while(i < MAX_CONCURRENT_DOWNLOADS && activeDownloads[i] != null) {
			i++;
		}
		return i;
	}

	@Override
	public void failed(Download d) {
		int removedKey = -1;
		synchronized (lock) {
			for (int i = 0; i < activeDownloads.length; i++) {
				if (activeDownloads[i] == d) {
					activeDownloads[i] = null;
					removedKey = i;
				}
			}
		}
		if (removedKey != -1) {
			LOG.info("process [{}] failed to download {}", removedKey, d.getFileName());
		} else {
			throw new IllegalStateException("could not find activeDownload for file " + d.getFileName());
		}
	}

	public void stop(int downloadToStop) {
		LOG.info("stopping [{}] ", downloadToStop);
		synchronized (lock) {
			activeDownloads[downloadToStop].stop();
		}
	}

	public void shutdown() {
		LOG.info("shutting down");
		downloadSlots.shutdown();
		try {
			downloadSlots.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOG.error("after 5 seconds, all downloads were not stopped, aborting");
		}
	}

	@Override
	public void ended(Download d, TerminationStatus status) {
		int removedKey = -1;
		synchronized (lock) {
			for (int i = 0; i < activeDownloads.length; i++) {
				if (activeDownloads[i] == d) {
					activeDownloads[i] = null;
					removedKey = i;
				}
			}
		}
		if (removedKey != -1) {
			LOG.info("process [{}] terminated {}", removedKey, status);
		} else {
			throw new IllegalStateException("could not find activeDownload for file " + d.getFileName());
		}
	}
}
