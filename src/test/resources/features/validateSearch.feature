@searchItem

Feature: Verify the search functionality works successfully

Scenario Outline: Verify the search functionality displays relevant results 
Given the user enters the Demo Web Shop website address
When the website title is Demo Web Shop
Then the user navigated to the website "Demo Web Shop"
When the user enters "laptop" in the search box
And the user clicks the Search button
Then the relevant products should be displayed in the search results