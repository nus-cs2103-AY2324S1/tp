---
  layout: default.md
  title: "Jing Sheng's Project Portfolio Page"
---

## Project: SEPlendid

Team Member: Tan Jing Sheng
<br>

---

**SEPlendid** is a versatile application used by prospective exchangers to plan their Student Exchange Programme (SEP)
study plans. Students from NUS Computing can use this CLI-based application, with a beautiful GUI, to view partner
universities and course mappings. The application is also bundled with many other useful features.

Given below are my contributions to the project.

<br>

---

### New Features:

##### Refactor AB3 UI to SEPlendid UI

Pull Request Link: [85](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/85)

In recognising the significance of minimalism for achieving simplicity, our team decided that a redesigning the original
AB3 UI into a custom-made minimalist SEPlendid interface would be the best course of action. Despite the initial
challenges, obstacles were overcomed and a UI was produced not only met our design goals but also offered a visually
appealing and highly functional experience tailored to the requirements of SEPlendid.

**Before :**
<div style="text-align: center;">

![AB3Ui.png](..%2Fimages%2FAB3Ui.png)

</div>

<div style="text-align: center;">

Address Book 3 User Interface

</div>

**After :**

<div style="text-align: center;">

![Ui.png](..%2Fimages%2FUi.png)

</div>

<div style="text-align: center;">

SEPlendid User Interface

</div>



##### Scraped data from NUSmods and Edurec to create sample data

Pull Request Link: [139](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/139)

To ensure SEPlendid is fully functional right out of the box, a robust dataset is essential. In this contribution, we
tackled this challenge by leveraging data from both Edurec and NUSmods. I wrote a script to scrape data using the
NUSmods API, complemented by exporting relevant information from Edurec. The data is then processed and formatted to
ensure proper integration with SEPlendid. By combining these sources, a comprehensive and realistic sample data was
crafted and added to SEPlendid.

**The Sample Data Included:**

- **413 Partner Courses**
- **413 Mappings**
- **198 Universities**
- **165 Local Courses**
- **3 Notes**

Below is the script I wrote to scrape NUSmods:

```
import json

import requests


def fetch_and_save_module(course_code):
    url = f"https://api.nusmods.com/v2/2023-2024/modules/{course_code}.json"

    try:
        response = requests.get(url)
        response.raise_for_status()  # Check for errors

        # Assuming the API response is in JSON format
        data = response.json()

        # Extract the description field
        description = data.get("description", "Description not available")
        title = data.get("title", "title not available")
        moduleCredit = data.get("moduleCredit", "ModuleCredit not available")

        # Save the JSON data to a text file
        filename = f"Desktop/modules.txt"
        with open(filename, "a") as file:
            file.write(course_code + ", " + title + ", " + description + "\n")

        print(f"Successfully saved {course_code} module data!")

    except requests.exceptions.HTTPError as errh:
        print(f"HTTP Error: {errh}")
    except requests.exceptions.ConnectionError as errc:
        print(f"Error Connecting: {errc}")
    except requests.exceptions.Timeout as errt:
        print(f"Timeout Error: {errt}")
    except requests.exceptions.RequestException as err:
        print(f"An error occurred: {err}")


# Example list of course codes
course_codes = [
    "CS2107", "CS3226", "CS3243", "CS3240", "CS2103", "CS2102", "CS2108"
]

# Fetch and save modules for each course code
for code in course_codes:
    fetch_and_save_module(code)

```

<br>

---

### Code Contributions

Click [here](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=tjingsheng&sort=groupTitle%20dsc&sortWithin=totalCommits%20dsc&since=2023-10-16&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other)
for the RepoSense Link to view my contributions.

<br>

---

### Enhancements to existing features

##### Add unit to `LocalCourses` and `PartnerCourses`

Pull Request Link: [108](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/108/)

In this enhancement, I focused on improving the functionality of `LocalCourses` and `PartnerCourses` within SEPlendid.
Different universities around the world uses different systems to measure the workload of their courses. The goal was to
provide users with additional information by incorporating the concept of "units" for courses. To achieve this, I
modified the codebase to include a unit field for both local and partner courses. This enhancement not only enriches the
data available to users but also contributes to a more comprehensive understanding of the courses listed in SEPlendid.

##### Further Improve UI

Pull Request Link: [116](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/116)

In this enhancement, I concentrated on augmenting SEPlendid’s UI by refining the styling aspects. Recognising the
importance of an aesthetically pleasing and user-friendly design, I modified into the style sheets, making modifications
to enhance the overall visual appeal.Specifically, the focus was on improving the styling elements to create a more
polished and streamlined appearance. This involved fine-tuning colour schemes, adjusting layout proportions, font sizes,
and ensuring visual consistency throughout the application. Additionally, unnecessary clutter and elements were removed
to achieve a cleaner codebase.

##### Add Tests for Sample Data

Pull Request Link: [155](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/155)

The substantial dataset mentioned above was generated within a `sampledata` package. Emphasising the importance of data
consistency, and only good data is preloaded into SEPlendid tests were written. This ensures the reliability of this
dataset validating its accuracy and maintaining data integrity.

##### Improve Usage Messages

Pull Request Link: [190](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/190)

In the initial phases of implementing SEPlendid, usage messages were initially embedded within each command. While this
strategy was effective with a smaller number of commands, challenges emerged as the project expanded and more commands
were added, leading to complexities in standardizing usage messages. To tackle this, all usage messages were abstracted
out. This abstraction was designed to improve maintainability, usability, and ensure consistent usage messages across
the expanding array of commands within SEPlendid.

##### Refactor Constraint Messages

Pull Request
Links: [237](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/237/)
       [241](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/241/)
       [279](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/279)

Similar to improving usage messages [above](#improve-usage-messages), in the initial implementation, constraint messages
were initially embedded within each attribute and datatype model. leading to the same complexities in standardising
constraint messages. A similar solution was done to abstract out all the constraint messages. This abstraction also
improve maintainability, usability, and ensure consistent usage messages across the expanding number of models within
SEPlendid.

##### Remove Address Book 3

Pull Request Link: [270](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/270)

This long-overdue enhancement marks a significant milestone. With the majority of the transition from AB3 to SEPlendid
complete, it is time to eliminate the remaining traces of AB3 from the codebase. This step not only streamlines the
application but also ensures a cohesive and unified code structure, reinforcing the full integration of SEPlendid and
eliminating any remnants of its predecessor, AB3.

<br>

---

### Bugs Fixed ##

##### Text Wrapping

Pull Request Link: [154](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/154/)

Numerous users provided feedback regarding text being clipped off within the application, making it challenging to view
the complete content. Even resizing the screen proved inadequate in most cases. In response to this user concern, a new
functionality was implemented to address the issue by automatically wrapping the text. This enhancement ensures that
users can easily view the full data without any content being truncated, significantly improving the overall user
experience.

##### Fix Help and Exit Command

Pull Request Link: [167](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/167)
                   [245](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/245)

Throughout the transition from AB3 to SEPlendid, certain non-essential commands received less attention, leading to
neglect and incomplete implementations in some instances. Specifically, the exit command and help command were among
those affected. In response, I took the initiative to rectify and thoroughly implement these commands, ensuring their
proper functionality and addressing any issues that may have caused them to break.

##### Fix UI bugs followed by persistent selection of item bug

Pull Request Link: [236](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/236/)
                   [281](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/281) 

A corrective measure was introduced to enhance consistency within SEPlendid. Previously, there was an issue where
executing certain commands, commands that switch the context of the data, populated the item list panel with new data
while leaving the previous detail panel intact, leading to inconsistency between the detail panel and item list panel.
To address this, a fix was implemented to clear the detail panel, ensuring a more cohesive user experience.

However, this introduced a new challenge where the currently selected item had to be deselected before it could be
selected again. This behaviour was not user-friendly and led to unexpected interactions. Consequently, a further fix was
implemented to automatically deselect the current item, eliminating the need for users to manually deselect before
making a new selection.

<br>

---

### Documentations ##

##### Standardise User Guide Parts

Pull Request Link: [263](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/263)

The user guide had some inconsistencies that should be fixed

Here are some fixes:

- Ran document through automatic formatting for consistency
- Standardized page separators and accompanying line breaks across sections
    - Use \<br\> instead of \<br /\> or \<br/\>
    - Shorten line seperator to "---" for better maintenance
    - Add page seperator for sections missing the line seperator
- Improve grammar in commands and fix links
    -  Since we are only able to search and sort by a single attribute and return multiple results.
       search a *datatype* by attributes -> search for *datatypes* by an attribute
       sort a *datatype* by attributes  -> sort all *datatypes* by an attribute
    - Clear tag a note -> Clear all tags from a note
    - Fixes link for 5.2.5
    - Fix all broken links, four broken links found
- Standardise the presentation of data types
    -  if the mention does not have a space e.g. "localcourse" it should be in the code format
- Make Tip Box and Warning Box consistent.
    - Some text are prefixed with the word "Warning:" or "Tip:"
    - Some Tip boxes using the wrong icon
    - Some Warning box icon colour not specified (for consistency)
    - Added prefix text to these boxes
    - Boxes with inconsistency e.g. "\<box type="info" icon=":exclamation :" icon-color="red"/\>"
- Update attribute to match application

##### Added table of contents for the developer guide

Pull Request Link: [284](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/284)

An enhancement in the developer guide involved the addition of a Table of Contents. This addition serves as a valuable
navigational aid, providing developers with a structured overview of the guide's content. The Table of Contents
facilitates efficient access to specific sections, improving overall readability and usability of the developer guide
within SEPlendid's documentation

##### Update all UI related components in the developer guide

Pull Request
Link: [273](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/273) 
      [275](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/275)

After the successful transition from AB3 to SEPlendid, it came to light that specific diagrams in the developer guide,
particularly those related to UI components, had become outdated. To uphold accuracy and relevance, I took the
initiative to thoroughly revise and update these diagrams. This proactive measure ensures that the developer guide
remains current and accurately reflects the latest changes and improvements introduced during the transition to
SEPlendid.

#### Afterword

The journey with SEPlendid has been a profound learning experience, emphasising the transformative power of
collaborative development. Redesigning the UI from AB3 underscored the importance of prioritising user experience and
simplicity. Integrating data from NUSmods and eduRec taught valuable lessons about data processing and enhancing
application functionality. Code contributions and bug fixes honed my skills in software development, emphasising the
significance of reliability and user-centric design. Documentation improvements highlighted the importance of clear
communication. As we chart the future, the lessons learned in innovation, responsiveness to feedback, and dedication to
excellence stand as a testament to personal and collective growth in software development.

It was a pleasure being part of SEPlendid.

<br>
<br>
<br>
<br>
