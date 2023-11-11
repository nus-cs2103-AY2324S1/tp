---
layout: page
title: He Yifan's Project Portfolio Page
---

### Project: ManaGease

ManaGease - Contact management app to simplify and centralize information for full-time employees.
It offers easy access to essential details such as name, email, phone number, address, bank accounts, salary, joining date, and annual leaves. With all the information, the app can help to track employees’ annual leave and also generate their payroll automatically. This helps to reduce errors and enhance overall operational efficiency. This results in time and cost savings, improved organization, and a smoother workflow for your business.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add deductions and benefits to employees' salary.
  * What it does: Allows the user to add deductions and benefits to employees' salary.
  * Justification: This feature is crucial to the effectiveness of the application because it allows the user to manipulate and manage any change to employees' salaries. Meanwhile, deductions and benefits are added together with valid reasons, and this increases the traceability of any possible error in the manipulation of monthly payroll.

* **New Feature**: Added the ability to generate payslips for employees.
  * What it does: Allows the user to generate payslips for employees.
  * Justification: This feature is crucial to the effectiveness of the application because it allows the user to calculate and manage employees' payroll on a given template in PDF format. The template meets the legal requirements of the Ministry of Manpower (MOM) in Singapore, where companies must keep meticulous records of employees' payrolls.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jibtaf&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Project management**:
  * Managed releases `v1.2`, `v1.3(trial)` and `v1.3(final)` on GitHub

* **Enhancements to existing features**:
  * Enhanced the `edit` command to allow the user to edit employees' salary, bank accounts, annual leaves and join dates.

* **Documentation**:
  * User Guide:
    * Added documentation for `edit`, `deduct`, `benefit`, and `payslip` commands.
    * Added a summary of parameter formats and a table of contents.
    * Modified the format of the User Guide to make PDF conversion easier.
  * Developer Guide:
    * Added implementation details of the `deduct`, `benefit`, and `payslip` commands.
    * Added Use Cases for the `deduct`, `benefit`, and `payslip` commands.

* **Community**:
  * PRs reviewed (with non-trivial review comments): #26, #46, #49, #51, #55, #62, #83, #87, #201

* **Tools**:
  * Integrated a third party library (`itextpdf`) into the project. 
  * Integrated a third party library (`fuzzywuzzy`) into the project.
