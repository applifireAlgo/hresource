DROP TABLE IF EXISTS `ast_AttendanceSummary_M`;

CREATE TABLE `ast_AttendanceSummary_M` ( `attendenceSummaryId` VARCHAR(256) NOT NULL, `maternityLeave` INT(10) NULL DEFAULT NULL, `empId` VARCHAR(256) NOT NULL, `monthValue` INT(10) NOT NULL, `yearValue` INT(10) NOT NULL, `present` INT(10) NOT NULL, `absent` INT(10) NOT NULL, `privilegeLeave` INT(10) NOT NULL, `casualLeave` INT(10) NOT NULL, `sickLeave` INT(10) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`attendenceSummaryId`));

