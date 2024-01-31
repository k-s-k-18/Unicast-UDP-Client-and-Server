CC = javac

all: client2 server2

client2: 
	$(CC) client2.java
	$(CC) ServerAddress.java

server2:
	$(CC) server2.java
	$(CC) InfoMapping.java

clean:
	rm -r *.class
