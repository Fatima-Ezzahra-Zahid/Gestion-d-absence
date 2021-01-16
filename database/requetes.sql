

create database gestiondabsence;
use gestiondabsence;


CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `tele` varchar(10) NOT NULL,
  `dateDeNaissance` date NOT NULL,
  `role` varchar(250)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `id_user_UNIQUE` (`id_user`);
  
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;
  
  
  
    CREATE TABLE `specialite` (
  `id_specialite` int(11) NOT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `numbreDeModule` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `specialite`
  ADD PRIMARY KEY (`id_specialite`);
  
ALTER TABLE `specialite`
  MODIFY `id_specialite` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE `salle` (
  `id_salle` int(11) NOT NULL,
  `nomDeSalle` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `salle`
  ADD PRIMARY KEY (`id_salle`);
  

ALTER TABLE `salle`
  MODIFY `id_salle` int(11) NOT NULL AUTO_INCREMENT;


CREATE TABLE `promo` (
  `id_promo` int(11) NOT NULL,
  `anneeDePromo` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `promo`
  ADD PRIMARY KEY (`id_promo`);
  
  ALTER TABLE `promo`
  MODIFY `id_promo` int(11) NOT NULL AUTO_INCREMENT;

CREATE TABLE apprenant (
  id_apprenant int(11) NOT NULL primary key auto_increment,
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
  `id_appr` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `absence`
  ADD PRIMARY KEY (`id_absence`),
  ADD KEY `FK_Association_1` (`id_appr`);
  
  ALTER TABLE `absence`
  MODIFY `id_absence` int(11) NOT NULL AUTO_INCREMENT;
  
  ALTER TABLE `absence`
  ADD CONSTRAINT `FK_Association_1` FOREIGN KEY (`id_appr`) REFERENCES `apprenant` (`id_apprenant`) ON DELETE CASCADE ON UPDATE CASCADE;
  

  


CREATE TABLE `formateur` (
  `id_formateur` int(11) NOT NULL primary key auto_increment,
  id_sp int(11),
  id_salle int(11),
  id_user int(11),
  foreign key(id_sp) references specialite(id_specialite) ON DELETE CASCADE ON UPDATE CASCADE,
  foreign key(id_salle) references salle(id_salle) ON DELETE CASCADE ON UPDATE CASCADE,
  foreign key(id_user) references user(id_user) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




























ALTER TABLE `secretaire`
  ADD PRIMARY KEY (`id_secretaire`);






ALTER TABLE `promo`
  MODIFY `id_promo` int(11) NOT NULL AUTO_INCREMENT;








ALTER TABLE `specialite`
  MODIFY `id_specialite` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `promo`
  ADD CONSTRAINT `FK_Association_3` FOREIGN KEY (`id_appr`) REFERENCES `apprenant` (`id_apprenant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `salle`
--
ALTER TABLE `salle`
  ADD CONSTRAINT `FK_Association_2` FOREIGN KEY (`id_forma`) REFERENCES `formateur` (`id_formateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `specialite`
--
ALTER TABLE `specialite`
  ADD CONSTRAINT `FK_Association_4` FOREIGN KEY (`id_appr`) REFERENCES `apprenant` (`id_apprenant`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
