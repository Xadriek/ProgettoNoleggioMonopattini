
1.	Contesto di riferimento e obiettivi

Contesto

L’obiettivo di questo progetto è quello di realizzare un prototipo di un’applicazione WEB
inerente al booking di un servizio di Electric scooter-sharing long term.
Visto l’attuale periodo in cui sta cambiando il paradigma relativo alla mobilità personale, ci è
sembrato plausibile prendere in considerazione il noleggio dei monopattini elettrici con la
modalità “a lungo termine” una sorta di leasing in cui è possibile prenotare per un periodo
variabile da 6 mesi a 2 anni (il massimo considerato anche l’obsolescenza tecnica del
mezzo).
Al servizio di noleggio abbiamo immaginato di associare anche una piccola feature
relativamente alla gestione della batteria: Infatti con una rete di distributori sarà possibile
cambiare “on the fly” la batteria, sostituendo quella scarica con una carica ovviamente
compatibile con il monopattino in uso.
Come sistema di Pagamento viene utilizzato un sistema a “Gettoni” in cui l’utente riconosce
un certo numero di “Gettoni” per il noleggio e alla rete costituita da utenti che forniscono il
servizio di “cambio batteria” (swap) viene a sua volta riconosciuta un certo numero di
“Gettoni”
•	Obiettivi

L’obiettivo è creare un sistema informativo su Web che contempli i seguenti casi d’uso che seguono 

 
2.	Casi D’Uso

Caso d'uso UC1: Consultazione:
Attore primario: utente non registrato. Un qualunque accesso anonimo (che può fruire direttamente il portale senza essere necessariamente registrato) può accedere alle pagine 
di consultazione.
L'utente ripete i passi precedenti un numero indefinito di volte. 

Caso d'uso UC2: Login applicazione: Sarà possibile da un portale (una semplice pagina di Login) effettuare l’accesso al sistema.:
Attore primario: Viene registrato un utente con le proprie credenziali. 
Vengono gestiti 3 tipologie di utente:
● Customer: L’utente che noleggia il mezzo, registrato nel portale che può essere o
meno in possesso di un monopattino (presupponiamo caso semplice, si noleggia
SOLO UN MONOPATTINO).
● Partner: L’utente, ovviamente registrato nel portale, che mette a disposizione il
“cambio batteria”. 
● Amministratore: Gestore di tutto il portale con eventuali possibilità di modifiche dati
protetti all’utente Customer o Partner.

Scenario principale di successo: 

E’ altresì necessario ovviamente loggarsi al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di amministrazione, di user oppure di partner. 
Attore primario: amministrazione o customer o partner.  Si presuppone che l’utente sia quello registrato con appositi permessi e registrato con un apposito ruolo su DB in grado di effettuare operazioni di creazione del dato o visualizzazione dello stesso.

Scenario principale di successo per Customer: 

È altresì necessario ovviamente loggarsi al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di customer. 
Il sistema presenterà una finestra di benvenuto visualizzando i dati di accesso e l’utente provvederà a:

•	Visualizzare:
Dati anagrafici
Dati di noleggio
Cambi batteria utilizzati
Cambio Password
Vari ed Eventuali
● Gestione Noleggio: L’utente crea un noleggio

Scenario principale di successo per Partner: 

È altresì necessario ovviamente loggarsi al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di Partner.  Per requisito si presuppone che il servizio degli utenti partner sia già preconfigurato senza la possibilità di “cancellarsi” dalla rete dei servizi.
Il sistema presenterà una finestra di benvenuto visualizzando i dati di accesso e l’utente provvederà a:

•	Visualizzare:
Dati anagrafici
ID Batterie (all’interno dell’HUB)
Swap in Arrivo (Prenotazione del Customer)
Swap terminati (Ritirati dal Customer)

Scenario principale di successo per Amministrator: 

È altresì necessario ovviamente loggarsi al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di Amministratore. 
Il sistema presenterà una finestra di benvenuto visualizzando i dati di accesso e l’utente provvederà a:

•	Modificare:
Dati protetti al Customer
Dati protetti al Partner
Vari ed Eventuali
•	Visualizzare:
Lista Customer
Noleggio in corso e terminato
Elenco Batterie, traccia dei Mezzi e Hub
Riscossione / Liquidazione gettoni ( a seguito delle operazioni di swap)
Lista Hub


Caso d'uso UC3: Creazione Noleggio 
Attore primario: Customer. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “Customer”, registrato con un apposito ruolo su DB.


Scenario principale:

Il customer crea un noleggio attraverso i menu.

Il sistema registra il noleggio attraverso l’apposita form. Al Customer il sistema assegna un wallet a debito stipulando un contratto di noleggio; ogni noleggio comprende un mezzo dotato di batteria (con proprio ID, voltaggio e capacità).


Caso d'uso UC4: Ricerca Swap e cambio batteria 
Attore primario: Customer. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “Customer”, registrato con un apposito ruolo su DB.







Scenario principale:

Il customer vuole sostituire la batteria attraverso i menu, visualizza le batterie disponibili dai partner, sceglie la batteria e la prenota. 
Per completare l’operazione una volta sostituita la batteria con l’ausilio del Partner, vengono detratti i gettoni dal wallet del customer e viene effettuato lo swap. Il portale incrementerà gli swap del cliente.

Caso d'uso UC5: Swap Batteria lato Partner.

Attore primario: Partner. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “Partner”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di creazione del dato come quello dell’esame.

Scenario principale di successo: 
Il portale notifica al partner l’avvenuta prenotazione da parte del Customer. All’arrivo del Customer inizia la fase di swap nel portale viene inserito il codice di prenotazione e viene incrementato il contatore dei gettoni.
Il portale rimuove la batteria nell’HUB e la assegna al mezzo del Customer.

Caso d'uso UC6: Visualizzazione dati.
Attore primario: amministrazione. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di visualizzazione del dato.

L’amministratore selezione dall’apposito menu da cruscotto visualizza tutti i dati legati al noleggio dei mezzi, agli utenti, i partner e le operazioni che si stanno effettuando (Swap in corso, Swap terminati) e Gettoni disponibili

