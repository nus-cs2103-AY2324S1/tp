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
