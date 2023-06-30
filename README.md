# ProjektSoftwarepraktikum

### Aufschlüsselung Teammitglieder:
- st177168: Luca Groner
- st177921/ts24017: Tony Steinbach
- st176033/ShaleryWad: Florian Schad
- AemsuCode: Remi Chaieb
- Nico Böklen
  
## LOGIN 

### USER:
- Benutzername:user+userID (z.B. user35)
- Passwort:1234
### Admin:
- Benutzername: admin
- Passwort: admin

## TO-DO's (Anforderungen Übungsblatt 3.3):

### Allgemein:
- Fehlerbehandlung und Validierung der Eingaben 

### Admin-Bereich:
- Erstellung einer neuen Willkommensseite
- Implementierung zweier Feedback-Elemente, die das Feedback aller Verhandlungen aggregiert
      + am besten direkt auf der Willkommensseite des Admin
- tabellarische Übersicht der Ergebnisse aller Verhandlungen, der gesetzten Ziele sowie das Feedback (Ziele vs. Ergebnis)
    + Anforderung des tabellarischen Feedbacks über Button; Weiterleitung an neue Seite
- Filterfunktion für die tabellarische Übersicht

### feedback.html:
- Anzeige eines weiteren Feedback-Elements während der Verhandlung
- --> Verlauf Contract Imbalance, Joint Utility (Ampelfarben!)
- weitere Seite für die Anzeige des Feedbacks nach der Verhandlung
- --> Spinnennetz für TKI-Style
- --> auf gewünschte Zeit eingehen
- Button zum Anfordern des Feedbacks bzw. zur Weiterleitung
- Optional: Exportfunktion der Grafik und des textuellen Feedbacks
    + Export als PDF mit Grafik und textuellem Feedback darunter


## TO-DO's (aus Feedback Übungsblatt 3.2)

### Allgemein:
- Entscheidung von "Home" oder "Sopra Negotiation Website" -> Einigung auf eine Navigationsmöglichkeit
- header, footer auslagern

### login.html:
- spezifische Fehlerbehandlung -> nicht ausschließlich der Verweis auf die Registrierungsfunktion

### register.html:
- Implementierung der Registrierungsfunktion
- falls nicht implementiert, dann rausnehmen

### home.html:
- Darstellung des Enddatums (?) -> aktuell rausgenommen
- Button für jede Verhandlung (funktioniert noch nicht)

### negotiation.html:
- Änderung der Beschreibung/Beschriftung zum TKI-Level
- bessere Fehlerbehandlung und Validierung der Eingaben
- Validierung zw AL >= RL
- 

### feedback.html:
- Überarbeitung der Utility-Werte (Mehrwert der Datentabelle darunter?)
- "wenig Aussagekraft hat, da die Angebote vom Partner abgelehnt werden können. Aussagekräftiger ist, wenn AL/RL durch die Angebote des Partners erreicht wurden"
- 

