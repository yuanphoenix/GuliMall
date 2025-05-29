package valid;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, UpdateGroup.class})
public interface UpdateWithDefaultGroup {

}
