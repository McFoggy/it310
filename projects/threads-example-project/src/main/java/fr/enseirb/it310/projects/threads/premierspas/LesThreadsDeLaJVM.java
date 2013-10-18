package fr.enseirb.it310.projects.threads.premierspas;

import java.util.Set;

public class LesThreadsDeLaJVM {
    public static void main(String[] args) {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        System.out.println(threadSet.toString().replace("],", "],\n"));
    }
}
