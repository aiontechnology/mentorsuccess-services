package io.aiontechnology.mentorsuccess.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.aiontechnology.mentorsuccess.client.feign.SchoolApi;
import io.aiontechnology.mentorsuccess.model.inbound.InboundSchool;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundSchool;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.UUID;

/**
 * Proxy object that can be used to make calls to the server to get/set school resources.
 *
 * @author Whitney Hunter
 * @since 0.4.0
 */
@RequiredArgsConstructor
public class SchoolApiProxy {

    private final SchoolApi api;

    public SchoolApiProxy(URI baseUri) {
        api = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(SchoolApi.class, baseUri.toString());
    }

    public OutboundSchool createSchool(InboundSchool school) {
        return api.createSchool(school);
    }

    public OutboundSchool getSchool(UUID schoolId) {
        return api.getSchool(schoolId);
    }

}
