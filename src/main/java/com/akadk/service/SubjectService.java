package com.akadk.service;

import com.akadk.entity.Subject;
import com.akadk.entity.User;

import java.util.Optional;

public interface SubjectService {

    Optional<Subject> findSubjectById(long id);

    void addSubject(Subject subject);

    void deleteSubject(long id);

    void setCurator(long userId,long subjectId);
}
