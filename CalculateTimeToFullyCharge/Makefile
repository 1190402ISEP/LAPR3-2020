main: main.o assembly.o
	gcc -Wall -g main.o assembly.o -o main

main.o: main.c
	gcc -Wall -g -c main.c

assembly.o: assembly.s
	gcc -Wall -g -c assembly.s

run: main
	./main
	rm *.o 

clean:
	rm *.o 
