# ProjektSoftwarepraktikum

### Aufschlüsselung Teammitglieder:
- st177168: Luca Groner
- st177921/ts24017: Tony Steinbach
- st176033/ShaleryWad: Florian Schad
- AemsuCode: Remi Chaieb
- Nico Böklen

## TO-DO's:

### Allgemein:
- einheitliches Layout finden -> auch für Überschriften und Texte (Tony)
- Check auf Fehlerbehandlungen
- Alles auf Englisch (einheitlich) (Luca) DONE
- footer (Luca) --> Grundlayout fertig. About us seite erstellen. 
- html head wieder einbinden
- über fragments header und footer auslagern (übersichtlicher) -> aufpassen da der in negotiation unterschiedlich (ruft js methode start negotiation auf)
- evtl. Js methoden auslagern
- nicht genutzte Imports weg

### aboutus.html
-mehr dazuschreiben (namen etc.)

### home.html:
- alles ein wenig "entzerren"
- bisher immer Verhandlung vorausgewählt
- Button oben ist weg
- mehr Text/Erläuterungen(?)
- rechts weißer balken weg
- weißer balken muss weg (dafür direkt footer)
- scrollen nur bei mehr als einer Verhandlung
- button größe ändert sich nicht mit (wegen css file, wo größe gesetzt wird) DONE -> Padding entfernt
 
### negotiation.html:
- weißer balken muss weg (dafür direkt footer)
- footer anpassen auf 2023
- footer kleiner

### feedback.html (Remi, Florian?):
- Serverfunktionen implementieren -> Berechnungen auf Basis der Eingabedaten; bisher stets die gleiche Grafik
- navbar einbinden mit Logout-Button
- bisheriger Ausloggen-Button entfernen
- textuelles Feedback integrieren -> auf Basis der Eingabedaten passend zur generierten Grafik
- allgemeine Erläuterung zur Grafik oder innerhalb des textuellen Feedbacks inkludiert
- Abspeichern des Feedbacks(?)
- Feedbackelement: Utility Wert Verlauf (Mitte der Verhandlung) und dann Bezug zu Aspiration und Reservation Level (als Text z.B.)
- Optional: Exportfunktion der Grafik und des textuellen Feedbacks
    + Export als PDF mit Grafik und textuellem Feedback darunter
