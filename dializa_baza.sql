-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Czas generowania: 19 Lut 2020, 17:50
-- Wersja serwera: 5.7.26
-- Wersja PHP: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `dializa_baza`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dane_dializy`
--

DROP TABLE IF EXISTS `dane_dializy`;
CREATE TABLE IF NOT EXISTS `dane_dializy` (
  `pesel` int(11) NOT NULL,
  `cisnienie_krwi` varchar(45) NOT NULL,
  `puls` int(11) NOT NULL,
  PRIMARY KEY (`pesel`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `dane_dializy`
--

INSERT INTO `dane_dializy` (`pesel`, `cisnienie_krwi`, `puls`) VALUES
(128219, 'adasda', 545),
(123456789, '140/80', 40),
(126, '120', 120),
(77778, '1.500000000', 60),
(1290, '819.000000000', 102);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dane_uzytkownikow`
--

DROP TABLE IF EXISTS `dane_uzytkownikow`;
CREATE TABLE IF NOT EXISTS `dane_uzytkownikow` (
  `id` int(45) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `haslo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `dane_uzytkownikow`
--

INSERT INTO `dane_uzytkownikow` (`id`, `login`, `haslo`) VALUES
(1, 'jankowalski', 'kowalskijan'),
(2, 'jakubnowak', 'nowakjakub1');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dni_dializy`
--

DROP TABLE IF EXISTS `dni_dializy`;
CREATE TABLE IF NOT EXISTS `dni_dializy` (
  `id` int(11) NOT NULL,
  `dni` varchar(90) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `dni_dializy`
--

INSERT INTO `dni_dializy` (`id`, `dni`) VALUES
(1, 'poniedzialek-sroda-piatek\r\n');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `imiona`
--

DROP TABLE IF EXISTS `imiona`;
CREATE TABLE IF NOT EXISTS `imiona` (
  `imie` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `imiona`
--

INSERT INTO `imiona` (`imie`) VALUES
('Ada'),
('Adalbert'),
('Adam'),
('Adela'),
('Adelajda'),
('Adrian'),
('Aga'),
('Agata'),
('Agnieszka'),
('Albert'),
('Alberta'),
('Aldona'),
('Aleksander'),
('Aleksandra'),
('Alfred'),
('Alicja'),
('Alina'),
('Amadeusz'),
('Ambroży'),
('Amelia'),
('Anastazja'),
('Anastazy'),
('Anatol'),
('Andrzej'),
('Aneta'),
('Angelika'),
('Angelina'),
('Aniela'),
('Anita'),
('Anna'),
('Antoni'),
('Antonina'),
('Anzelm'),
('Apolinary'),
('Apollo'),
('Apolonia'),
('Apoloniusz'),
('Ariadna'),
('Arkadiusz'),
('Arkady'),
('Arlena'),
('Arleta'),
('Arletta'),
('Arnold'),
('Arnolf'),
('August'),
('Augustyna'),
('Aurela'),
('Aurelia'),
('Aurelian'),
('Aureliusz'),
('Balbina'),
('Baltazar'),
('Barbara'),
('Bartłomiej'),
('Bartosz'),
('Bazyli'),
('Beata'),
('Benedykt'),
('Benedykta'),
('Beniamin'),
('Bernadeta'),
('Bernard'),
('Bernardeta'),
('Bernardyn'),
('Bernardyna'),
('Błażej'),
('Bogdan'),
('Bogdana'),
('Bogna'),
('Bogumił'),
('Bogumiła'),
('Bogusław'),
('Bogusława'),
('Bohdan'),
('Bolesław'),
('Bonawentura'),
('Bożena'),
('Bronisław'),
('Broniszław'),
('Bronisława'),
('Brunon'),
('Brygida'),
('Cecyl'),
('Cecylia'),
('Celestyn'),
('Celestyna'),
('Celina'),
('Cezary'),
('Cyprian'),
('Cyryl'),
('Dalia'),
('Damian'),
('Daniel'),
('Daniela'),
('Danuta'),
('Daria'),
('Dariusz'),
('Dawid'),
('Diana'),
('Dianna'),
('Dobrawa'),
('Dominik'),
('Dominika'),
('Donata'),
('Dorian'),
('Dorota'),
('Dymitr'),
('Edmund'),
('Edward'),
('Edwin'),
('Edyta'),
('Egon'),
('Eleonora'),
('Eliasz'),
('Eligiusz'),
('Eliza'),
('Elwira'),
('Elżbieta'),
('Emanuel'),
('Emanuela'),
('Emil'),
('Emilia'),
('Emilian'),
('Emiliana'),
('Ernest'),
('Ernestyna'),
('Erwin'),
('Erwina'),
('Eryk'),
('Eryka'),
('Eugenia'),
('Eugeniusz'),
('Eulalia'),
('Eustachy'),
('Ewelina'),
('Fabian'),
('Faustyn'),
('Faustyna'),
('Felicja'),
('Felicjan'),
('Felicyta'),
('Feliks'),
('Ferdynand'),
('Filip'),
('Franciszek'),
('Franciszek'),
('Salezy'),
('Franciszka'),
('Fryderyk'),
('Fryderyka'),
('Gabriel'),
('Gabriela'),
('Gaweł'),
('Genowefa'),
('Gerard'),
('Gerarda'),
('Gerhard'),
('Gertruda'),
('Gerwazy'),
('Godfryd'),
('Gracja'),
('Gracjan'),
('Grażyna'),
('Greta'),
('Grzegorz'),
('Gustaw'),
('Gustawa'),
('Gwidon'),
('Halina'),
('Hanna'),
('Helena'),
('Henryk'),
('Henryka'),
('Herbert'),
('Hieronim'),
('Hilary'),
('Hipolit'),
('Honorata'),
('Hubert'),
('Ida'),
('Idalia'),
('Idzi'),
('Iga'),
('Ignacy'),
('Igor'),
('Ildefons'),
('Ilona'),
('Inga'),
('Ingeborga'),
('Irena'),
('Ireneusz'),
('Irma'),
('Irmina'),
('Irwin'),
('Ismena'),
('Iwo'),
('Iwona'),
('Izabela'),
('Izolda'),
('Izyda'),
('Izydor'),
('Jacek'),
('Jadwiga'),
('Jagoda'),
('Jakub'),
(''),
('Jan'),
(''),
('Janina'),
('January'),
('Janusz'),
('Jarema'),
('Jarogniew'),
('Jaromir'),
('Jarosław'),
('Jarosława'),
('Jeremi'),
('Jeremiasz'),
('Jerzy'),
('Jędrzej'),
('Joachim'),
('Joanna'),
('Jolanta'),
('Jonasz'),
('Jonatan'),
('Jowita'),
('Józef'),
('Józefa'),
('Józefina'),
('Judyta'),
('Julian'),
('Julianna'),
('Julita'),
('Juliusz'),
('Justyn'),
('Justyna'),
('Kacper'),
('Kaja'),
('Kajetan'),
('Kalina'),
('Kamil'),
('Kamila'),
('Karina'),
('Karol'),
('Karolina'),
('Kacper'),
('Kasper'),
('Katarzyna'),
('Kazimiera'),
('Kazimierz'),
('Kinga'),
('Klara'),
('Klarysa'),
('Klaudia'),
('Klaudiusz'),
('Klaudyna'),
('Klemens'),
('Klementyn'),
('Klementyna'),
('Kleopatra'),
('Klotylda'),
('Konrad'),
('Konrada'),
('Konstancja'),
('Konstanty'),
(''),
('Konstantyn'),
('Kordelia'),
('Kordian'),
('Kordula'),
('Kornel'),
('Kornelia'),
('Kryspin'),
('Krystian'),
('Krystyn'),
('Krystyna'),
('Krzysztof'),
('Ksenia'),
('Kunegunda'),
('Laura'),
('Laurenty'),
('Laurentyn'),
('Laurentyna'),
('Lech'),
('Lechosław'),
('Lechosława'),
('Leokadia'),
('Leon'),
('Leonard'),
('Leonarda'),
('Leonia'),
('Leopold'),
('Leopoldyna'),
('Lesław'),
('Lesława'),
('Leszek'),
('Lidia'),
('Ligia'),
('Lilian'),
('Liliana'),
('Lilianna'),
('Lilla'),
('Liwia'),
('Liwiusz'),
('Liza'),
('Lolita'),
('Longin'),
('Loretta'),
('Luba'),
('Lubomir'),
('Lubomira'),
('Lucja'),
('Lucjan'),
('Lucjusz'),
('Lucyna'),
('Ludmiła'),
('Ludomił'),
('Ludomir'),
('Ludosław'),
('Ludwik'),
('Ludwika'),
('Ludwina'),
('Luiza'),
('Lukrecja'),
('Lutosław'),
('Łucja'),
('Łucjan'),
('Łukasz'),
('Maciej'),
('Madlena'),
('Magda'),
('Magdalena'),
(''),
('Makary'),
('Maksym'),
('Maksymilian'),
('Malina'),
('Malwin'),
('Malwina'),
('Małgorzata'),
('Manfred'),
('Manfreda'),
('Manuela'),
('Marcel'),
('Marcela'),
('Marceli'),
('Marcelina'),
('Marcin'),
('Marcjan'),
('Marcjanna'),
('Marcjusz'),
('Marek'),
('Margareta'),
('Maria'),
('MariaMagdalena'),
('Marian'),
('Marianna'),
('Marietta'),
('Marina'),
('Mariola'),
('Mariusz'),
('Marlena'),
('Marta'),
('Martyna'),
('Maryla'),
('Maryna'),
('Marzanna'),
('Marzena'),
('Mateusz'),
('Matylda'),
('Maurycy'),
('Melania'),
('Melchior'),
('Metody'),
('Michalina'),
('Michał'),
('Mieczysław'),
('Mieczysława'),
('Mieszko'),
('Mikołaj'),
('Milena'),
('Miła'),
('Miłosz'),
('Miłowan'),
('Miłowit'),
('Mira'),
('Mirabella'),
('Mirella'),
('Miron'),
('Mirosław'),
('Mirosława'),
('Modest'),
('Monika'),
('Nadia'),
('Nadzieja'),
('Napoleon'),
('Narcyz'),
('Narcyza'),
('Nastazja'),
('Natalia'),
('Natasza'),
('Nikita'),
('Nikodem'),
('Nina'),
('Nora'),
('Norbert'),
('Norberta'),
('Norma'),
('Norman'),
('Oda'),
('Odila'),
('Odon'),
('Ofelia'),
('Oksana'),
('Oktawia'),
('Oktawian'),
('Olaf'),
('Oleg'),
('Olga'),
('Olgierd'),
('Olimpia'),
('Oliwia'),
('Oliwier'),
('Onufry'),
('Orfeusz'),
('Oskar'),
('Otto'),
('Otylia'),
('Pankracy'),
('Parys'),
('Patrycja'),
('Patrycy'),
('Patryk'),
('Paula'),
('Paulina'),
('Paweł'),
('Pelagia'),
('Petronela'),
('Petronia'),
('Petroniusz'),
('Piotr'),
('Pola'),
('Polikarp'),
('Protazy'),
('Przemysław'),
('Radomił'),
('Radomiła'),
('Radomir'),
('Radosław'),
('Radosława'),
('Radzimir'),
('Rafael'),
('Rafaela'),
('Rafał'),
('Rajmund'),
('Rajmunda'),
('Rajnold'),
('Rebeka'),
('Regina'),
('Remigiusz'),
('Rena'),
('Renata'),
('Robert'),
('Roberta'),
('Roch'),
('Roderyk'),
('Rodryg'),
('Rodryk'),
('Roger'),
('Roksana'),
('Roland'),
('Roma'),
('Roman'),
('Romana'),
('Romeo'),
('Romuald'),
('Rozalia'),
('Rozanna'),
('Róża'),
('Rudolf'),
('Rudolfa'),
('Rudolfina'),
('Rufin'),
('Rupert'),
('Ryszard'),
('Ryszarda'),
('Sabina'),
('Salomea'),
('Salomon'),
('Samuel'),
('Samuela'),
('Sandra'),
('Sara'),
('Sawa'),
('Sebastian'),
('Serafin'),
('Sergiusz'),
('Sewer'),
('Seweryn'),
('Seweryna'),
('Sędzisław'),
('Sędziwoj'),
('Siemowit'),
('Sława'),
('Sławomir'),
('Sławomira'),
('Sławosz'),
('Sobiesław'),
('Sobiesława'),
('Sofia'),
('Sonia'),
('Stanisław'),
('Stanisława'),
('Stefan'),
('Stefania'),
('Sulimiera'),
('Sulimierz'),
('Sulimir'),
('Sydonia'),
('Sykstus'),
('Sylwan'),
('Sylwana'),
('Sylwester'),
('Sylwia'),
('Sylwiusz'),
('Symeon'),
('Szczepan'),
('Szczęsna'),
('Szczęsny'),
('Szymon'),
('Ścibor'),
('Świętopełk'),
('Tadeusz'),
('Tamara'),
('Tatiana'),
('Tekla'),
('Telimena'),
('Teodor'),
('Teodora'),
('Teodozja'),
('Teodozjusz'),
('Teofil'),
('Teofila'),
('Teresa'),
('Tobiasz'),
('Toma'),
('Tomasz'),
('Tristan'),
('Trojan'),
('Tycjan'),
('Tymon'),
('Tymoteusz'),
('Tytus'),
('Unisław'),
('Ursyn'),
('Urszula'),
('Violetta'),
('Wacław'),
('Wacława'),
('Waldemar'),
('Walenty'),
('Walentyna'),
('Waleria'),
('Walerian'),
('Waleriana'),
('Walery'),
('Walter'),
('Wanda'),
('Wasyl'),
('Wawrzyniec'),
('Wera'),
('Werner'),
('Weronika'),
('Wieńczysła'),
('Wiesław'),
('Wiesława'),
('Wiktor'),
('Wiktoria'),
('Wilhelm'),
('Wilhelmina'),
('Wilma'),
('Wincenta'),
('Wincenty'),
('Wińczysła'),
('Wiola'),
('Wioletta'),
('Wirgiliusz'),
('Wirginia'),
('Wirginiusz'),
('Wisław'),
('Wisława'),
('Wit'),
('Witalis'),
('Witold'),
('Witolda'),
('Witołd'),
('Witomir'),
('Wiwanna'),
('Władysława'),
('Władysław'),
('Włodzimierz'),
('Włodzimir'),
('Wodzisław'),
('Wojciech'),
('Wojciecha'),
('Zachariasz'),
('Zbigniew'),
('Zbysław'),
('Zbyszko'),
('Zdobysław'),
('Zdzisław'),
('Zdzisława'),
('Zenobia'),
('Zenobiusz'),
('Zenon'),
('Zenona'),
('Ziemowit'),
('Zofia'),
('Zula'),
('Zuzanna'),
('Zygfryd'),
('Zygmunt'),
('Zyta'),
('Żaklina'),
('Żaneta'),
('Żanna'),
('Żelisław'),
('Żytomir');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `miejsce_wklucia`
--

DROP TABLE IF EXISTS `miejsce_wklucia`;
CREATE TABLE IF NOT EXISTS `miejsce_wklucia` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `miejsce_wklucia`
--

INSERT INTO `miejsce_wklucia` (`id`, `nazwa`) VALUES
(1, 'zyla udowa lewa');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `opisy`
--

DROP TABLE IF EXISTS `opisy`;
CREATE TABLE IF NOT EXISTS `opisy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opis` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `opisy`
--

INSERT INTO `opisy` (`id`, `opis`) VALUES
(1, 'Ok');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pacjenci`
--

DROP TABLE IF EXISTS `pacjenci`;
CREATE TABLE IF NOT EXISTS `pacjenci` (
  `PESEL` bigint(11) NOT NULL AUTO_INCREMENT,
  `Imie` varchar(45) NOT NULL,
  `Nazwisko` varchar(45) NOT NULL,
  `Telefon_pacjenta` int(11) NOT NULL,
  `Telefon_dodatkowy` int(11) NOT NULL,
  `Dni_dializy` int(11) NOT NULL,
  `Zmiana` int(11) NOT NULL,
  `ID_stacji` int(11) DEFAULT NULL,
  `Rodzaj_dostepu` int(11) NOT NULL,
  `Miejsce_wklucia` int(11) NOT NULL,
  PRIMARY KEY (`PESEL`)
) ENGINE=MyISAM AUTO_INCREMENT=98765432124 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `pacjenci`
--

INSERT INTO `pacjenci` (`PESEL`, `Imie`, `Nazwisko`, `Telefon_pacjenta`, `Telefon_dodatkowy`, `Dni_dializy`, `Zmiana`, `ID_stacji`, `Rodzaj_dostepu`, `Miejsce_wklucia`) VALUES
(12345678912, 'Marek', 'Kowalski', 647389283, 847593827, 1, 1, 1, 1, 1),
(18127186261, 'janusz', 'kowalski', 0, 0, 1, 1, 1, 1, 1),
(72878173816, 'Jan', 'Kowalski', 0, 0, 1, 1, 1, 1, 1),
(98765432123, 'Maciej', 'Holub', 123456789, 0, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `parametry_dializy`
--

DROP TABLE IF EXISTS `parametry_dializy`;
CREATE TABLE IF NOT EXISTS `parametry_dializy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PESEL` bigint(19) NOT NULL,
  `sucha_waga` double NOT NULL,
  `heparyna_bolus` double DEFAULT NULL,
  `heparyna_pompa` double DEFAULT NULL,
  `heparyna_stop` double DEFAULT NULL,
  `CLEXANE_dawka` double DEFAULT NULL,
  `K` double DEFAULT NULL,
  `Ca` double DEFAULT NULL,
  `Na` double DEFAULT NULL,
  `rodzaj_dializatora` int(11) DEFAULT NULL,
  `data` date NOT NULL,
  `glukoza` double DEFAULT NULL,
  `epo` double DEFAULT NULL,
  `fe` double DEFAULT NULL,
  `numer_koncentratu` double DEFAULT NULL,
  `numer_stanowiska` int(11) DEFAULT NULL,
  `numer_aparatu` int(11) DEFAULT NULL,
  `uf` double DEFAULT NULL,
  `czas` double DEFAULT NULL,
  `rodzaj_dializy` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przebieg_dializy`
--

DROP TABLE IF EXISTS `przebieg_dializy`;
CREATE TABLE IF NOT EXISTS `przebieg_dializy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `godzina` timestamp NULL DEFAULT NULL,
  `PESEL` bigint(20) DEFAULT NULL,
  `RR` double DEFAULT NULL,
  `puls` double DEFAULT NULL,
  `uf` double DEFAULT NULL,
  `cisnienie_tetnicze` double NOT NULL,
  `cisnienie_zylne` double NOT NULL,
  `tmp` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `przebieg_dializy`
--

INSERT INTO `przebieg_dializy` (`id`, `data`, `godzina`, `PESEL`, `RR`, `puls`, `uf`, `cisnienie_tetnicze`, `cisnienie_zylne`, `tmp`) VALUES
(18, '2019-07-24', '2019-07-24 10:10:28', 11111111111, 1.1, 120, 12, 200.7, 250, 100.64);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rodzaje_dializ`
--

DROP TABLE IF EXISTS `rodzaje_dializ`;
CREATE TABLE IF NOT EXISTS `rodzaje_dializ` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rodzaj` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `rodzaje_dializ`
--

INSERT INTO `rodzaje_dializ` (`id`, `rodzaj`) VALUES
(1, 'HD'),
(2, 'HDF'),
(3, 'HF');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rodzaj_dializatora`
--

DROP TABLE IF EXISTS `rodzaj_dializatora`;
CREATE TABLE IF NOT EXISTS `rodzaj_dializatora` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `rodzaj_dializatora`
--

INSERT INTO `rodzaj_dializatora` (`id`, `nazwa`) VALUES
(1, 'cewkowy');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rodzaj_dostepu`
--

DROP TABLE IF EXISTS `rodzaj_dostepu`;
CREATE TABLE IF NOT EXISTS `rodzaj_dostepu` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `rodzaj_dostepu`
--

INSERT INTO `rodzaj_dostepu` (`id`, `nazwa`) VALUES
(1, 'cewnik');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stacje_dializ`
--

DROP TABLE IF EXISTS `stacje_dializ`;
CREATE TABLE IF NOT EXISTS `stacje_dializ` (
  `id_stacji` int(11) NOT NULL,
  `nazwa` varchar(90) NOT NULL,
  `wojewodztwo` varchar(45) NOT NULL,
  `miasto` varchar(45) NOT NULL,
  `ulica` varchar(45) NOT NULL,
  `telefon` int(11) NOT NULL,
  PRIMARY KEY (`id_stacji`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `stacje_dializ`
--

INSERT INTO `stacje_dializ` (`id_stacji`, `nazwa`, `wojewodztwo`, `miasto`, `ulica`, `telefon`) VALUES
(1, 'sdfasdfs', 'dfsdf', 'sdfsd', 'fsdfs', 34535);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zakonczenie_dializy`
--

DROP TABLE IF EXISTS `zakonczenie_dializy`;
CREATE TABLE IF NOT EXISTS `zakonczenie_dializy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PESEL` bigint(20) DEFAULT NULL,
  `waga` double DEFAULT NULL,
  `objetosc_krwi` double DEFAULT NULL,
  `objetosc_substytucji` double DEFAULT NULL,
  `czas_zakonczenia` time DEFAULT NULL,
  `data` date DEFAULT NULL,
  `opis_lekarz` varchar(200) DEFAULT NULL,
  `opis_pielegniarka` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zmiany`
--

DROP TABLE IF EXISTS `zmiany`;
CREATE TABLE IF NOT EXISTS `zmiany` (
  `id` int(11) NOT NULL,
  `zmiana` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `zmiany`
--

INSERT INTO `zmiany` (`id`, `zmiana`) VALUES
(1, 'rano');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
