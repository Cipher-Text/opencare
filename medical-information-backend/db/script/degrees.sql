-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 24, 2019 at 09:44 AM
-- Server version: 5.7.26-0ubuntu0.18.04.1
-- PHP Version: 7.2.19-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bd_geo_code`
--

-- --------------------------------------------------------

--
-- Table structure for table `degrees`
--

create table `degrees` (
	`id` int (3) NOT NULL,
	`name` varchar (50) NOT NULL,
	`abbreviation` varchar (150) NOT NULL,
	`types` varchar (50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `degrees`
--

insert  into `degrees`(`id`,`name`,`abbreviation`,`types`) values
(1,'DA','Doctor of Arts','Post Graduate'),
(2,'DClinSurg','Doctor of Clinical Surgery','Post Graduate'),
(3,'DCH','Diploma in Child Health','Post Graduate'),
(4,'DCM','Doctor of Clinical Medicine','Post Graduate'),
(5,'DDV','Diploma in Dermatology & Venereology','Graduate'),
(6,'DM','Doctor of Medicine by research MD(Res)','Post Graduate'),
(7,'DMSc','Doctor of Medical Science','Post Graduate'),
(8,'DO','Doctor of Osteopathic Medicine','Graduate'),
(9,'DS','Doctor of Surgery','Post Graduate'),
(10,'FACP','Fellow of the American College of Physicians','Graduate'),
(11,'FCPS','Fellow of College of Physicians and Surgeons','Graduate'),
(12,'FRCP','Fellow of the Royal College of Physicians','Graduate'),
(13,'MBBS','Bachelor of Medicine and a Bachelor of Surgery','Undergraduate'),
(14,'MCAP','Master Level Certified Addiction Professional','Graduate'),
(15,'MCM','Master of Clinical Medicine','Post Graduate'),
(16,'MCPS','Membership of the College of Bangladesh and Surgeons','Graduate'),
(17,'MD','Doctor of Medicine','Graduate'),
(18,'MM','Master of Medicine','Post Graduate'),
(19,'MMSc','Master of Medical Science','Post Graduate'),
(20,'MPhil','Master of Philosophy','Graduate'),
(21,'MPHil','Master of Public Health','Post Graduate'),
(22,'MPhO','Master of Philosophy in Ophthalmology','Post Graduate'),
(23,'MPHO','Master of Public Health and Ophthalmology','Post Graduate'),
(24,'MRCP','Membership of the Royal Colleges of Physicians','Graduate'),
(25,'MRCR','Membership of the Royal College of Radiologists','Graduate'),
(26,'MS','Master of Surgery','Post Graduate'),
(27,'MSc','Master of Science','Graduate'),
(28,'MSc','Master of Science in Medicine or Surgery','Post Graduate'),
(29,'USMLE','United States Medical Licensing Examination','Graduate');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `degrees`
--
ALTER TABLE `degrees`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `degrees`
--
ALTER TABLE `degrees`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `institutions`
--

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

