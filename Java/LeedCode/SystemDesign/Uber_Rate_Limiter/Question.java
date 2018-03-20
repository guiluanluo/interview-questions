package SystemDesign.Uber_Rate_Limiter;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a Rate Limiter: Whenever you expose a web service / api endpoint, you need to implement a rate limiter to
 * prevent abuse of the service (DOS attacks). Implement a RateLimiter Class with an isAllow method. Every request comes
 * in with a unique clientID, deny a request if that client has made more than 100 requests in the past second.
 */
public class Question {

  private static final long ONE_SECOND_IN_MILLIS = 1 * 1000;
  private Map<String, Integer> counterMap = new HashMap<>();
  private Map<String, Long> requestedTimeMap = new HashMap<>();

  public boolean isAllow(String clientId) {
    long now = System.currentTimeMillis();
    int requestedCounter = counterMap.get(clientId);
    long requestTime = requestedTimeMap.get(clientId);

    if ((now - requestTime) >= ONE_SECOND_IN_MILLIS && requestedCounter > 100) {
      return false;
    }

    if ((now - requestTime) >= ONE_SECOND_IN_MILLIS && requestedCounter <= 100) {
      resetRateLimiter(clientId, now);
    } else {
      int increase = counterMap.get(clientId) + 1;
      counterMap.put(clientId, increase);
    }

    return true;
  }

  private void resetRateLimiter(String clientId, long timestamp) {
    counterMap.put(clientId, 1);
    requestedTimeMap.put(clientId, timestamp);
  }


}

