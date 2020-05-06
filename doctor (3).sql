-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 06, 2020 at 03:38 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doctor`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
CREATE TABLE IF NOT EXISTS `doctors` (
  `doctorID` int(11) NOT NULL AUTO_INCREMENT,
  `doctorName` varchar(200) NOT NULL,
  `doctorPhone` varchar(200) NOT NULL,
  `doctorSpecialty` varchar(200) NOT NULL,
  `doctorExperience` varchar(200) NOT NULL,
  PRIMARY KEY (`doctorID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`doctorID`, `doctorName`, `doctorPhone`, `doctorSpecialty`, `doctorExperience`) VALUES
(2, 'Dr (Mrs) Chandini Perera', '11887768', 'Plastic Surgeons', 'ten years'),
(1, 'Prof. Anoja Abeyjeewa', '11987633', 'Anaesthetists', 'five years'),
(3, 'Dr.Sunethra Senanayake', '11343435', 'Neurologists', 'three years'),
(5, 'Dr.G.C.A.U.Patabedige', '11232323', 'Microbiologist', 'two years');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
