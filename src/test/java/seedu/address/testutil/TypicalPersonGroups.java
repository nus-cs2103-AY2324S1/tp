package seedu.address.testutil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalPersonGroups {

	public static final Group CS2100 = new GroupBuilder().withName("CS2100")
			.withGroupRemark("CS2100 remark").withListOfGroupMates("Amy", "Benny", "Carl", "Dave")
			.withTimeIntervalList("mon 1200 - mon 1400", "wed 1600 - thur 1800").build();

	public static final Group CS2102 = new GroupBuilder().withName("CS2102")
			.withGroupRemark("CS2102 remark").withListOfGroupMates("Amelia", "Benson", "Charles", "David")
			.withTimeIntervalList("mon 1200 - mon 1400", "wed 1600 - thur 1800").build();

	public static final Group CS2103 = new GroupBuilder().withName("CS2103")
			.withGroupRemark("CS2103 remark").withListOfGroupMates("Annie", "Ben", "Cricket", "Dog")
			.withTimeIntervalList("mon 1200 - mon 1400", "wed 1600 - thur 1800").build();
	public static final Group CS2105 = new GroupBuilder().withName("CS2105")
			.withGroupRemark("CS2105 remark").withListOfGroupMates("Avner", "Bernie", "Coin", "Dawson")
			.withTimeIntervalList("mon 1200 - mon 1400", "wed 1600 - thur 1800").build();


	public static GroupList getTypicalPGroup() {

		GroupList groupList = new GroupList();
		Group[] groupArray = {CS2100, CS2102, CS2103, CS2105};

		Arrays.stream(groupArray).forEach(groupList::add);

		return groupList;


	}

}
