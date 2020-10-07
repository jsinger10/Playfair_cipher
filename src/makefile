run: PlayfairCipher.class
	java PlayfairCipher $(ARGS)
PlayfairCipher.class: PlayfairCipher.java
	javac PlayfairCipher.java
help:
	$(info 1 Instructions on How to Run and Comments)
	$(info 2 make -run will run the test cases given in the assignment)
	$(info 3 make -run ARGS="encode message key" will return the encryption of that message using the given key)
	$(info 4 make -run ARGS="decode message key" will return the decrypted message using the given key)
	$(info)
	$(info Made by Julian Singer)

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

