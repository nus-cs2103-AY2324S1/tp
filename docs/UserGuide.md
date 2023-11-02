- Invalid format (e.g., no prefix):


- The contact you are trying to delete does not exist in your contact list: `Please provide the person's full name as in the existing contact list`.


### Finding contacts `find`
You can find all the persons from the contact list with the matching keyword.


**Format:** `find n/KEYWORDS_IN_NAME`


**Acceptable values:**
- `KEYWORDS_IN_NAME` must be alphanumeric, and it is not case sensitive.


**Example(s):**
- `find n/alice alex john`
  Returns all the contacts with names of Alice, Alex, and John.


**Potential error(s):**
- Invalid format (e.g., no prefix):




### Listing contacts `list`
You can list all the contacts in your contact list.


**Format:** `list`


**Acceptable values:**
No additional parameters.


**Example(s):**
- `List`
  Lists all the people in the group.


**Potential error(s):**
- Extra inputs detected.


## Commands to Manage Groups


### Adding a group `new`
You can create a group in your contact list.


**Format:** `new g/GROUP_NAME`


**Acceptable values:**
- `GROUP_NAME` must be alphanumeric and cannot be blank.
- `GROUP_NAME` must not be an existing group in your contact list.


**Example(s):**
- `new g/CS2103T tp` creates a new group named "CS2103T tp".


**Potential error(s):**
- Incorrect format (e.g., no prefix):


- The group you are trying to add already exists in your contact list: `This group already exists in the contact list`.


### Deleting a group `delete`
You can delete a group in your contact list.


**Format:** `delete n/GROUP_NAME`


**Acceptable values:**
- `GROUP_NAME` must be alphanumeric and cannot be blank.
- `GROUP_NAME` must be an existing group in your contact list.


**Example(s):**
- `delete g/CS1203T`
  This deletes the group "CS2103T" from your contact list, given "CS2103T" is an existing group in your contact list.


**Potential error(s):**
- Incorrect format (e.g., no prefix):


- The group you are trying to delete does not exist in your contact list:




### Finding a group `find`
You can find a group in your contact list. This allows you to view the group's members and remarks.


**Format:** `find g/GROUP_NAME`


**Acceptable values:**
- `GROUP_NAME` must be alphanumeric and cannot be blank.
- `GROUP_NAME` must be an existing group in the contact list.

**Example(s):**
- `find g/CS2103T`
  This returns the members and remarks of the existing "CS2103T" group in your contact list.


**Potential error(s):**
- Incorrect format (e.g., no prefix):


- The group you are trying to find does not exist in your contact list:




### Listing a group `listgroup`
You can list all the groups in your contact list.


**Format:** `listgroup`


**Acceptable values:**
No additional parameters.


**Example(s):**
- `listgroup`
  This lists all the groups in your contact list.


**Potential error(s):**
- Extra inputs detected.


### Grouping a Person `group`
You can add an existing contact to an existing group.


**Format:** `group n/NAME g/GROUP_NAME`


**Acceptable values:**
- `NAME` must be alphanumeric and cannot be blank.
- `NAME` must be an existing contact in your contact list.
- `GROUP_NAME` must be alphanumeric and cannot be blank.
- `GROUP_NAME` must be an existing group in your contact list.
- `NAME` must not be a member of `GROUP_NAME`.


**Example(s):**
- `group n/Alex Yeoh g/CS2103T`
  This adds your contact "Alex Yeoh" into the group "CS2103T".


**Potential error(s):**
- Incorrect format (e.g., no prefix):


- The contact you are trying to add is already a member of the group: `NAME is already in this group: GROUP_NAME`.


### Ungrouping a Person `ungroup`
You can remove a person from a group.


**Format:** `ungroup n/NAME g/GROUP_NAME`


**Acceptable values:**
- `NAME` must be alphanumeric and cannot be blank.
- `NAME` must be an existing contact in your contact list.
- `GROUP_NAME` must be alphanumeric and cannot be blank.
- `GROUP_NAME` must be an existing group in your contact list.
- `NAME` must be a member of `GROUP_NAME`.


**Example(s):**
- `ungroup n/Alex Yeoh g/CS2103T`
  This removes your contact "Alex Yeoh" from the group "CS2103T".


**Potential error(s):**
- Incorrect format (e.g., no prefix):


- The contact you are trying to remove is not a member of the group: `Bernice Yu is not in this group: CS2103T`.


## Commands to Manage Time


### Adding Time to a Contact `addtime`
You can add time slots when your contacts are available.


**Format:** `addtime n/NAME t/FREE_TIME`
- Provide the full name of the contact using the `n/` prefix.
- Provide the time slot of the contact using the `t/` prefix.
- Time slot is with respect to the weekly schedule.


**Acceptable values:**
- `NAME` must be alphanumeric.
- `FREE_TIME` must be a time slot within a weekly schedule.


**Example(s):**
- `addtime n/Alex Yeoh  t/mon 1400 - mon 1600`

This adds a time slot when Alex Yeoh is available to your contact list.
Insert Image
Free time added to: Alex Yeoh


**Potential error(s):**
- Contact does not exist in the contact list.
- The time slot you are trying to add is not valid.


### Removing Time from a Contact `deletetime`
You can remove available time slots of your contacts.


**Format:** `deletetime n/NAME t/FREE_TIME`
- Provide the full name of the contact using the `n/` prefix.
- Provide the time slot of the contact using the `t/` prefix.
- Time slot is with respect to the weekly schedule.


**Acceptable values:**
- `NAME` must be alphanumeric.
- `FREE_TIME` must be a time slot within a weekly schedule.
- `FREE_TIME` must be a time slot that has been added to the contact.


**Example(s):**
- `deletetime n/Alex Yeoh  t/mon 1400 - mon 1600`
  This removes a time slot when Alex Yeoh is available from your contact list.
  Insert Image
  Deleted Time From: Alex Yeoh


**Potential error(s):**
- Contact does not exist in the contact list.
- Time slot does not exist for the contact.
- Invalid time slot format.


### Listing Time from a Contact `listtime`
You list all available time slots of your contacts.


**Format:** `listtime n/NAME`
- Provide the full name of the contact using the `n/` prefix.


**Acceptable values:**
- `NAME` must be alphanumeric.


**Example(s):**
- `listtime n/Alex Yeoh`
  This lists all time slots when Alex Yeoh is available from your contact list.
  Insert Image
  Listed times of Person: Alex Yeoh
  MON 1400 - MON 1600


**Potential error(s):**
- Contact does not exist in the contact list.


### Add Meeting to a Group `addmeeting`
You can add a meeting time slot for your group.


**Format:** `addmeeting g/GROUP_NAME t/MEETING_TIME`
- Provide the full name of the group using the `g/` prefix.
- Provide the time slot of the meeting using the `t/` prefix.
- Time slot is with respect to the weekly schedule.


**Acceptable values:**
- `GROUP_NAME` must be alphanumeric.
- `MEETING_TIME` must be a time slot within a weekly schedule.


**Example(s):**
- `addmeeting g/CS2100  t/mon 1400 - mon 1600`
  This adds a meeting for your group CS2100.
  Insert Image
  Free time added to: 2100


**Potential error(s):**
- Group does not exist in the contact list.
- Invalid time slot format.


### Remove Meeting Time from a Group `deletetime`
You remove meeting time for your groups.


**Format:** `deletetime g/GROUP_NAME t/MEETING_TIME`
- Provide the full name of the group using the `g/` prefix.
- Provide the time slot of the meeting using the `t/` prefix.
- Time slot is with respect to the weekly schedule.


**Acceptable values:**
- `GROUP_NAME` must be alphanumeric.
