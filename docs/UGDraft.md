# LoveBook User Guide Draft

LoveBook, is a dating-focused height book application, revolving around providing users with a convenient
and enjoyable tool to enhance their dating experiences. Featuring user profile management, date organization,
compatibility ranking, and customizable filtering options, LoveBook enhances the efficiency and effectiveness of your
online dating journey.

## Wireframes

![Figma](/docs/images/user-guide/Figma.png)

## Features

### Listing current dates : `list`

Format: `list`

Expected output: `Lists all dates and their associated details`

### Deletion of dates: `delete`

Format: `delete INDEX`

Parameter constraints:
- The index must be a positive integer, and be within the range of the recorded dates

Example: `delete 2`

Expected output: `Deletes the task at the specified INDEX.`

Output if error : `The index you have provided is out of bounds of your current list of dates`

### Editing your profile `editP`

Format: `editP /name NAME /age AGE /gender GENDER /horoscope  HOROSCOPE`

Parameter constraints:
- Name should be non-empty string.
- Age should be positive integer.
- Gender should be a character (M/F).
- Horoscope should be a non-empty string.

Example: `editP /name Ryann /age 22 /gender F /horoscope Taurus`

Expected output: `User profile has been successfully updated!`

Output if error: ```Please follow the required format (EditP /name Adam /age 123 /gender F /horoscope Cancer)```

### Creation of new date
Format: `NewD/ name NAME / age AGE / gender GENDER / horoscope HOROSCOPE`
Parameter constraints:
- Name should be non non-empty string.
- Age should be a positive integer.
- Gender should be a character (M/F).
- Horoscope should be a non-empty string.

Example:
`New date /name Cleon /age 22 /gender F /horoscope Taurus`

Expected output: `New date has been successfully added!`

Output if error:
Please follow the required format to add a new date (New date /name Adam /age 123 /gender F /horoscope Cancer)

### Edit existing dates
Format: `EditD INDEX /METRIC NEW ARG`

Parameter constraints:
- The index must be a positive integer, and be within the range of the recorded dates
- Metric is limited to `gender, age, horoscope, name` only
- New arg replaces the existing argument for that metric
- User can edit up to n number of metrics in one command line, where n refers to the number of metrics available

Example:
- `EditD 3 /name Cleon` (editing 1 metric)
- `EditD 3 /name Cleon /horoscope Cancer` (editing 2 metrics)
- `EditD 3 /horoscope Cancer /name Cleon` (sequence doesn't matter)

Expected Output: `The date information has been successfully updated!`

### Random Date Generator
Format: `random`

Expected Output: `A random date entry is printed`

Output if error: ```No dates in list!```

### Filter by Metric
Format: `filter /METRIC ARG`

Parameter constraints:
- Filter must be from list of metrics
- Metric is limited to `gender, age, horoscope, name` only

Example:
- `filter /name Cleon`
- `filter /gender M`

Expected Output: `Lists the dates with the metric specified`

Output if error: ```No dates found!```
