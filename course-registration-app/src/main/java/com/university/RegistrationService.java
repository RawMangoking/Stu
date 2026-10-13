package com.university;

public class RegistrationService {

    private static final int MAX_CREDITS = 20;

    public boolean register(Student student, Course course) {

        if (!course.isActive()) {
            return false;
        }

        if (course.getCurrentEnrollment() >= course.getCapacity()) {
            return false;
        }

        if (student.isRegistered(course.getCourseCode())) {
            return false;
        }

        String prerequisite = course.getPrerequisite();

        if (prerequisite != null
                && !prerequisite.isBlank()
                && !student.hasCompletedPrerequisite(prerequisite)) {
            return false;
        }

        if (student.getTotalCredits() + course.getCredits() > MAX_CREDITS) {
            return false;
        }

        student.addRegistration(
                course.getCourseCode(),
                course.getCredits());

        course.incrementEnrollment();

        return true;
    }
    
    public boolean withdraw(Student student, Course course) {
        if (!student.isRegistered(course.getCourseCode())) {
            return false;
        }

        boolean removed = student.removeRegistration(
                course.getCourseCode(),
                course.getCredits()
        );

        if (!removed) {
            return false;
        }

        course.decrementEnrollment();

        return true;
    }

}