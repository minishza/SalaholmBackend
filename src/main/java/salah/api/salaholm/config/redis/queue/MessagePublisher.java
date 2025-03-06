package salah.api.salaholm.config.redis.queue;

public interface MessagePublisher {

    void publish(final String message);
}