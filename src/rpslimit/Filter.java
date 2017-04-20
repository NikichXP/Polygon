package rpslimit;

public class Filter {

	private volatile int allowedRequests;

	public Filter(int RPS) {

		allowedRequests = 0;
		Thread allowedRequestsGenerator = new Thread(() -> {
			if (RPS >= 1000) {
				while (true) {
					if (allowedRequests < RPS) {
						allowedRequests += (RPS/100);
					}
					sleep(10);
				}
			} else if (RPS >= 100) {
				while (true) {
					if (allowedRequests < RPS) {
						allowedRequests += (RPS / 10);
					}
					sleep(100);
				}
			} else {
				while (true) {
					if (allowedRequests < RPS) {
						allowedRequests++;
					}
					sleep(1000 / RPS);
				}
			}
		});
		allowedRequestsGenerator.setDaemon(true);
		allowedRequestsGenerator.start();
	}

	public synchronized boolean requestAPI() {
		if (allowedRequests > 0) {
			allowedRequests--;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method that invoked thread sleeping. Just to avoid lines of try/catch in code an make in clearer
	 * @param ms - milliseconds to sleep
	 */
	public static void sleep (long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
