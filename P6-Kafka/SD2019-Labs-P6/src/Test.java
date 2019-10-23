import java.util.Arrays;
import java.util.List;

import kakfa.KafkaPublisher;
import kakfa.KafkaSubscriber;
import kakfa.KafkaUtils;
import microgram.impl.srv.rest.JavaMedia;

public class Test {

	public static final String NEW_REFERENCE = "Microgram-MediaStorageEvent-newRef";
	public static final String DELETED_REFERENCE = "Microgram-MediaStorageEvents-deleteRef";
	
	void publisher() {
		
		final KafkaPublisher kafka = new KafkaPublisher();

		KafkaUtils.createTopics(Arrays.asList(NEW_REFERENCE, DELETED_REFERENCE));
		
		// upon createPost -> url do post, postID do post
		kafka.publish(NEW_REFERENCE, "URL", "POSTID");
		
		// upon deletePost -> url do post, postID do post
		kafka.publish(DELETED_REFERENCE, "URL", "POSTID");
		
	}
	
	
	void consumer() {
		List<String> topics = Arrays.asList(NEW_REFERENCE, DELETED_REFERENCE);
		
		KafkaSubscriber subscriber = new KafkaSubscriber(topics);
		
		subscriber.consume((topic, key, value) -> {
			System.out.printf("Kafka: topic: %s key: %s value: %s\n", topic, key, value);
		});
		
	}
	
	
	static class Pair {
		int x = 0;
		int y = 1;
		
		void m() {
			
		}
	}
	
	
	public static void main(String[] args) {
		
		Pair p = new Pair();
		
		new Thread( () -> p.x = 1);
		
		
		
		
	}
	
}
