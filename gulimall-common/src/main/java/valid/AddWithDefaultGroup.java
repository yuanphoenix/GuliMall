package valid;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, AddGroup.class})
public interface AddWithDefaultGroup {

}
