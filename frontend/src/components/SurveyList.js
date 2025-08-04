import React from 'react';

const SurveyList = ({ surveys, onEdit, onDelete, onView }) => {
    return (
        <div>
            <h2>All Surveys</h2>
            <table className="survey-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Date of Survey</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {surveys.map((survey) => (
                        <tr key={survey.id}>
                            <td>{survey.firstName} {survey.lastName}</td>
                            <td>{survey.email}</td>
                            <td>{survey.dateOfSurvey}</td>
                            <td className="actions-cell">
                                <button onClick={() => onView(survey)} className="view-button">View</button>
                                <button onClick={() => onEdit(survey)} className="edit-button">Edit</button>
                                <button onClick={() => onDelete(survey.id)} className="delete-button">Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default SurveyList;
