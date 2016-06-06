

ALTER TABLE `ast_AttendanceSummary_M` ADD CONSTRAINT FOREIGN KEY (`empId`) REFERENCES `ast_EmployeeInfo_M`(`empId`);

