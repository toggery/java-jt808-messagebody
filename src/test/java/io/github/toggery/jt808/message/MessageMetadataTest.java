package io.github.toggery.jt808.message;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author togger
 */
class MessageMetadataTest {

    @Test
    void inbounds() {
        final Map<Integer, MessageMetadata> messageMetadataMap = MessageMetadata.inbounds();
        System.out.println("Default inbound message metadata:");
        System.out.println(messageMetadataMap.values().stream()
                .sorted(Comparator.comparing(MessageMetadata::getId))
                .collect(Collectors.toList()));
    }

    @Test
    void outbounds() {
        final Map<Integer, MessageMetadata> messageMetadataMap = MessageMetadata.outbounds();
        System.out.println("Default outbound message metadata:");
        System.out.println(messageMetadataMap.values().stream()
                .sorted(Comparator.comparing(MessageMetadata::getId))
                .collect(Collectors.toList()));
    }

}