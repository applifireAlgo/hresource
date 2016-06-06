DROP TABLE IF EXISTS `ast_CostToCompany_M`;

CREATE TABLE `ast_CostToCompany_M` ( `salaryId` VARCHAR(256) NOT NULL, `perk` DOUBLE(21,2) NULL DEFAULT NULL, `totalCTC` DOUBLE(21,2) NULL DEFAULT NULL, `takeHome` DOUBLE(21,2) NULL DEFAULT NULL, `yearValue` INT(10) NOT NULL, `basic` DOUBLE(21,2) NULL DEFAULT NULL, `hra` DOUBLE(21,2) NULL DEFAULT NULL, `convenceAllowance` DOUBLE(21,2) NULL DEFAULT NULL, `medicalAllowance` DOUBLE(21,2) NULL DEFAULT NULL, `educationalAllowance` DOUBLE(21,2) NULL DEFAULT NULL, `specialAllowance` DOUBLE(21,2) NULL DEFAULT NULL, `empId` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`salaryId`));
