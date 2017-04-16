#Makefile pre proj2 do IVS#

PROJECT_NAME = IVS_proj2
#JAVA_FILES = $(shell find ./src/Kalkulacka/app/src/ -type f -name '*.java')
#CLASSES_FILES = $(JAVA_FILES:./src/Kalkulacka/app/src/%.java=./classes/%.class)

#Projektova struktura na vysledne zabalenie
ALL_FILES = html mockup plan profiling src manual.pdf README.txt screenshot.png Makefile hodnoceni.txt
doc:
	doxygen ./Doxyfile

pack:
	zip $(PROJECT_NAME).zip $(ALL_FILES)

clean:
	$(RM) *.zip
