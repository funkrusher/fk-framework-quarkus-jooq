
Im Bezug auf Evolutions (Liquibase), bin ich derzeit der Überzeugung, dass diese
nur DDL (Data Definition) enthalten dürfen. 

Alle DML (Data Manipulation) sollte stattdessen in einer eigenen Klasse
auf "Zuruf" genutzt werden um die Datenbank mit den wichtigen Basisdaten zu befüllen.
Dadurch können beim erstmaligen Aufsetzen des Backends, als auch beim Ausführen von IT/Unit-Tests, 
diese Basisdaten sehr frühzeitig in die Datenbank eingefügt werden. Vorteil ist hier, 
dass diese typsicher im Code gepflegt werden und dadurch bei Änderungen angepasst werden können.

Im Bezug auf Release-Installationen sollten dann diese Basisdatenanpassungen manuell auf dem 
Live-System / Staging-System ausgeführt werden und nicht als Teil der Liquibase Evolutions
(oder über einen noch offenen anderen automatisierten Weg).

Ein Vorteil ist hier dass das Schreiben der Unit-Tests / IT-Tests somit viel einfacher passieren kann,
da man nicht in allen bereits jemals erstellen Evolutions nach den Basisdaten suchen muss, die sich auch
im Laufe der Zeit ändern können, sondern immer die aktuell relevante Variante direkt im typsicheren Code (Init),
erkennen kann und sich im Test darauf beziehen kann.