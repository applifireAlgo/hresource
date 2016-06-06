DROP TABLE IF EXISTS `ast_EmployeeInfo_M`;

CREATE TABLE `ast_EmployeeInfo_M` ( `empId` VARCHAR(256) NOT NULL, `contactId` VARCHAR(64) NOT NULL, `deptTypeCode` VARCHAR(256) NOT NULL, `designationTypeCode` VARCHAR(256) NOT NULL, `reportingOfficer` VARCHAR(256) NOT NULL, `pan` VARCHAR(256) NOT NULL, `jobTypeCode` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`empId`), UNIQUE KEY (`pan`));

