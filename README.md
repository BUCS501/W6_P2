# GuessF1Drivers Android App
### Leaning to Maintain State, Bundles &amp; SharedPreferences: Worksheet 6 - Part 2

Given Tasks:

- [ ] Create a (very) simple App, with one Button, one imageview and one EditText.  Add several drawables (images) of your choosing to your project.  Clicking on the button will load a random image into the imageview.  Enter some text in the EditText yourself.  You may lay things out in any way you like.  
- [ ] Create a SharedPreferences object to save the state (image and text) when onDestroy(..) is called.  
- [ ] Add code to the onCreate(..) event that will automatically reload the information stored in step b.  
- [ ] One might expect this to be enough to ensure your app “remembers” its state, even when it is shut off.  But, under what circumstances will the SharedPreferences Fragment fail to restore your App.?
