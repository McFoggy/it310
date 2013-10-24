package fr.enseirb.it310.projects.cta.engine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Copier {
	private static final int BUF_SIZE = 0x1000; // 4K

	public static long copy(InputStream from, OutputStream to)
			throws IOException, InterruptedException {
		byte[] buf = new byte[BUF_SIZE];
		long total = 0;
		while (true) {
			if (Thread.interrupted()) {
				Thread.currentThread().interrupt();
				throw new InterruptedException();
			}
			int r = from.read(buf);
			if (r == -1) {
				break;
			}
			to.write(buf, 0, r);
			total += r;
		}
		return total;
	}
}
