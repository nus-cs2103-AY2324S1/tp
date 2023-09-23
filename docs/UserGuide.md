<a name="br1"></a> 

**Staff-Snap User Guide**

**User Interface Guide**

**Notation Guide**

Here are some conventions used in this guide when describing the commands:

Notation

Description

<input>

Angle brackets indicate that you need to fill in the relevant

parameters for that command

[input]

Square brackets indicate that this parameter is optional and

need not always be provided



<a name="br2"></a> 

**Features**

**Add : Adding a new employee entry**

Add a new employee entry to the current list.

Format: add n/<name> id/<identifier> d/<department> j/<jobTitle>

s/<salary> sd/<startDate> f/<isFullTime>

Example:

● add n/John Doe i/S7891234A d/Marketing j/Cleaner s/1600

sd/2023-09-18 f/true

Acceptable values for each parameter:

● name : non-empty string

● identifier : string of length 9

● department : non-empty string

● jobTitle : non-empty string

● salary : non-negative integer value

● startDate : YYYY-MM-DD

● isFullTime : true or false

Expected output when the command succeeds:

● Working area shows the updated list of employees

● Response area success message shown to user: “Added employee <name>

successfully.”

Expected output when the command fails:

● Incorrect number of parameters: “The number of input parameters is incorrect.

The format should be add n/<name> id/<identifier> d/<department>

j/<jobTitle> s/<salary> sd/<startDate> f/<isFullTime>.”

● Non-unique identifier: “Employee with identifier <identifier> already exists.”

● Negative salary: “Salary cannot be negative.”

● Invalid identifier: “Invalid identifier. The format should be <XXXXXXXXX>.”

● Invalid startDate: “Invalid startDate. The format should be <YYYY-MM-DD>.”

● Invalid isFullTime: “Invalid isFullTime. The value should either be true or

false.”



<a name="br3"></a> 

UI mockup:



<a name="br4"></a> 

**Edit : Editing an employee entry**

Edit the details of a current employee

Format: Edit i/<index> [n/<name>] [id/<identifier>]

[d/<department>] [j/<jobTitle>] [s/<salary>] [sd/<startDate>]

[f/<isFullTime>]

Example:

● Edit 1 s/1500 sd/2023-09-20

Acceptable value for each parameter:

● index : positive integer not larger than the number of employees

● name : non-empty string

● identifier : string of length 9

● department : non-empty string

● jobTitle : non-empty string

● salary : non-negative integer value

● startDate : YYYY-MM-DD

● isFullTime : true or false

Expected output when the command succeeds:

● Working area shows the updated employee details

● Response area success message shown to user: “Updated employee <name>

details successfully.”

Expected output when the command fails:

● Non-unique identifier: “Employee with identifier <identifier> already exists.”

● Negative salary: “Salary cannot be negative.”

● Invalid identifier: “Invalid identifier. The format should be <XXXXXXXXX>.”

● Invalid startDate: “Invalid startDate. The format should be <YYYY-MM-DD>.”

● Invalid isFullTime: “Invalid isFullTime. The value should either be true or

false.”

● Invalid index: “Invalid index. The index should be a positive integer not larger

than the number of employees.”

UI mockup:



<a name="br5"></a> 



<a name="br6"></a> 

**List : Listing all employee entries**

Displays the full list of all employee entries.

Format: list

Examples:

● list

Acceptable values for each parameter:

● No parameters required.

Expected output when the command succeeds:

● Response Area shows “Listed all employees.”.

● Working Area shows the full indexed list of employees with their details.

● Vertical scrollbar can be used to scroll if the list is too long for the viewport.

● Empty list: “The employee list is empty.”

Expected output when the command fails:

● This command should never fail.

UI mockup:



<a name="br7"></a> 



<a name="br8"></a> 

**Delete : Deleting an employee entry**

Deletes a particular employee entry based on their index number.

Format: delete i/<index>

Examples:

● delete 2 deletes the employee with index number 2

Acceptable values for each parameter:

● index : positive integer not larger than the number of employees

Expected output when the command succeeds:

● Response Area shows “Deleted employee with index number <index>.”

● Working Area shows the updated list of employees with their details

Expected output when the command fails:

● Missing index. “Missing index. ”

● Invalid index: “Invalid index. The index should be a positive integer not larger

than the number of employees.”



<a name="br9"></a> 

UI mockup:



<a name="br10"></a> 

**Find : Finding an employee by name**

Find employees whose name contains a particular keyword.

Format: find <keyword> [more\_keywords]

● The search is case-insensitive, e.g. LEE will return both lee and Lee

● The order of the keywords does not matter. e.g. Aly Han will match Han Aly

● Only the name is searched

● Any person whose name contains the sequence of characters given as the

keyword will be given as a result. e.g. Ed will match both Edward and Ed

● Persons matching at least one keyword will be returned (i.e. OR search). e.g.

Hans Bo will return Hans Gruber, Bo Yang

Examples:

● find CARL finds any employee whose name contains “carl”

● find CARL YANG finds any employee whose name contains “carl” or contains

“yang”

Acceptable values for each parameter:

● keyword: a string with at least 1 character

● more\_keywords: a string with at least 1 character, optional to have, must be

preceded by a keyword

Expected output when the command succeeds:

● Working area shows list of persons matching the name shown

● Response area shows success message “Results for: keyword

[more\_keywords]”

Expected output when the command fails:

● No keyword given: “Please input a keyword.”

● No matches found: “No matches found.”

UI mockup:



<a name="br11"></a> 



<a name="br12"></a> 

**Sort: Sorting employees by descriptor**

Sorts the employee list by using a particular descriptor as the sorting criteria.

Format: sort <descriptor>

Examples:

●

●

sort name sorts the employee list by name

sort salary sorts the employee list by salary

Acceptable values for each parameter:

descriptor : must be one of the following strings below.

●

○

○

○

○

○

name

department

job\_title

salary

start\_date

Expected output when the command succeeds:

● Working area shows the employee list sorted by the descriptor chosen.

● Response area shows success message “Employees sorted by

<descriptor>.”

Expected output when the command fails:

● Missing descriptor: “Please input a descriptor to sort by.”

● Invalid descriptor: “Please input a valid descriptor from this list: name /

department / job\_title / salary / start\_date.”

UI mockup:



<a name="br13"></a> 



<a name="br14"></a> 

**Filter: Filtering employees by descriptor criteria**

Filters the employee list by using a particular descriptor criteria. Returns a list of employees that

satisfies the specified criterion.

Format: filter d/<descriptor> c/<criteria>

●

descriptor: specifies the descriptor to be used for filtering

●

criteria: the specific detail within the descriptor that has to be filtered down to, for

example, under department descriptor, the criteria can be marketing, sales, etc

Examples:

●

filter department marketing: filters to show only employees whose department

is marketing

●

filter job\_title cleaner: filters to show only employees whose job title is

cleaner

Acceptable value for each parameter:

●

descriptor: must be one of the following strings below.

○

department

job\_title

○

●

criteria: a string that represents a subcategory of the preceding descriptor

Expected output when the command succeeds:

●

●

Working area shows list of persons filtered by the criteria used

Response area shows success message “Employees filtered by <criteria>”

Expected output when the command fails:

●

●

No descriptor given: “Please input a descriptor to filter by!”

No criteria given: “Please input a term to filter by”

UI mockup:



<a name="br15"></a> 



<a name="br16"></a> 

**Saving the data**

Saves the data to a local file any time there is a change to the employee list

Format: no command required. The data will be automatically saved to a local file every

time one of these command is executed:

● Add

● Delete

● Mark

● Unmark

Examples:

● After adding a new employee, the data file is updated

● After deleting an employee, the data file is updated

Acceptable values for each parameter:

● Acceptable values for parameters of the commands listed above is needed to

execute the save function

Expected output when the command succeeds:

● When any commands above successfully execute, the response area shows a

message “Datafile updated” in addition to the successful message of the other

command

Expected output when the command fails:

● When any commands above fails to execute, the response area shows a

message “Datafile not updated” in addition to the error message of the other

command

UI mockup:



<a name="br17"></a> 



<a name="br18"></a> 

**Filter Function**

The filter function allows you to refine your employee list by specifying whether you

want to see only full-time or part-time employees.

How it Works:

\- Use the `Filter` command followed by either `fulltime` or `parttime` to apply the filter.

\- For example, you can use `Filter fulltime` to see only full-time employees or `Filter

parttime` to see only part-time employees.

Command format:

\- Filter + fulltime/partime

Example command:

\- Filter fulltime

\- Fillter parttime

Available Parameters:

\- `fulltime`: Use this parameter to filter for full-time employees.

\- `parttime`: Use this parameter to filter for part-time employees.

Output:

\- When you use the filter correctly, it will display the list of employees that match your

chosen filter. For example, if you use `Filter fulltime`, it will show only the full-time

employees and say “Filtered to show only fulltime”.

Output on Failure:

\- If you input an invalid filter condition, you will receive a message asking you to provide

a valid filter condition. “Please provide a valid filter condition”

Mockup:



<a name="br19"></a> 



<a name="br20"></a> 

**Purge Function**

The purge function allows you to easily clear all the current data stored in the system.

How it Works:

\- To clear all data, simply use the `Reset` command.

\- For instance, just type `Reset` to start the data clearing process and the serialised file

storing data will be deleted.

Command format:

\- Reset

Usage Examples:

\- Example Command: Reset

\- This command initiates the data clearing process.

Parameters:

\- Acceptable Parameters: None

\- You don't need to include any additional information or settings when using this

function.

Output:

\- After executing the `Reset` command, all data will be removed, effectively resetting the

system.

\- You will receive a confirmation message saying: "All data cleared!"

Output on Failure:

\- If you input an invalid command, you'll be prompted to enter a valid command with the

message “Please enter a valid input!”

Mockup:



<a name="br21"></a> 

