@sortItems

Feature: Verify the sort functionality works successfully

Scenario Outline: Verify the sort functionality displays relevant results 
Given the user enters the Demo Web Shop website address
When the website title is Demo Web Shop
Then the user navigated to the website "Demo Web Shop"
When the user clicks Books from top-menu category
And "Books" category items are displayed
When sort <range> is selected
Then sort feature is validated


    Examples:
      | range |
      | "A to Z" |
      | "Z to A" |

