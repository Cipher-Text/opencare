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
-- Table structure for table `time-slots`
--

create table `time-slots` (
	`id` int (11) NOT NULL,
	`doctor_workplace_id` int (11) NOT NULL,
	`day_id` int (1) NOT NULL,
	`start_datetime` DATETIME,
    `end_datetime` DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `time-slots`
--

-- insert into `time-slots` (`id`, `doctor_id`, `position`, `speciality_id`, `hospital_id`, `institution_id`) values(1,1,'Consultant',1,1,1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `time-slots`
--

ALTER TABLE `time-slots`
  ADD PRIMARY KEY (`id`),
  ADD KEY `doctor_workplace_id` (`doctor_workplace_id`),
  ADD KEY `day_id` (`day_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `time-slots`
--
ALTER TABLE `time-slots`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `time-slots`
--
ALTER TABLE `time-slots` ADD CONSTRAINT `time-slots_fk_doctor_workplaces` FOREIGN KEY (`doctor_workplace_id`) REFERENCES `doctor-workplaces` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `time-slots` ADD CONSTRAINT `time-slots_fk_days` FOREIGN KEY (`day_id`) REFERENCES `days` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
