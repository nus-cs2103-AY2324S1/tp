---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# JABPro User Guide

JobApplicationsBook Pro (JABPro) is a **desktop app for hiring managers of companies to ease the management of applicants, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, JABPro can get your applicant management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `jabpro.jar` from [here](https://github.com/AY2324S1-CS2103T-W09-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your JabPro.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar jabpro.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:

   * `add linkedin 1 alexyeoh`: Adds LinkedIn account to candidate's existing contact information
  
   * `github Alex Yeoh`: Redirects the user to the Github account of the candidate

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add /name NAME`, `NAME` is a parameter which can be used as `add /name John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Adding Github/LinkedIn username for a candidate: 'add linkedin/github'

Adds the username for their social profile [LinkedIn/Github] to the existing contact details of candidates

Format: `add linkedin INDEX USERNAME` or `add github INDEX USERNAME`

Examples:
* `add github 2 MadLamprey`
* `add linkedin 4 aditya-misra`

### Opening user LinkedIn or GitHub account: 'linkedin' or 'github'

Redirect user to candidate's LinkedIn or Github account

Format: `linkedin NAME` or `github NAME`

Examples:
* `linkedin Alex Yeoh`
* `github Bernice Sanders`
  
--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous JABPro home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action                     | Format, Examples
---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------
**Add Github/LinkedIn**    | `add linkedin INDEX USERNAME` or `add github INDEX USERNAME` e.g., `add linkedin 1 alex-yeoh`, `add github 2 bernicesanders123`
**Open Github/LinkedIn**    | `linkedin NAME` or `github NAME` e.g., `linkedin Alex Yeoh`, `github Bernice Sanders`
