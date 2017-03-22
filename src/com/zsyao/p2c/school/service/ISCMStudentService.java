package com.zsyao.p2c.school.service;

import com.zsyao.p2c.school.model.SCMStudent;


public interface ISCMStudentService
{
	SCMStudent getStudent(Integer studentId) throws Exception;
}
