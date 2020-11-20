package io.aiontechnology.mentorsuccess.model.outbound;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Value
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@NonFinal
@Relation(collectionRelation = "teacherModelList")
public class OutboundTeacher extends RepresentationModel<OutboundTeacher> {

    /** The first name of the teacher. */
    String firstName;

    /** The last name of the teacher. */
    String lastName;

    /** The teacher's email address. */
    String email;

    /** The teacher's work phone number. */
    String workPhone;

    /** The teacher's cell phone */
    String cellPhone;

    /** First grade taught by teacher. */
    Integer grade1;

    /** Second grade taught by teacher. Null if there is only one grade. */
    Integer grade2;

}
