create database eleiçao;
use eleiçao;
create table presidente(
num_partido integer primary key not null,
nome varchar(20),
partido varchar(35),
vice_nome varchar(20),
votos int(100));
create table governador(
num_partido integer primary key not null,
nome varchar(20),
partido varchar(35),
vice_nome varchar (20),
votos int(100));
insert into presidente values
(15
,'TOM'
,'PARTIDO DO DESENHO AMERICANO'
,'JERRY'
, 0);
insert into presidente values
(30
,'SHIROU EMIYA'
,'PARTIDO DA ANIMAÇÃO JAPONESA'
,'KOTAROU TENNOUJI'
, 0);

insert into presidente values
(2
, 'VOTO BRANCO'
, 'NENHUM'
, 'VOTO BRANCO'
, 0);

insert into presidente values (
1
, 'VOTO NULO'
, 'NÚMERO ERRADO'
, 'VOTO NULO'
, 0);

insert into governador values
(2
, 'VOTO BRANCO'
, 'NENHUM'
, 'VOTO BRANCO'
, 0);

insert into governador values (
1
, 'VOTO NULO'
, 'NÚMERO ERRADO'
, 'VOTO NULO'
, 0);

insert into governador values
(13
,'IRMÃO DO JOREL'
,'PARTIDO ANIMADO BRASILEIRO'
,'JOREL'
, 0);
insert into governador values
(16
,'SUPERMAN'
,'PARTIDO DAS ADAPTAÇÕES'
,'BATMAN'
, 0);
select * from presidente;
select * from governador;