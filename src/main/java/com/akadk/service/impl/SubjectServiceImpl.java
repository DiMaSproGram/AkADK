package com.akadk.service.impl;

import com.akadk.common.Constant;
import com.akadk.entity.Subject;
import com.akadk.entity.User;
import com.akadk.exception.CantDeleteException;
import com.akadk.exception.RestrictedAccessException;
import com.akadk.repository.SubjectRepository;
import com.akadk.repository.UserRepository;
import com.akadk.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Subject> findSubjectById(long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(long id) {
        subjectRepository.delete(findSubjectById(id).
                orElseThrow(() ->
                        new CantDeleteException(String.format(Constant.CANT_DELETE_BY_ID_EXCEPTION,
                                id,
                                Subject.class))

                ));
    }

    @Override
    public void setCurator(long userId, long subjectId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Subject> subject = findSubjectById(subjectId);

        if (user.isPresent() && subject.isPresent()) {
            subject.get().setUser(user.get());
            subjectRepository.save(subject.get());
        } else {
            throw new RestrictedAccessException("test");
        }
    }
}
