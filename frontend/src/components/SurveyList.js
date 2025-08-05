import React from 'react';

const SurveyList = ({ surveys, onEdit, onDelete, onView }) => {
    return (
        <div className="survey-list-container">
            {surveys.map((survey) => (
                <div key={survey.id} className="survey-card">
                    <div className="survey-card-header">
                        <h3>{survey.firstName} {survey.lastName}</h3>
                        <p>{survey.email}</p>
                    </div>
                    <div className="survey-card-body">
                        <p><strong>Date:</strong> {survey.dateOfSurvey}</p>
                    </div>
                    <div className="survey-card-actions">
                        <button onClick={() => onView(survey)} className="icon-button view-button">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg>
                            <span>View</span>
                        </button>
                        <button onClick={() => onEdit(survey)} className="icon-button edit-button">
                             <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg>
                            <span>Edit</span>
                        </button>
                        <button onClick={() => onDelete(survey.id)} className="icon-button delete-button">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path><line x1="10" y1="11" x2="10" y2="17"></line><line x1="14" y1="11" x2="14" y2="17"></line></svg>
                            <span>Delete</span>
                        </button>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default SurveyList;
