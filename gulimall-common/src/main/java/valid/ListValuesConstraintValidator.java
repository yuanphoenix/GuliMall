package valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ListValuesConstraintValidator implements ConstraintValidator<ListValues, Integer> {

    Set<Integer> set = new HashSet<>();

    @Override
    public void initialize(ListValues constraintAnnotation) {
        int[] val = constraintAnnotation.val();
        set.addAll(Arrays.stream(val).boxed().toList());
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }

}
