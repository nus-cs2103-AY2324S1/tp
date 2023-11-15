---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# **RTPM User Guide**

<br>

<div style="text-align:center;">
   <img src="images/Ui.png" alt="Ui" style="width:500px; border:1px solid #000; box-shadow: 7px 7px 7px #878787;"/>
</div>

<br>
<br>

RealtorTrackerPlusMax (RTPM) is the hottest new computer app 
for realtors. RTPM allows you to manage your clients, optimized for use via a 
Command Line Interface (CLI), aimed at maximising your speed in entering data.
Whether you are learning how to get into the real estate industry, or looking for a simple and free app
to get rid of your cluttered spreadsheets, RTPM is for you!

<div style="page-break-after: always;"></div>

## Contents

* [Quick Start](#quick-start)<br>
* [Configuration](#configuration)<br>
* [Features](#features)<br>
    * Adding a client<br>
       * [Adding a buyer: `buyer`](#adding-a-buyer-buyer)<br>
       * [Adding a seller: `seller`](#adding-a-seller-seller)<br>
    * Editing a client<br>
      * [Editing a buyer: `bedit`](#editing-a-buyer-bedit)<br>
      * [Editing a seller: `sedit`](#editing-a-seller-sedit)<br>
    * Deleting a client<br>
      * [Deleting a buyer: `bdelete`](#deleting-a-buyer-bdelete)<br>
      * [Deleting a seller: `sdelete`](#deleting-a-seller-sdelete)<br>
      * [Clearing all entries: `clear`](#clearing-all-entries-clear)<br>
    * Setting a client's priority<br>
      * [Setting a buyer's priority: `bprio`](#setting-a-buyer-s-priority-bprio)<br>
      * [Setting a seller's priority: `sprio`](#setting-a-seller-s-priority-sprio)<br>
    * Viewing the clients<br>
      * [Filtering buyers and sellers: `filter`](#filtering-buyers-and-sellers-filter)<br>
      * [Listing all buyers and sellers: `list`](#listing-all-buyers-and-sellers-list)<br>
      * [Displaying a buyer from buyer list: `blist`](#displaying-a-buyer-from-buyer-list-blist)<br>
      * [Displaying a seller from seller list: `slist`](#displaying-a-seller-from-seller-list-slist)<br>
      * [Sorting buyers: `bsort`](#sorting-buyers-bsort)<br>
      * [Sorting sellers: `ssort`](#sorting-sellers-ssort)<br>
    * Miscellaneous commands<br>
      * [Undoing previous action: `undo`](#undoing-previous-action-undo)<br>
      * [Redoing previous action: `redo`](#redoing-previous-action-redo)<br>
      * [Viewing help: `help`](#viewing-help-help)<br>
      * [Exiting the program: `exit`](#exiting-the-program-exit)<br>
* [FAQ](#faq)<br>
* [Known Issues](#known-issues)<br>
* [Command Summary](#command-summary)<br>
* Appendices
    * [Appendix A: Warnings](#appendix-a-warnings)<br>
    * [Appendix B: Fields](#appendix-b-fields)<br>
    * [Appendix C: Similar names](#appendix-c-similar-names)<br>
    * [Appendix D: Definitions](#appendix-d-definitions)<br>


<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure you have Java `11` or above installed in your computer. For more info, check the [FAQ](#faq).

2. Download the latest `rtpm.jar` [here](https://github.com/AY2324S1-CS2103T-F11-3/tp/releases/tag/v1.4).

3. Move the application to the folder you want to use as the _setup folder_ for RTPM. 
(On startup, the application will generate a few files for configuration and storage.)

4. You should be able to run the application by double-clicking. If this does not work for you, check the [FAQ](#faq).<br>
   A display similar to the below should appear in a few seconds. If you are starting the app for the first time,
 there will be some sample data loaded.<br>
<div style="text-align:center;">
   <img src="images/startUi.png" alt="startUi" style="width:450px"/>
</div>
<br>


5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `buyer n/John Doe` : Adds a buyer named John Doe to the RTPM.

    * `list` : Lists all buyers and sellers.

    * `bdelete 2` : Deletes the 2nd buyer shown in the current list.

    * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

<div style="page-break-after: always;"></div>


<br>

## Configuration
If you are looking to change the size of the application, resizing can simply be done by clicking and 
dragging the borders in the direction you desire. 
If you do this to the white header atop the app, you can also move the window around.
Both size and position of the application are saved, so that 
you don't need to spend time fiddling with the window.

<box type="info" seamless>

**For advanced users only:**

You can change the location in which RTPM's data is stored by editing the preferences.json file using any text
editing app of your choice, such as Notepad or Microsoft Word.
Edit the filePath string to the directory that you wish to move it to, relative to the RTPM.jar location. 
Note that backslashes need to be repeated twice.

</box>

<br>

--------------------------------------------------------------------------------------------------------------------

## Features

Here are some things regarding RTPM's features to take note of before using them.

<box type="definition" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` represent the parameters, or data, to be supplied by the user.<br>
  e.g. in `buyer n/NAME`, `NAME` is a parameter which can be used as `buyer n/John Doe`.

* Prefixes in the format `small_letters/` indicate to RTPM where your data is located. <br>
  e.g. in `buyer n/NAME`, `n/` is a prefix which tells the app that `NAME` is representing a buyer's name.
  You must include a space before the prefix.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* The order in which you input data is not fixed by RTPM.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</box>

<div style="page-break-after: always;"></div>

<br>


<box type="definition" seamless>

**Notes about the command format (Continued):**<br>

* All commands and prefixes ignore case. Parameters however, are case-sensitive. <br>
  e.g. if the command specifies `buyer n/NAME`, `BUYER N/NAME` is also acceptable.

* Extra parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

<br>


<box type="definition" seamless>

**General notes about features:**<br>

* RTPM does not require all fields to be filled in when creating buyers or sellers. Fields that have been omitted will be set to RTPM's preset default values.

* RTPM accepts unconventional entries for data values to an extent. However, the warning system informs the user of any valid but possibly unintended inputs. For more information, refer to [Appendix A: Warnings](#appendix-a-warnings)

* The priority system allows for the designation of levels of importance to each buyer and seller, which will be displayed as a tag in RTPM. When this priority is set to `nil`, there will be no tag.

* RTPM data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

* RTPM data are saved automatically as a JSON file `[JAR file location]/data/rtpm.json`.

* Note that RTPM checks for duplicate and similar entries. Refer to [Appendix C: Similar Names](#appendix-c-similar-names) for more info.

* **If you manually make changes to the saved data file such that the JSON format is invalid, RTPM will discard all data and start with an empty data file at the next run.
  Hence, manually modifying the saved data file is not recommended.**
</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>



<div style="page-break-after: always;"></div>

<br>

### Adding a buyer: `buyer`


You can add buyers with a few keystrokes, instead of having to use an excel sheet. 
The `buyer` command allows you to add a buyer with just their name,
so that you can quickly add them on the go.

You can also include contact details of the buyer with the `[p/PHONE_NUMBER]` and `[e/EMAIL]` fields,
and add more details about them with the `[t/TAG]` field.

<box type="warning" seamless>

Note that this command throws warnings. 
For more info on fields, head [here.](#appendix-b-fields) 
For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `buyer n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [i/HOUSE_INFO] [prio/PRIORITY] [t/TAG]…`


Example:
`buyer n/Jane Doe p/91234567 e/janedoe@gmail.com ah/1 College Ave East i/Central Area 5 Room Condominium prio/high`

<box type="success" seamless>

Expected output when the command succeeds:

>Got it. I've added a buyer contact:<br>
Jane Doe; Phone: 91234567; Email: janedoe@<span></span>gmail.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: high; Tags:

</box>

<box type="wrong" seamless>

Possible invalid inputs & their corresponding error messages:

* If your command is not in the proper format:

>Invalid command format!<br>
buyer: Adds a buyer to the address book. Parameters: n/NAME [p/PHONE] [e/EMAIL] [ah/ADDRESS] [i/INFO] [prio/PRIORITY] [t/TAG]...<br> 
Example: buyer n/John Doe p/98765432 e/johnd@example.com ah/311, Clementi Ave 2, #02-25 i/Central Area 5 Room Condominium prio/medium t/friends t/owesMoney

* If you use an invalid email:

>Emails must contain at least one '@'.

</box>
<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<br>

### Adding a seller: `seller`

Similar to [`buyer`](#adding-a-buyer-buyer),
 the `seller` command allows you to add clients who have homes to sell. 
Additionally, you can add information about the house that your client wants to sell with the `[i/HOUSE_INFO]` field. 

<box type="warning" seamless>

Note that this command throws warnings. For more info on fields, head [here.](#appendix-b-fields) For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `seller n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [as/SELLING_ADDRESS] [i/HOUSE_INFO] [prio/PRIORITY] [t/TAG]…`


Example: `seller n/Ryan p/91234567 e/ryan@gmail.com ah/My Secret Home as/47D Lor Sarhad, Singapore 119164 i/4 Room Flat in Sarhad Ville prio/high`

<box type="success" seamless>
Expected output when the command succeeds:

>Got it. I've added a seller contact:<br>
Ryan; Phone: 91234567; Email: ryan@<span></span>gmail.com; Address: My Secret Home; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: high; Tags:

</box>

<box type="wrong" seamless>
Possible invalid inputs & their corresponding error messages:  

* If your command is not in the proper format:
>Invalid command format!<br>
seller: Adds a seller to the address book. Parameters: n/NAME p/PHONE e/EMAIL ah/ADDRESS as/SELLING_ADDRESS i/HOUSE_INFO [t/TAG]...<br>
Example: seller n/Ryan p/91234567 e/ryan@gmail.com ah/My Secret Home as/47D Lor Sarhad, Singapore 119164 i/4 Room Flat in Sarhad Ville prio/medium t/friends t/owesMoney

* If you use an invalid email:
>Emails must contain at least one '@'.

</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<br>

### Editing a buyer: `bedit`

Congratulations on adding buyers and sellers!
Now, when you have time to review your client's information, 
you can fill in additional information about buyers, or 
edit their existing information.

The `bedit` command allows you to edit any information about your buyer,
so that you can capture the dynamic needs of your client. 
To select the buyer you want to edit, just indicate their index number. 

<box type="warning" seamless>

Note that this command throws warnings. For more info on fields, head [here.](#appendix-b-fields) For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `bedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]…`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyers' list
* `PREFIX/VALUE`: Refer to the appendix linked above.

Example: `bedit 3 e/example@email.com ah/Residential Street`

<box type="success" seamless>
Expected output when the command succeeds:

>Got it. I've edited a buyer contact:<br>
Jane Doe; Phone: 91234567; Email: something@<span></span>else.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: nil; Tags:

</box>

<box type="wrong" seamless>
Possible invalid inputs & their corresponding error messages:  

* If your command is not in the proper format:

>Invalid command format!<br> 
bedit: Edits the details of the buyer identified by the index number used in the displayed buyer list. Existing values will be overwritten by the input values.<br>
Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [ah/ADDRESS] [i/HOUSE_INFO] [t/TAG]...<br>
[prio/PRIORITY] Example: bedit 1 p/91234567 e/johndoe@example.com

* If you don't provide any fields to edit:
>At least one field to edit must be provided!

* If you provide an invalid index:
>The buyer index provided is higher than the last number in the list!

</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<br>

### Editing a seller: `sedit`

Similar to [`bedit`](#editing-a-buyer-bedit),
 the `sedit` command allows you to edit the information of a home seller.

<box type="warning" seamless>

Note that this command throws warnings. For more info on fields, head [here.](#appendix-b-fields) For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `sedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]…`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the sellers' list
* `PREFIX/VALUE`: Refer to the appendix linked above.

Example: `sedit 3 e/example@email.com ah/Residential Street`

<box type="success" seamless>
Expected output when the command succeeds:

>Got it. I've edited a seller contact:<br>
Ryan; Phone: 91234567; Email: ryan@<span></span>gmail.com; Address: Another Place; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: nil; Tags:

</box>

<box type="wrong" seamless>
Possible invalid inputs & their corresponding error messages:

* If your command is not in the proper format:
>Invalid command format!<br>
sedit: Edits the details of the seller identified by the index number used in the displayed seller list. Existing values will be overwritten by the input values.<br>
Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [ah/ADDRESS] [as/SELLING_ADDRESS] [i/HOUSE_INFO] [t/TAG]...<br>
[prio/PRIORITY] Example: sedit 1 p/91234567 e/johndoe@example.com

* If you don't provide any fields:
>At least one field to edit must be provided!

* If you provide an invalid index:
>The seller index provided is higher than the last number in the list!
</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>



<br>

### Deleting a buyer: `bdelete`

Congratulations on helping a buyer find their dream home! With the deal now complete, it's time to shift your focus
onto securing the next deal and making the next buyer satisfied.

The `bdelete` command allows you to delete a buyer based on their index number in the buyers’ list, so that you can keep
your buyer list organised and de-cluttered by keeping only the relevant buyers and removing outdated entries.

Format: `bdelete INDEX`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyers' list

Example: `bdelete 3`

<box type="success" seamless>
Expected output when the command succeeds:

>Got it. I’ve deleted a buyer contact:<br>
Jane Doe; Phone: 91234567; Email: janedoe@<span></span>gmail.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: nil; Tags:

</box>

<box type="wrong" seamless>
Possible invalid inputs & their corresponding error messages:

* If your command is not in the proper format: 
>Invalid command format!<br>
bdelete: Deletes the buyer identified by the index number used in the displayed buyer list.<br>
Parameters: INDEX (must be a positive integer)<br>
Example: bdelete 1

* If you provide an invalid index:
>The buyer index provided is higher than the last number in the list!

</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<br>

### Deleting a seller: `sdelete`

Similar to `bdelete,` the `sdelete` deletes a seller based on their index number in the sellers’ list, so that you can keep
your seller list organised and de-cluttered by keeping only the relevant sellers and removing outdated entries.


Format: `sdelete INDEX`
* `INDEX`: A positive integer (1, 2, 3, …), which must not exceed last index in the sellers’ list

Example: `sdelete 3`

<box type="success" seamless>
Expected output when the command succeeds:

>Got it. I’ve deleted a seller contact:<br>
Ryan; Phone: 91234567; Email: ryan@<span></span>gmail.com; Address: My Secret Home; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: nil; Tags:

</box>

<box type="wrong" seamless>
Possible invalid inputs & their corresponding error messages:

* If your command is not in the proper format:
>Invalid command format! <br>
sdelete: Deletes the seller identified by the index number used in the displayed seller list.<br>
Parameters: INDEX (must be a positive integer)<br>
Example: sdelete 1

* If you provide an invalid index
>The seller index provided is higher than the last number in the list!

</box>

--------------------------------------------------------------------------------------------------------------------

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

<box type="success" seamless>
Expected output when the command succeeds:

>Address book has been cleared!

</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>



<br>

### Setting a buyer's priority: `bprio`

Get your priorities straight. Focus on the tasks that matter the most, and maximise your time and productivity
by securing the most important deals, first and foremost.

The `bprio` command allows you set the priority level of a buyer based on their index number in the buyer's list, 
so that you can seamlessly differentiate buyers at a glance by their priority level. 
The priority tags are colored intuitively for you to easily spot a buyer's priority level at first glance.
After assigning priorities
to your buyers, a simple `bsort prio/d` command would then sort your buyer list by priority level, with the 
higher priority buyers appearing first at the top of the list, allowing you to get started with the right 
client, right away.

<box type="warning" seamless>

Note that this command throws warnings. For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Format: `bprio INDEX PRIORITY`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyer's list
* `PRIORITY`: See [here.](#appendix-b-fields)

Example: `bprio 3 high`

<box type="info">

**Note:**
* `PRIORITY` only considers the first letter of the input (`h` for high, `m` for medium, `l` for low, or `n` for nil). For example, `bprio 3 hlow` sets the priority of buyer 3 to high.
* Any extra inputs given after `PRIORITY` will be ignored. For example, `bprio 3 high low` is equivalent to `bprio 3 high`.

</box>

<box type="success" seamless>
Expected output when the command succeeds:

>The buyer's priority level has been set:<br>
Jane Doe; Phone: 91234567; Email: something@<span></span>else.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: high; Tags:

</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<box type="wrong" seamless>
Possible invalid inputs & their corresponding error messages:

* If your command is not in the proper format:
>Invalid command format!<br>
bprio: Sets a priority level for the buyer, identified by index in the displayed buyer list. INDEX must be a positive integer, while PRIORITY can be either 'high', 'medium', 'low', or 'nil'.<br>
Parameters: INDEX PRIORITY

* If you provide an invalid index:
>The buyer index provided is higher than the last number in the list!

</box>


--------------------------------------------------------------------------------------------------------------------

### Setting a seller's priority: `sprio`

<box type="warning" seamless>

Note that this command throws warnings. For more info on warnings, head [here.](#appendix-a-warnings)

</box>

Similar to `bprio`, the `sprio` command allows you to set the priority level of a seller (instead of a buyer) 
based on their index number in the seller's list, so that you can seamlessly differentiate sellers too at a glance 
by their priority level. 

Format: `sprio INDEX PRIORITY`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyer's list
* `PRIORITY`: See [here.](#appendix-b-fields)

Example: `sprio 3 high`


<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>



<br>

<box type="info">


**Note:**
* `PRIORITY` only considers the first letter of the input (`h` for high, `m` for medium, `l` for low, or `n` for nil). For example, `bprio 3 hlow` sets the priority of buyer 3 to high.
* Any extra inputs given after `PRIORITY` will be ignored. For example, `bprio 3 high low` is equivalent to `bprio 3 high`.

</box>

<box type="success" seamless>
Expected output when the command succeeds:

>The seller's priority level has been set:<br>
Ryan; Phone: 91234567; Email: ryan@<span></span>gmail.com; Address: Another Place; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: high; Tags:

</box>

<box type="wrong" seamless>
Possible invalid inputs & their corresponding error messages:

* If your command is not in the proper format:
>Invalid command format!<br>
sprio: Sets a priority level for the seller, identified by index in the displayed seller list. INDEX must be a positive integer, while PRIORITY can be either 'high', 'medium', 'low', or 'nil'.<br>
Parameters: INDEX PRIORITY

* If you provide an invalid index
>The seller index provided is higher than the last number in the list!

</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>



<br>

### Filtering buyers and sellers: `filter`

Say goodbye to the struggles of scrolling through your entire list to find that one elusive client.

The `filter` allows you to find any client by name easily, so you never have to waste time searching for a client no matter how long your lists get.
* This command only matches names
* Your keywords can be given in any order
* Only complete words will be matched
* Any match will result in the buyer or seller being displayed

Format: `filter KEYWORD [MORE_KEYWORDS]…`

- `KEYWORD`: No restrictions; case-insensitive

Example: `filter John Doe`

<box type="tip">

**Tip: `filter John Doe` will display**
- [x] John (matches "John")
- [x] john (matches "John", case-insensitive)
- [x] John Do (matches "John")
- [x] Jane Doe (matches "Doe")
- [x] John Doe (matches both "John" and "Doe")
- [ ] JohnDoe (matches neither "John" nor "Doe")
- [ ] Johnny ("Johnny" does not match "John" fully)
- [ ] Joh N (does not match "John")

</box>

Expected output when the command succeeds:

<box type="success" seamless>

>1 buyer(s) and 0 seller(s) listed!

</box>

<box type="wrong" seamless>

If you don't put any keywords to filter for:

>Invalid command format! 
>filter: Filters all buyers and sellers whose names contain any of the specified keywords (case-insensitive) and displays them as a list with index numbers.
>Parameters: KEYWORD [MORE_KEYWORDS]...
>Example: filter alice bob charlie

</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>



### Listing all buyers and sellers: `list`

The `list` command allows you to display all buyers and sellers, so that you can get back to work after using the `filter` command or editing the data file directly.

Format: `list`

--------------------------------------------------------------------------------------------------------------------

### Displaying a buyer from buyer list: `blist`

The `blist` command allows you to display the information of any buyer as a status message, so that you can view it, or highlight and copy it for further use.

Format: `blist INDEX`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the buyers' list

Example: `blist 3`

<box type="success" seamless>

Expected output when the command succeeds:

>Got it. Here's the information of this buyer:<br>
Jane Doe; Phone: 91234567; Email: janedoe@<span></span>gmail.com; Address: 1 College Ave East; House Info: Central Area 5 Room Condominium; Priority: nil; Tags:

</box>

--------------------------------------------------------------------------------------------------------------------


### Displaying a seller from seller list: `slist`

The `slist` command allows you to display the information of any seller as a status message, so that you can view it, or highlight and copy it for further use.

Format: `slist INDEX`
* `INDEX`: A positive integer (1, 2, 3, …) which must not exceed the last index in the sellers' list

Example: `slist 3`

<box type="success" seamless>

Expected outputs when the command succeeds:

>Got it. Here's the information of this seller:<br>
Ryan; Phone: 91234567; Email: ryan@<span></span>gmail.com; Address: My Secret Home; Selling Address: 47D Lor Sarhad, Singapore 119164; House Info: 4 Room Flat in Sarhad Ville; Priority: nil; Tags:

</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>



### Sorting buyers: `bsort`

Elevate your contact list through organisation based on your priorities.

The `bsort` command allows you to quickly organise your buyer contacts by various criteria, so that you can sieve out
your most important contacts to maintain and the most valuable opportunities you have.

Format: `bsort [PREFIX/DIRECTION]`
* `PREFIX`: Choose <u>one</u> of:
  * `n` - sort by **name**
  * `ah` - sort by **home address**
  * `i` - sort by **house info**
  * `prio` - sort by **priority**
* `DIRECTION`: "a" OR "d" for ascending or descending respectively

<box type="tip">

**Tip:** When no prefix and direction is provided, `bsort` sorts by the list's original order.

</box>

Examples: 
* `bsort` to sort by original order
* `bsort n/d` to sort by name descending

<box type="success" seamless>

Expected outputs when the command succeeds:

>Got it. I've sorted the buyer list!

</box>


<box type="wrong" seamless>

Expected outputs when the command fails:

* If your command is not in the proper format:

>Invalid command format!<br>
bsort: Sorts the buyers in RTPM. Parameters: Choose zero or one of [n/] [ah/] [i/] [prio/] a/d (for ASC/DESC)<br>
Example: bsort prio/d

* If the same prefix is used more than once:

>Multiple values specified for the following single-valued field(s): ...

</box>


<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<box type="info">

**Note:**

* The sort command will ignore any extraneous inputs and invalid prefixes after the `bsort` keyword and before the next 
valid prefix (`n`, `ah`, `i` or `prio`).
  * For example, `bsort qwerty z/asdf prio/d` will execute `bsort prio/d`.
  * Any extraneous inputs or invalid prefixes after a valid prefix will cause an error.
* When two or more valid prefixes are provided, `bsort` will sort by only one of the provided prefixes, which is chosen
  based on this order:<br> **1. Name**, **2. Home address**, **3. House info**, **4. Priority**.
  * For example, `bsort prio/d n/d` will execute `bsort n/d`.

</box>

--------------------------------------------------------------------------------------------------------------------


### Sorting sellers: `ssort`

Elevate your contact list through organisation based on your priorities.

The `ssort` command allows you to quickly organise your seller contacts by various criteria, so that you can sieve out
your most important contacts to maintain and the most valuable opportunities you have.

Format: `ssort [PREFIX/DIRECTION]`
* `PREFIX`: Choose <u>one</u> of:
    * `n` - sort by **name**
    * `ah` - sort by **home address**
    * `i` - sort by **house info**
    * `prio` - sort by **priority**
* `DIRECTION`: "a" OR "d" for ascending or descending respectively

<box type="tip">

**Tip:** When no prefix and direction is provided, `ssort` sorts by the list's original order.

</box>

Examples:
* `ssort` to sort by default
* `ssort n/d` to sort by name descending




<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<box type="success" seamless>

Expected outputs when the command succeeds:

>Got it. I've sorted the seller list!

</box>


<box type="wrong" seamless>

Expected outputs when the command fails:

* If your command is not in the proper format:

>Invalid command format!<br>
ssort: Sorts the sellers in RTPM. Parameters: Choose zero or one of [n/] [ah/] [i/] [prio/] a/d (for ASC/DESC)<br>
Example: ssort prio/d 

* If the same prefix is used more than once:

>Multiple values specified for the following single-valued field(s): ...

</box>

<box type="info">

**Note:**

* Similar to `bsort`, the `ssort` command will also ignore any extraneous inputs and invalid prefixes after the `ssort` keyword and before the next
  valid prefix (`n`, `ah`, `i` or `prio`).
* Similar to `bsort`, when two or more valid prefixes are provided, `ssort` will sort by only one of the provided prefixes, which is chosen
  based on this order: <br> **1. Name**, **2. Home address**, **3. House info**, **4. Priority**.

</box>


<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<br>

### Undoing previous action: `undo`

Made a mistake? The `undo` command allows you to undo the previous action, easily correcting your mistakes and keeping
your contact list on track.

Format: `undo`

<box type="tip">

**Tip:** Using `undo` multiple times will undo multiple actions in order.

</box>

<box type="success" seamless>

Expected output when the command succeeds:

>Last command was undone.

</box>

<box type="wrong" seamless>

If there is no command to undo:

>No commands to undo!

</box>

--------------------------------------------------------------------------------------------------------------------

### Redoing previous action: `redo`

The `redo` command allows you to restore a previously undone action, making it easy to go back to any state and 
continue working from there.

Format : `redo`

<box type="tip">

**Tip:** Using `redo` multiple times will redo multiple actions in order.

</box>

<box type="success" seamless>

Expected output when the command succeeds:

>The next command was redone.

</box>

<box type="wrong" seamless>

If there is no command to redo:

>No commands to redo!

</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<br>

### Viewing help: `help`

Unsure of how to use our system or forgot the available commands? The `help` command allows you to access our user 
guide, ensuring that you have all the information you need at your fingertips.

Format: `help`

<br>

--------------------------------------------------------------------------------------------------------------------

### Exiting the program: `exit`

Wrap up your contact management session and start making the deals happen.

The `exit` command allows you to terminate the program gracefully and securely, safeguarding all data and changes
you've made during the session.

Format: `exit`

<box type="info">

**Note:** Upon exit, the latest data is saved to your computer at data/rtpm.json.

</box>

<br>

--------------------------------------------------------------------------------------------------------------------

## FAQ

A list of frequently asked questions to resolve common issues you may have.

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous RTPM home folder.

**Q**: My entries are missing! Did they get deleted somehow?<br>
**A**: One possible fix is to try calling the `list` command. If you manually edit the data file, or you filtered the list,
the entries may not appear in the application until you refresh the list.

**Q**: I made a mistake! How do I fix it?<br>
**A**: You can make use of the handy [undo](#undoing-previous-action-undo) and [redo](#redoing-previous-action-redo)
commands to fix any errors made.

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


<br>

**Q**: I can't double-click on the application to run it! <br>
**A**: First, check that you have Java 11 installed. If you do, and you still can't double-click, try this:
Open a command terminal,use the `cd` command to change into the folder you put the application file in,
and type in the `java -jar rtpm.jar`command to run the application.


**Q**: How do I install Java 11? <br>
**A**: Try installing from the official Oracle archive [here](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html).

**Q**: I still have issues! <br>
**A**: Try contacting our developer team by creating an issue on our 
[GitHub](https://github.com/AY2324S1-CS2103T-F11-3/tp/issues), and we will see how we can help.


--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
<br>
2. Adding multiple contacts with excessively long names (>5000 characters) may cause RTPM to lag significantly. It is recommended to use nicknames or initials if necessary.
<br>
3. Long fields can cause the details of a client to not be displayed fully. A workaround is to use the 
```slist```/```blist``` commands to display the details of the client in the result box. 


<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>



## Command summary

| Action                  | Format, Examples                                                                                                                                                                                                                                                                   |
|-------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Buyer**           | `buyer n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [i/BUY_HOUSE_INFO] [prio/PRIORITY] [t/TAG]`<br> e.g. buyer n/Jane Doe p/91234567 e/janedoe@gmail.com ah/1 College Ave East i/Central Area 5 Room Condominium prio/high                                                  |
| **Add Seller**          | `seller n/NAME [p/PHONE_NUMBER] [e/EMAIL] [ah/HOME_ADDRESS] [as/SELLING_ADDRESS] [i/SELLING_HOUSE_INFO] [prio/PRIORITY] [t/TAG]​`<br> e.g. seller n/Ryan p/91234567 e/ryan@gmail.com ah/My Secret Home as/47D Lor Sarhad, Singapore 119164 i/4 Room Flat in Sarhad Ville prio/high |
| **Edit Buyer**          | `bedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]`<br> e.g. bedit 3 e/example@email.com ah/Residential Street                                                                                                                                                                          |
| **Edit Seller**         | `sedit INDEX PREFIX/VALUE [MORE_PREFIX/VALUE]`<br> e.g. sedit 3 e/example@email.com as/Selling Street                                                                                                                                                                              |
| **Delete Buyer**        | `bdelete INDEX`<br> e.g. bdelete 2                                                                                                                                                                                                                                                 |
| **Delete Seller**       | `sdelete INDEX`<br> e.g. sdelete 2                                                                                                                                                                                                                                                 |
| **Clear**               | `clear`                                                                                                                                                                                                                                                                            |
| **Set Buyer Priority**  | `bprio INDEX PRIORITY`<br> e.g. bprio 3 high                                                                                                                                                                                                                                       |
| **Set Seller Priority** | `sprio INDEX PRIORITY`<br> e.g. sprio 3 high                                                                                                                                                                                                                                       |
| **List All**            | `list`                                                                                                                                                                                                                                                                             |
| **Filter**              | `filter KEYWORD [MORE_KEYWORDS]`<br> e.g. filter John Doe                                                                                                                                                                                                                          |
| **List Buyer**          | `blist INDEX` <br> e.g. blist 1                                                                                                                                                                                                                                                    |
| **List Seller**         | `slist INDEX`<br> e.g. slist 1                                                                                                                                                                                                                                                     |
| **Sort Buyers**         | `bsort [PREFIX/DIRECTION]`<br> e.g. bsort n/d                                                                                                                                                                                                                                      |
| **Sort Sellers**        | `ssort [PREFIX/DIRECTION]`<br> e.g. ssort prio/a                                                                                                                                                                                                                                   |
| **Undo**                | `undo`                                                                                                                                                                                                                                                                             |
| **Redo**                | `redo`                                                                                                                                                                                                                                                                             |
| **Exit**                | `exit`                                                                                                                                                                                                                                                                             |
| **Help**                | `help`                                                                                                                                                                                                                                                                             |

<br>


<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>



## Appendix A: Warnings

RTPM allows you to flexibly input most fields of data, so that the app doesn't unnecessarily restrict you.
For example, you can insert chinese characters as names, or
use slashes to abbreviate "son of" as "s/o".

Hence, the warning system aims to inform you of any valid but possibly unintended inputs.
The warning system is able to alert you of multiple errors at once, allowing you to 
correct all mistakes together instead of one-by-one.

The warning system is also used to notify you when
there are two similar buyers or sellers, 
or when a buyer shares the same name as a seller. This reduces the chance that you will 
accidentally make two entries for the same person, cluttering your data.
See [Appendix C](#appendix-c-similar-names) for more details.

<box type="definition">

Here is a non-exhaustive list of some warning messages.

```
Warning!; [Phone numbers should only contain numbers, and it should 
be at least 3 digits long. Area codes are allowed, signified by a 
'+' and up to 3 numbers, followed by a space separating this from 
the main number.]
Please ignore if this is expected.
```
```
Warning!; [Emails should be of the format local-part@domain and 
adhere to the following constraints:
1. The local-part should only contain alphanumeric characters and 
these special characters, excluding the parentheses, (+_.-). The 
local-part may not start or end with any special characters.
2. This is followed by a '@' and then a domain name. The domain 
name is made up of domain labels separated by periods.
The domain name must:
    - end with a domain label at least 2 characters long
    - have each domain label start and end with alphanumeric 
      characters
    - have each domain label consist of alphanumeric characters, 
      separated only by hyphens, if any.]
Please ignore if this is expected.
```
</box>

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


## Appendix B: Fields
Here, we provide the exact checks that our application does for you,
and the warning given if the field is inappropriate.

| Field            | Valid                                    | Appropriate                                                                                       | Exact warning given                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
|------------------|------------------------------------------|---------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Name**         | Must not be blank                        | Alphanumeric characters only                                                                      | `Names should contain only alphanumeric characters and spaces`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **Phone Number** | At least one numerical character         | Only numerical characters, and at least 3 digits long. Area codes allowed as provided in warning. | `Phone numbers should only contain numbers, and it should be at least 3 digits long. Area codes are allowed, signified by a '+' and up to 3 numbers, followed by a space separating this from the main number.`                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| **Email**        | At least one `@` character               | See warning message.                                                                              | `Emails should be of the format local-part@domain and adhere to the following constraints: 1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters. 2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods. The domain name must: - end with a domain label at least 2 characters long - have each domain label start and end with alphanumeric characters - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.` |
| **Address**      | Must not be blank                        | Nil                                                                                               | Nil                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| **Info**         | Must not be blank                        | Nil                                                                                               | Nil                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |

<div style='text-align: right;'>

[Back to top](#contents)

</div>
<div style="page-break-after: always;"></div>

| Field            | Valid                                    | Appropriate                                                                                       | Exact warning given                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
|------------------|------------------------------------------|---------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Priority**     | Starts with one of the letters `h,l,m,n` | Matches the first part of one of the words `high, low, medium, nil`.                              | `Inputs should be 'high', 'medium', 'low' or 'nil'. However, if at least the first letter is valid, we will read correctly.`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| **Tags**         | Must not be blank                        | Alphanumeric characters only                                                                      | `Tags names should be alphanumeric`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |


--------------------------------------------------------------------------------------------------------------------

## Appendix C: Similar names
RTPM does checks to ensure users do not accidentally enter the same person twice, preventing cases where you have duplicate entries.
This is also true if you try to edit two people to have the same name.

There may be some ambiguity when names are similar but not exactly the same; in this case,
the app will warn the user in case this was unintentional,
but will not prevent the command from executing. 

<box type="definition">
This section aims to explain how exactly similar names are determined.

RTPM's definition of 'similar' is as follows: Either one of the names is contained 
in the other, or the names require 2 or fewer edits (deletions, insertions, substitutions)
to make them the same. (for more details look for the definition of Levenshtein distance 
<a href="https://en.wikipedia.org/wiki/Levenshtein_distance" class="badge bg-primary">here</a>.
</box>

An example when the same name is detected across buyers and sellers: 
```This seller potentially also exists in the buyer list: If so, please verify that their contact information is the same```

An example when similar names are detected:
```The seller is similar to one of the sellers in the list!```

An example when the same name is detected:
```This seller already exists in the address book```
(This is not allowed, hence the command will not execute.)

<div style='text-align: right;'>

[Back to top](#contents)

</div>

<div style="page-break-after: always;"></div>


## Appendix D: Definitions
Provided is a useful definition list of terms that were used in the guide and in the application.

- Area code: A prefix for phone numbers in the format +123, containing anywhere from 1 to 3 digits.
- Alphanumeric: Consisting of only English-language letters and the digits from 0-9.
- CLI: Command-Line Interface. A text-based means of interacting with applications. 
In RTPM, you use this to interact with the internal logic.
- Client: In RTPM, refers to either a buyer or a seller.
- Hard disk: The storage component of your computer.
- Computer: Desktops or laptops, but not mobile phones.
- Index: Any positive (above 0) integer.
- Integer: A number without a fractional part.
- JSON: A storage format that RTPM uses to store its data. See [here](https://en.wikipedia.org/wiki/JSON) for more info.
- Numerical: Consisting only digits from 0-9.
- Parameters: Specific details, instructions and information about the command you are executing.
- Priority: The importance of a specific client, as determined by you.
- RTPM: The acronym for this app, RealtorTrackerPlusMax.
- Special characters: All non-alphanumeric characters.


<div style='text-align: right;'>

[Back to top](#contents)

</div>

