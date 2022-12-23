--Voeg medewerkers toe in de database
insert into `medewerkers` (gebruikersnaam, naam, wachtwoord, role)
values ('kawal12', 'Kawal Sandhu', '$2a$10$I2j2fA2pYVT5dv4ZnLbm6uDjyCfLgn1jPpNy0spFPbQWE5PuUQsEW', 'ROLE_MONTEUR');

insert into `medewerkers` (gebruikersnaam, naam, wachtwoord, role)
values ('mark12', 'Mark', '$2a$10$I2j2fA2pYVT5dv4ZnLbm6uDjyCfLgn1jPpNy0spFPbQWE5PuUQsEW',
        'ROLE_ADMINISTRATIEFMEDEWERKER');

insert into `medewerkers` (gebruikersnaam, naam, wachtwoord, role)
values ('john12', 'John', '$2a$10$I2j2fA2pYVT5dv4ZnLbm6uDjyCfLgn1jPpNy0spFPbQWE5PuUQsEW', 'ROLE_KASSAMEDEWERKER');




--voeg klanten toe in databases
insert into `klant` (email, klantnummer, naam, tel_nummer)
values ('kawal@mail.nl', '123456', 'Dirk', '0612345678'),
       ('jolly@mail.nl', '654123', 'Jolly', '068787878'),
       ('ghuga@mail.nl', '523641', 'ghuga', '0686789478');

--voeg autos toe in database
insert into `auto` (merk, model, kenteken, km_stand, bouw_jaar, kleur, klant_id)
values ('Audi', 'RS7', '74-056-KP', '152633', '2016', 'Rood', '1'),
       ('Mercedes', 'G63 AMG', '12-0H6-PK', '133652', '2015', 'zwart', '2'),
       ('BMW', 'M5', '74-022-3K', '143652', '2010', 'Blauw', '3');

--voeg onderdelen in database toe
insert into `onderdeel` (naam, prijs, voorraad) values
                                                    ('Uitlaat', '250', '5'),
                                                    ('Achterlicht', '150', '10'),
                                                    ('Gordel', '120', '13');

--voeg handelingen in database toe
insert into `handeling` (naam, prijs) values
                                          ('Uitlaat vervangen', '150'),
                                          ('Achterlicht vervangen', '165'),
                                          ('Auto uitlijnen', '100');

insert into `afspraak`(status, klant_id, auto_id) values
                                            ('GEPLAND', '1','1');