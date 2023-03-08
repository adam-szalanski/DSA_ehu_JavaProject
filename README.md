# DSA_ehu_JavaProject
This project was created as part of Data Structures and Analysis classes conducted by [Jose Javier Dolado Cosin](https://www.ehu.eus/es/web/graduak/grado-ingenieria-informatica/profesorado?p_redirect=fichaPDI&p_idp=3647&nredi=1) at the [University of the Basque Country](https://www.ehu.eus/en/en-home).
It's purpose is to load people and their relationships from included txt files into custom classes and connect them into groups based on thier favourite movies lists.

# Features
This program is operated via console and keyboard input.

Project allows to load people and their relationships from txt files and group them together based on thier favourite movies lists using graphs.

Loading multiple people files adds new people to people already loaded into the program without loading duplicate people.
Same for relationships.

The program also allows to save current state into new .txt files allowing to move and reload program state between multiple instances.

By use of files like residential.txt containing:
idperson (mandatory first field) and ids separated by new lines, program is allowed to select data of specified people only.

# .TXT Files
Files with names like "\*people\*" contain information about people separated with comas and for multiple value fields, each value separated with semicolon.

Contained data are:
- idperson - Id of a person in String format
- name - Name of a person
- lastname - Last name of a person
- birthdate - Birthdate in dd-mm-yyyy format
- gender - Gender as String (full gender names)
- birthplace - City of birth
- home - City of residence
- studiedat - Multivalue field containing names of places where person has studied
- workplaces - Multivalue field containing names of places where person has worked
- films - Multivalue field containing names of persons favourite movies
- groupcode - Code of a group that person is a part of

Files with names like "\*friends\*" contain pairs of people later referenced as relationships.

Containded data are:
- friend1 - Id of a person (same as id in "\*people\*" files)
- friend2 - Id of a person (same as id in "\*people\*" files)
