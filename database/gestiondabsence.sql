-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 09 fév. 2021 à 19:32
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestiondabsence`
--

-- --------------------------------------------------------

--
-- Structure de la table `absence`
--

DROP TABLE IF EXISTS `absence`;
CREATE TABLE IF NOT EXISTS `absence` (
  `id_absence` int(11) NOT NULL AUTO_INCREMENT,
  `absences` varchar(250) DEFAULT NULL,
  `dateAbsence` date DEFAULT current_timestamp(),
  `justification` varchar(250) DEFAULT 'Non justifiée',
  `id_appr` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_absence`),
  KEY `FK_Association_1` (`id_appr`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `absence`
--

INSERT INTO `absence` (`id_absence`, `absences`, `dateAbsence`, `justification`, `id_appr`) VALUES
(14, 'jour', '2021-02-04', 'justifiée', 'ID121212'),
(15, '', '2021-02-09', 'justifiée', 'B6544333'),
(21, 'Journée', '2021-02-09', 'Non justifiée', 'B8765432');

-- --------------------------------------------------------

--
-- Structure de la table `apprenant`
--

DROP TABLE IF EXISTS `apprenant`;
CREATE TABLE IF NOT EXISTS `apprenant` (
  `cin` varchar(50) NOT NULL,
  `id_sp` int(11) DEFAULT NULL,
  `id_salle` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_prom` int(11) DEFAULT NULL,
  PRIMARY KEY (`cin`),
  KEY `id_sp` (`id_sp`),
  KEY `id_salle` (`id_salle`),
  KEY `id_user` (`id_user`),
  KEY `id_prom` (`id_prom`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `apprenant`
--

INSERT INTO `apprenant` (`cin`, `id_sp`, `id_salle`, `id_user`, `id_prom`) VALUES
('B6544333', 24, 4, 44, 2),
('B8765432', 24, 5, 45, 3),
('ID111111', 15, 4, 52, 3),
('ID121212', 16, 4, 23, 2);

-- --------------------------------------------------------

--
-- Structure de la table `formateur`
--

DROP TABLE IF EXISTS `formateur`;
CREATE TABLE IF NOT EXISTS `formateur` (
  `id_formateur` int(11) NOT NULL AUTO_INCREMENT,
  `id_sp` int(11) DEFAULT NULL,
  `id_salle` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_formateur`),
  KEY `id_sp` (`id_sp`),
  KEY `id_salle` (`id_salle`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `promo`
--

DROP TABLE IF EXISTS `promo`;
CREATE TABLE IF NOT EXISTS `promo` (
  `id_promo` int(11) NOT NULL AUTO_INCREMENT,
  `nomPromo` varchar(250) NOT NULL,
  `anneeDePromo` date DEFAULT NULL,
  PRIMARY KEY (`id_promo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `promo`
--

INSERT INTO `promo` (`id_promo`, `nomPromo`, `anneeDePromo`) VALUES
(2, '1ere année', '2019-01-07'),
(3, 'CUPOFJAVA', '2019-09-09'),
(4, '2éme annees', '2021-02-04');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `id_salle` int(11) NOT NULL AUTO_INCREMENT,
  `nomDeSalle` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_salle`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`id_salle`, `nomDeSalle`) VALUES
(4, 'salle2'),
(5, 'salle3'),
(7, 'Salle 7');

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

DROP TABLE IF EXISTS `specialite`;
CREATE TABLE IF NOT EXISTS `specialite` (
  `id_specialite` int(11) NOT NULL AUTO_INCREMENT,
  `nom_sp` varchar(254) DEFAULT NULL,
  `numbreDeModule` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_specialite`),
  UNIQUE KEY `UC_specialite` (`nom_sp`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `specialite`
--

INSERT INTO `specialite` (`id_specialite`, `nom_sp`, `numbreDeModule`) VALUES
(15, 'C++', 23),
(16, 'FB', 22),
(24, 'java', 32);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `tele` varchar(10) NOT NULL,
  `dateDeNaissance` date NOT NULL,
  `role` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  UNIQUE KEY `Con_email` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `nom`, `prenom`, `password`, `mail`, `tele`, `dateDeNaissance`, `role`) VALUES
(22, 'samir', 'samirr', '$2a$12$2g9tgGY138HWXoibHK6LNOiqC6.qfMX.QIsFopFkouh44iLhGyCEa', 'samir@gmail.com', '0618187535', '1998-02-06', 'Admin'),
(23, 'user', 'user', '$2a$12$4WDimoZFiRvXE/vTrZkBu.ZZF1rxhZK3IlS6Cmafxn43NpY2xtjaG', 'user@gmail.com', '0618187535', '1998-02-06', 'Apprenant'),
(41, 'admin', 'admin', '$2a$12$/bHFqBGvCRLnQc3vv4oQC.f.NqZReOU0UvnNIIBM4dOZygHqEdmBO', 'admin@gmail.com', '0618187535', '1998-02-06', 'Admin'),
(44, 'marzak', 'bouchra', '$2a$12$GOxf.pC9RlfwGesoF4/zVe7R0Zc7/w5B9ROP495fGLnLD6JDksmi6', 'bouchra@gmail.com', '0657789096', '2002-02-15', 'Formateur'),
(45, 'test', 'test', '$2a$12$slFU24GGhfbTsheCTFroeu1Cc5YvIZ3Y0Ls.WR4lxQ7ReZepOSAfW', 'test@gmail.com', '0654356754', '2003-02-08', 'Apprenant'),
(47, 'secr', 'secr', '$2a$12$BF1qqmF2jsrHAOJwqW6i9.w.pIvEtKIcKiIfKf1WKNeRnhjJB9Jya', 'secr@gmal.com', '0617171717', '2000-02-18', 'Secretaire'),
(51, 'test', 'test', '$2a$12$QIcx/WWKgkrn7UD4rxHPqeHFDO2MxbvSuDlDXiALz5jb9GI3BWx..', 'user1@gmail.com', '0654356754', '2003-02-08', 'Apprenant'),
(52, 'test', 'test', '$2a$12$tjPnpyFrejzkTWr.tksLvOCTduBLV6s3XBxxEIpHnQHsgQr/vGSn6', 'appr@gmail.com', '0654356754', '2003-02-08', 'Apprenant');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `FK_Association_1` FOREIGN KEY (`id_appr`) REFERENCES `apprenant` (`cin`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `apprenant`
--
ALTER TABLE `apprenant`
  ADD CONSTRAINT `apprenant_ibfk_1` FOREIGN KEY (`id_sp`) REFERENCES `specialite` (`id_specialite`),
  ADD CONSTRAINT `apprenant_ibfk_2` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id_salle`),
  ADD CONSTRAINT `apprenant_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `apprenant_ibfk_4` FOREIGN KEY (`id_prom`) REFERENCES `promo` (`id_promo`);

--
-- Contraintes pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD CONSTRAINT `formateur_ibfk_1` FOREIGN KEY (`id_sp`) REFERENCES `specialite` (`id_specialite`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `formateur_ibfk_2` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id_salle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `formateur_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
