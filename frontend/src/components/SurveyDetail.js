import React from 'react';

const SurveyDetail = ({ survey, onBack }) => {
    if (!survey) {
        return <div className="loading">Loading survey details...</div>;
    }

    return (
        <div className="detail-container">
            <div className="detail-header">
                <h2>{survey.firstName} {survey.lastName}</h2>
                <button onClick={onBack} className="button-secondary">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                    <span>Back to List</span>
                </button>
            </div>
            <div className="detail-content">
                <div className="detail-card">
                    <h3>Contact Information</h3>
                    <p><strong>Email:</strong> {survey.email}</p>
                    <p><strong>Telephone:</strong> {survey.telephoneNumber}</p>
                    <p><strong>Address:</strong> {survey.streetAddress}, {survey.city}, {survey.state} {survey.zip}</p>
                </div>
                <div className="detail-card">
                    <h3>Survey Responses</h3>
                    <p><strong>Date of Survey:</strong> {survey.dateOfSurvey}</p>
                    <p><strong>Liked Most:</strong> {survey.likedMost}</p>
                    <p><strong>Interest Source:</strong> {survey.interestSource}</p>
                    <p><strong>Recommendation:</strong> {survey.recommendationLikelihood}</p>
                </div>
                 <div className="detail-card">
                    <h3>Timestamps</h3>
                    <p><strong>Created At:</strong> {new Date(survey.createdAt).toLocaleString()}</p>
                    <p><strong>Updated At:</strong> {new Date(survey.updatedAt).toLocaleString()}</p>
                </div>
            </div>
        </div>
    );
};

export default SurveyDetail;
