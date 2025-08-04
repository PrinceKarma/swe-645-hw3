import React from 'react';

const SurveyDetail = ({ survey, onBack }) => {
    if (!survey) {
        return <div>No survey selected.</div>;
    }

    return (
        <div className="survey-detail">
            <h2>Survey Details</h2>
            <div className="detail-grid">
                <p><strong>ID:</strong> {survey.id}</p>
                <p><strong>Name:</strong> {survey.firstName} {survey.lastName}</p>
                <p><strong>Address:</strong> {survey.streetAddress}, {survey.city}, {survey.state} {survey.zip}</p>
                <p><strong>Telephone:</strong> {survey.telephoneNumber}</p>
                <p><strong>Email:</strong> {survey.email}</p>
                <p><strong>Date of Survey:</strong> {survey.dateOfSurvey}</p>
                <p><strong>Liked Most:</strong> {survey.likedMost}</p>
                <p><strong>Interest Source:</strong> {survey.interestSource}</p>
                <p><strong>Recommendation:</strong> {survey.recommendationLikelihood}</p>
                <p><strong>Created At:</strong> {new Date(survey.createdAt).toLocaleString()}</p>
                <p><strong>Updated At:</strong> {new Date(survey.updatedAt).toLocaleString()}</p>
            </div>
            <button onClick={onBack} className="back-button">Back to List</button>
        </div>
    );
};

export default SurveyDetail;
