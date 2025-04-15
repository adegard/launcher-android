


Launcher for Youngs





Intent
* description of an operation to be performed

action/data pairs


| Action       | Data                              | Description                                                                                                                       |
|--------------|-----------------------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| ACTION_VIEW  | content://contacts/people/1       | Display info about id "1".                                                                     |
| ACTION_DIAL  | content://contacts/people/1       | Display the phone dialer with the person filled in.                                                                               |
| ACTION_VIEW  | tel:123                           | Display the phone dialer with the given number filled in. VIEW action does the most reasonable thing for a particular URI.       |
| ACTION_DIAL  | tel:123                           | Display the phone dialer with the given number filled in.                                                                         |
| ACTION_EDIT  | content://contacts/people/1       | Edit information about the person whose identifier is "1".                                                                        |
| ACTION_VIEW  | content://contacts/people/        | Display a list of people to browse. Selecting a person starts a new intent to display that person's details.                      |

ACTION_MAIN
ACTION_VIEW
ACTION_ATTACH_DATA
ACTION_EDIT
ACTION_PICK
ACTION_CHOOSER
ACTION_GET_CONTENT
ACTION_DIAL
ACTION_CALL
ACTION_SEND
ACTION_SENDTO
ACTION_ANSWER
ACTION_INSERT
ACTION_DELETE
ACTION_RUN
ACTION_SYNC
ACTION_PICK_ACTIVITY
ACTION_SEARCH
ACTION_WEB_SEARCH
ACTION_FACTORY_TEST


To be researched
- manifest


