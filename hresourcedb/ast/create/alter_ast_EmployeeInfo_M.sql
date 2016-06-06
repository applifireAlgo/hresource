

ALTER TABLE `ast_EmployeeInfo_M` ADD CONSTRAINT FOREIGN KEY (`contactId`) REFERENCES `ast_CoreContacts_T`(`contactId`);



ALTER TABLE `ast_EmployeeInfo_M` ADD CONSTRAINT FOREIGN KEY (`deptTypeCode`) REFERENCES `ast_DepartmentType_M`(`departmentTypeCode`);



ALTER TABLE `ast_EmployeeInfo_M` ADD CONSTRAINT FOREIGN KEY (`designationTypeCode`) REFERENCES `ast_DesignationType_M`(`designationTypeCode`);



ALTER TABLE `ast_EmployeeInfo_M` ADD CONSTRAINT FOREIGN KEY (`jobTypeCode`) REFERENCES `ast_JobType_M`(`jobTypeCode`);

