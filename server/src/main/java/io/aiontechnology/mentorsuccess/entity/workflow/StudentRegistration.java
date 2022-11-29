package io.aiontechnology.mentorsuccess.entity.workflow;

import io.aiontechnology.mentorsuccess.entity.Identifiable;
import lombok.Data;

import java.util.UUID;

@Data
public class StudentRegistration implements Identifiable<UUID> {

    private UUID id;
    private String parent1EmailAddress;
    private String parent1FirstName;
    private String parent1LastName;
    private String studentFirstName;
    private String studentLastName;

}
