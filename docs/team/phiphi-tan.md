---
layout: page
title: Phi Phi's Project Portfolio Page
---

### Project: CodeSphere

**CodeSphere** is a desktop **contact management app, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). It is an app targeted at connecting Year 1 Computer Science students in the NUS School of Computing.
Given below are my contributions to the project.

* **New Feature**: Command History.
  * What it does: Through utilising the 'UP' and 'DOWN' arrows on the keyboard,
  users can access commands that were previously inputted into the application.
  Commands that result in an error will be shown in red. 
  * Justification: This feature allows the user to be able to access and edit erroneous commands or repeat previous actions
  without having to re-type the entire command again

* **UI Feature**: Display Panel
  * What it does: Attached to the 'Main Window' as part of the application's UI, this display panel provides an easy way
  to toggle between 3 different views (Splash Screen, Course Panel, Combined Panel).
  * Justification: Due to commands working in different 'stages' of our application, the user interface should
  be updated accordingly as well to reflect these different stages. By attaching view panels to a single 'display panel',
  what is displayed to the user can easily be changed and updated according to commands and logic flows.
  * Highlights: Upon application start-up, a splash screen of the CodeSphere logo will be displayed to the user, which
  gives the app a more 'polished' feel and improves the general user experience

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=phiphi-tan&breakdown=true)
  
* **Project management**:
  * To be added soon.

* **Enhancements to existing features**:
  * **UI Enhancement**: Course list panel
    * With the enhancements made over the AB3 codebase, the User Interface had to be updated respectively
      to fit with the new changes and features added.
  * **UI Enhancement**: Combined panel sidebar
    * Provides a sidebar of all courses alongside the view of the particular student list of a selected course
      The selected course in the side panel is highlighted while the other courses are faded.
    * The combined panel serves as a direct upgrade to the previous 'Student List' panel 
      by creating a 'sidebar' that allows users to still see summary statistics of other courses while viewing students in
      a certain course.
  * **UI Enhancement**: CodeSphere UI Upgrades
    * User Interface was updated from AB3 to better fit our CodeSphere application (fonts, team logo, icons etc.) 
  * Modified existing test cases and added new tests.


* **Documentation**:
  * User Guide:
    * Added in a Beginner's Tutorial that walked new users through navigating the application and 
      how to use some of the more basic functions in CodeSphere.
  * Developer Guide:
    * Updated the DG regarding Storage as CodeSphere introduced a new 'InputStorage' class
    * Updated and rewrote the portion on User Interface (UI) with new class diagrams as well as in-depth
      explanations regarding the different UI components that were added in.

* **Community**:
  * Reviewed PRs, and suggested improvements for things like Javadoc style and code quality.
  * Reported bugs for team CS2103T-W13-3 during PE-D and suggested possible areas of improvements
  
* **Tools**:
  * To be added soon.
