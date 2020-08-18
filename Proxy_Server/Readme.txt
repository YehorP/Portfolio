SKJ Pierwsze zadanie programistyczne 
Yehor Pakhomov s18776 
------------------------------------------------------------------------------------------------------------------------------
--Opis implementacji
	W moim projekcie zostały utworzone dwie klasy 
	1)ProxyServer-zadaniem tej klasy jest uruchomienie serwera na podanym przez skrypt jako argument adresie i porcie 
  	 po czym ta klasa zaczyna otrzymywać  żądania wysłane przez przeglądarke i przekazywać ich do egzemplarza 
  	 klasy ProxyThred .
	2)ProxyThread- identyfikuje i obrabia ządania przekazane jako argument z egzemplarza klasy ProxyServer , najpierw 
  	 identyfikuje typ ządania HTTP albo HTTPS , potem w zależnośći od poprzednio zidentyfikowanego typu żądania nawiązuje
  	 połąńczenie i wymienia danymi z docelowym serwerem.
--Jak zainstalować
	Dostatecznym jest sciąganie folderu z projektem również koniecznym jest instalacja Java 8 w przypadku jej braku
--Jak uruchomić to co działa.
	Najpierw trzeba uruchomić plik compile.bat po uruchomieniu compile project zostanie skompilowany przed urucomieniem 
	pliku run.bat trzeba podać jako argument wywołani skryptu port(port powinny pyć większy od 1023 i mniejszy 
	od 65536 inazcej zostanie nadany port 8080 jako domyślny ) na kturym będzie uruchomiony proxy następnie 
	trzeba uruchomić plik run.bat po czym program zostanie wykonywany.
--Jak używać
	Po uruchomieniu projektu koniecznym jest ustalienie ustawień proxy serwera na adres:localhost albo 127.0.0.1 
	i jako port:(port wyświetlony jako komunikat podczas wykonania programu).
------------------------------------------------------------------------------------------------------------------------------