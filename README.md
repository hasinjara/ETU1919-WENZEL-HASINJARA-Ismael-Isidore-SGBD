# ETU1919-WENZEL-HASINJARA-Ismael-Isidore-SGBD
--les types possible
	.String
	.double
	.int
	.float
--les conditions possible  = , != , <= , >= , < , > ,like
--Il faut respecter les espaces pour le faire marcher correctement

1 - Creation table
create table nom_table ( String c1,double c2,int c3,float c4 )
warning:il faut respecter les espaces

2 - Insertion donne
insert into nom_table ( test,1,2,3 ) 
warning:il faut respecter les espaces

4 - Modification donne
update nom_table set ( c1 = update,c2 = 0 ) where [ c3 = 1 ]
warning:il faut respecter les espaces

5 - Supression donne
delete from nom_table where [ c3 = 1 ]
delete from nom_table
warning:il faut respecter les espaces

6 - voir tout les tables
show table

7 - decrire une table
describe nom_table 

8 - selection
select * from nom_table
select c1,c2 from nom_table
warning:il faut respecter les espaces

9 - selection avec condition (multiple)
select * from nom_table where [ c1 = test,c2 = 1,c3 = 2 ]
select c1,c2 from nom_table [ c1 like test ]
warning:il faut respecter les espaces

10 - jointure (jointure naturelle multiple) 
jointure naturelle : jointure qui ne s'applique que sur les colonnes qui ont les memes noms
select * from nom_table1 join nom_table2,join nom_table3,...,join nom_tablen
remarque : on peut appliquer les conditions et les projections sur la jointure
warning:il faut respecter les espaces

