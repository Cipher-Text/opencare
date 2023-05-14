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
-- Table structure for table `specialties`
--

create table `specialties` (
	`id` int (6)  NOT NULL,
	`name` varchar (150)  NOT NULL,
	`parent_id` int (6)  NOT NULL,
	`description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `specialties`
--

insert  into `specialties`(`id`,`name`,`parent_id`,`description`) values
(1,'Allergy and immunology',0,'description needed'),
(2,'Anesthesiology',0,'description needed'),
(3,'Dermatology',0,'description needed'),
(4,'Diagnostic radiology',0,'description needed'),
(5,'Emergency medicine',0,'description needed'),
(6,'Family medicine',0,'description needed'),
(7,'Internal medicine',0,'description needed'),
(8,'Medical genetics',0,'description needed'),
(9,'Neurology',0,'description needed'),
(10,'Nuclear medicine',0,'description needed'),
(11,'Obstetrics and gynecology',0,'description needed'),
(12,'Ophthalmology',0,'description needed'),
(13,'Pathology',0,'description needed'),
(14,'Pediatrics',0,'description needed'),
(15,'Physical medicine and rehabilitation',0,'description needed'),
(16,'Preventive medicine',0,'description needed'),
(17,'Psychiatry',0,'description needed'),
(18,'Radiation oncology',0,'description needed'),
(19,'Surgery',0,'description needed'),
(20,'Urology',0,'description needed'),
(21,'Critical care medicine',2,'description needed'),
(22,'Hospice and palliative care',2,'description needed'),
(23,'Pain medicine',2,'description needed'),
(24,'Pediatric anesthesiology',2,'description needed'),
(25,'Sleep medicine',2,'description needed'),
(26,'Dermatopathology',3,'description needed'),
(27,'Pediatric dermatology',3,'description needed'),
(28,'Procedural dermatology',3,'description needed'),
(29,'Abdominal radiology',4,'description needed'),
(30,'Breast imaging',4,'description needed'),
(31,'Cardiothoracic radiology',4,'description needed'),
(32,'Cardiovascular radiology',4,'description needed'),
(33,'Chest radiology',4,'description needed'),
(34,'Emergency radiology',4,'description needed'),
(35,'Endovascular surgical neuroradiology',4,'description needed'),
(36,'Gastrointestinal radiology',4,'description needed'),
(37,'Genitourinary radiology',4,'description needed'),
(38,'Head and neck radiology',4,'description needed'),
(39,'Interventional radiology',4,'description needed'),
(40,'Musculoskeletal radiology',4,'description needed'),
(41,'Neuroradiology',4,'description needed'),
(42,'Nuclear radiology',4,'description needed'),
(43,'Pediatric radiology',4,'description needed'),
(44,'Radiation oncology',4,'description needed'),
(45,'Vascular and interventional radiology',4,'description needed'),
(46,'Anesthesiology critical care medicine',5,'description needed'),
(47,'Emergency medical services',5,'description needed'),
(48,'Hospice and palliative medicine',5,'description needed'),
(49,'Internal medicine / Critical care medicine',5,'description needed'),
(50,'Medical toxicology',5,'description needed'),
(51,'Pain medicine',5,'description needed'),
(52,'Pediatric emergency medicine',5,'description needed'),
(53,'Sports medicine',5,'description needed'),
(54,'Undersea and hyperbaric medicine',5,'description needed'),
(55,'Adolescent medicine',6,'description needed'),
(56,'Geriatric medicine',6,'description needed'),
(57,'Hospice and palliative medicine',6,'description needed'),
(58,'Geriatric medicine',6,'description needed'),
(59,'Sleep medicine',6,'description needed'),
(60,'Sports medicine',6,'description needed'),
(61,'Advanced heart failure and transplant cardiology',7,'description needed'),
(62,'Cardiovascular disease',7,'description needed'),
(63,'Clinical cardiac electrophysiology',7,'description needed'),
(64,'Critical care medicine',7,'description needed'),
(65,'Endocrinology, diabetes, and metabolism',7,'description needed'),
(66,'Gastroenterology',7,'description needed'),
(67,'Geriatric medicine',7,'description needed'),
(68,'Hematology',7,'description needed'),
(69,'Hematology and oncology',7,'description needed'),
(70,'Infectious disease',7,'description needed'),
(71,'Internal medicine',7,'description needed'),
(72,'Interventional cardiology',7,'description needed'),
(73,'Nephrology',7,'description needed'),
(74,'Oncology',7,'description needed'),
(75,'Pediatric internal medicine',7,'description needed'),
(76,'Pulmonary disease',7,'description needed'),
(77,'Pulmonary disease and critical care medicine',7,'description needed'),
(78,'Rheumatology',7,'description needed'),
(79,'Sleep medicine',7,'description needed'),
(80,'Sports medicine',7,'description needed'),
(81,'Transplant hepatology',7,'description needed'),
(82,'Biochemical genetics',8,'description needed'),
(83,'Clinical cytogenetics',8,'description needed'),
(84,'Clinical genetics',8,'description needed'),
(85,'Molecular genetic pathology',8,'description needed'),
(86,'Brain injury medicine',9,'description needed'),
(87,'Child neurology',9,'description needed'),
(88,'Clinical neurophysiology',9,'description needed'),
(89,'Endovascular surgical neuroradiology',9,'description needed'),
(90,'Hospice and palliative medicine',9,'description needed'),
(91,'Neurodevelopmental disabilities',9,'description needed'),
(92,'Neuromuscular medicine',9,'description needed'),
(93,'Pain medicine',9,'description needed'),
(94,'Sleep medicine',9,'description needed'),
(95,'Vascular neurology',9,'description needed'),
(96,'Female pelvic medicine and reconstructive surgery',11,'description needed'),
(97,'Gynecologic oncology',11,'description needed'),
(98,'Maternal-fetal medicine',11,'description needed'),
(99,'Reproductive endocrinologists and infertility',11,'description needed'),
(100,'Anterior segment/cornea ophthalmology',12,'description needed'),
(101,'Glaucoma ophthalmology',12,'description needed'),
(102,'Neuro-ophthalmology',12,'description needed'),
(103,'Ocular oncology',12,'description needed'),
(104,'Oculoplastics/orbit',12,'description needed'),
(105,'Ophthalmic Plastic & Reconstructive Surgery',12,'description needed'),
(106,'Retina/uveitis',12,'description needed'),
(107,'Strabismus/pediatric ophthalmology',12,'description needed'),
(108,'Anatomical pathology',13,'description needed'),
(109,'Blood banking and transfusion medicine',13,'description needed'),
(110,'Chemical pathology',13,'description needed'),
(111,'Clinical pathology',13,'description needed'),
(112,'Cytopathology',13,'description needed'),
(113,'Forensic pathology',13,'description needed'),
(114,'Genetic pathology',13,'description needed'),
(115,'Hematology',13,'description needed'),
(116,'Immunopathology',13,'description needed'),
(117,'Medical microbiology',13,'description needed'),
(118,'Molecular pathology',13,'description needed'),
(119,'Neuropathology',13,'description needed'),
(120,'Pediatric pathology',13,'description needed'),
(121,'Adolescent medicine',14,'description needed'),
(122,'Child abuse pediatrics',14,'description needed'),
(123,'Developmental-behavioral pediatrics',14,'description needed'),
(124,'Neonatal-perinatal medicine',14,'description needed'),
(125,'Pediatric cardiology',14,'description needed'),
(126,'Pediatric critical care medicine',14,'description needed'),
(127,'Pediatric endocrinology',14,'description needed'),
(128,'Pediatric gastroenterology',14,'description needed'),
(129,'Pediatric hematology-oncology',14,'description needed'),
(130,'Pediatric infectious diseases',14,'description needed'),
(131,'Pediatric nephrology',14,'description needed'),
(132,'Pediatric pulmonology',14,'description needed'),
(133,'Pediatric rheumatology',14,'description needed'),
(134,'Pediatric sports medicine',14,'description needed'),
(135,'Pediatric transplant hepatology',14,'description needed'),
(136,'Brain injury medicine',15,'description needed'),
(137,'Hospice and palliative medicine',15,'description needed'),
(138,'Neuromuscular medicine',15,'description needed'),
(139,'Pain medicine',15,'description needed'),
(140,'Pediatric rehabilitation medicine',15,'description needed'),
(141,'Spinal cord injury medicine',15,'description needed'),
(142,'Sports medicine',15,'description needed'),
(143,'Aerospace medicine',16,'description needed'),
(144,'Medical toxicology',16,'description needed'),
(145,'Occupational medicine',16,'description needed'),
(146,'Public health medicine',16,'description needed'),
(147,'Addiction psychiatry',17,'description needed'),
(148,'Administrative psychiatry',17,'description needed'),
(149,'Child and adolescent psychiatry',17,'description needed'),
(150,'Community psychiatry',17,'description needed'),
(151,'Consultation/liaison psychiatry',17,'description needed'),
(152,'Emergency psychiatry',17,'description needed'),
(153,'Forensic psychiatry',17,'description needed'),
(154,'Geriatric psychiatry',17,'description needed'),
(155,'Mental retardation psychiatry',17,'description needed'),
(156,'Military psychiatry',17,'description needed'),
(157,'Pain medicine',17,'description needed'),
(158,'Psychiatric research',17,'description needed'),
(159,'Psychosomatic medicine',17,'description needed'),
(160,'Hospice and palliative medicine',18,'description needed'),
(161,'Pain medicine',18,'description needed'),
(162,'Colon and rectal surgery',19,'description needed'),
(163,'General surgery',19,'description needed'),
(164,'Gynecologic oncology',19,'description needed'),
(165,'Plastic surgery',19,'description needed'),
(166,'Neurological surgery',19,'description needed'),
(167,'Ophthalmic surgery',19,'description needed'),
(168,'Oral and maxillofacial surgery',19,'description needed'),
(169,'Orthopaedic surgery',19,'description needed'),
(170,'Otolaryngology',19,'description needed'),
(171,'Otology neurotology',19,'description needed'),
(172,'Pediatric surgery',19,'description needed'),
(173,'Surgical Intensivists, specializing in critical care patients',19,'description needed'),
(174,'Thoracic Surgery',19,'description needed'),
(175,'Vascular surgery',19,'description needed'),
(176,'Pediatric urology',20,'description needed'),
(177,'Urologic oncology',20,'description needed'),
(178,'Renal transplant',20,'description needed'),
(179,'Male infertility',20,'description needed'),
(180,'Calculi',20,'description needed'),
(181,'Female urology',20,'description needed'),
(182,'Neurourology',20,'description needed'),
(183,'Surgical critical care',163,'description needed'),
(184,'Craniofacial surgery',165,'description needed'),
(185,'Hand surgery',165,'description needed'),
(186,'Endovascular surgical neuroradiology',166,'description needed'),
(187,'Adult reconstructive orthopaedics',169,'description needed'),
(188,'Foot and ankle orthopaedics',169,'description needed'),
(189,'Musculoskeletal oncology',169,'description needed'),
(190,'Orthopaedic sports medicine',169,'description needed'),
(191,'Orthopaedic surgery of the spine',169,'description needed'),
(192,'Prenatal',172,'description needed'),
(193,'Trauma',172,'description needed'),
(194,'Pediatric oncology',172,'description needed'),
(195,'Congenital cardiac surgery',174,'description needed'),
(196,'Thoracic surgery-integrated',174,'description needed'),
(206,'Orthopaedic trauma',169,'description needed'),
(207,'Pediatric orthopaedics',169,'description needed'),
(208,'Pediatric otolaryngology',170,'description needed'),
(209,'Neonatal',172,'description needed');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `specialties`
--
ALTER TABLE `specialties`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `specialties`
--
ALTER TABLE `specialties`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=210;

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

