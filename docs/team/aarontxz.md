---
  layout: default.md
  title: "Teng Xin Zhuan's Project Portfolio Page"
---

### Project: InsuraHub

InsuraHub - InsuraHub is a CLI application that targets financial advisors that type fast and prefers to use CLI over GUI to use the app.
The application is mainly used by financial advisors in their day-to-day planning and scheduling of meetings with clients,
assisting them by providing quick access to important information of each client stored in the database, displayed on the UI of InsuraHub.

While InsuraHub has limited functionalities at this stage, there are further plans to improve the application with the timeline being set in place.

Given below are my contributions to the project.

- **New Feature**: Added the ability to allow users to easily create and open a folder for a specific client.

  - What it does: Creates or open a folder if it already exists for a client, to store any words or pdf documents etc
  - Justification: This feature improves the product as it provides the insurance agent a convenient and organised way to be file their important documents for each clients.
  - Highlights: I had to ensure that the creation of folders are stored properly in the correct file path and the correct folder is opened for each client based on their name.
  - Credits: Edit command AB3.

- **New Feature**: Added a compulsory initial setting of password and subsequent entering of password to enter InsuraHub.

  - What it does: When InsuraHub is first opened, user will be required to set a password, subsequently the password has to be entered to enter InsuraHub.
  - Justification: This feature improves the product as it adds a layer of authentication before displaying crucial client information that an Insurance Agent would not want any random person who opens the app to see.
  - Highlights This feature was challenging as I had to ensure that the passwordManager and passwordDialog come together to only allow the user to enter InsuraHub when a correct password is entered and notify the user when a wrong passwrod is given. I had to learn how javaFX works to create my own dialogBox and also how AB3 controls the UI windows to integrate my feature successfully.
  - Credits: MainWindow AB3.

- **New Feature**: Added the ability to changePassword

  - What it does: Change the password previously set to enter InsuraHub.
  - Justification: This feature improves the product as it allows the Insruance agent to secure their clients information by being able to periodically change the password required to enter InsuraHub if they want to.
  - Highlights: I had to ensure that the passwordManager only edits the file which stores the correct password when the old password given matches the current password before the change before rewriting the password saved in the encoded.txt file

- **New Feature**: Added the ability view Policy details

  - What it does: selects a specific policy of a specific client to display details of the policy that is not shown on the InsuraHub.
  - Justification: This feature improves the product as it allows the Insurance Agent to see the specific details of each policy of each client, important details such as start time and end time of the policy instead of just the name of the policy shown on InsuraHub.
  - Highlights: I came up with the idea of implementing this feature as the group needed a fix to the lack of display of crucial details that our Policy class hold that could not be shown on the screen. This helped give the Policy class a purpose instead of just having the name of the policy displayed on the screen.
  - Credit: removePolicy Command done by my teammate in InsuraHub.

- **New Feature**: Added the Ability to Toggle UI mode

  - What it does: Toggles between DarkMode and LightMode for the UI of InsuraHub the next time InsuraHub is opened.
  - Justification: This feature improves the product as it allows the Insurance Agent to change to a Lighter display for InsuraHub if it better suit their personality/preference, as most Insurance agent tend to have a bright personality to engage their clients. Being able to toggle between their preferred mode would also help enhance the productivity of the Insurance Agent, as some people read better in light mode.
  - Highlights: This feature was challenging to implement as I had to learn which part of the css code affects which part of the display in InsuraHub.
  - Credit: DarkTheme.css, MainWindow.fxml AB3

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=aarontxz&breakdown=true)

- **Project management**:

  - Participated in releases v1.1 - v1.4 on github with 2 releases currently, namely v1.3.trial and v1.3.final, v1.4 will be released when it is done.
  - Fixed most of the testcases that failed due to changes in code done by the team throughout the project development in [\#216](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/216)
  - increase test coverage by adding testcases in [\#241](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/241)

- **Enhancements to existing features**:

  - Add file command in [\#65](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/65)
  - Add set and enter password requirement [\#98](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/98)
  - Add changePassword command [\#103](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/103)
  - Add ToggleModeCommand in [\#118](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/118)
  - Add viewPolicyCommand in [\#121](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/121)

- **Documentation**:

  - User Guide:
    - Add commands to user guide and refined in [\#126](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/126)
  - Developer Guide:
    - Added initial user stories in [\#36](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/36)
    - Added activity and sequence diagrams in [\#234](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/234)

- **Community**:

  - Reviewed 24 PRs and provided feedback
  - Reviewed 4 other IPs, 2 during the peer review in week 8 and 2 during the week 4
  - Contributed to 2 forum posts and discussions (examples: [link](https://github.com/nus-cs2103-AY2324S1/forum/issues/218#issuecomment-1736617241))
  - Reported 5 bugs and suggestions for other teams in the class (examples: [link](https://github.com/AY2324S1-CS2103T-W16-4/tp/issues/124))
