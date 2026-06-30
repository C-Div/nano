package cdiv.nano.api;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface NotImplemented {
    /**
     * <p>The optional description</p>
     */
    String value() default "";
}
