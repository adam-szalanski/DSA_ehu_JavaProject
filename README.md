# DSA_ehu_JavaProject
This project was created as part of Data Structures and Algorithms classes conducted by [Jose Javier Dolado Cosin](https://www.ehu.eus/es/web/graduak/grado-ingenieria-informatica/profesorado?p_redirect=fichaPDI&p_idp=3647&nredi=1) at the [University of the Basque Country](https://www.ehu.eus/en/en-home).
It's purpose is to load people and their relationships from included txt files into custom classes and connect them into groups based on thier favourite movies lists.

# Features
This program is operated via console and keyboard input.

Project allows to load people and their relationships from txt files and group them together based on thier favourite movies lists using graphs.

Loading multiple people files adds new people to people already loaded into the program without loading duplicate people.
Same for relationships.

The program also allows to save current state into new .txt files allowing to move and reload program state between multiple instances.

By use of files like [residential.txt](../main/residential.txt) containing:
idperson (mandatory first field) and ids separated by new lines, program is allowed to select data of specified people only.

# .TXT Files
Files with names like [\*people\*](../main/df_people_8.txt) contain information about people separated with comas and for multiple value fields, each value separated with semicolon.

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

Files with names like [\*friends\*](../main/df_friends_8L19.txt) contain pairs of people later referenced as relationships.

Containded data are:
- friend1 - Id of a person (same as id in \*people\* files)
- friend2 - Id of a person (same as id in \*people\* files)

# Authors
<table>
<tr>
<td><p align="center">Adam Szałański</p></td>
<td><p align="center">Brajan Miśkowicz</p></td>
<td><p align="center">Łucja Pałkus</p></td>
</tr>
<tr>
<td>
<a href="https://linkedin.com/in/adam-szalanski">
   <img src="https://media.licdn.com/dms/image/D4D03AQEQQN-CcweVoQ/profile-displayphoto-shrink_800_800/0/1675129015106?e=1683763200&v=beta&t=Js1TJsuLwZvTTGnCSXgcr014M445PwfR4sUXZrQ7fWA" alt="Adam Szałański">
  </a>
   </td>
<td>
<a href="https://linkedin.com/in/brajan-miskowicz">
   <img src="https://media.licdn.com/dms/image/D4D35AQEIDYEK-jbHow/profile-framedphoto-shrink_800_800/0/1676476161135?e=1679050800&v=beta&t=c5DBY8K-dXd-Qe5e1h3yKahaIOB-j5oF3DCZN42od0w" alt="Brajan Miśkowicz">
  </a>
</td>
<td>
<a href="https://linkedin.com/in/lucja-palkus">
   <img src="https://media.licdn.com/dms/image/C4E03AQHiZl75qDVgAw/profile-displayphoto-shrink_800_800/0/1656453073719?e=1683763200&v=beta&t=CMzF7RO_2cQASvBa0P4qjfXH62EpZHLmnKDC6P8LDS4" alt="Łucja Pałkus">
  </a>
</td>
</tr>
<tr>
<td align="center">

[![LinkedIn][linkedin-shield]][linkedin-url-adam]

</td>
<td align="center">

[![LinkedIn][linkedin-shield]][linkedin-url-brajan]

</td>
<td align="center">

[![LinkedIn][linkedin-shield]][linkedin-url-lucja]

</td>
</tr>
</table>



[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url-adam]: https://linkedin.com/in/adam-szalanski
[linkedin-url-brajan]: https://www.linkedin.com/in/brajan-miskowicz/
[linkedin-url-lucja]: https://www.linkedin.com/in/lucja-palkus/
