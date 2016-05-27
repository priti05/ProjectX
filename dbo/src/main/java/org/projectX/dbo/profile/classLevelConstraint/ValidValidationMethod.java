package org.projectX.dbo.profile.classLevelConstraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * <p>Validate to see if {@link UserProfile} contains either valid email or phoneNumber</p>
 *
 * @author  Priti Patel
 * @see     org.projectX.dbo.profile.classLevelConstraint.ValidValidationMethodValidator
 * @since   0.0.1
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidValidationMethodValidator.class })
@Documented
public @interface ValidValidationMethod {
	String message() default "{Either email or phoneNumber should have value}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
