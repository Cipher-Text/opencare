SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medical_information`
--

-- --------------------------------------------------------

--
-- Table structure for table `hospital-types`
--

CREATE TABLE `hospital-types` (
  `id` int(2) NOT NULL,
  `name` varchar(25) NOT NULL,
  `bn_name` varchar(25) NOT NULL,
  `description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hospital-types`
--

insert  into `hospital-types`(`id`,`name`,`bn_name`,`description`) values
(1,'Cancer','ক্যান্সার','description needed'),
(2,'Chest Disease','বক্ষব্যাধি','description needed'),
(3,'College','কলেজ','description needed'),
(4,'Community Clinic','ইউনিয়ন সাব সেন্টার','description needed'),
(5,'Dental','ডেন্টাল','description needed'),
(6,'District','জেলা','description needed'),
(7,'Eye','চোখ','description needed'),
(8,'Infectious Disease','সংক্রামক ব্যাধি','description needed'),
(9,'Kidney','কিডনি','description needed'),
(10,'Leprosy','কুষ্ঠ','description needed'),
(11,'Mental','মানসিক','description needed'),
(12,'Mother and Child','মা ও শিশু','description needed'),
(13,'Specialized','বিশেষায়িত','description needed'),
(14,'Trauma','ট্রমা','description needed'),
(15,'University','বিশ্ববিদ্যালয়','description needed'),
(16,'Upazila','উপজেলা','description needed'),
(17,'Union Subcenter','ইউনিয়ন সাব সেন্টার','description needed');



(1,'Cancer','ক্যান্সার','description needed'),
(2,'Chest Disease','বক্ষব্যাধি ','description needed'),
(3,'College','কলেজ','description needed'),
(4,'Dental','ডেন্টাল','description needed'),
(5,'District','জেলা','description needed'),
(6,'Eye','চোখ','description needed'),
(7,'Infectious Disease','সংক্রামক ব্যাধি','description needed'),
(8,'Kidney','কিডনি','description needed'),
(9,'Leprosy','কুষ্ঠ','description needed'),
(10,'Mental','মানসিক','description needed'),
(11,'Mother and Child','মা ও শিশু ','description needed'),
(12,'Specialized','বিশেষায়িত','description needed'),
(13,'Trauma',' ট্রমা ','description needed'),
(14,'University','বিশ্ববিদ্যালয়','description needed'),
(15,'Upazila','উপজেলা ','description needed');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hospital-types`
--
ALTER TABLE `hospital-types`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hospital-types`
--
ALTER TABLE `hospital-types`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;