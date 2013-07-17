
* EChatLibrary is an android-library.

* It generates a RichChat view which contains message along with a preview of any urls entered in the message.

* The preview includes a thumbnail , title and description of the url ( description not always present ) .

* The RichChat view can be added through xml as well as programmatically.

	- Example of xml inclusion :
	    - xmlns:richChat = "http://schemas.android.com/apk/res-auto (this line should be added in your main view)
	       
	    - \<com.example.EChatLibrary.RichChat
			android:id = "@+id/RichChatView1"
	        	android:layout_width = "match_parent"
			android:layout_height = "wrap_content"
			richChat:chatText = "random url : http://www.youtube.com/watch?v=J9rBIKHJoaY" />

	- Example of including programmatically :
	    - RichChat richChat = new RichChat ( context , "random url : http://www.youtube.com/watch?v=J9rBIKHJoaY" );
	    - LinearLayout richChatLayout = (LinearLayout) findViewById(R.id.richChatLayout);
	    - richChatLayout.addView(richChat);
 
