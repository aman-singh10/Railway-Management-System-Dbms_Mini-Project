-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 07, 2018 at 07:24 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rail`
--

-- --------------------------------------------------------

--
-- Table structure for table `daytable`
--

CREATE TABLE `daytable` (
  `Train_Number` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `passengertable`
--

CREATE TABLE `passengertable` (
  `PNR_Num` int(11) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Age` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `Seat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `passengertable`
--

INSERT INTO `passengertable` (`PNR_Num`, `Name`, `Age`, `Gender`, `Seat`) VALUES
(1, 'mark', '54', 'M', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `routetable`
--

CREATE TABLE `routetable` (
  `Train_Number` varchar(50) DEFAULT NULL,
  `Station_Name` varchar(200) DEFAULT NULL,
  `Arrival` varchar(200) DEFAULT NULL,
  `Departure` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `routetable`
--

INSERT INTO `routetable` (`Train_Number`, `Station_Name`, `Arrival`, `Departure`) VALUES
('234231', 'mumbai', '23:43', '24:00'),
('234231', 'delhi', '20:12', '20:30');

-- --------------------------------------------------------

--
-- Table structure for table `stationtable`
--

CREATE TABLE `stationtable` (
  `Id` int(11) NOT NULL,
  `STATION_CODE` varchar(20) NOT NULL,
  `STATION_NAME` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stationtable`
--

INSERT INTO `stationtable` (`Id`, `STATION_CODE`, `STATION_NAME`) VALUES
(1, '23', 'mumbai'),
(2, '42', 'gulbarga'),
(3, '43', 'delhi');

-- --------------------------------------------------------

--
-- Table structure for table `tickettable`
--

CREATE TABLE `tickettable` (
  `PNR` int(11) NOT NULL,
  `Train_Number` varchar(100) NOT NULL,
  `Class` varchar(50) NOT NULL,
  `Src` varchar(45) NOT NULL,
  `Dest` varchar(45) NOT NULL,
  `TotalFare` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tickettable`
--

INSERT INTO `tickettable` (`PNR`, `Train_Number`, `Class`, `Src`, `Dest`, `TotalFare`) VALUES
(1, '234231', '3A', 'mumbai', 'mumbai', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `traintable`
--

CREATE TABLE `traintable` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Number` varchar(50) NOT NULL,
  `Src` varchar(45) NOT NULL,
  `Dest` varchar(45) NOT NULL,
  `Fare2a` int(11) NOT NULL DEFAULT '0',
  `Fare3a` int(11) NOT NULL DEFAULT '0',
  `FareSL` int(11) NOT NULL DEFAULT '0',
  `Mon` tinyint(4) DEFAULT NULL,
  `Tue` tinyint(4) DEFAULT NULL,
  `Wed` tinyint(4) DEFAULT NULL,
  `Thu` tinyint(4) DEFAULT NULL,
  `Fri` tinyint(4) DEFAULT NULL,
  `Sat` tinyint(4) DEFAULT NULL,
  `Sun` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `traintable`
--

INSERT INTO `traintable` (`Id`, `Name`, `Number`, `Src`, `Dest`, `Fare2a`, `Fare3a`, `FareSL`, `Mon`, `Tue`, `Wed`, `Thu`, `Fri`, `Sat`, `Sun`) VALUES
(1, 'mumbai express', '234231', 'mumbai', 'delhi', 400, 1000, 300, 0, 1, 0, 1, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `daytable`
--
ALTER TABLE `daytable`
  ADD KEY `par_ind` (`Train_Number`);

--
-- Indexes for table `passengertable`
--
ALTER TABLE `passengertable`
  ADD KEY `par_ind` (`PNR_Num`);

--
-- Indexes for table `routetable`
--
ALTER TABLE `routetable`
  ADD KEY `par_ind` (`Train_Number`),
  ADD KEY `par_ind2` (`Station_Name`);

--
-- Indexes for table `stationtable`
--
ALTER TABLE `stationtable`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `STATION_NAME` (`STATION_NAME`);

--
-- Indexes for table `tickettable`
--
ALTER TABLE `tickettable`
  ADD PRIMARY KEY (`PNR`);

--
-- Indexes for table `traintable`
--
ALTER TABLE `traintable`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Number` (`Number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stationtable`
--
ALTER TABLE `stationtable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tickettable`
--
ALTER TABLE `tickettable`
  MODIFY `PNR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `traintable`
--
ALTER TABLE `traintable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `daytable`
--
ALTER TABLE `daytable`
  ADD CONSTRAINT `fk_trainTable` FOREIGN KEY (`Train_Number`) REFERENCES `traintable` (`Number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `passengertable`
--
ALTER TABLE `passengertable`
  ADD CONSTRAINT `fk_ticketTable` FOREIGN KEY (`PNR_Num`) REFERENCES `tickettable` (`PNR`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `routetable`
--
ALTER TABLE `routetable`
  ADD CONSTRAINT `fk_routestationtable` FOREIGN KEY (`Station_Name`) REFERENCES `stationtable` (`STATION_NAME`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_routetrainTable` FOREIGN KEY (`Train_Number`) REFERENCES `traintable` (`Number`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
