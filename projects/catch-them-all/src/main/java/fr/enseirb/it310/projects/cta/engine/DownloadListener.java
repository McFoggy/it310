package fr.enseirb.it310.projects.cta.engine;

public interface DownloadListener {
	public enum TerminationStatus {
		NORMAL, INTERRUPTED;
	};
	public void started(Download d);
	public void ended(Download d, TerminationStatus status);
	public void failed(Download d);
}
