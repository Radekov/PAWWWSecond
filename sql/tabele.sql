create table KATEGORIA
(
    id INTEGER not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    kategoria VARCHAR(30) not null
);
create table JEDNOSTKA
(
    id INTEGER not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),  
    jednostka VARCHAR(10) not null
);
create table PRODUKT
(
    id INTEGER not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    nazwa VARCHAR(30) not null,
    ilosc INTEGER not null,
    cena DECIMAL(10,2),
    id_kategoria INTEGER REFERENCES KATEGORIA(id),
    id_jednostka INTEGER REFERENCES JEDNOSTKA(id)
);
create table UZYTKOWNIK
(
    id INTEGER not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),  
    nazwa_spolki VARCHAR(30) not null,
    nip VARCHAR(30),
    login VARCHAR(30),
    haslo VARCHAR(64),
    id_ostatnie INTEGER-- 1<-->1 -aby pamietac zapisane zamowienie
);
create table ZAMOWIENIE
(
    id INTEGER not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),  
    data_zamowienia DATE not null,
    data_zrealizowania DATE,
    id_uzytkownik INTEGER REFERENCES UZYTKOWNIK(id)
);
create table ZAMOWIENIE_PRODUKT
(
    id_produkt INTEGER REFERENCES PRODUKT(id) not null,
    id_zamowienie INTEGER REFERENCES ZAMOWIENIE(ID) not null,
    ilosc INTEGER not null,
    cena DECIMAL(10,2),
    primary key (id_produkt,id_zamowienie)
);
--W Entity:
--  potworzyc normalne klucze obce
--      Uzytkownik(id_ostatnie) 1<-->1 Zamowienie(id)
--      Zamowienie(id_uzytkownik) INF<-->1 Uzytkownik(id)
--  zamienic cena : Double na BigInteger
--  ??zamienic Date na TimeStamp??

INSERT INTO JEDNOSTKA (jednostka)
VALUES ('mg');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('g');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('dag');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('kg');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('t');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('mm');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('cm');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('m');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('cm2');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('m2');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('km2');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('a');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('ha');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('ml');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('l');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('m3');
INSERT INTO JEDNOSTKA (jednostka)
VALUES ('szt');

INSERT INTO KATEGORIA (kategoria)
VALUES ('opal');
INSERT INTO KATEGORIA (kategoria)
VALUES ('ciecz');
INSERT INTO KATEGORIA (kategoria)
VALUES ('odziez');
INSERT INTO KATEGORIA (kategoria)
VALUES ('budownictwo');
INSERT INTO KATEGORIA (kategoria)
VALUES ('owoce');
INSERT INTO KATEGORIA (kategoria)
VALUES ('warzywa');
INSERT INTO KATEGORIA (kategoria)
VALUES ('drob');
INSERT INTO KATEGORIA (kategoria)
VALUES ('zabawki');
INSERT INTO KATEGORIA (kategoria)
VALUES ('wolowina');
INSERT INTO KATEGORIA (kategoria)
VALUES ('wieprzowina');
INSERT INTO KATEGORIA (kategoria)
VALUES ('alkohol');
INSERT INTO UZYTKOWNIK (nazwa_spolki,nip,login,haslo)
VALUES ('Przyklad','1234563218','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
--INSERT INTO PRODUKT (nazwa,ilosc,cena,id_kategoria,id_jednostka)
--VALUES ('nazwa_produktu',0,0.0,0,0);