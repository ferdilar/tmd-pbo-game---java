create database tubes_pbo;

use tubes_pbo;

create table tskor(
id_skor int primary key auto_increment,
username varchar(10) unique,
skor int
);

create table tkata(
id_kata int primary key auto_increment,
hurufDepan char(1),
kata varchar(20)
);

insert into tskor value(null,'edi',1);
insert into tskor value(null,'rosa',3);
insert into tskor value(null,'eka',2);
insert into tskor value(null,'budi',4);


insert into tkata value(null,'P','Permainan');
insert into tkata value(null,'S','Saya');
insert into tkata value(null,'S','Semangat');
insert into tkata value(null,'M','Masa');
insert into tkata value(null,'D','Depan');
insert into tkata value(null,'C','Cerah');