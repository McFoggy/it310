package fr.enseirb.it310.projects.synchronized_keyword;


public class SynchronizedUsage {
    public static synchronized void uneMethodeDeClasseSynchronisee() {
        // synchronization done with SynchronizedUsage.class object
    }
    
    public synchronized void uneMethodeSynchronisee() {
        // synchronization done with "this" object
    }

    private final Object theLock = new Object();

    
    public void aMethod() {
    	// Des traitements
        synchronized (theLock) {
            // synchronization done with "this" object
        }
     // Des traitements
    }
}
