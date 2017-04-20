package rpslimit;

import java.util.concurrent.CompletableFuture;
import static rpslimit.Filter.sleep;

public class Main {

	private static int success, fails;

	public static void main(String[] args) {
		success = 0;
		fails = 0;

		Filter filter = new Filter(2000);

		for (long t = System.currentTimeMillis(); System.currentTimeMillis() - t < 10_000; ) {
			for (int i = 0; i < 10; i++) {
				CompletableFuture.runAsync(() -> {
					if (filter.requestAPI()) {
						success = success + 1;
					} else {
						fails = fails + 1;
					}
				});
			}
			sleep(1);

		}

		System.out.println("Success: " + success + " // Fails: " + fails);

	}

}
