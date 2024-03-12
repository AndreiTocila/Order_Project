package com.project.feedbackservice.exception.custom;

public class InvalidFeedbackException extends RuntimeException
{
    public InvalidFeedbackException()
    {
        super("Invalid feedback format!");
    }
}
