package io.aiontechnology.mentorsuccess.model.outbound;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@Relation(collectionRelation = "teacherModelList")
public class OutboundTeacher extends RepresentationModel<OutboundTeacher> {

    /** The first name of the teacher. */
    private final String firstName;

    /** The last name of the teacher. */
    private final String lastName;

    /** The teacher's email address. */
    private final String email;

    /** The teacher's work phone number. */
    private final String workPhone;

    /** The teacher's cell phone */
    private final String cellPhone;

    /** First grade taught by teacher. */
    private final Integer grade1;

    /** Second grade taught by teacher. Null if there is only one grade. */
    private final Integer grade2;

}
