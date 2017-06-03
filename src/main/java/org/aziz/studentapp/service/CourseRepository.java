package org.aziz.studentapp.service;

import org.aziz.studentapp.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
