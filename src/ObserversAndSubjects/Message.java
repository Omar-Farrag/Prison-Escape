package ObserversAndSubjects;

public class Message {
    Subject origin;
    Topic topic;
    Object payload;

    public Message(Subject origin, Topic t, Object payload) {
        this.origin = origin;
        this.topic = t;
        this.payload = payload;
    }

    public Message(Message m) {
        origin = m.origin;
        topic = m.topic;
        payload = m.payload;
    }

    public Topic getTopic() {
        return topic;
    }

    public Object getPayload() {
        return payload;
    }
}
