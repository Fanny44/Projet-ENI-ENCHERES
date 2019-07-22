USE ENCHERES
go

INSERT into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) 
values('bobo','bob','boby','boby@gmail.com','0625364578','2 impasse eni', '44800', 'st herblain', 'bobyboby',500, 0); 

Select * From UTILISATEURS; 