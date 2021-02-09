use gestiondabsence;



CREATE TABLE apprenant (
 cin varchar(50) NOT NULL primary key,
  id_sp int(11),
  id_salle int(11),
  id_user int(11),
  id_prom int(11),
  foreign key(id_sp) references specialite(id_specialite),
  foreign key(id_salle) references salle(id_salle),
  foreign key(id_user) references user(id_user),
  foreign key(id_prom) references promo(id_promo)
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `absence` (
  `id_absence` int(11) NOT NULL,
  `absences` decimal(8,0) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `justification` varchar(250) DEFAULT 'Non justifiée',
  `id_appr` varchar(50) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `absence`
  ADD PRIMARY KEY (`id_absence`),
  ADD KEY `FK_Association_1` (`id_appr`);
  
  ALTER TABLE `absence`
  MODIFY `id_absence` int(11) NOT NULL AUTO_INCREMENT;
  
  ALTER TABLE `absence`
  ADD CONSTRAINT `FK_Association_1` FOREIGN KEY (`id_appr`) REFERENCES `apprenant` (`cin`) ON DELETE CASCADE ON UPDATE CASCADE;
  
  
  
  SELECT cin,nom,prenom,nom, absences ,justification,date
  from apprenant,specialite,absence,user 
  where apprenant.cin=absence.id_appr 
  and apprenant.id_sp=specialite.id_specialite
  and user.id_user=apprenant.id_user 
  GROUP by cin,nom,prenom,nom, absences