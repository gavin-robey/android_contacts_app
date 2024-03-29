# Contacts App

## Project Information

This project is a Contacts App for Andriod that uses Model View View Model to update UI, state, and data to a local database. Users can add contacts, delete contacts, update contact information, call, email and text all contacts. All actions are done via a fluid UI that is User-friendly. All contact information is stored to the device using <a href="https://developer.android.com/training/data-storage/room">Room</a>. All Java code can be found in: app/src/main/java/com/usu/A02340346/. Below are demo images demonstrating the app in use.

### Home Page
* Add contacts by tapping on the blue "+"
* Delete contacts by tapping the button with the three dots, then tapping the red "x"
* Tap the contact card to view contact information

&nbsp;

<p align="middle">
  <img width="320" alt="homePage" src="https://user-images.githubusercontent.com/106276626/224109798-91af21a6-b93b-4a2b-9b78-108451cb2a94.png">
  <span>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span>
  <img width="318" alt="delete" src="https://user-images.githubusercontent.com/106276626/224113420-2d7ca31f-afa5-4163-89cf-a8ca690bb66e.png">  
</p>

&nbsp;

### Create Contact
Users can add the following contact information
* Full name
* Phone number
* Email
* Company 
* Address 
* Birthday

&nbsp;

<p align="center"> 
<img width="320" alt="createContact" src="https://user-images.githubusercontent.com/106276626/224118637-8447b187-c0d6-4d32-a36c-40395ae0375c.png">
</p>

&nbsp;

### View Contact
* Call using the blue call icon
* Text using the green message icon
* Email using the blue mail icon
* Update contact information using the yellow edit icon

&nbsp;

<p align="center"> 
<img width="320" alt="editContact" src="https://user-images.githubusercontent.com/106276626/224120164-0b69a9aa-083b-46b3-9a6e-134589ac40c0.png">
</p>

&nbsp;

### Edit Contact
* All information can be edited and updated by the user
* Once Information is updated, click blue checkmark to update

&nbsp;

<p align="center"> 
<img width="320" alt="edit-contact" src="https://user-images.githubusercontent.com/106276626/224128134-a6941731-3387-454b-88ee-20e5647853ce.png">
</p>



## Build Instructions

1. Clone the Repository:
```commandline
git clone git@github.com:gavin-robey/android_contacts_app.git
```

2. Download Andriod Studio:

* Go to <a href="https://developer.android.com/studio?gclid=Cj0KCQiAgaGgBhC8ARIsAAAyLfFpW2xmX7UHlWjsFSTzCaOdjMSHSjXRASHeGOoGDnIdCUyXY2PNO0IaAjuiEALw_wcB&gclsrc=aw.ds" target="_blank">Android Studio Download</a> and follow download instructions

3. Once download for Android Studio is complete, open the cloned repository in Andriod Studio and allow gradle build to finish

4. Once build is complete, add a new virtual device by clicking the drop down menu with the text "No Devices". Then select "Device Manager" and click "Create Virtual Device".

![createVirtualDevice](https://user-images.githubusercontent.com/106276626/223861369-15d9c85a-9bc1-4cbe-b023-0a3ee89e4995.png)

5. Then select a device to emulate.


![selectHardware](https://user-images.githubusercontent.com/106276626/223861650-706bc71f-67a4-4224-90b0-c2586f96714e.png)



6. Download Android API level 32, click "next", accept the developer lisence, and click "download" to download SDK.

<img width="1440" alt="selectSoftware" src="https://user-images.githubusercontent.com/106276626/223861674-34238923-fb4b-4723-8c0c-f35b465398c9.png">


7. Once download is complete, click "run app" to run the app



<img width="1440" alt="runProject" src="https://user-images.githubusercontent.com/106276626/223862364-d8bf8848-b9ad-413d-9f52-f4b734b262be.png">



![projectRuning](https://user-images.githubusercontent.com/106276626/223862608-e8f38c58-ce75-4182-a65c-d3ee7d0b6ef0.png)



