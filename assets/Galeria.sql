-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 08, 2020 at 08:47 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Galeria`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_liked_notification` (IN `IID` VARCHAR(40), IN `UID` VARCHAR(40))  INSERT INTO Notification (Notification.UID, Notification.code, Notification.payload, Notification.message) 
SELECT
(SELECT i.UID from Image i WHERE i.IID = IID) as uid,
'IMAGE_LIKED',
IID,
(SELECT CONCAT(CONCAT(u.first_name, CONCAT(' ',u.last_name)),' likes an image you uploaded') from User u WHERE u.UID = UID) as message$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `create_sales_notification` (IN `IID` VARCHAR(40), IN `UID` VARCHAR(40))  INSERT INTO Notification (Notification.UID, Notification.code, Notification.payload, Notification.message) 
SELECT
    (SELECT i.UID FROM Image i WHERE i.IID = IID) as uid,
    'IMAGE_SOLD',
    IID,
    (SELECT CONCAT(CONCAT(u.first_name, CONCAT(' ',u.last_name)),' has purchased your image') from User u WHERE u.UID = UID) as message$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `handle_redemption_success` (IN `UID` VARCHAR(40), IN `amount` DOUBLE)  UPDATE User 
SET User.earned = User.earned - amount
WHERE User.UID = UID$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `image_rated_notification` (IN `IID` VARCHAR(40), IN `UID` VARCHAR(40), IN `points` INT)  INSERT INTO Notification (Notification.UID, Notification.code, Notification.payload, Notification.message) 
SELECT
(SELECT i.UID from Image i WHERE i.IID = IID) as uid,
'IMAGE_RATED',
IID,
(SELECT CONCAT(CONCAT(CONCAT(u.first_name, CONCAT(' ',u.last_name)),' has rated '), CONCAT(points, ' stars on your image')) from User u WHERE u.UID = UID) as message$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `redeem_inqueue_notification` (IN `UID` VARCHAR(40))  INSERT INTO Notification (Notification.UID, Notification.code, Notification.message) VALUES 
(UID,
'REDEEM_INQUEUE',
'Your request has been received and is being reviewed.')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `redeem_rejected_notification` (IN `UID` VARCHAR(40))  INSERT INTO Notification (Notification.UID, Notification.code, Notification.message) VALUES 
(UID,
'REDEEM_REJECTED',
'There is a problem accepting your request. Sorry for inconvenience. Please try again later')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `redeem_req_accepted_notification` (IN `UID` VARCHAR(40))  INSERT INTO Notification (Notification.UID, Notification.code, Notification.message) VALUES 
(UID,
'REDEEM_REQ_ACCEPTED',
'Your request has been accepted. Your earnings shall appear within 5 days')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `redeem_success_notification` (IN `UID` VARCHAR(40))  INSERT INTO Notification (Notification.UID, Notification.code, Notification.message) VALUES 
(UID,
'REDEEM_SUCCESS',
'Your earning redemption succeeded.')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update_earnings` (IN `IID` VARCHAR(40))  UPDATE
    User
SET User.earned = User.earned + (
    SELECT
        i.price
    FROM
        Image i
    WHERE
        i.IID = IID
)
WHERE User.UID =(
    SELECT
        i.UID
    FROM
        Image i
    WHERE
        i.IID = IID
)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `app_status`
-- (See below for the actual view)
--
CREATE TABLE `app_status` (
`total_images` bigint(21)
,`total_users` bigint(21)
,`tax_collected` double
,`service_charge_collected` double
);

-- --------------------------------------------------------

--
-- Table structure for table `Bank_Detail`
--

CREATE TABLE `Bank_Detail` (
  `BID` int(11) NOT NULL,
  `UID` varchar(40) NOT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `account_name` varchar(150) DEFAULT NULL,
  `account_number` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Bank_Detail`
--

INSERT INTO `Bank_Detail` (`BID`, `UID`, `bank_name`, `account_name`, `account_number`) VALUES
(6, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'Everest Bank Ltd', 'Alyssa Tyler', '5523-3453-5323-234'),
(7, '6cd212c6-99a0-4c98-886c-f1198bf7003e', 'Everest Bank Ltd', 'John Doe', '5555-5555-5555-666'),
(8, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'Prabhu Bank Ltd', 'Max Ryder', '9839-9349-8989-343');

-- --------------------------------------------------------

--
-- Table structure for table `Category`
--

CREATE TABLE `Category` (
  `CID` varchar(40) NOT NULL,
  `title` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Category`
--

INSERT INTO `Category` (`CID`, `title`) VALUES
('de09169c-f818-4593-91a0-3d5aa340edf7', 'Abstract'),
('92554379-a357-4fb4-b373-6c4ad8c3fae3', 'Adventure'),
('b5d7f358-cfcf-4f87-b0c7-62ab6e0b61a4', 'Nature'),
('91c04dfd-022e-4d75-bd46-d47953081a4d', 'Wallpaper');

-- --------------------------------------------------------

--
-- Table structure for table `Favourite`
--

CREATE TABLE `Favourite` (
  `FID` int(11) NOT NULL,
  `IID` varchar(40) NOT NULL,
  `UID` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Favourite`
--

INSERT INTO `Favourite` (`FID`, `IID`, `UID`) VALUES
(4, 'eb79f144-2534-498d-8d34-0f232cc925b1', '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6'),
(13, '3c6496be-e99d-44bd-85c6-a6e43b15332f', '6cd212c6-99a0-4c98-886c-f1198bf7003e'),
(15, '05d8a394-e730-4bf3-80c9-84f62aa5a61a', '6cd212c6-99a0-4c98-886c-f1198bf7003e'),
(17, 'eb79f144-2534-498d-8d34-0f232cc925b1', '6cd212c6-99a0-4c98-886c-f1198bf7003e');

--
-- Triggers `Favourite`
--
DELIMITER $$
CREATE TRIGGER `like_notification_trigger` AFTER INSERT ON `Favourite` FOR EACH ROW CALL create_liked_notification(NEW.IID, NEW.UID)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `highly_rated_images`
-- (See below for the actual view)
--
CREATE TABLE `highly_rated_images` (
`IID` varchar(40)
,`title` varchar(50)
,`rates` decimal(14,4)
,`rate_count` bigint(21)
);

-- --------------------------------------------------------

--
-- Table structure for table `Image`
--

CREATE TABLE `Image` (
  `IID` varchar(40) NOT NULL,
  `CID` varchar(40) DEFAULT NULL,
  `UID` varchar(40) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(120) NOT NULL,
  `price` double NOT NULL,
  `source` varchar(50) NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT 1,
  `upload_time` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Image`
--

INSERT INTO `Image` (`IID`, `CID`, `UID`, `title`, `description`, `price`, `source`, `available`, `upload_time`) VALUES
('05d8a394-e730-4bf3-80c9-84f62aa5a61a', '92554379-a357-4fb4-b373-6c4ad8c3fae3', '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'Desert', 'Desert Adventure', 0, '05d8a394-e730-4bf3-80c9-84f62aa5a61a.jpeg', 1, '2020-11-01 12:54:35'),
('3c6496be-e99d-44bd-85c6-a6e43b15332f', 'de09169c-f818-4593-91a0-3d5aa340edf7', '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'Abstract Wallpaper', 'Abstracted to look good', 0.9, '3c6496be-e99d-44bd-85c6-a6e43b15332f.jpeg', 1, '2020-11-01 12:53:55'),
('5255fe52-8aa6-4c4b-95de-8beddde3a2fa', '92554379-a357-4fb4-b373-6c4ad8c3fae3', '6cd212c6-99a0-4c98-886c-f1198bf7003e', 'Beach', 'Cool Beach', 0, '5255fe52-8aa6-4c4b-95de-8beddde3a2fa.jpeg', 1, '2020-11-08 00:38:45'),
('6dfb6fab-0f48-4256-9175-e4ac24859694', 'b5d7f358-cfcf-4f87-b0c7-62ab6e0b61a4', '6cd212c6-99a0-4c98-886c-f1198bf7003e', 'Cloudy Sky', 'High as Sky', 0.9, '6dfb6fab-0f48-4256-9175-e4ac24859694.jpeg', 1, '2020-11-01 12:51:16'),
('70018621-4d3e-4380-ad89-27fb99b2d68a', 'de09169c-f818-4593-91a0-3d5aa340edf7', 'ef43c593-b914-462c-8d9c-df7e191bae78', 'Abstract Image', 'Abstract image as you see', 0, '70018621-4d3e-4380-ad89-27fb99b2d68a.jpeg', 1, '2020-11-01 12:47:45'),
('eb79f144-2534-498d-8d34-0f232cc925b1', '91c04dfd-022e-4d75-bd46-d47953081a4d', 'ef43c593-b914-462c-8d9c-df7e191bae78', 'Cool Wallpaper', 'Wallpaper that every walls should have', 0, 'eb79f144-2534-498d-8d34-0f232cc925b1.jpeg', 1, '2020-11-01 12:47:16');

-- --------------------------------------------------------

--
-- Stand-in structure for view `images`
-- (See below for the actual view)
--
CREATE TABLE `images` (
`UID` varchar(40)
,`IID` varchar(40)
,`uploaded_by` varchar(40)
,`category` varchar(50)
,`title` varchar(50)
,`source` varchar(50)
,`price` double
,`is_free` int(1)
,`ratings` decimal(14,4)
,`rate_count` bigint(21)
,`is_favourite` int(1)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `mostly_liked_images`
-- (See below for the actual view)
--
CREATE TABLE `mostly_liked_images` (
`IID` varchar(40)
,`title` varchar(50)
,`likes` bigint(21)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `mostly_sold_images`
-- (See below for the actual view)
--
CREATE TABLE `mostly_sold_images` (
`IID` varchar(40)
,`title` varchar(50)
,`sales` bigint(21)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `most_expensive_images`
-- (See below for the actual view)
--
CREATE TABLE `most_expensive_images` (
`IID` varchar(40)
,`title` varchar(50)
,`price` double
);

-- --------------------------------------------------------

--
-- Table structure for table `Notification`
--

CREATE TABLE `Notification` (
  `NID` int(11) NOT NULL,
  `UID` varchar(40) NOT NULL,
  `code` enum('IMAGE_SOLD','IMAGE_LIKED','IMAGE_RATED','REDEEM_REQ_ACCEPTED','REDEEM_SUCCESS','REDEEM_REJECTED','REDEEM_INQUEUE','REPORT_ACTION_TAKEN') DEFAULT NULL,
  `payload` varchar(150) DEFAULT NULL,
  `message` varchar(120) NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Notification`
--

INSERT INTO `Notification` (`NID`, `UID`, `code`, `payload`, `message`, `timestamp`) VALUES
(14, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_RATED', '70018621-4d3e-4380-ad89-27fb99b2d68a', 'John Doe has rated 2 stars on your image', '2020-11-01 12:51:42'),
(15, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_RATED', '70018621-4d3e-4380-ad89-27fb99b2d68a', 'Max Ryder has rated 3 stars on your image', '2020-11-01 12:54:51'),
(16, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_RATED', 'eb79f144-2534-498d-8d34-0f232cc925b1', 'Max Ryder has rated 4 stars on your image', '2020-11-01 12:55:30'),
(17, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_LIKED', 'eb79f144-2534-498d-8d34-0f232cc925b1', 'Max Ryder likes an image you uploaded', '2020-11-01 12:55:50'),
(20, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_LIKED', 'eb79f144-2534-498d-8d34-0f232cc925b1', 'John Doe likes an image you uploaded', '2020-11-01 12:58:17'),
(21, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_LIKED', '70018621-4d3e-4380-ad89-27fb99b2d68a', 'John Doe likes an image you uploaded', '2020-11-01 12:58:19'),
(22, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_RATED', '05d8a394-e730-4bf3-80c9-84f62aa5a61a', 'John Doe has rated 3 stars on your image', '2020-11-01 12:58:25'),
(23, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_LIKED', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'John Doe likes an image you uploaded', '2020-11-05 18:38:05'),
(24, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_SOLD', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'John Doe has purchased your image', '2020-11-05 18:39:09'),
(25, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_RATED', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'John Doe has rated 3 stars on your image', '2020-11-05 18:39:30'),
(26, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_LIKED', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'John Doe likes an image you uploaded', '2020-11-07 23:50:31'),
(27, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_LIKED', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'test test likes an image you uploaded', '2020-11-08 00:47:25'),
(28, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_SOLD', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'test test has purchased your image', '2020-11-08 00:47:37'),
(29, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_RATED', '70018621-4d3e-4380-ad89-27fb99b2d68a', 'test test has rated 3 stars on your image', '2020-11-08 00:48:34'),
(30, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_RATED', 'eb79f144-2534-498d-8d34-0f232cc925b1', 'test test has rated 2 stars on your image', '2020-11-08 00:48:56'),
(31, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_RATED', 'eb79f144-2534-498d-8d34-0f232cc925b1', 'John Doe has rated 3 stars on your image', '2020-11-08 01:00:38'),
(32, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_RATED', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'John Doe has rated 3 stars on your image', '2020-11-08 01:04:25'),
(33, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_LIKED', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'John Doe likes an image you uploaded', '2020-11-08 01:06:13'),
(34, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_LIKED', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'John Doe likes an image you uploaded', '2020-11-08 01:21:18'),
(35, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_LIKED', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'John Doe likes an image you uploaded', '2020-11-08 01:22:07'),
(36, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_LIKED', '3c6496be-e99d-44bd-85c6-a6e43b15332f', 'John Doe likes an image you uploaded', '2020-11-08 01:23:48'),
(37, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_LIKED', '05d8a394-e730-4bf3-80c9-84f62aa5a61a', 'John Doe likes an image you uploaded', '2020-11-08 07:08:37'),
(38, '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 'IMAGE_LIKED', '05d8a394-e730-4bf3-80c9-84f62aa5a61a', 'John Doe likes an image you uploaded', '2020-11-08 07:15:15'),
(39, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_LIKED', '70018621-4d3e-4380-ad89-27fb99b2d68a', 'John Doe likes an image you uploaded', '2020-11-08 07:42:34'),
(40, 'ef43c593-b914-462c-8d9c-df7e191bae78', 'IMAGE_LIKED', 'eb79f144-2534-498d-8d34-0f232cc925b1', 'John Doe likes an image you uploaded', '2020-11-08 09:12:49');

-- --------------------------------------------------------

--
-- Table structure for table `Rating`
--

CREATE TABLE `Rating` (
  `RID` int(11) NOT NULL,
  `IID` varchar(40) NOT NULL,
  `UID` varchar(40) NOT NULL,
  `point` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Rating`
--

INSERT INTO `Rating` (`RID`, `IID`, `UID`, `point`) VALUES
(6, '70018621-4d3e-4380-ad89-27fb99b2d68a', '6cd212c6-99a0-4c98-886c-f1198bf7003e', 2),
(7, '70018621-4d3e-4380-ad89-27fb99b2d68a', '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 3),
(8, 'eb79f144-2534-498d-8d34-0f232cc925b1', '82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', 4),
(10, '05d8a394-e730-4bf3-80c9-84f62aa5a61a', '6cd212c6-99a0-4c98-886c-f1198bf7003e', 3),
(14, 'eb79f144-2534-498d-8d34-0f232cc925b1', '6cd212c6-99a0-4c98-886c-f1198bf7003e', 3),
(15, '3c6496be-e99d-44bd-85c6-a6e43b15332f', '6cd212c6-99a0-4c98-886c-f1198bf7003e', 3);

--
-- Triggers `Rating`
--
DELIMITER $$
CREATE TRIGGER `image_rated_trigger` AFTER INSERT ON `Rating` FOR EACH ROW CALL image_rated_notification(NEW.IID, NEW.UID, NEW.point)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `Redeem_Request`
--

CREATE TABLE `Redeem_Request` (
  `RRID` int(11) NOT NULL,
  `UID` varchar(40) NOT NULL,
  `amount` double DEFAULT NULL,
  `status` enum('accepted','success','in_queue','declined') NOT NULL DEFAULT 'in_queue',
  `timestamp` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `Redeem_Request`
--
DELIMITER $$
CREATE TRIGGER `check_redeem_amount` BEFORE INSERT ON `Redeem_Request` FOR EACH ROW IF (
    SELECT COUNT(r.RRID)=0 
    FROM Redeem_Request r
    WHERE r.UID = NEW.UID
    AND r.status NOT IN ('success','declined')
)
THEN
BEGIN

IF NEW.amount IS NULL
THEN
BEGIN
IF 5.0 < (
    SELECT u.earned
    FROM users u
    WHERE u.UID = NEW.UID
)
THEN
BEGIN
    SET NEW.amount = (
    	SELECT u.earned
        FROM users u
        WHERE u.UID = NEW.UID
    );
END;
ELSE
BEGIN
	SIGNAL SQLSTATE '45000' 
    SET MESSAGE_TEXT = 'cannot redeem. your current earning is less than requirement.(ie. $5)';
END;
END IF;
END;
ELSE
BEGIN

IF NEW.amount < 5.0
THEN
BEGIN
	SIGNAL SQLSTATE '45000' 
    SET MESSAGE_TEXT = 'cannot redeem. redeem amount is less than requirement.(ie. $5)';
END;
ELSEIF NEW.amount > (
    SELECT u.earned
    FROM users u
    WHERE u.UID = NEW.UID
)
THEN
BEGIN
	SIGNAL SQLSTATE '45000' 
	SET MESSAGE_TEXT = 'cannot redeem. redeem amount is higher than earned.';
END;
END IF;

END;
END IF;

END;
ELSE
BEGIN
	SIGNAL SQLSTATE '45000' 
	SET MESSAGE_TEXT = 'cannot make request. please wait until previous request is resolved.';
END;
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_redemption_status` AFTER UPDATE ON `Redeem_Request` FOR EACH ROW IF NEW.status='success' THEN
BEGIN
	CALL handle_redemption_success(NEW.UID, NEW.amount);
    CALL redeem_success_notification(NEW.UID);
END;
ELSEIF NEW.status='in_queue' THEN
BEGIN
    CALL redeem_inqueue_notification(NEW.UID);
END;
ELSEIF NEW.status='declined' THEN
BEGIN
    CALL redeem_rejected_notification(NEW.UID);
END;
ELSEIF NEW.status='accepted' THEN
BEGIN
    CALL redeem_req_accepted_notification(NEW.UID);
END;
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `redeem_request_made` AFTER INSERT ON `Redeem_Request` FOR EACH ROW CALL Redeem_inqueue_notification(NEW.UID)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `Sold_Image`
--

CREATE TABLE `Sold_Image` (
  `SID` int(11) NOT NULL,
  `IID` varchar(40) NOT NULL,
  `UID` varchar(40) NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Sold_Image`
--

INSERT INTO `Sold_Image` (`SID`, `IID`, `UID`, `timestamp`) VALUES
(7, '3c6496be-e99d-44bd-85c6-a6e43b15332f', '6cd212c6-99a0-4c98-886c-f1198bf7003e', '2020-11-05 18:39:09');

--
-- Triggers `Sold_Image`
--
DELIMITER $$
CREATE TRIGGER `sold_notification` AFTER INSERT ON `Sold_Image` FOR EACH ROW CALL create_sales_notification(NEW.IID, NEW.UID)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_earnings` AFTER INSERT ON `Sold_Image` FOR EACH ROW CALL update_earnings(NEW.IID)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `top_earning_accounts`
-- (See below for the actual view)
--
CREATE TABLE `top_earning_accounts` (
`UID` varchar(40)
,`first_name` varchar(32)
,`last_name` varchar(32)
,`earnings` double
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `top_rated_accounts`
-- (See below for the actual view)
--
CREATE TABLE `top_rated_accounts` (
`UID` varchar(40)
,`first_name` varchar(32)
,`last_name` varchar(32)
,`overall_rating` decimal(14,4)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `transaction_statement`
-- (See below for the actual view)
--
CREATE TABLE `transaction_statement` (
`UID` varchar(40)
,`date` datetime
,`particulars` varchar(94)
,`debit` double
,`credit` double
);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `UID` varchar(40) NOT NULL,
  `join_date` datetime NOT NULL DEFAULT current_timestamp(),
  `profile_picture` varchar(32) DEFAULT NULL,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` enum('Male','Female','Others','') NOT NULL,
  `email` varchar(64) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(256) NOT NULL,
  `earned` double NOT NULL DEFAULT 0,
  `blocked` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UID`, `join_date`, `profile_picture`, `first_name`, `last_name`, `birth_date`, `gender`, `email`, `username`, `password`, `earned`, `blocked`) VALUES
('6cd212c6-99a0-4c98-886c-f1198bf7003e', '2020-11-01 12:49:29', NULL, 'John', 'Doe', '1994-10-19', 'Male', 'john.doe@gmail.com', 'johnD', '$2a$12$WPjzM5M6JL9RSyltesfxw.gvY3WlA.4GOu1.YOYhaJ2K4SiDpaWsm', 1.01, 0),
('82de7cac-3fa4-4d31-98d3-8dfb7902f2b6', '2020-11-01 12:52:40', NULL, 'Max', 'Ryder', '1998-11-11', 'Male', 'max@gmail.com', 'max', '$2a$12$C.q5MyZWsundlZIfGhxUju48If.l37EcW84AbcqzrzfRAaBeyIJG.', 1.8, 0),
('bdd93123-5436-4cc5-af23-6b1c1e8efa91', '2020-10-31 17:33:01', NULL, 'Blocked', 'Blocked', '2018-08-08', 'Male', 'blocked@gmail.com', 'blocked', '$2a$12$PLz6SJm3xsgY6bcRK.5V4.bydg.1X/GgHDG7NWyYOooqv7w9KM.cm', 0, 1),
('ef43c593-b914-462c-8d9c-df7e191bae78', '2020-10-31 17:21:18', NULL, 'Alyssa', 'Tyler', '1984-08-08', 'Female', 'alyssa@gmail.com', 'alyssa', '$2a$12$5fV5J.MRP3j28MBeL66FuOK3oLxFjuwya0tgrC5RctsR8naLESMPC', 0, 0);

-- --------------------------------------------------------

--
-- Stand-in structure for view `users`
-- (See below for the actual view)
--
CREATE TABLE `users` (
`UID` varchar(40)
,`first_name` varchar(32)
,`last_name` varchar(32)
,`username` varchar(32)
,`email` varchar(64)
,`birth_date` date
,`gender` enum('Male','Female','Others','')
,`earned` double
,`account_number` varchar(32)
,`total_uploads` bigint(21)
,`overall_rating` decimal(14,4)
,`likes_collected` bigint(21)
,`favourites` bigint(21)
,`redeemed` double
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `wishes`
-- (See below for the actual view)
--
CREATE TABLE `wishes` (
`UID` varchar(40)
,`IID` varchar(40)
,`uploaded_by` varchar(40)
,`title` varchar(50)
,`source` varchar(50)
,`price` double
,`is_free` int(1)
,`ratings` decimal(14,4)
,`rate_count` bigint(21)
,`is_favourite` int(1)
);

-- --------------------------------------------------------

--
-- Table structure for table `Wishlist`
--

CREATE TABLE `Wishlist` (
  `WID` int(11) NOT NULL,
  `IID` varchar(40) NOT NULL,
  `UID` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure for view `app_status`
--
DROP TABLE IF EXISTS `app_status`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `app_status`  AS  select (select count(`i`.`IID`) from `Image` `i`) AS `total_images`,(select count(`u`.`UID`) from `User` `u`) AS `total_users`,(select ifnull(sum(`r`.`amount` * 0.05),0) from `Redeem_Request` `r` where `r`.`status` = 'success') AS `tax_collected`,(select ifnull(sum(`r`.`amount` * 0.095),0) from `Redeem_Request` `r` where `r`.`status` = 'success') AS `service_charge_collected` ;

-- --------------------------------------------------------

--
-- Structure for view `highly_rated_images`
--
DROP TABLE IF EXISTS `highly_rated_images`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `highly_rated_images`  AS  select `i`.`IID` AS `IID`,`i`.`title` AS `title`,(select avg(`r`.`point`) from `Rating` `r` where `r`.`IID` = `i`.`IID`) AS `rates`,(select count(`r`.`IID`) from `Rating` `r` where `r`.`IID` = `i`.`IID`) AS `rate_count` from `Image` `i` where (select avg(`r`.`point`) from `Rating` `r` where `r`.`IID` = `i`.`IID`) > 0 order by (select avg(`r`.`point`) from `Rating` `r` where `r`.`IID` = `i`.`IID`) desc,(select count(`r`.`IID`) from `Rating` `r` where `r`.`IID` = `i`.`IID`) desc ;

-- --------------------------------------------------------

--
-- Structure for view `images`
--
DROP TABLE IF EXISTS `images`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `images`  AS  select `u`.`UID` AS `UID`,`i`.`IID` AS `IID`,`i`.`UID` AS `uploaded_by`,`c`.`title` AS `category`,`i`.`title` AS `title`,`i`.`source` AS `source`,`i`.`price` AS `price`,`i`.`price` = 0 AS `is_free`,(select ifnull(avg(`r`.`point`),0) from `Rating` `r` where `r`.`IID` = `i`.`IID`) AS `ratings`,(select count(`r`.`RID`) from `Rating` `r` where `r`.`IID` = `i`.`IID`) AS `rate_count`,(select count(`f`.`FID`) > 0 from `Favourite` `f` where `f`.`IID` = `i`.`IID` and `f`.`UID` = `u`.`UID`) AS `is_favourite` from ((`Image` `i` left join `Category` `c` on(`c`.`CID` = `i`.`CID`)) join `User` `u`) where `i`.`available` = 1 ;

-- --------------------------------------------------------

--
-- Structure for view `mostly_liked_images`
--
DROP TABLE IF EXISTS `mostly_liked_images`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostly_liked_images`  AS  select `i`.`IID` AS `IID`,`i`.`title` AS `title`,(select count(`f`.`FID`) from `Favourite` `f` where `f`.`IID` = `i`.`IID`) AS `likes` from `Image` `i` where (select count(`f`.`FID`) from `Favourite` `f` where `f`.`IID` = `i`.`IID`) > 0 order by (select count(`f`.`FID`) from `Favourite` `f` where `f`.`IID` = `i`.`IID`) desc ;

-- --------------------------------------------------------

--
-- Structure for view `mostly_sold_images`
--
DROP TABLE IF EXISTS `mostly_sold_images`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostly_sold_images`  AS  select `i`.`IID` AS `IID`,`i`.`title` AS `title`,(select count(`s`.`SID`) from `Sold_Image` `s` where `s`.`IID` = `i`.`IID`) AS `sales` from `Image` `i` where (select count(`s`.`SID`) from `Sold_Image` `s` where `s`.`IID` = `i`.`IID`) > 0 order by (select count(`s`.`SID`) from `Sold_Image` `s` where `s`.`IID` = `i`.`IID`) desc ;

-- --------------------------------------------------------

--
-- Structure for view `most_expensive_images`
--
DROP TABLE IF EXISTS `most_expensive_images`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `most_expensive_images`  AS  select `i`.`IID` AS `IID`,`i`.`title` AS `title`,`i`.`price` AS `price` from `Image` `i` where `i`.`price` > 0.0 order by `i`.`price` desc ;

-- --------------------------------------------------------

--
-- Structure for view `top_earning_accounts`
--
DROP TABLE IF EXISTS `top_earning_accounts`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `top_earning_accounts`  AS  select `u`.`UID` AS `UID`,`u`.`first_name` AS `first_name`,`u`.`last_name` AS `last_name`,`u`.`earned` + `u`.`redeemed` AS `earnings` from `users` `u` where `u`.`earned` + `u`.`redeemed` > 0.0 order by `u`.`earned` + `u`.`redeemed` desc ;

-- --------------------------------------------------------

--
-- Structure for view `top_rated_accounts`
--
DROP TABLE IF EXISTS `top_rated_accounts`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `top_rated_accounts`  AS  select `u`.`UID` AS `UID`,`u`.`first_name` AS `first_name`,`u`.`last_name` AS `last_name`,`u`.`overall_rating` AS `overall_rating` from `users` `u` where `u`.`overall_rating` > 0.0 order by `u`.`overall_rating` desc ;

-- --------------------------------------------------------

--
-- Structure for view `transaction_statement`
--
DROP TABLE IF EXISTS `transaction_statement`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `transaction_statement`  AS  (select `i`.`UID` AS `UID`,`s`.`timestamp` AS `date`,concat(`u`.`first_name`,concat(' ',concat(`u`.`last_name`,' purchased one of your image.'))) AS `particulars`,`i`.`price` AS `debit`,NULL AS `credit` from ((`Sold_Image` `s` join `Image` `i`) join `User` `u`) where `s`.`IID` = `i`.`IID` and `s`.`UID` = `u`.`UID`) union (select `r`.`UID` AS `UID`,`r`.`timestamp` AS `date`,'You redeemed your earning successfully.' AS `particulars`,NULL AS `debit`,`r`.`amount` AS `credit` from `Redeem_Request` `r` where `r`.`status` = 'success') union (select `r`.`UID` AS `UID`,`r`.`timestamp` AS `date`,'Redemption Request Declined' AS `particulars`,NULL AS `debit`,NULL AS `credit` from `Redeem_Request` `r` where `r`.`status` = 'declined') union (select `r`.`UID` AS `UID`,`r`.`timestamp` AS `date`,'Redemption in progress' AS `particulars`,NULL AS `debit`,NULL AS `credit` from `Redeem_Request` `r` where `r`.`status` = 'in_queue') ;

-- --------------------------------------------------------

--
-- Structure for view `users`
--
DROP TABLE IF EXISTS `users`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `users`  AS  select `u`.`UID` AS `UID`,`u`.`first_name` AS `first_name`,`u`.`last_name` AS `last_name`,`u`.`username` AS `username`,`u`.`email` AS `email`,`u`.`birth_date` AS `birth_date`,`u`.`gender` AS `gender`,`u`.`earned` AS `earned`,`b`.`account_number` AS `account_number`,(select count(`i`.`IID`) from `Image` `i` where `i`.`UID` = `u`.`UID`) AS `total_uploads`,(select ifnull(avg(`r`.`point`),0) from (`Rating` `r` join `Image` `i`) where `r`.`IID` = `i`.`IID` and `i`.`UID` = `u`.`UID`) AS `overall_rating`,(select count(`f`.`FID`) from (`Favourite` `f` join `Image` `i`) where `f`.`IID` = `i`.`IID` and `i`.`UID` = `u`.`UID`) AS `likes_collected`,(select count(`f`.`FID`) from `Favourite` `f` where `f`.`UID` = `u`.`UID`) AS `favourites`,(select ifnull(sum(`r`.`amount` * 0.855),0.0) from `Redeem_Request` `r` where `r`.`UID` = `u`.`UID` and `r`.`status` = 'success') AS `redeemed` from (`User` `u` left join `Bank_Detail` `b` on(`b`.`UID` = `u`.`UID`)) ;

-- --------------------------------------------------------

--
-- Structure for view `wishes`
--
DROP TABLE IF EXISTS `wishes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wishes`  AS  select `w`.`UID` AS `UID`,`i`.`IID` AS `IID`,`i`.`uploaded_by` AS `uploaded_by`,`i`.`title` AS `title`,`i`.`source` AS `source`,`i`.`price` AS `price`,`i`.`is_free` AS `is_free`,`i`.`ratings` AS `ratings`,`i`.`rate_count` AS `rate_count`,(select count(`f`.`FID`) > 0 from `Favourite` `f` where `f`.`IID` = `i`.`IID` and `f`.`UID` = `w`.`UID`) AS `is_favourite` from (`images` `i` join `Wishlist` `w`) where `i`.`IID` = `w`.`IID` and `i`.`UID` = `w`.`UID` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Bank_Detail`
--
ALTER TABLE `Bank_Detail`
  ADD PRIMARY KEY (`BID`),
  ADD KEY `bid_uid_fk` (`UID`);

--
-- Indexes for table `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`CID`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indexes for table `Favourite`
--
ALTER TABLE `Favourite`
  ADD PRIMARY KEY (`FID`),
  ADD KEY `fid_uid_fk` (`UID`),
  ADD KEY `fid_iid_fk` (`IID`);

--
-- Indexes for table `Image`
--
ALTER TABLE `Image`
  ADD PRIMARY KEY (`IID`),
  ADD KEY `iid_uid_fk` (`UID`),
  ADD KEY `iid_cid_fk` (`CID`);

--
-- Indexes for table `Notification`
--
ALTER TABLE `Notification`
  ADD PRIMARY KEY (`NID`),
  ADD KEY `nid_uid_fk` (`UID`);

--
-- Indexes for table `Rating`
--
ALTER TABLE `Rating`
  ADD PRIMARY KEY (`RID`),
  ADD KEY `rid_iid_fk` (`IID`),
  ADD KEY `rid_uid_fk` (`UID`);

--
-- Indexes for table `Redeem_Request`
--
ALTER TABLE `Redeem_Request`
  ADD PRIMARY KEY (`RRID`),
  ADD KEY `rrid_uid_fk` (`UID`);

--
-- Indexes for table `Sold_Image`
--
ALTER TABLE `Sold_Image`
  ADD PRIMARY KEY (`SID`),
  ADD KEY `sid_iid_fk` (`IID`),
  ADD KEY `sid_uid_fk` (`UID`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`UID`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `Wishlist`
--
ALTER TABLE `Wishlist`
  ADD PRIMARY KEY (`WID`),
  ADD KEY `wid_iid_fk` (`IID`),
  ADD KEY `wid_uid_fk` (`UID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Bank_Detail`
--
ALTER TABLE `Bank_Detail`
  MODIFY `BID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `Favourite`
--
ALTER TABLE `Favourite`
  MODIFY `FID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `Notification`
--
ALTER TABLE `Notification`
  MODIFY `NID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `Rating`
--
ALTER TABLE `Rating`
  MODIFY `RID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `Redeem_Request`
--
ALTER TABLE `Redeem_Request`
  MODIFY `RRID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Sold_Image`
--
ALTER TABLE `Sold_Image`
  MODIFY `SID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `Wishlist`
--
ALTER TABLE `Wishlist`
  MODIFY `WID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Bank_Detail`
--
ALTER TABLE `Bank_Detail`
  ADD CONSTRAINT `bid_uid_fk` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`);

--
-- Constraints for table `Favourite`
--
ALTER TABLE `Favourite`
  ADD CONSTRAINT `fid_iid_fk` FOREIGN KEY (`IID`) REFERENCES `Image` (`IID`),
  ADD CONSTRAINT `fid_uid_fk` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`);

--
-- Constraints for table `Image`
--
ALTER TABLE `Image`
  ADD CONSTRAINT `iid_cid_fk` FOREIGN KEY (`CID`) REFERENCES `Category` (`CID`),
  ADD CONSTRAINT `iid_uid_fk` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`);

--
-- Constraints for table `Notification`
--
ALTER TABLE `Notification`
  ADD CONSTRAINT `nid_uid_fk` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`);

--
-- Constraints for table `Rating`
--
ALTER TABLE `Rating`
  ADD CONSTRAINT `rid_iid_fk` FOREIGN KEY (`IID`) REFERENCES `Image` (`IID`),
  ADD CONSTRAINT `rid_uid_fk` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`);

--
-- Constraints for table `Redeem_Request`
--
ALTER TABLE `Redeem_Request`
  ADD CONSTRAINT `rrid_uid_fk` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`);

--
-- Constraints for table `Sold_Image`
--
ALTER TABLE `Sold_Image`
  ADD CONSTRAINT `sid_iid_fk` FOREIGN KEY (`IID`) REFERENCES `Image` (`IID`),
  ADD CONSTRAINT `sid_uid_fk` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`);

--
-- Constraints for table `Wishlist`
--
ALTER TABLE `Wishlist`
  ADD CONSTRAINT `wid_iid_fk` FOREIGN KEY (`IID`) REFERENCES `Image` (`IID`),
  ADD CONSTRAINT `wid_uid_fk` FOREIGN KEY (`UID`) REFERENCES `User` (`UID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
