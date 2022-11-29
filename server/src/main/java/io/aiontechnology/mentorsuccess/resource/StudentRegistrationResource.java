package io.aiontechnology.mentorsuccess.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.aiontechnology.mentorsuccess.entity.workflow.StudentRegistration;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundStudentRegistration;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"content"})
public class StudentRegistrationResource extends OutboundStudentRegistration<StudentRegistration> {

    public StudentRegistrationResource(StudentRegistration studentRegistration) {
        super(studentRegistration);
    }

}
