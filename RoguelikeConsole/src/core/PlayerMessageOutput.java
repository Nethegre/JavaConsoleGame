package core;

import java.util.concurrent.ConcurrentLinkedQueue;

//This class will be used to collect and manage the output for the messages that will show up in the UI for the character
public final class PlayerMessageOutput {

    private static ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<>();

    public static void addToPlayerMessageOutputBuffer(String message)
    {
        messageQueue.add(message);
    }

}
